# Python安装与配置指南

## 1. 下载Python安装包

从 [Python官网](https://www.python.org/downloads/) 下载最新版本的Python 3.8或更高版本。

## 2. 安装Python

运行下载的安装包，注意以下几点：

1. **勾选"Add Python to PATH"** - 这是关键步骤，确保Python命令能在任何目录下使用
2. 选择"Install Now"进行默认安装
3. 等待安装完成
4. 点击"Close"完成安装

## 3. 验证安装

安装完成后，打开命令提示符（CMD）或PowerShell，执行以下命令：

```bash
# 验证Python版本
python --version

# 验证pip是否可用
pip --version
```

如果显示版本信息，则安装成功。

## 4. 故障排查

### 问题："python" 不是内部或外部命令

**解决方案**：
1. 重新运行Python安装包
2. 确保勾选了"Add Python to PATH"
3. 如果已安装，手动添加Python路径到系统环境变量

### 手动添加环境变量步骤

1. 右键点击"此电脑" → "属性" → "高级系统设置" → "环境变量"
2. 在"系统变量"中找到"Path"，点击"编辑"
3. 点击"新建"，添加Python安装路径（默认为 `C:\Users\用户名\AppData\Local\Programs\Python\Python310`）
4. 点击"新建"，添加pip路径（默认为 `C:\Users\用户名\AppData\Local\Programs\Python\Python310\Scripts`）
5. 点击"确定"保存所有更改
6. 重新打开命令提示符，验证Python是否可用

## 5. 后续步骤

Python安装成功后，继续以下步骤：

1. 安装AI Module依赖：`pip install -r requirements.txt`
2. 启动AI Module服务：`python main.py`
3. 测试服务是否正常运行

如果遇到任何问题，请参考此指南或联系技术支持。
