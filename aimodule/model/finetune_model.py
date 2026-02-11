from transformers import AutoModelForCausalLM, AutoTokenizer, TrainingArguments, Trainer
from peft import LoraConfig, get_peft_model, prepare_model_for_kbit_training
from datasets import Dataset
import torch
import os
import tempfile

class FinetuneModel:
    def __init__(self):
        # 微调任务状态
        self.finetune_tasks = {}
    
    def finetune(self, model_name, data, epochs=3, learning_rate=1e-4, batch_size=8):
        """
        微调模型
        """
        try:
            # 生成任务ID
            task_id = f"finetune_{len(self.finetune_tasks) + 1}"
            self.finetune_tasks[task_id] = "running"
            
            # 加载基础模型和分词器
            base_model_name = model_name if model_name != "default" else "Qwen/Qwen2.5-0.5B-Instruct"
            tokenizer = AutoTokenizer.from_pretrained(base_model_name)
            model = AutoModelForCausalLM.from_pretrained(
                base_model_name,
                torch_dtype=torch.float16,
                device_map="auto"
            )
            
            # 准备模型进行微调
            model = prepare_model_for_kbit_training(model)
            
            # 配置LoRA
            lora_config = LoraConfig(
                r=16,
                lora_alpha=32,
                target_modules=["q_proj", "k_proj", "v_proj", "o_proj"],
                lora_dropout=0.05,
                bias="none",
                task_type="CAUSAL_LM"
            )
            
            # 创建PEFT模型
            model = get_peft_model(model, lora_config)
            
            # 准备数据集
            dataset = self._prepare_dataset(data, tokenizer)
            
            # 配置训练参数
            training_args = TrainingArguments(
                output_dir="./finetune_output",
                num_train_epochs=epochs,
                per_device_train_batch_size=batch_size,
                gradient_accumulation_steps=4,
                learning_rate=learning_rate,
                weight_decay=0.01,
                logging_steps=10,
                save_strategy="epoch",
                fp16=True,
                push_to_hub=False
            )
            
            # 创建Trainer
            trainer = Trainer(
                model=model,
                args=training_args,
                train_dataset=dataset,
                tokenizer=tokenizer
            )
            
            # 开始训练
            trainer.train()
            
            # 保存微调后的模型
            output_dir = os.path.join("./finetune_output", f"{model_name}_finetuned")
            os.makedirs(output_dir, exist_ok=True)
            model.save_pretrained(output_dir)
            tokenizer.save_pretrained(output_dir)
            
            # 更新任务状态
            self.finetune_tasks[task_id] = "completed"
            
            return output_dir
        except Exception as e:
            print(f"微调模型失败: {e}")
            # 更新任务状态
            if task_id in self.finetune_tasks:
                self.finetune_tasks[task_id] = "failed"
            raise
    
    def _prepare_dataset(self, data, tokenizer):
        """
        准备数据集
        """
        def format_instruction(sample):
            return f"### 指令:\n{sample['instruction']}\n### 输入:\n{sample['input']}\n### 输出:\n{sample['output']}"
        
        # 格式化数据
        formatted_data = [format_instruction(item) for item in data]
        
        # 创建Dataset
        dataset = Dataset.from_dict({"text": formatted_data})
        
        # 分词
        def tokenize_function(examples):
            return tokenizer(examples["text"], padding="max_length", truncation=True, max_length=512)
        
        tokenized_dataset = dataset.map(tokenize_function, batched=True)
        return tokenized_dataset
    
    def get_status(self, task_id):
        """
        获取微调任务状态
        """
        return self.finetune_tasks.get(task_id, "unknown")
