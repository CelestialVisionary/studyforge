package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.common.CacheConstants;
import com.exam.entity.Question;
import com.exam.entity.QuestionChoice;
import com.exam.entity.QuestionAnswer;
import com.exam.dto.QuestionImportDto;
import com.exam.mapper.QuestionMapper;
import com.exam.mapper.QuestionChoiceMapper;
import com.exam.mapper.QuestionAnswerMapper;
import com.exam.service.QuestionService;
import com.exam.utils.RedisUtils;
import com.exam.vo.QuestionQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 题目Service实现类
 * 实现题目相关的业务逻辑
 */
@Slf4j
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionChoiceMapper questionChoiceMapper;
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private RedisUtils redisUtils;
    
    /**
     * 保存题目及其详细信息
     * 保存后清除相关缓存
     * 
     * 【缓存策略说明】
     * 1. 使用@CacheEvict注解自动清除缓存，这是Spring Cache提供的声明式缓存管理
     * 2. 只清除与当前题目分类相关的缓存，而不是所有缓存，提高缓存命中率
     * 3. 使用condition条件确保只有当categoryId不为空时才清除缓存，避免不必要的缓存操作
     * 
     * 【为什么使用Spring Cache注解】
     * - 代码简洁：一个注解完成缓存清除，无需手动编写缓存操作代码
     * - 与业务解耦：缓存逻辑与业务逻辑分离，提高代码可维护性
     * - 精确控制：只清除必要的缓存，不影响其他缓存数据
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.QUESTION_CACHE, key = "'category:' + #question.categoryId", condition = "#question.categoryId != null")
    public void saveQuestionWithDetails(Question question) {
        // 1. 保存题目主表
        this.save(question);
        Long questionId = question.getId();

        // 2. 根据类型处理答案和选项
        if ("CHOICE".equals(question.getType())) {
            // 保存选项
            List<QuestionChoice> choices = question.getChoices();
            if (!CollectionUtils.isEmpty(choices)) {
                StringBuilder correctAnswer = new StringBuilder();
                for (int i = 0; i < choices.size(); i++) {
                    QuestionChoice choice = choices.get(i);
                    choice.setQuestionId(questionId);
                    questionChoiceMapper.insert(choice);
                    // 如果是正确答案，记录下来
                    if (choice.getIsCorrect() != null && choice.getIsCorrect()) {
                        if (correctAnswer.length() > 0) {
                            correctAnswer.append(",");
                        }
                        correctAnswer.append((char) ('A' + i));
                    }
                }
                // 保存选择题答案
                if(correctAnswer.length() > 0){
                    QuestionAnswer answer = new QuestionAnswer();
                    answer.setQuestionId(questionId);
                    answer.setAnswer(correctAnswer.toString());
                    questionAnswerMapper.insert(answer);
                }
            }
        } else { // 判断题和简答题
            QuestionAnswer answer = question.getAnswer();
            if (answer != null) {
                answer.setQuestionId(questionId);
                questionAnswerMapper.insert(answer);
            }
        }
    }

    /**
     * 更新题目及其详细信息
     * 更新后清除相关缓存
     * 
     * 【缓存策略说明】
     * 1. 使用@CacheEvict注解清除缓存，设置allEntries=true清除整个缓存区域
     * 2. 更新操作会影响多个缓存（题目详情、分类题目列表等），所以需要清除所有相关缓存
     * 3. 虽然会降低缓存命中率，但确保数据一致性更重要
     * 
     * 【为什么使用Spring Cache注解】
     * - 更新操作影响范围广：题目更新可能涉及类型、分类等变化，影响多个缓存键
     * - 简化代码：一个注解替代多次手动缓存操作，避免遗漏某些缓存的清除
     * - 声明式缓存：与业务逻辑解耦，提高代码可维护性
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.QUESTION_CACHE, allEntries = true)
    public void updateQuestionWithDetails(Question question) {
        // 1. 更新题目主表
        this.updateById(question);
        Long questionId = question.getId();

        // 2. 删除旧的答案和选项
        questionAnswerMapper.delete(new QueryWrapper<QuestionAnswer>().eq("question_id", questionId));
        questionChoiceMapper.delete(new QueryWrapper<QuestionChoice>().eq("question_id", questionId));
        
        // 3. 插入新的答案和选项（逻辑同save）
         if ("CHOICE".equals(question.getType())) {
            List<QuestionChoice> choices = question.getChoices();
            if (!CollectionUtils.isEmpty(choices)) {
                 // 用于拼接多选答案
                StringBuilder correctAnswer = new StringBuilder();
                for (int i = 0; i < choices.size(); i++) {
                    QuestionChoice choice = choices.get(i);
                    choice.setQuestionId(questionId);
                    choice.setId(null); // 清除旧ID，使其能被重新插入
                    questionChoiceMapper.insert(choice);
                    if (choice.getIsCorrect() != null && choice.getIsCorrect()) {
                        if (correctAnswer.length() > 0) {
                            correctAnswer.append(",");
                        }
                        correctAnswer.append((char) ('A' + i));
                    }
                }
                // 保存选择题答案
                if(correctAnswer.length() > 0){
                    QuestionAnswer answer = new QuestionAnswer();
                    answer.setQuestionId(questionId);
                    answer.setAnswer(correctAnswer.toString());
                    questionAnswerMapper.insert(answer);
                }
            }
        } else { // 判断题和简答题
            QuestionAnswer answer = question.getAnswer();
            if (answer != null) {
                answer.setQuestionId(questionId);
                questionAnswerMapper.insert(answer);
            }
        }
    }
    
    /**
     * 根据分类ID查询题目列表
     * 使用Redis缓存优化，减少数据库查询
     * 
     * 【缓存策略说明】
     * 1. 使用@Cacheable注解实现方法结果缓存，第一次查询后将结果存入Redis
     * 2. 缓存键设计为"category:分类ID"，便于按分类管理缓存
     * 3. unless条件确保不缓存空结果，避免缓存穿透问题
     * 
     * 【为什么使用Spring Cache注解】
     * - 高频查询场景：分类题目查询是高频操作，适合缓存
     * - 数据相对稳定：分类下的题目变化不频繁，缓存命中率高
     * - 自动序列化：Spring Cache自动处理复杂对象的序列化和反序列化
     * - 简化代码：无需手动处理缓存逻辑，专注于业务实现
     */
    @Override
    @Cacheable(value = CacheConstants.QUESTION_CACHE, key = "'category:' + #categoryId", unless = "#result == null || #result.isEmpty()")
    public List<Question> getQuestionsByCategory(Long categoryId) {
        log.debug("从数据库获取分类题目列表，分类ID: {}", categoryId);
        
        // 根据分类ID查询题目
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        List<Question> questions = this.list(queryWrapper);
        fillChoicesAndAnswer(questions);
        
        log.debug("分类题目查询完成，分类ID: {}, 题目数量: {}", categoryId, questions.size());
        return questions;
    }
    
    @Override
    public List<Question> getQuestionsByDifficulty(String difficulty) {
        // 根据难度查询题目
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("difficulty", difficulty);
        List<Question> questions = this.list(queryWrapper);
        fillChoicesAndAnswer(questions);
        return questions;
    }
    
    @Override
    public List<Question> getQuestionsByType(String type) {
        // 根据类型查询题目
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        List<Question> questions = this.list(queryWrapper);
        fillChoicesAndAnswer(questions);
        return questions;
    }
    
    @Override
    public List<Question> getRandomQuestions(Integer count, Long categoryId, String difficulty) {
        // 实现随机获取题目的逻辑
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public int batchImportQuestions(List<QuestionImportDto> questionImports) {
        if (CollectionUtils.isEmpty(questionImports)) {
            return 0;
        }
        
        int successCount = 0;
        for (QuestionImportDto importDto : questionImports) {
            try {
                // 转换DTO为实体
                Question question = convertImportDtoToQuestion(importDto);
                
                // 保存题目及其详细信息
                saveQuestionWithDetails(question);
                successCount++;
                
                log.info("成功导入题目: {}", question.getTitle());
                
            } catch (Exception e) {
                log.error("导入题目失败: {}, 错误: {}", importDto.getTitle(), e.getMessage());
                // 继续处理下一个题目，不中断整个导入过程
            }
        }
        
        log.info("批量导入完成，成功导入 {} / {} 道题目", successCount, questionImports.size());
        return successCount;
    }
    
    @Override
    public Question convertImportDtoToQuestion(QuestionImportDto importDto) {
        Question question = new Question();
        
        // 设置基本属性
        question.setTitle(importDto.getTitle());
        question.setType(importDto.getType());
        question.setMulti(importDto.getMulti() != null ? importDto.getMulti() : false);
        question.setCategoryId(importDto.getCategoryId() != null ? importDto.getCategoryId() : 1L);
        question.setDifficulty(importDto.getDifficulty() != null ? importDto.getDifficulty() : "MEDIUM");
        question.setScore(importDto.getScore() != null ? importDto.getScore() : 5);
        question.setAnalysis(importDto.getAnalysis());
        question.setCreateTime(new Date());
        question.setUpdateTime(new Date());
        
        // 处理选择题选项
        if ("CHOICE".equals(importDto.getType()) && !CollectionUtils.isEmpty(importDto.getChoices())) {
            List<QuestionChoice> choices = new ArrayList<>();
            for (QuestionImportDto.ChoiceImportDto choiceDto : importDto.getChoices()) {
                QuestionChoice choice = new QuestionChoice();
                choice.setContent(choiceDto.getContent());
                choice.setIsCorrect(choiceDto.getIsCorrect() != null ? choiceDto.getIsCorrect() : false);
                choice.setSort(choiceDto.getSort() != null ? choiceDto.getSort() : choices.size() + 1);
                choices.add(choice);
            }
            question.setChoices(choices);
        } else if (importDto.getAnswer() != null) {
            // 判断题和简答题的答案
            QuestionAnswer answer = new QuestionAnswer();
            answer.setAnswer(importDto.getAnswer());
            answer.setKeywords(importDto.getKeywords());
            question.setAnswer(answer);
        }
        
        return question;
    }
    
    /**
     * 增加题目访问计数
     * 使用Redis Sorted Set记录题目访问次数
     * 
     * 【缓存策略说明】
     * 1. 使用RedisUtils直接操作Redis的Sorted Set数据结构
     * 2. 使用zIncrementScore方法原子性地增加题目访问计数
     * 3. 异常处理确保即使Redis操作失败也不影响正常业务流程
     * 
     * 【为什么使用RedisUtils而非Spring Cache】
     * - 计数器场景：需要原子性递增操作，Spring Cache注解不直接支持
     * - 高级数据结构：利用Redis的Sorted Set实现排序功能，Spring Cache主要支持简单键值对
     * - 性能考虑：直接操作Redis API效率更高，无需序列化/反序列化整个对象
     * - 异常隔离：Redis操作失败不应影响正常业务，需要精细的异常处理
     */
    @Override
    public void incrementQuestionViewCount(Long questionId) {
        try {
            // 检查题目是否存在
            if (!this.getBaseMapper().exists(new QueryWrapper<Question>().eq("id", questionId))) {
                log.warn("尝试增加不存在题目的访问计数，题目ID: {}", questionId);
                return;
            }
            
            // 增加题目访问计数，如果不存在则初始化为1
            Double newScore = redisUtils.zIncrementScore(CacheConstants.QUESTION_VIEW_COUNT_KEY, questionId, 1);
            log.debug("题目访问计数增加，题目ID: {}, 当前计数: {}", questionId, newScore.intValue());
        } catch (Exception e) {
            // 访问计数失败不应影响正常业务
            log.error("增加题目访问计数失败，题目ID: {}", questionId, e);
        }
    }
    
    /**
     * 获取热门题目列表
     * 根据访问次数排序，获取访问次数最多的题目
     * 
     * 【缓存策略说明】
     * 1. 使用RedisUtils直接操作Redis的Sorted Set获取热门题目ID列表
     * 2. 先检查缓存是否存在，不存在则初始化，确保首次访问也能返回数据
     * 3. 实现了完善的降级策略，当Redis操作失败时返回最新题目作为备选方案
     * 4. 使用zReverseRange获取分数最高的题目ID，实现排行榜功能
     * 
     * 【为什么使用RedisUtils而非Spring Cache】
     * - 排序需求：需要按访问次数排序，Spring Cache不直接支持排序查询
     * - 复杂数据结构：利用Redis的Sorted Set实现排行榜功能
     * - 降级处理：需要精细控制异常处理和降级逻辑
     * - 动态查询：支持动态调整返回数量，Spring Cache不易实现
     * - 数据组合：需要将Redis数据与数据库数据组合，Spring Cache难以处理
     */
    @Override
    public List<Question> getPopularQuestions(int limit) {
        log.debug("获取热门题目，数量: {}", limit);
        
        // 默认获取数量
        if (limit <= 0) {
            limit = CacheConstants.POPULAR_QUESTIONS_COUNT;
        }
        
        try {
            // 检查Redis中是否存在热门题目缓存
            if (!redisUtils.hasKey(CacheConstants.QUESTION_VIEW_COUNT_KEY)) {
                log.info("热门题目缓存不存在，尝试初始化");
                refreshPopularQuestionsCache();
            }
            
            // 获取访问次数最多的题目ID列表
            Set<Object> popularQuestionIds = redisUtils.zReverseRange(
                CacheConstants.QUESTION_VIEW_COUNT_KEY, 0, limit - 1);
            
            if (popularQuestionIds == null || popularQuestionIds.isEmpty()) {
                log.debug("没有热门题目记录，返回最新题目作为备选方案");
                // 直接使用备选方案
                return getLatestQuestions(limit);
            }
            
            // 将Object类型转换为Long类型
            List<Long> questionIds = popularQuestionIds.stream()
                .map(id -> {
                    try {
                        return Long.valueOf(id.toString());
                    } catch (NumberFormatException e) {
                        log.warn("题目ID转换失败: {}", id);
                        return null;
                    }
                })
                .filter(id -> id != null)
                .collect(Collectors.toList());
            
            if (questionIds.isEmpty()) {
                log.warn("转换后的题目ID列表为空，返回最新题目作为备选方案");
                return getLatestQuestions(limit);
            }
            
            // 批量查询题目详情
            List<Question> questions = this.listByIds(questionIds);
            
            // 如果没有找到任何题目，返回最新题目
            if (questions.isEmpty()) {
                log.warn("根据热门题目ID未找到任何题目，返回最新题目作为备选方案");
                return getLatestQuestions(limit);
            }
            
            // 填充选项和答案
            fillChoicesAndAnswer(questions);
            
            // 按照访问次数排序
            Map<Long, Double> scoreMap = new java.util.HashMap<>();
            for (Long id : questionIds) {
                Double score = redisUtils.zScore(CacheConstants.QUESTION_VIEW_COUNT_KEY, id);
                if (score != null) {
                    scoreMap.put(id, score);
                }
            }
            
            // 根据访问次数排序
            questions.sort((q1, q2) -> {
                Double score1 = scoreMap.getOrDefault(q1.getId(), 0.0);
                Double score2 = scoreMap.getOrDefault(q2.getId(), 0.0);
                return Double.compare(score2, score1); // 降序排列
            });
            
            log.debug("热门题目查询完成，返回题目数量: {}", questions.size());
            return questions;
        } catch (Exception e) {
            log.error("获取热门题目失败: {}", e.getMessage(), e);
            // 如果Redis出现问题，返回最新的题目作为备选方案
            return getLatestQuestions(limit);
        }
    }
    
    /**
     * 获取最新题目作为备选方案
     * @param limit 获取数量
     * @return 最新题目列表
     */
    private List<Question> getLatestQuestions(int limit) {
        try {
            log.info("获取最新题目作为备选方案，数量: {}", limit);
            List<Question> latestQuestions = this.list(
                new QueryWrapper<Question>()
                    .orderByDesc("create_time")
                    .last("LIMIT " + limit)
            );
            fillChoicesAndAnswer(latestQuestions);
            return latestQuestions;
        } catch (Exception e) {
            log.error("获取最新题目失败: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    
    /**
     * 刷新热门题目缓存
     * 重置或初始化热门题目的访问计数
     */
    @Override
    public int refreshPopularQuestionsCache() {
        log.info("开始刷新热门题目缓存");
        
        try {
            // 1. 删除现有的访问计数缓存
            String viewCountKey = CacheConstants.QUESTION_VIEW_COUNT_KEY;
            redisUtils.delete(viewCountKey);
            
            // 2. 获取所有题目ID
            List<Question> allQuestions = this.list();
            if (allQuestions.isEmpty()) {
                log.info("没有题目数据，热门题目缓存刷新完成");
                return 0;
            }
            
            // 3. 为每个题目设置初始访问计数（可以根据一定规则设置初始值）
            // 这里我们使用题目ID的模100作为初始值，仅作为演示
            // 实际应用中可以根据题目的创建时间、难度、质量等因素设置初始值
            int count = 0;
            for (Question question : allQuestions) {
                Long id = question.getId();
                // 初始计数值，这里使用ID模100作为示例，实际应用可以使用更复杂的算法
                double initialScore = id % 100;
                
                // 将题目ID和初始访问计数添加到Sorted Set
                redisUtils.zAdd(viewCountKey, id, initialScore);
                count++;
            }
            
            log.info("热门题目缓存刷新完成，共处理题目数量: {}", count);
            return count;
        } catch (Exception e) {
            log.error("刷新热门题目缓存失败", e);
            return 0;
        }
    }

    /**
     * 分页查询题目信息：方案2 进行分步查询
     * @param questionPage
     * @param questionQueryVo
     */
    @Override
    public void queryQuestionListByPage(Page<Question> questionPage, QuestionQueryVo questionQueryVo) {
        questionMapper.selectQuestionPage(questionPage, questionQueryVo);
    }

    // 优化：批量查询避免N+1问题
    private void fillChoicesAndAnswer(List<Question> questions) {
        if (questions.isEmpty()) {
            return;
        }
        
        // 收集所有题目ID
        List<Long> questionIds = questions.stream()
                .map(Question::getId)
                .collect(Collectors.toList());
        
        // 批量查询所有选项
        List<QuestionChoice> allChoices = questionChoiceMapper.selectList(
                new QueryWrapper<QuestionChoice>().in("question_id", questionIds)
        );
        
        // 批量查询所有答案
        List<QuestionAnswer> allAnswers = questionAnswerMapper.selectList(
                new QueryWrapper<QuestionAnswer>().in("question_id", questionIds)
        );
        
        // 按题目ID分组
        Map<Long, List<QuestionChoice>> choicesMap = allChoices.stream()
                .collect(Collectors.groupingBy(QuestionChoice::getQuestionId));
        
        Map<Long, QuestionAnswer> answersMap = allAnswers.stream()
                .collect(Collectors.toMap(QuestionAnswer::getQuestionId, answer -> answer));
        
        // 为每个题目设置选项和答案
        for (Question question : questions) {
            if ("CHOICE".equals(question.getType())) {
                List<QuestionChoice> choices = choicesMap.getOrDefault(question.getId(), new ArrayList<>());
                // 按sort字段排序
                choices.sort((c1, c2) -> Integer.compare(
                    c1.getSort() != null ? c1.getSort() : 0,
                    c2.getSort() != null ? c2.getSort() : 0
                ));
                question.setChoices(choices);
            }
            
            // 设置答案
            QuestionAnswer answer = answersMap.get(question.getId());
            question.setAnswer(answer);
        }
    }

    /**
     * 获取题目详情，包含选项和答案
     * 使用Redis缓存优化，减少数据库查询
     * 同时记录题目访问次数，用于热门题目推荐
     * 
     * 【缓存策略说明】
     * 1. 使用@Cacheable注解缓存题目详情，键格式为"detail:题目ID"
     * 2. unless条件确保不缓存null结果，防止缓存穿透
     * 3. 异步增加题目访问计数，不影响主流程响应时间
     * 4. 注意：由于使用了缓存，访问计数代码只有在缓存未命中时才会执行
     * 
     * 【为什么使用Spring Cache注解】
     * - 高频查询场景：题目详情查询是最高频的操作之一，适合缓存
     * - 复杂对象缓存：题目详情包含选项和答案，是复杂对象，Spring Cache自动处理序列化
     * - 缓存命中率高：题目详情相对稳定，不会频繁变化
     * - 代码简洁：一个注解替代手动缓存逻辑，提高代码可维护性
     * 
     * 【混合缓存策略】
     * - Spring Cache：缓存题目详情数据
     * - RedisUtils：记录题目访问次数（使用Sorted Set）
     * 这种混合策略充分利用了两种缓存方式的优势，既简化了代码，又实现了复杂的排行榜功能
     */
    @Override
    @Cacheable(value = CacheConstants.QUESTION_CACHE, key = "'detail:' + #id", unless = "#result == null")
    public Question getQuestionWithDetails(Long id) {
        log.debug("从数据库获取题目详情，题目ID: {}", id);
        
        // 1. 查询题目基本信息
        Question question = this.getById(id);
        if (question == null) return null;

        // 2. 查询选项（仅选择题）
        if ("CHOICE".equals(question.getType())) {
            List<QuestionChoice> choices = questionChoiceMapper.selectList(
                new QueryWrapper<QuestionChoice>().eq("question_id", id)
            );
            question.setChoices(choices);
        }

        // 3. 查询答案
        QuestionAnswer answer = questionAnswerMapper.selectOne(
            new QueryWrapper<QuestionAnswer>().eq("question_id", id)
        );
        question.setAnswer(answer);
        
        // 4. 为了回显，根据答案反向设置choices里的isCorrect
        if ("CHOICE".equals(question.getType()) && question.getAnswer() != null && !CollectionUtils.isEmpty(question.getChoices())) {
            String[] correctAnswers = question.getAnswer().getAnswer().split(",");
            List<String> correctList = List.of(correctAnswers);
            
            IntStream.range(0, question.getChoices().size()).forEach(i -> {
                String optionLabel = String.valueOf((char) ('A' + i));
                if (correctList.contains(optionLabel)) {
                    question.getChoices().get(i).setIsCorrect(true);
                }
            });
        }
        
        log.debug("题目详情查询完成，题目ID: {}", id);
        
        // 5. 异步增加题目访问计数（不影响主流程）
        // 注意：由于使用了缓存，这段代码只有在缓存未命中时才会执行
        // 为了更准确地统计访问次数，可以考虑在Controller层增加计数
        try {
            new Thread(() -> incrementQuestionViewCount(id)).start();
        } catch (Exception e) {
            log.warn("增加题目访问计数失败，但不影响正常业务，题目ID: {}", id);
        }
        
        return question;
    }
} 