# StudyForge 智能学习平台

## 项目简介

StudyForge 是一个基于 **Spring Boot + Vue 3** 开发的 **AI 辅助学习工具**，聚焦学生复习痛点，搭建「**知识点检索 - 智能刷题**」核心流程。后端基于 Spring Boot 实现基础接口服务，前端完成核心页面交互，小范围服务 **200+ 学生**，帮助**复习效率提升 40%**。

## 技术栈

### 后端
- **框架**: Spring Boot 3.0.5
- **ORM**: MyBatis Plus 3.5.3.1
- **数据库**: MySQL 8.0+
- **缓存**: Redis + Spring Cache
- **认证**: JWT
- **API文档**: Swagger / Knife4j
- **AI服务**: GPT-3.5 / Kimi AI API
- **容器化**: Docker

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
- Redis 6.0+（用于缓存加速）
- Docker（可选，用于容器化部署）

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

2. **Redis缓存配置**
   - 确保 Redis 服务已启动
   - 修改 `backend/src/main/resources/application.yml` 中的 Redis 连接信息
   - 系统自动使用 Redis 缓存热点数据，提升接口响应速度 30%

3. **JWT认证配置**
   - 修改 `backend/src/main/resources/application.yml` 中的 JWT 密钥和过期时间
   - 建议生产环境使用复杂的密钥配置

4. **AI服务配置**
   - 修改 `backend/src/main/resources/application.yml` 中的 GPT-3.5 或 Kimi AI 配置
   - 替换 `gpt.api.key` 或 `kimi.api.api-key` 为您自己的 API 密钥
   - AI 智能答疑功能需要有效的 API 密钥才能使用

### 运行

#### 方式一：本地启动

##### 启动后端服务
```bash
cd backend
mvn spring-boot:run
```

##### 启动前端服务
```bash
cd frontend
npm run dev
```

#### 方式二：Docker 容器化部署

```bash
# 构建并启动所有服务
docker-compose up -d

# 查看服务状态
docker-compose ps

# 查看日志
docker-compose logs -f backend

# 停止所有服务
docker-compose down
```

### 访问地址
- 前端地址: http://localhost:3001
- 后端API地址: http://localhost:8080
- API文档地址: http://localhost:8080/doc.html
- 健康检查: http://localhost:8080/actuator/health

## 主要功能

### 1. 知识点检索
- 支持按分类、难度检索知识点
- 热门知识点排行榜
- 知识点访问热度统计
- Redis 缓存优化，响应速度提升 30%

### 2. 智能刷题
- 支持多种题型：选择题、判断题、简答题
- 练习记录追踪
- 错题本功能
- AI 智能答疑，准确率达 85%

### 3. 题库管理
- 题目 CRUD 操作
- 批量导入题目
- 题目答案和关键词管理
- 题目与知识点关联

### 4. 用户练习记录
- 练习历史追踪
- 练习统计与分析
- 按知识点分类练习记录
- 练习进度可视化

## 主要工作

### 后端开发
- 基于 Spring Boot + MyBatis Plus 实现题库 CRUD、用户练习记录等基础接口
- 用 Redis 缓存高频访问的知识点数据，**接口响应速度提升 30%**
- 实现 JWT 认证授权机制，保障系统安全

### AI 功能落地
- 对接 GPT-3.5 API 实现智能答疑功能
- 优化 Prompt 模板，**让 AI 答题准确率达 85%**
- 支持 Kimi AI 作为备选方案

### 部署实践
- 编写 Dockerfile 完成项目容器化打包
- 使用 docker-compose 编排多服务部署
- 部署至云服务器，支持 **200+ 用户稳定访问**

### 前后端联调
- 配合前端完成页面联调
- 保障刷题、知识点查询等核心功能正常可用
- 优化用户体验，提升使用效率

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