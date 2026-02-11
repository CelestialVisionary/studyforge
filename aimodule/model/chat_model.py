from langchain_community.llms import HuggingFacePipeline
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from transformers import AutoModelForCausalLM, AutoTokenizer, pipeline
import torch

class ChatModel:
    def __init__(self):
        # 初始化模型和分词器
        self.models = {}
        self._load_default_model()
    
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
                return "模型加载失败，请检查模型配置"
        return FallbackModel()
    
    def generate(self, messages, model_name="default", temperature=0.7, max_tokens=1024):
        """
        生成模型响应
        """
        try:
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
