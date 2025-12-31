# 硅谷AI链习室API文档


[TOC]






# 用户管理


## 用户登录


**接口地址**:`/api/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户通过用户名和密码进行登录验证，返回用户信息和token</p>



**请求示例**:


```javascript
{
  "username": "admin",
  "password": "123456"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|loginRequestVo|用户登录请求参数|body|true|LoginRequestVo|LoginRequestVo|
|&emsp;&emsp;username|用户名||true|string||
|&emsp;&emsp;password|登录密码||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultLoginResponseVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||LoginResponseVo|LoginResponseVo|
|&emsp;&emsp;userId|用户ID|integer(int64)||
|&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;realName|用户真实姓名|string||
|&emsp;&emsp;role|用户角色,可用值:ADMIN,TEACHER,STUDENT|string||
|&emsp;&emsp;token|登录令牌，用于后续API调用的身份验证|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"userId": 1,
		"username": "admin",
		"realName": "管理员",
		"role": "ADMIN",
		"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
	}
}
```


## 检查管理员权限


**接口地址**:`/api/user/check-admin/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>验证指定用户是否具有管理员权限</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId|用户ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultBoolean|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": true
}
```