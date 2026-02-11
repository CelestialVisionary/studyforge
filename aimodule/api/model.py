from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from typing import List, Optional
from model.model_manager import ModelManager

router = APIRouter()

# 模型信息响应模型
class ModelInfo(BaseModel):
    name: str
    path: str
    status: str
    type: str

# 初始化模型管理器
model_manager = ModelManager()

@router.get("/list", response_model=List[ModelInfo])
def list_models():
    """
    列出所有可用模型
    """
    try:
        models = model_manager.list_models()
        return [
            ModelInfo(
                name=model["name"],
                path=model["path"],
                status=model["status"],
                type=model["type"]
            )
            for model in models
        ]
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@router.post("/load/{model_name}")
def load_model(model_name: str):
    """
    加载指定模型
    """
    try:
        result = model_manager.load_model(model_name)
        return {"status": "success", "message": result}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@router.post("/unload/{model_name}")
def unload_model(model_name: str):
    """
    卸载指定模型
    """
    try:
        result = model_manager.unload_model(model_name)
        return {"status": "success", "message": result}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
