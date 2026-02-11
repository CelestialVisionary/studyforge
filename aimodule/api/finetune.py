from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from typing import List, Optional
from model.finetune_model import FinetuneModel

router = APIRouter()

# 微调数据模型
class FinetuneData(BaseModel):
    instruction: str
    input: Optional[str] = ""
    output: str

# 微调请求模型
class FinetuneRequest(BaseModel):
    model_name: str
    data: List[FinetuneData]
    epochs: Optional[int] = 3
    learning_rate: Optional[float] = 1e-4
    batch_size: Optional[int] = 8

# 微调响应模型
class FinetuneResponse(BaseModel):
    status: str
    model_path: str
    message: Optional[str] = ""

# 初始化微调模型
finetune_model = FinetuneModel()

@router.post("/create", response_model=FinetuneResponse)
def create_finetune(request: FinetuneRequest):
    """
    创建微调任务
    """
    try:
        # 准备微调数据
        finetune_data = [
            {
                "instruction": item.instruction,
                "input": item.input,
                "output": item.output
            }
            for item in request.data
        ]
        
        # 执行微调
        model_path = finetune_model.finetune(
            model_name=request.model_name,
            data=finetune_data,
            epochs=request.epochs,
            learning_rate=request.learning_rate,
            batch_size=request.batch_size
        )
        
        return FinetuneResponse(
            status="success",
            model_path=model_path,
            message="模型微调成功"
        )
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@router.get("/status/{task_id}")
def get_finetune_status(task_id: str):
    """
    获取微调任务状态
    """
    try:
        status = finetune_model.get_status(task_id)
        return {"status": status}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
