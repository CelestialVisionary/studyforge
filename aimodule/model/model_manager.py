import os
from transformers import AutoModelForCausalLM, AutoTokenizer, pipeline
from langchain_community.llms import HuggingFacePipeline
import torch

class ModelManager:
    def __init__(self):
        # 已加载的模型
        self.loaded_models = {}
        # 模型存储路径
        self.model_paths = {
            "default": "Qwen/Qwen2.5-0.5B-Instruct"
        }
        # 初始化时加载默认模型
        self._load_default_model()
    
    def _load_default_model(self):
        """
        加载默认模型
        """
        try:
            # 创建一个简单的回退模型
            class FallbackModel:
                def __call__(self, prompt, **kwargs):
                    return "模型加载失败，请检查模型配置"
            
            model_name = "default"
            model_path = self.model_paths[model_name]
            
            # 使用回退模型
            self.loaded_models[model_name] = {
                "model": FallbackModel(),
                "path": model_path,
                "type": "fallback"
            }
            print("使用回退模型，服务将快速启动")
        except Exception as e:
            print(f"加载默认模型失败: {e}")
    
    def list_models(self):
        """
        列出所有可用模型
        """
        models = []
        
        # 添加已加载的模型
        for name, info in self.loaded_models.items():
            models.append({
                "name": name,
                "path": info["path"],
                "status": "loaded",
                "type": info["type"]
            })
        
        # 添加微调后的模型
        finetune_dir = "./finetune_output"
        if os.path.exists(finetune_dir):
            for subdir in os.listdir(finetune_dir):
                subdir_path = os.path.join(finetune_dir, subdir)
                if os.path.isdir(subdir_path):
                    # 检查是否是模型目录
                    if any(file.endswith(".bin") or file.endswith(".safetensors") for file in os.listdir(subdir_path)):
                        status = "loaded" if subdir in self.loaded_models else "available"
                        models.append({
                            "name": subdir,
                            "path": subdir_path,
                            "status": status,
                            "type": "finetuned"
                        })
        
        return models
    
    def load_model(self, model_name):
        """
        加载指定模型
        """
        try:
            # 检查模型是否已加载
            if model_name in self.loaded_models:
                return f"模型 {model_name} 已加载"
            
            # 检查是否是微调后的模型
            finetune_path = os.path.join("./finetune_output", model_name)
            if os.path.exists(finetune_path):
                # 加载微调后的模型
                tokenizer = AutoTokenizer.from_pretrained(finetune_path)
                model = AutoModelForCausalLM.from_pretrained(
                    finetune_path,
                    torch_dtype=torch.float16,
                    device_map="auto"
                )
                
                pipe = pipeline(
                    "text-generation",
                    model=model,
                    tokenizer=tokenizer,
                    max_new_tokens=1024,
                    temperature=0.7
                )
                
                hf_pipeline = HuggingFacePipeline(pipeline=pipe)
                self.loaded_models[model_name] = {
                    "model": hf_pipeline,
                    "path": finetune_path,
                    "type": "finetuned"
                }
                return f"模型 {model_name} 加载成功"
            
            # 检查是否是预训练模型
            if model_name in self.model_paths:
                model_path = self.model_paths[model_name]
                tokenizer = AutoTokenizer.from_pretrained(model_path)
                model = AutoModelForCausalLM.from_pretrained(
                    model_path,
                    torch_dtype=torch.float16,
                    device_map="auto"
                )
                
                pipe = pipeline(
                    "text-generation",
                    model=model,
                    tokenizer=tokenizer,
                    max_new_tokens=1024,
                    temperature=0.7
                )
                
                hf_pipeline = HuggingFacePipeline(pipeline=pipe)
                self.loaded_models[model_name] = {
                    "model": hf_pipeline,
                    "path": model_path,
                    "type": "huggingface"
                }
                return f"模型 {model_name} 加载成功"
            
            return f"模型 {model_name} 不存在"
        except Exception as e:
            print(f"加载模型 {model_name} 失败: {e}")
            return f"加载模型 {model_name} 失败: {str(e)}"
    
    def unload_model(self, model_name):
        """
        卸载指定模型
        """
        try:
            if model_name in self.loaded_models:
                # 从内存中移除模型
                del self.loaded_models[model_name]
                # 清空GPU缓存
                torch.cuda.empty_cache()
                return f"模型 {model_name} 卸载成功"
            else:
                return f"模型 {model_name} 未加载"
        except Exception as e:
            print(f"卸载模型 {model_name} 失败: {e}")
            return f"卸载模型 {model_name} 失败: {str(e)}"
