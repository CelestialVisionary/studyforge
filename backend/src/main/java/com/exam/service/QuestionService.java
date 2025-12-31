package com.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.Question;
import com.exam.dto.QuestionImportDto;
import com.exam.vo.QuestionQueryVo;

import java.util.List;

/**
 * 题目业务服务接口 - 定义题目相关的业务逻辑
 * 
 * Spring Boot三层架构教学要点：
 * 1. Service层：业务逻辑层，位于Controller和Mapper之间
 * 2. 接口设计：定义业务方法规范，便于不同实现类的切换
 * 3. 继承IService：使用MyBatis Plus提供的通用服务接口，减少重复代码
 * 4. 事务管理：Service层是事务的边界，复杂业务操作应该加@Transactional
 * 5. 业务封装：将复杂的数据操作封装成有业务意义的方法
 * 
 * MyBatis Plus教学：
 * - IService<T>：提供基础的CRUD方法（save、update、remove、list等）
 * - 自定义方法：在接口中定义特定业务需求的方法
 * - 实现类：继承ServiceImpl<Mapper, Entity>并实现自定义业务方法
 * 
 * 设计原则：
 * - 单一职责：专门处理题目相关的业务逻辑
 * - 开闭原则：通过接口定义，便于扩展新的实现
 * - 依赖倒置：Controller依赖接口而不是具体实现
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
public interface QuestionService extends IService<Question> {
    
    /**
     * 保存题目及其完整信息（包含选项和答案）
     * 
     * 业务特点：
     * - 事务性操作：需要同时操作questions、question_choices、question_answers三张表
     * - 数据一致性：要么全部成功，要么全部回滚
     * - 关联处理：自动处理主外键关系
     * 
     * 实现要点：
     * 1. 使用@Transactional确保事务完整性
     * 2. 先保存题目主表获得ID
     * 3. 基于题目ID保存选项和答案
     * 4. 异常处理：任何步骤失败都要回滚
     * 
     * @param question 包含完整信息的题目对象（含选项choices和答案answer）
     */
    void saveQuestionWithDetails(Question question);

    /**
     * 更新题目及其完整信息（包含选项和答案）
     * 
     * 业务复杂性：
     * - 需要处理选项的增删改：删除旧选项，添加新选项
     * - 答案更新：覆盖原有答案或新增答案
     * - 数据完整性：确保更新过程中数据一致
     * 
     * 实现策略：
     * 1. 更新题目主表信息
     * 2. 删除原有选项，重新插入新选项（简化逻辑）
     * 3. 更新或插入答案信息
     * 
     * @param question 包含更新信息的题目对象
     */
    void updateQuestionWithDetails(Question question);

    /**
     * 根据分类查询题目列表
     * 
     * 业务场景：
     * - 题目管理：按学科、章节分类浏览
     * - 智能组卷：从特定分类中选择题目
     * - 学习路径：按知识点组织学习内容
     * 
     * @param categoryId 题目分类ID，关联categories表
     * @return 该分类下的所有题目列表
     */
    List<Question> getQuestionsByCategory(Long categoryId);
    
    /**
     * 根据难度等级查询题目列表
     * 
     * 教学价值：
     * - 分层教学：根据学生水平提供不同难度的题目
     * - 循序渐进：从简单到困难的学习路径
     * - 个性化：为不同能力的学生定制练习
     * 
     * @param difficulty 难度等级枚举（EASY：简单，MEDIUM：中等，HARD：困难）
     * @return 指定难度的题目列表
     */
    List<Question> getQuestionsByDifficulty(String difficulty);
    
    /**
     * 根据题目类型查询题目列表
     * 
     * 题型说明：
     * - CHOICE：选择题（单选/多选），适合客观评价
     * - JUDGE：判断题（对/错），适合概念理解
     * - TEXT：简答题，适合主观分析和表达能力
     * 
     * @param type 题目类型枚举（CHOICE、JUDGE、TEXT）
     * @return 指定类型的题目列表
     */
    List<Question> getQuestionsByType(String type);
    
    /**
     * 随机获取题目 - 智能组卷的核心算法
     * 
     * 算法设计：
     * 1. 构建候选题目池：根据分类和难度筛选
     * 2. 随机选择：使用数据库RAND()或Java Random
     * 3. 去重处理：确保选中的题目不重复
     * 4. 数量控制：严格按照需求数量返回
     * 
     * 性能优化：
     * - 大数据集随机：使用TABLESAMPLE或分页随机
     * - 缓存策略：热门组合可以缓存结果
     * - 索引优化：在筛选字段上建立复合索引
     * 
     * 业务扩展：
     * - 智能权重：根据题目使用频率调整选择概率
     * - 知识点覆盖：确保题目覆盖不同知识点
     * - 难度分布：按比例分配不同难度的题目
     * 
     * @param count 需要获取的题目数量
     * @param categoryId 限定分类ID，null表示不限制分类
     * @param difficulty 限定难度等级，null表示不限制难度
     * @return 随机选择的题目列表，数量不超过count
     */
    List<Question> getRandomQuestions(Integer count, Long categoryId, String difficulty);

    /**
     * 获取包含完整信息的题目详情
     * 
     * 关联查询设计：
     * - 主表查询：获取题目基本信息
     * - 选项关联：查询question_choices表获取选项
     * - 答案关联：查询question_answers表获取答案
     * - 分类关联：查询categories表获取分类信息
     * 
     * 性能考虑：
     * - 懒加载vs急加载：根据使用场景选择加载策略
     * - N+1问题：避免循环查询，考虑批量加载
     * - 缓存应用：对于热门题目可以缓存详情
     * 
     * @param id 题目主键ID
     * @return 包含选项、答案、分类等完整信息的题目对象
     */
    Question getQuestionWithDetails(Long id);
    
    /**
     * 批量导入题目 - Excel/CSV数据导入功能
     * 
     * 导入流程：
     * 1. 数据验证：检查必填字段、格式规范、业务规则
     * 2. 重复检查：避免导入重复题目
     * 3. 批量插入：使用批处理提高性能
     * 4. 异常处理：记录失败原因，支持部分成功
     * 
     * 性能优化：
     * - 批量操作：使用MyBatis Plus的saveBatch
     * - 事务控制：大批量数据分段提交
     * - 内存管理：流式处理避免内存溢出
     * 
     * 数据质量：
     * - 格式验证：题目内容、选项格式、答案格式
     * - 业务验证：分类存在性、难度合法性
     * - 完整性验证：选择题必须有选项，所有题目必须有答案
     * 
     * @param questionImports 题目导入DTO列表，包含Excel解析后的数据
     * @return 成功导入的题目数量
     */
    int batchImportQuestions(List<QuestionImportDto> questionImports);
    
    /**
     * 数据转换方法：将导入DTO转换为题目实体
     * 
     * 转换逻辑：
     * - 基础字段映射：标题、类型、难度等
     * - 选项处理：解析选项字符串为选项对象列表
     * - 答案处理：解析答案字符串为答案对象
     * - 默认值设置：创建时间、分值等
     * 
     * 数据清洗：
     * - 去除空格：trim处理文本内容
     * - 格式统一：统一枚举值格式
     * - 类型转换：字符串转换为相应的数据类型
     * 
     * @param importDto 题目导入DTO对象
     * @return 转换后的题目实体对象
     */
    Question convertImportDtoToQuestion(QuestionImportDto importDto);
    
    /**
     * 获取热门题目列表
     * 
     * 业务场景：
     * - 首页推荐：展示最受欢迎的题目
     * - 学习引导：引导学生练习高频题目
     * - 数据分析：了解题目受欢迎程度
     * 
     * 实现技术：
     * - Redis Sorted Set：高效存储和排序访问计数
     * - 缓存策略：定期更新热门题目列表
     * - 异步计数：不影响用户体验的访问统计
     * 
     * @param limit 获取数量
     * @return 按访问次数排序的热门题目列表
     */
    List<Question> getPopularQuestions(int limit);
    
    /**
     * 增加题目访问计数
     * 
     * 业务价值：
     * - 用户行为分析：了解哪些题目最受关注
     * - 智能推荐：为热门题目推荐提供数据支持
     * - 内容优化：根据热度调整题目质量和难度
     * 
     * 实现要点：
     * - 高性能：使用Redis Sorted Set存储计数
     * - 低延迟：异步增加计数，不阻塞主流程
     * - 容错性：计数失败不影响核心业务
     * 
     * @param questionId 题目ID
     */
    void incrementQuestionViewCount(Long questionId);
    
    /**
     * 刷新热门题目缓存
     * 
     * 管理功能：
     * - 手动刷新：管理员可强制更新热门题目数据
     * - 重置计数：清除历史访问数据，重新开始统计
     * - 初始化：系统首次部署时初始化热门题目
     * 
     * 实现策略：
     * - 全量刷新：重建整个热门题目缓存
     * - 选择性刷新：保留部分历史数据
     * - 定时任务：可配置为定期自动刷新
     * 
     * @return 刷新后的热门题目数量
     */
    int refreshPopularQuestionsCache();

    /**
     * 分页查询题目信息：方案2 进行分步查询
     * @param questionPage
     * @param questionQueryVo
     */
    void queryQuestionListByPage(Page<Question> questionPage, QuestionQueryVo questionQueryVo);
} 