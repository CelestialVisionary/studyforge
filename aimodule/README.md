# AI Module 服务部署与使用说明

## 项目简介

AI Module 是一个基于 Python 的可微调大模型服务，使用 LangChain 和 LangGraph 构建，支持与后端系统通过 JSON 进行通信。

主要功能：
- 聊天完成接口
- 智能答疑功能
- 模型微调（支持 LoRA 方法）
- 模型管理（加载、卸载、列出）

## 环境要求

### 硬件要求
- CPU：至少 4 核
- 内存：至少 8GB
- GPU：推荐使用 NVIDIA GPU（支持 CUDA）以获得更好的性能

### 软件要求
- Python 3.8 或更高版本
- pip 包管理工具
- Java 后端系统（用于集成）

## 安装步骤

### 1. 安装 Python

如果系统中未安装 Python，请从 [Python 官网](https://www.python.org/downloads/) 下载并安装 Python 3.8 或更高版本。

### 2. 克隆项目

确保 AI Module 目录已存在于 `d:\javaproj\studyforge\aimodule`。

### 3. 安装依赖

打开命令行窗口，进入 AI Module 目录：

```bash
cd d:\javaproj\studyforge\aimodule
```

创建虚拟环境（可选但推荐）：

```bash
python -m venv venv
```

激活虚拟环境：

```bash
# Windows
venv\Scripts\activate.bat

# Linux/Mac
source venv/bin/activate
```

安装依赖包：

```bash
pip install -r requirements.txt
```

## 启动服务

### 方法 1：使用启动脚本

在 AI Module 目录中运行：

```bash
# Windows
start_service.bat

# Linux/Mac
chmod +x start_service.sh
./start_service.sh
```

### 方法 2：手动启动

激活虚拟环境后，运行：

```bash
python main.py
```

服务默认在 `http://localhost:8000` 启动。

## 服务配置

### 配置文件

AI Module 服务的主要配置包括：

- 服务地址：默认 `0.0.0.0:8000`
- 默认模型：`Qwen/Qwen2.5-0.5B-Instruct`
- 微调模型存储路径：`./finetune_output`

### 与后端集成配置

在 Java 后端的 `application.yml` 文件中添加以下配置：

```yaml
aimodule:
  api:
    base-url: http://localhost:8000  # AI Module 服务地址
    model: default  # 默认模型名称
```

## API 接口说明

### 1. 聊天完成接口

**请求地址**：`POST /api/chat/completions`

**请求参数**：

```json
{
  "model": "default",
  "messages": [
    {
      "role": "system",
      "content": "你是一个智能助手"
    },
    {
      "role": "user",
      "content": "你好，今天天气怎么样？"
    }
  ],
  "temperature": 0.7,
  "max_tokens": 1024
}
```

**响应**：

```json
{
  "content": "你好！我是一个AI助手，无法实时获取天气信息。不过，你可以通过天气预报网站或应用查看当地的最新天气情况。如果你有其他问题，我很乐意帮助你！"
}
```

### 2. 智能答疑接口

**请求地址**：`POST /api/chat/smart-answer`

**请求参数**：

```json
{
  "question": "什么是Java中的多态？"
}
```

**响应**：

```json
{
  "content": "1. 定义：多态是Java面向对象编程的三大特性之一，指同一个方法调用可以根据对象的不同而表现出不同的行为。\n2. 实现方式：\n   - 方法重写（Override）：子类重写父类的方法\n   - 方法重载（Overload）：同一个类中同名但参数不同的方法\n3. 示例：\n   ```java\n   // 父类\n   class Animal {\n       public void makeSound() {\n           System.out.println(\"Animal makes sound\");\n       }\n   }\n   // 子类\n   class Dog extends Animal {\n       @Override\n       public void makeSound() {\n           System.out.println(\"Dog barks\");\n       }\n   }\n   // 测试\n   Animal animal = new Dog();\n   animal.makeSound(); // 输出：Dog barks\n   ```\n4. 作用：提高代码的灵活性、可扩展性和可维护性。"
}
```

### 3. 模型微调接口

**请求地址**：`POST /api/finetune/create`

**请求参数**：

```json
{
  "model_name": "default",
  "data": [
    {
      "instruction": "解释什么是面向对象编程",
      "input": "",
      "output": "面向对象编程是一种编程范式，它将数据和操作数据的方法组合成对象，通过对象之间的交互来实现程序功能。"
    },
    {
      "instruction": "解释什么是封装",
      "input": "",
      "output": "封装是面向对象编程的三大特性之一，它将对象的属性和方法结合在一起，隐藏对象的内部实现细节，只对外提供公共接口。"
    }
  ],
  "epochs": 3,
  "learning_rate": 0.0001,
  "batch_size": 8
}
```

**响应**：

```json
{
  "status": "success",
  "model_path": "./finetune_output/default_finetuned",
  "message": "模型微调成功"
}
```

### 4. 模型管理接口

**列出所有模型**：`GET /api/model/list`

**加载模型**：`POST /api/model/load/{model_name}`

**卸载模型**：`POST /api/model/unload/{model_name}`

## 与后端集成使用

### 1. 后端接口调用

在 Java 后端代码中，可以通过以下方式调用 AI Module 服务：

```java
// 使用默认模型
String answer = aiService.getSmartAnswer(question);

// 指定使用 AI Module
String answer = aiService.getSmartAnswer(question, "aimodule");
```

### 2. 前端调用示例

```javascript
// 智能答疑请求
fetch('/api/ai/smart-answer', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    question: '什么是Java中的多态？',
    modelType: 'aimodule'  // 使用 AI Module
  })
})
.then(response => response.json())
.then(data => {
  console.log('AI 回答:', data.answer);
});
```

## 故障排查

### 常见问题

1. **服务启动失败**
   - 检查 Python 版本是否满足要求
   - 检查依赖包是否正确安装
   - 检查端口 8000 是否被占用

2. **模型加载失败**
   - 检查网络连接（首次加载模型需要下载）
   - 检查 GPU 内存是否足够
   - 检查磁盘空间是否充足

3. **与后端通信失败**
   - 检查 AI Module 服务是否正在运行
   - 检查后端配置中的服务地址是否正确
   - 检查网络连接是否正常

4. **微调模型失败**
   - 检查训练数据格式是否正确
   - 检查 GPU 内存是否足够
   - 检查磁盘空间是否充足

### 日志查看

AI Module 服务的日志会输出到控制台，可以通过查看日志来定位问题。

## 性能优化

1. **使用 GPU**：如果有 NVIDIA GPU，确保安装了 CUDA 和 cuDNN，以获得更好的性能。

2. **模型选择**：根据硬件资源选择合适大小的模型，较小的模型加载更快，推理速度也更快。

3. **批量处理**：对于微调任务，适当调整 batch_size 以充分利用硬件资源。

4. **缓存**：考虑使用缓存来存储频繁使用的模型响应，减少重复计算。

## 安全注意事项

1. **API 访问控制**：在生产环境中，建议添加 API 访问控制，防止未授权访问。

2. **输入验证**：对用户输入进行严格验证，防止注入攻击。

3. **资源限制**：设置合理的请求频率限制，防止服务被滥用。

4. **数据安全**：确保训练数据和模型参数的安全存储，避免敏感信息泄露。

## 版本更新

### 未来计划

- 支持更多模型类型
- 优化微调算法
- 添加模型评估功能
- 提供更丰富的 API 接口
- 支持模型部署到云端

## 联系方式

如有问题或建议，请联系开发团队。

---

**注意**：本服务使用的默认模型 `Qwen/Qwen2.5-0.5B-Instruct` 是一个轻量级模型，主要用于演示和测试。在生产环境中，建议使用更适合业务需求的模型。
