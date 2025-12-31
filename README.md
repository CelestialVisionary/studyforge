# StudyForge 智能学习平台

## 项目简介
StudyForge 是一个基于 Spring Boot 和 Vue 3 开发的智能学习平台，提供在线考试、智能判卷、成绩管理、轮播图管理等功能。该平台支持多种题型，包括选择题、判断题和简答题，具备AI智能判卷能力，能够自动评分和生成详细的考试报告。

## 技术栈

### 后端
- **框架**: Spring Boot 3.0.5
- **ORM**: MyBatis Plus 3.5.3.1
- **数据库**: MySQL 8.0+
- **文件存储**: MinIO
- **AI服务**: Kimi AI
- **API文档**: Knife4j

### 前端
- **框架**: Vue 3
- **构建工具**: Vite
- **UI组件库**: Element Plus
- **HTTP客户端**: Axios
- **状态管理**: Pinia
- **路由**: Vue Router

## 项目结构

```
studyforge/
├── backend/                # 后端代码
│   ├── src/main/java/com/exam/           # 主源码目录
│   ├── src/main/resources/             # 资源文件
│   ├── pom.xml                        # Maven配置
│   └── README.md                      # 后端说明文档
├── frontend/               # 前端代码
│   ├── src/                           # 主源码目录
│   ├── index.html                     # 入口HTML
│   ├── package.json                   # NPM配置
│   └── vite.config.ts                 # Vite配置
├── material/               # 项目资料
│   ├── 课件/                          # 教学课件
│   └── 数据库脚本/                      # 数据库脚本
├── .gitignore             # Git忽略文件配置
└── README.md              # 项目说明文档
```

## 快速开始

### 环境要求
- JDK 17+
- Node.js 16+
- MySQL 8.0+
- MinIO（可选，用于文件存储）

### 安装依赖

#### 后端依赖安装
```bash
cd backend
mvn install
```

#### 前端依赖安装
```bash
cd frontend
npm install
```

### 配置

1. **数据库配置**
   - 创建数据库 `exam_system_online`
   - 执行 `material/数据库脚本/` 目录下的SQL脚本初始化数据库
   - 修改 `backend/src/main/resources/application.yml` 中的数据库连接信息

2. **文件存储配置**
   - 如果使用MinIO，修改 `backend/src/main/resources/application.yml` 中的MinIO配置
   - 如果不使用MinIO，系统会自动切换到本地文件存储

3. **AI服务配置**
   - 修改 `backend/src/main/resources/application.yml` 中的Kimi AI配置
   - 替换 `kimi.api.key` 为您自己的API密钥

### 运行

#### 启动后端服务
```bash
cd backend
mvn spring-boot:run
```

#### 启动前端服务
```bash
cd frontend
npm run dev
```

### 访问地址
- 前端地址: http://localhost:3001
- 后端API地址: http://localhost:8080
- API文档地址: http://localhost:8080/doc.html

## 主要功能

### 1. 题目管理
- 支持多种题型：选择题、判断题、简答题
- 支持批量导入题目
- 支持按分类、难度、题型筛选题目
- 支持题目答案和关键词管理

### 2. 试卷管理
- 支持创建和编辑试卷
- 支持手动组卷和自动组卷
- 支持设置考试时长和及格分数
- 支持试卷发布和关闭

### 3. 考试管理
- 支持在线考试
- 支持考试状态监控
- 支持切屏次数统计
- 支持自动提交和手动提交

### 4. 智能判卷
- 客观题自动判分
- 主观题AI智能判分
- 支持评分标准自定义
- 支持判卷结果查看和修改

### 5. 成绩管理
- 支持考试成绩查询
- 支持成绩统计和分析
- 支持成绩导出
- 支持考试排行榜

### 6. 轮播图管理
- 支持轮播图添加、编辑和删除
- 支持轮播图状态管理
- 支持轮播图排序

## 开发指南

### 后端开发
1. 遵循RESTful API设计规范
2. 使用MyBatis Plus进行数据库操作
3. 服务层使用@Transactional注解管理事务
4. 控制器层统一返回Result对象

### 前端开发
1. 遵循Vue 3组合式API开发规范
2. 使用Pinia进行状态管理
3. 使用Axios进行API调用
4. 使用Element Plus组件库

## 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开Pull Request

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 联系方式

如有问题或建议，请通过以下方式联系：
- GitHub Issues: https://github.com/CelestialVisionary/studyforge/issues
- 邮箱: your-email@example.com

---

感谢使用 StudyForge 智能学习平台！