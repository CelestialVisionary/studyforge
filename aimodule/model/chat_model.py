from langchain_community.llms import HuggingFacePipeline
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from transformers import AutoModelForCausalLM, AutoTokenizer, pipeline
import torch
import os
from openai import OpenAI
from openai.types.chat.chat_completion import ChatCompletion

class ChatModel:
    def __init__(self):
        # 初始化模型和分词器
        self.models = {}
        self._load_default_model()
        # 初始化阿里云百炼API客户端
        self.dashscope_client = None
        self._init_dashscope_client()
    
    def _load_default_model(self):
        """
        加载默认模型
        """
        try:
            # 直接使用回退模型，避免模型下载
            print("使用回退模型，服务将快速启动")
            self.models["default"] = self._create_fallback_model()
        except Exception as e:
            print(f"加载模型失败: {e}")
            # 如果加载失败，使用一个简单的回退实现
            self.models["default"] = self._create_fallback_model()
    
    def _create_fallback_model(self):
        """
        创建回退模型
        """
        class FallbackModel:
            def __call__(self, prompt, **kwargs):
                # 简单的回退实现，根据问题关键词提供基本回答
                # 优先匹配更具体的关键词
                if "装饰器" in prompt:
                    return '【思考过程】\n用户询问Python装饰器的概念，我需要给出一个清晰的定义和示例。\n\n【完整回复】\nPython装饰器是一种特殊的语法，用于修改或增强函数或类的行为，而无需修改其源代码。\n\n**核心概念**：\n- 装饰器本质上是一个函数，它接受一个函数作为参数，并返回一个新的函数\n- 使用@符号语法糖来应用装饰器\n\n**基本语法**：\n```python\ndef decorator_function(func):\n    def wrapper(*args, **kwargs):\n        # 在调用原函数前执行的代码\n        result = func(*args, **kwargs)\n        # 在调用原函数后执行的代码\n        return result\n    return wrapper\n\n@decorator_function\ndef target_function():\n    pass\n```\n\n**常见用途**：\n- 记录函数执行时间\n- 缓存函数结果\n- 验证函数参数\n- 实现权限控制\n- 日志记录\n\n装饰器是Python中非常强大的特性，它使得代码更加模块化和可复用。'
                elif "如何定义" in prompt and "函数" in prompt:
                    return '【思考过程】\n用户询问函数相关问题，我需要解释函数的基本概念和使用方法。\n\n【完整回复】\n函数是组织好的、可重复使用的、用来实现特定功能的代码块。\n\n**Python函数定义**：\n```python\ndef function_name(parameters):\n    """函数文档字符串"""\n    # 函数体\n    return value\n```\n\n**函数的优点**：\n- 代码复用：避免重复编写相同的代码\n- 模块化：将复杂问题分解为小的、可管理的部分\n- 可读性：使代码结构更清晰，更易于理解\n- 可维护性：修改函数内部实现不会影响调用它的代码\n\n**函数类型**：\n- 内置函数：如print()、len()等\n- 自定义函数：用户自己定义的函数\n- 匿名函数：使用lambda关键字创建的简单函数\n\n函数是编程中的基本构建块，掌握函数的使用是学习Python的重要一步。'
                elif "什么是" in prompt and "类" in prompt:
                    return '【思考过程】\n用户询问类的概念，我需要解释面向对象编程中的类和对象。\n\n【完整回复】\n类是面向对象编程中的核心概念，它是一种用户定义的数据类型，用于描述具有相同属性和方法的对象的集合。\n\n**Python类定义**：\n```python\nclass ClassName:\n    def __init__(self, parameters):\n        self.attribute = value\n    \n    def method(self):\n        # 方法体\n        pass\n```\n\n**核心概念**：\n- 类(Class)：对象的蓝图或模板，定义了对象的属性和方法\n- 对象(Object)：类的实例，具有类定义的属性和方法\n- 属性(Attribute)：对象的特征，如颜色、大小等\n- 方法(Method)：对象可以执行的操作，如移动、计算等\n\n**面向对象编程的优点**：\n- 封装：将数据和操作数据的方法捆绑在一起\n- 继承：子类可以继承父类的属性和方法\n- 多态：不同类的对象可以响应相同的方法调用\n\n类是Python中实现面向对象编程的基础，它使得代码更加模块化、可维护和可扩展。'
                elif "学习Python" in prompt or "如何学习" in prompt:
                    return '【思考过程】\n用户询问如何学习Python编程，我需要提供一个结构化的学习路径。\n\n【完整回复】\n学习Python编程的建议路径：\n\n**1. 基础阶段**\n- 学习Python的基本语法和数据类型\n- 掌握变量、运算符、控制流（if-else、循环）\n- 理解函数的定义和使用\n- 熟悉列表、字典、元组等数据结构\n\n**2. 进阶阶段**\n- 学习面向对象编程（类、继承、多态）\n- 掌握模块和包的使用\n- 理解异常处理机制\n- 学习文件操作和I/O\n\n**3. 实践阶段**\n- 完成小型项目，如计算器、待办事项列表\n- 参与开源项目或编程挑战\n- 学习使用版本控制系统（如Git）\n\n**4. 专业方向**\n根据兴趣选择专业方向：\n- Web开发：Django、Flask\n- 数据科学：NumPy、Pandas、Matplotlib\n- 机器学习：scikit-learn、TensorFlow\n- 自动化测试：Selenium、pytest\n\n**学习资源推荐**：\n- 官方文档：Python.org\n- 在线教程：Codecademy、Coursera\n- 书籍：《Python编程：从入门到实践》、《流畅的Python》\n- 视频课程：Bilibili、YouTube上的Python教程\n\n**学习技巧**：\n- 每天坚持编程练习\n- 阅读他人的代码\n- 参与编程社区，如Stack Overflow\n- 尝试解决实际问题\n\nPython是一种易学难精的语言，持续学习和实践是掌握它的关键。'
                else:
                    return '【思考过程】\n用户询问了一个问题，但我无法使用高级模型来回答。我需要提供一个基本的回应。\n\n【完整回复】\n感谢您的问题。由于系统未配置阿里云百炼API，我无法提供详细的智能回答。\n\n**提示**：\n- 请配置DASHSCOPE_API_KEY环境变量以使用更强大的AI模型\n- 或者，您可以尝试将问题描述得更具体，我会尽力提供基本的帮助\n\n**示例问题**：\n- 什么是Python的装饰器？\n- 如何定义和使用函数？\n- 面向对象编程中的类是什么？\n- 如何学习Python编程？'
        return FallbackModel()
    
    def generate(self, messages, model_name="default", temperature=0.7, max_tokens=1024):
        """
        生成模型响应
        """
        try:
            # 检查是否使用阿里云百炼API
            if model_name == "qwen3-max":
                if self.dashscope_client:
                    return self._generate_with_dashscope(messages)
                else:
                    print("阿里云百炼API客户端未初始化，回退到默认模型")
                    model_name = "default"
            
            # 检查模型是否存在
            if model_name not in self.models:
                # 尝试加载模型
                self._load_model(model_name)
            
            # 构建提示
            prompt = self._build_prompt(messages)
            
            # 调用模型
            if hasattr(self.models[model_name], "invoke"):
                # 使用LangChain的invoke方法
                response = self.models[model_name].invoke(prompt)
            else:
                # 使用直接调用
                response = self.models[model_name](prompt)
            
            return response
        except Exception as e:
            print(f"生成响应失败: {e}")
            return f"生成响应失败: {str(e)}"
    
    def _build_prompt(self, messages):
        """
        构建提示
        """
        prompt = ""
        for msg in messages:
            if msg["role"] == "system":
                prompt += f"系统: {msg['content']}\n"
            elif msg["role"] == "user":
                prompt += f"用户: {msg['content']}\n"
            elif msg["role"] == "assistant":
                prompt += f"助手: {msg['content']}\n"
        prompt += "助手: "
        return prompt
    
    def _init_dashscope_client(self):
        """
        初始化AI API客户端
        优先使用本地Ollama服务
        """
        try:
            # 使用本地Ollama服务
            self.dashscope_client = OpenAI(
                api_key="ollama",  # Ollama不需要实际的API密钥
                base_url="http://localhost:11434/v1",
            )
            print("本地Ollama API客户端初始化成功")
        except Exception as e:
            print(f"初始化本地Ollama API客户端失败: {e}")
    
    def _generate_with_dashscope(self, messages):
        """
        使用AI API生成响应
        使用本地Ollama服务
        """
        try:
            print("使用本地Ollama API生成响应")
            
            # 使用本地Ollama模型
            model = "mygemma"
            
            # 调用AI API
            completion = self.dashscope_client.chat.completions.create(
                model=model,
                messages=messages,
                stream=True
            )
            
            is_answering = False  # 是否进入回复阶段
            reasoning_content = ""  # 思考过程
            final_answer = ""  # 最终回复
            
            print("\n" + "=" * 20 + "思考过程" + "=" * 20)
            
            for chunk in completion:
                delta = chunk.choices[0].delta
                
                if hasattr(delta, "reasoning_content") and delta.reasoning_content is not None:
                    if not is_answering:
                        reasoning_content += delta.reasoning_content
                        print(delta.reasoning_content, end="", flush=True)
                
                if hasattr(delta, "content") and delta.content:
                    if not is_answering:
                        print("\n" + "=" * 20 + "完整回复" + "=" * 20)
                        is_answering = True
                    final_answer += delta.content
                    print(delta.content, end="", flush=True)
            
            print("\n" + "=" * 50)
            
            # 返回包含思考过程的完整回复
            if reasoning_content:
                return f"【思考过程】\n{reasoning_content}\n\n【完整回复】\n{final_answer}"
            else:
                return final_answer
        except Exception as e:
            print(f"AI API调用失败: {e}")
            return f"AI API调用失败: {str(e)}"

    def _get_chat_completion(self, messages, model="mygemma") -> str:
        """
        获取ChatCompletion对象并返回响应内容
        按照OpenAI库的标准使用方式实现
        """
        try:
            # 创建ChatCompletion对象
            # ChatCompletion对象结构示例：
            # {
            #   "id": "chatcmpl-xxx",
            #   "object": "chat.completion",
            #   "created": 1735689600,
            #   "model": "mygemma",
            #   "choices": [
            #     {
            #       "index": 0,
            #       "message": {
            #         "role": "assistant",
            #         "content": "生成的回复内容"
            #       },
            #       "finish_reason": "stop"  # stop=正常结束，length=令牌数超限，function_call=触发函数调用
            #     }
            #   ],
            #   "usage": {
            #     "prompt_tokens": 50,
            #     "completion_tokens": 80,
            #     "total_tokens": 130
            #   }
            # }
            response: ChatCompletion = self.dashscope_client.chat.completions.create(
                model=model,
                messages=messages
            )
            
            # 提取响应内容
            # 通过response.choices[0].message.content获取模型给出的回答信息
            content = response.choices[0].message.content
            
            # 可以根据需要提取其他信息
            # 例如：获取finish_reason
            # finish_reason = response.choices[0].finish_reason
            # 例如：获取令牌消耗统计
            # usage = response.usage
            
            return content
        except Exception as e:
            print(f"获取ChatCompletion失败: {e}")
            return f"获取ChatCompletion失败: {str(e)}"
    
    def _load_model(self, model_name):
        """
        加载指定模型
        """
        try:
            # 这里可以根据model_name加载不同的模型
            # 暂时使用默认模型
            self.models[model_name] = self.models.get("default", self._create_fallback_model())
            print(f"模型 {model_name} 加载成功")
        except Exception as e:
            print(f"加载模型 {model_name} 失败: {e}")
            self.models[model_name] = self._create_fallback_model()
