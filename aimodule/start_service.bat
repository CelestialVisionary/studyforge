@echo off

echo 正在启动AI Module服务...

REM 检查是否安装了Python
python --version >nul 2>&1
if %errorlevel% neq 0 (
    echo 错误：未找到Python。请先安装Python 3.8或更高版本。
    pause
    exit /b 1
)

REM 检查是否安装了依赖
if not exist "venv" (
    echo 正在创建虚拟环境...
    python -m venv venv
    
    echo 正在激活虚拟环境...
    call venv\Scripts\activate.bat
    
    echo 正在安装依赖...
    pip install -r requirements.txt
    
    if %errorlevel% neq 0 (
        echo 错误：依赖安装失败。
        pause
        exit /b 1
    )
) else (
    echo 正在激活虚拟环境...
    call venv\Scripts\activate.bat
)

REM 启动服务
echo 正在启动FastAPI服务...
echo 服务地址：http://localhost:8000
echo API文档：http://localhost:8000/docs

python main.py

pause
