from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import uvicorn
import json

from api.chat import router as chat_router
from api.finetune import router as finetune_router
from api.model import router as model_router

# 创建FastAPI应用
app = FastAPI(
    title="AI Module API",
    description="可微调大模型服务API",
    version="1.0.0"
)

# 配置CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 在生产环境中应该设置具体的后端地址
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# 注册路由
app.include_router(chat_router, prefix="/api/chat", tags=["聊天"])
app.include_router(finetune_router, prefix="/api/finetune", tags=["微调"])
app.include_router(model_router, prefix="/api/model", tags=["模型管理"])

# 健康检查接口
@app.get("/health")
def health_check():
    return {"status": "healthy"}

if __name__ == "__main__":
    uvicorn.run(
        "main:app",
        host="0.0.0.0",
        port=8000,
        reload=True
    )
