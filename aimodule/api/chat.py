from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from typing import List, Optional
from model.chat_model import ChatModel

router = APIRouter()

# 聊天消息模型
class ChatMessage(BaseModel):
    role: str
    content: str

# 聊天请求模型
class ChatRequest(BaseModel):
    model: Optional[str] = "default"
    messages: List[ChatMessage]
    temperature: Optional[float] = 0.7
    max_tokens: Optional[int] = 1024

# 聊天响应模型
class ChatResponse(BaseModel):
    content: str

# 初始化聊天模型
chat_model = ChatModel()

@router.post("/completions", response_model=ChatResponse)
def chat_completions(request: ChatRequest):
    """
    聊天完成接口
    """
    try:
        # 转换消息格式
        messages = [
            {"role": msg.role, "content": msg.content}
            for msg in request.messages
        ]
        
        # 调用模型获取响应
        response = chat_model.generate(
            messages=messages,
            model_name=request.model,
            temperature=request.temperature,
            max_tokens=request.max_tokens
        )
        
        return ChatResponse(content=response)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@router.post("/smart-answer", response_model=ChatResponse)
def smart_answer(question: str):
    """
    智能答疑接口
    """
    try:
        # 构建系统提示
        system_message = {
            "role": "system",
            "content": "你是一个专业的智能学习助手，专注于回答学生的学习问题，准确率要求达到85%以上。请严格遵循以下要求：\n" +
                      "1. 角色定位：你是一位知识渊博、耐心细致的学习导师，熟悉各类学科知识，尤其是计算机科学、数学、物理等基础学科。\n" +
                      "2. 回答原则：\n" +
                      "   - 准确性：确保回答内容准确无误，引用权威来源，避免传播错误信息。\n" +
                      "   - 简洁性：回答要简洁明了，避免冗长的表述，突出重点。\n" +
                      "   - 易懂性：使用通俗易懂的语言，避免过于专业的术语，必要时进行解释。\n" +
                      "   - 结构化：采用分点作答的方式，使回答条理清晰，便于理解。\n" +
                      "3. 回答格式：\n" +
                      "   - 对于概念性问题：先给出明确的定义，再进行解释和举例。\n" +
                      "   - 对于解题类问题：先给出解题思路，再逐步推导，最后给出答案。\n" +
                      "   - 对于比较类问题：列出对比点，分别说明，最后总结。\n" +
                      "4. 不确定情况处理：如果对问题的答案不确定，明确说明，并建议学生查阅相关权威资料或咨询老师。\n" +
                      "5. 知识范围：只回答与学习相关的问题，对于无关问题礼貌拒绝。"
        }
        
        # 构建用户消息
        user_message = {
            "role": "user",
            "content": question
        }
        
        # 调用模型获取响应
        response = chat_model.generate(
            messages=[system_message, user_message],
            model_name="default",
            temperature=0.7,
            max_tokens=1024
        )
        
        return ChatResponse(content=response)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
