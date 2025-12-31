# 硅谷AI链习室API文档（总）


[TOC]






# 分类管理


## 获取分类列表


**接口地址**:`/api/categories`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有题目分类列表，包含每个分类下的题目数量统计</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Category|
|&emsp;&emsp;id|分类ID，唯一标识|integer(int64)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer(int64)||
|&emsp;&emsp;sort|排序序号，数字越小越靠前|integer(int32)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;count|该分类下的题目数量|integer(int64)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{}
					],
					"count": 25
				}
			],
			"count": 25
		}
	]
}
```


## 添加新分类


**接口地址**:`/api/categories`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>创建新的题目分类，支持设置父分类实现层级结构</p>



**请求示例**:


```javascript
{
  "id": 1,
  "name": "Java基础",
  "parentId": 0,
  "sort": 1,
  "children": [
    {
      "id": 1,
      "name": "Java基础",
      "parentId": 0,
      "sort": 1,
      "children": [
        {
          "id": 1,
          "name": "Java基础",
          "parentId": 0,
          "sort": 1,
          "children": [
            {}
          ],
          "count": 25
        }
      ],
      "count": 25
    }
  ],
  "count": 25
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|category|题目分类信息|body|true|Category|Category|
|&emsp;&emsp;id|分类ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;name|分类名称||false|string||
|&emsp;&emsp;parentId|父分类ID，顶级分类为0||false|integer(int64)||
|&emsp;&emsp;sort|排序序号，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构||false|array|Category|
|&emsp;&emsp;count|该分类下的题目数量||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 更新分类信息


**接口地址**:`/api/categories`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改分类的名称、描述、排序等信息</p>



**请求示例**:


```javascript
{
  "id": 1,
  "name": "Java基础",
  "parentId": 0,
  "sort": 1,
  "children": [
    {
      "id": 1,
      "name": "Java基础",
      "parentId": 0,
      "sort": 1,
      "children": [
        {
          "id": 1,
          "name": "Java基础",
          "parentId": 0,
          "sort": 1,
          "children": [
            {}
          ],
          "count": 25
        }
      ],
      "count": 25
    }
  ],
  "count": 25
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|category|题目分类信息|body|true|Category|Category|
|&emsp;&emsp;id|分类ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;name|分类名称||false|string||
|&emsp;&emsp;parentId|父分类ID，顶级分类为0||false|integer(int64)||
|&emsp;&emsp;sort|排序序号，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构||false|array|Category|
|&emsp;&emsp;count|该分类下的题目数量||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 获取分类树形结构


**接口地址**:`/api/categories/tree`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取题目分类的树形层级结构，用于前端树形组件展示</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Category|
|&emsp;&emsp;id|分类ID，唯一标识|integer(int64)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer(int64)||
|&emsp;&emsp;sort|排序序号，数字越小越靠前|integer(int32)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;count|该分类下的题目数量|integer(int64)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{}
					],
					"count": 25
				}
			],
			"count": 25
		}
	]
}
```


## 删除分类


**接口地址**:`/api/categories/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定的题目分类，注意：删除前需确保分类下没有题目</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|分类ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


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
|loginRequest|用户登录请求参数|body|true|LoginRequest|LoginRequest|
|&emsp;&emsp;username|用户名||true|string||
|&emsp;&emsp;password|登录密码||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultLoginResponse|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||LoginResponse|LoginResponse|
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


# 视频管理(管理端)


## 下架视频


**接口地址**:`/api/admin/videos/{videoId}/offline`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>管理员将已发布的视频下架</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoId|视频ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 审核视频


**接口地址**:`/api/admin/videos/{videoId}/audit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>管理员审核用户投稿的视频，可以通过或拒绝</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoId|视频ID|path|true|integer(int64)||
|status|审核状态：1-通过，2-拒绝|query|true|integer(int32)||
|reason|审核原因（拒绝时必填）|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 管理员上传视频


**接口地址**:`/api/admin/videos/upload`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>管理员直接上传视频，无需审核直接发布</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|title|视频标题|query|true|string||
|categoryId|分类ID|query|true|integer(int64)||
|uploaderName|上传者名称|query|true|string||
|videoFile|视频文件|query|true|file||
|description|视频描述|query|false|string||
|tags|标签|query|false|string||
|duration|视频时长（秒）|query|false|integer(int32)||
|coverFile|封面文件|query|false|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 获取视频列表(管理端)


**接口地址**:`/api/admin/videos`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>管理端分页获取视频列表，支持多条件筛选</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|页码，默认1|query|false|integer(int32)||
|size|每页大小，默认10|query|false|integer(int32)||
|status|状态：0-待审核，1-已发布，2-已拒绝，3-已下架|query|false|integer(int32)||
|uploaderType|上传者类型：1-用户投稿，2-管理员上传|query|false|integer(int32)||
|keyword|搜索关键字|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultIPageVideo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||IPageVideo|IPageVideo|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;records|视频信息|array|Video|
|&emsp;&emsp;&emsp;&emsp;id|视频ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;&emsp;&emsp;description|视频描述|string||
|&emsp;&emsp;&emsp;&emsp;categoryId|分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;fileUrl|视频文件URL|string||
|&emsp;&emsp;&emsp;&emsp;coverUrl|封面图片URL|string||
|&emsp;&emsp;&emsp;&emsp;duration|视频时长（秒）|integer||
|&emsp;&emsp;&emsp;&emsp;fileSize|文件大小（字节）|integer||
|&emsp;&emsp;&emsp;&emsp;uploaderName|上传者名称|string||
|&emsp;&emsp;&emsp;&emsp;uploaderType|上传者类型：1-用户投稿，2-管理员上传|integer||
|&emsp;&emsp;&emsp;&emsp;userId|上传用户ID（用户投稿时）|integer||
|&emsp;&emsp;&emsp;&emsp;adminId|管理员ID（管理员上传时）|integer||
|&emsp;&emsp;&emsp;&emsp;status|状态：0-待审核，1-已发布，2-已拒绝，3-已下架|integer||
|&emsp;&emsp;&emsp;&emsp;auditAdminId|审核管理员ID|integer||
|&emsp;&emsp;&emsp;&emsp;auditTime|审核时间|string||
|&emsp;&emsp;&emsp;&emsp;auditReason|审核原因（拒绝时）|string||
|&emsp;&emsp;&emsp;&emsp;viewCount|观看次数|integer||
|&emsp;&emsp;&emsp;&emsp;likeCount|点赞次数|integer||
|&emsp;&emsp;&emsp;&emsp;tags|标签，逗号分隔|string||
|&emsp;&emsp;&emsp;&emsp;createdAt|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updatedAt|更新时间|string||
|&emsp;&emsp;&emsp;&emsp;categoryName|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;uploaderTypeText|上传者类型文本|string||
|&emsp;&emsp;&emsp;&emsp;statusText|状态文本|string||
|&emsp;&emsp;&emsp;&emsp;auditAdminName|审核管理员名称|string||
|&emsp;&emsp;&emsp;&emsp;durationText|视频时长格式化文本|string||
|&emsp;&emsp;&emsp;&emsp;fileSizeText|文件大小格式化文本|string||
|&emsp;&emsp;&emsp;&emsp;isLiked|当前用户是否已点赞|boolean||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"size": 0,
		"records": [
			{
				"id": 1,
				"title": "Java基础语法入门",
				"description": "从零开始学习Java编程语言，掌握基本语法和编程思想",
				"categoryId": 11,
				"fileUrl": "http://localhost:9000/videos/original/java-basic-syntax.mp4",
				"coverUrl": "http://localhost:9000/videos/covers/java-basic-syntax.jpg",
				"duration": 1800,
				"fileSize": 104857600,
				"uploaderName": "技术讲师",
				"uploaderType": 2,
				"userId": 123,
				"adminId": 1,
				"status": 1,
				"auditAdminId": 1,
				"auditTime": "",
				"auditReason": "内容不符合平台规范",
				"viewCount": 156,
				"likeCount": 23,
				"tags": "Java,基础,语法,入门",
				"createdAt": "",
				"updatedAt": "",
				"categoryName": "Java基础",
				"uploaderTypeText": "管理员上传",
				"statusText": "已发布",
				"auditAdminName": "管理员",
				"durationText": "30:00",
				"fileSizeText": "100MB",
				"isLiked": true
			}
		],
		"current": 0,
		"total": 0,
		"pages": 0
	}
}
```


## 获取视频详细统计


**接口地址**:`/api/admin/videos/{videoId}/stats`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定视频的详细统计数据，包括观看、点赞等趋势</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoId|视频ID|path|true|integer(int64)||
|days|统计天数，默认30天|query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 获取视频统计


**接口地址**:`/api/admin/videos/statistics`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取视频相关的统计数据，用于仪表板展示</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 删除视频


**接口地址**:`/api/admin/videos/{videoId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>管理员删除视频（危险操作，会删除所有相关数据）</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoId|视频ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


# 考试管理


## 提交考试答案


**接口地址**:`/api/exams/{examRecordId}/submit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>学生提交考试答案，系统记录答题情况</p>



**请求示例**:


```javascript
[
  {
    "questionId": 1,
    "userAnswer": "A"
  }
]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|examRecordId|考试记录ID|path|true|integer(int32)||
|submitAnswerDtos|考试答案提交数据|body|true|array|SubmitAnswerDto|
|&emsp;&emsp;questionId|题目ID，指定回答的是哪道题||true|integer(int32)||
|&emsp;&emsp;userAnswer|用户提交的答案||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## AI自动批阅


**接口地址**:`/api/exams/{examRecordId}/grade`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>使用AI技术自动批阅试卷，特别是简答题的智能评分</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|examRecordId|考试记录ID|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultExamRecord|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||ExamRecord|ExamRecord|
|&emsp;&emsp;id|考试记录ID，唯一标识|integer(int32)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;createTime|记录创建时间|string(date-time)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|答题记录ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|试卷ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|试卷创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|试卷更新时间|string||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"createTime": "",
		"answerRecords": [
			{
				"id": 1,
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 1,
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"createTime": "",
			"updateTime": "",
			"questions": [
				{
					"id": 1,
					"title": "以下关于Java面向对象编程的说法正确的是？",
					"type": "CHOICE",
					"multi": false,
					"categoryId": 1,
					"difficulty": "MEDIUM",
					"score": 5,
					"paperScore": 10,
					"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
					"createTime": "",
					"updateTime": "",
					"choices": [
						{
							"id": 1,
							"questionId": 1,
							"content": "Java是面向对象编程语言",
							"isCorrect": true,
							"sort": 1
						}
					],
					"answer": {
						"id": 1,
						"questionId": 1,
						"answer": "正确",
						"keywords": "面向对象,封装,继承,多态"
					},
					"category": {
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{
								"id": 1,
								"name": "Java基础",
								"parentId": 0,
								"sort": 1,
								"children": [
									{}
								],
								"count": 25
							}
						],
						"count": 25
					}
				}
			]
		}
	}
}
```


## 开始考试


**接口地址**:`/api/exams/start`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>学生开始考试，创建考试记录并返回试卷内容</p>



**请求示例**:


```javascript
{
  "paperId": 1,
  "studentName": "张三"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|startExamDto|开始考试请求参数|body|true|StartExamDto|StartExamDto|
|&emsp;&emsp;paperId|试卷ID，指定要参加的考试试卷||true|integer(int32)||
|&emsp;&emsp;studentName|考生姓名||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultExamRecord|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||ExamRecord|ExamRecord|
|&emsp;&emsp;id|考试记录ID，唯一标识|integer(int32)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;createTime|记录创建时间|string(date-time)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|答题记录ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|试卷ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|试卷创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|试卷更新时间|string||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"createTime": "",
		"answerRecords": [
			{
				"id": 1,
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 1,
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"createTime": "",
			"updateTime": "",
			"questions": [
				{
					"id": 1,
					"title": "以下关于Java面向对象编程的说法正确的是？",
					"type": "CHOICE",
					"multi": false,
					"categoryId": 1,
					"difficulty": "MEDIUM",
					"score": 5,
					"paperScore": 10,
					"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
					"createTime": "",
					"updateTime": "",
					"choices": [
						{
							"id": 1,
							"questionId": 1,
							"content": "Java是面向对象编程语言",
							"isCorrect": true,
							"sort": 1
						}
					],
					"answer": {
						"id": 1,
						"questionId": 1,
						"answer": "正确",
						"keywords": "面向对象,封装,继承,多态"
					},
					"category": {
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{
								"id": 1,
								"name": "Java基础",
								"parentId": 0,
								"sort": 1,
								"children": [
									{}
								],
								"count": 25
							}
						],
						"count": 25
					}
				}
			]
		}
	}
}
```


## 查询考试记录详情


**接口地址**:`/api/exams/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定考试记录的详细信息，包括答题情况和得分</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|考试记录ID|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultExamRecord|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||ExamRecord|ExamRecord|
|&emsp;&emsp;id|考试记录ID，唯一标识|integer(int32)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;createTime|记录创建时间|string(date-time)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|答题记录ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|试卷ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|试卷创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|试卷更新时间|string||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"createTime": "",
		"answerRecords": [
			{
				"id": 1,
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 1,
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"createTime": "",
			"updateTime": "",
			"questions": [
				{
					"id": 1,
					"title": "以下关于Java面向对象编程的说法正确的是？",
					"type": "CHOICE",
					"multi": false,
					"categoryId": 1,
					"difficulty": "MEDIUM",
					"score": 5,
					"paperScore": 10,
					"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
					"createTime": "",
					"updateTime": "",
					"choices": [
						{
							"id": 1,
							"questionId": 1,
							"content": "Java是面向对象编程语言",
							"isCorrect": true,
							"sort": 1
						}
					],
					"answer": {
						"id": 1,
						"questionId": 1,
						"answer": "正确",
						"keywords": "面向对象,封装,继承,多态"
					},
					"category": {
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{
								"id": 1,
								"name": "Java基础",
								"parentId": 0,
								"sort": 1,
								"children": [
									{}
								],
								"count": 25
							}
						],
						"count": 25
					}
				}
			]
		}
	}
}
```


## 获取考试记录列表


**接口地址**:`/api/exams/records`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有考试记录列表，包含基本信息和成绩</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListExamRecord|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|ExamRecord|
|&emsp;&emsp;id|考试记录ID，唯一标识|integer(int32)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;createTime|记录创建时间|string(date-time)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|答题记录ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|试卷ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|试卷创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|试卷更新时间|string||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"examId": 1,
			"studentName": "张三",
			"score": 85,
			"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
			"startTime": "",
			"endTime": "",
			"status": "已批阅",
			"windowSwitches": 2,
			"createTime": "",
			"answerRecords": [
				{
					"id": 1,
					"examRecordId": 1,
					"questionId": 5,
					"userAnswer": "A",
					"score": 5,
					"isCorrect": 1,
					"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
				}
			],
			"paper": {
				"id": 1,
				"name": "Java基础知识测试",
				"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
				"status": "PUBLISHED",
				"totalScore": 100,
				"questionCount": 20,
				"duration": 120,
				"createTime": "",
				"updateTime": "",
				"questions": [
					{
						"id": 1,
						"title": "以下关于Java面向对象编程的说法正确的是？",
						"type": "CHOICE",
						"multi": false,
						"categoryId": 1,
						"difficulty": "MEDIUM",
						"score": 5,
						"paperScore": 10,
						"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
						"createTime": "",
						"updateTime": "",
						"choices": [
							{
								"id": 1,
								"questionId": 1,
								"content": "Java是面向对象编程语言",
								"isCorrect": true,
								"sort": 1
							}
						],
						"answer": {
							"id": 1,
							"questionId": 1,
							"answer": "正确",
							"keywords": "面向对象,封装,继承,多态"
						},
						"category": {
							"id": 1,
							"name": "Java基础",
							"parentId": 0,
							"sort": 1,
							"children": [
								{
									"id": 1,
									"name": "Java基础",
									"parentId": 0,
									"sort": 1,
									"children": [
										{}
									],
									"count": 25
								}
							],
							"count": 25
						}
					}
				]
			}
		}
	]
}
```


# 公告管理


## 更新公告信息


**接口地址**:`/api/notices/update`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改公告的内容、标题、类型等信息</p>



**请求示例**:


```javascript
{
  "id": 1,
  "title": "系统维护通知",
  "content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
  "type": "SYSTEM",
  "priority": 1,
  "isActive": true,
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|notice|系统公告信息|body|true|Notice|Notice|
|&emsp;&emsp;id|公告ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;title|公告标题||false|string||
|&emsp;&emsp;content|公告内容详情||false|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE||false|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||
|&emsp;&emsp;createTime|公告创建时间||false|string(date-time)||
|&emsp;&emsp;updateTime|公告更新时间||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 切换公告状态


**接口地址**:`/api/notices/toggle/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>启用或禁用指定的公告，禁用后不会在前台显示</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|公告ID|path|true|integer(int64)||
|isActive|是否启用，true为启用，false为禁用|query|true|boolean||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 发布新公告


**接口地址**:`/api/notices/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>创建并发布新的系统公告</p>



**请求示例**:


```javascript
{
  "id": 1,
  "title": "系统维护通知",
  "content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
  "type": "SYSTEM",
  "priority": 1,
  "isActive": true,
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|notice|系统公告信息|body|true|Notice|Notice|
|&emsp;&emsp;id|公告ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;title|公告标题||false|string||
|&emsp;&emsp;content|公告内容详情||false|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE||false|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||
|&emsp;&emsp;createTime|公告创建时间||false|string(date-time)||
|&emsp;&emsp;updateTime|公告更新时间||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 根据ID获取公告


**接口地址**:`/api/notices/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据公告ID获取单个公告的详细信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|公告ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultNotice|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Notice|Notice|
|&emsp;&emsp;id|公告ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||
|&emsp;&emsp;createTime|公告创建时间|string(date-time)||
|&emsp;&emsp;updateTime|公告更新时间|string(date-time)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"title": "系统维护通知",
		"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
		"type": "SYSTEM",
		"priority": 1,
		"isActive": true,
		"createTime": "",
		"updateTime": ""
	}
}
```


## 获取所有公告


**接口地址**:`/api/notices/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有公告列表，包括启用和禁用的，供管理后台使用</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListNotice|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Notice|
|&emsp;&emsp;id|公告ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||
|&emsp;&emsp;createTime|公告创建时间|string(date-time)||
|&emsp;&emsp;updateTime|公告更新时间|string(date-time)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "系统维护通知",
			"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
			"type": "SYSTEM",
			"priority": 1,
			"isActive": true,
			"createTime": "",
			"updateTime": ""
		}
	]
}
```


## 获取最新公告


**接口地址**:`/api/notices/latest`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取最新发布的公告列表，用于首页推荐展示</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|limit|限制数量|query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListNotice|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Notice|
|&emsp;&emsp;id|公告ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||
|&emsp;&emsp;createTime|公告创建时间|string(date-time)||
|&emsp;&emsp;updateTime|公告更新时间|string(date-time)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "系统维护通知",
			"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
			"type": "SYSTEM",
			"priority": 1,
			"isActive": true,
			"createTime": "",
			"updateTime": ""
		}
	]
}
```


## 获取启用的公告


**接口地址**:`/api/notices/active`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取状态为启用的公告列表，供前台首页展示使用</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListNotice|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Notice|
|&emsp;&emsp;id|公告ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||
|&emsp;&emsp;createTime|公告创建时间|string(date-time)||
|&emsp;&emsp;updateTime|公告更新时间|string(date-time)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "系统维护通知",
			"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
			"type": "SYSTEM",
			"priority": 1,
			"isActive": true,
			"createTime": "",
			"updateTime": ""
		}
	]
}
```


## 删除公告


**接口地址**:`/api/notices/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除指定的公告</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|公告ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


# 试卷管理


## 获取试卷详情


**接口地址**:`/api/papers/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取试卷的详细信息，包括试卷基本信息和包含的所有题目</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|试卷ID|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultPaper|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Paper|Paper|
|&emsp;&emsp;id|试卷ID，唯一标识|integer(int32)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;createTime|试卷创建时间|string(date-time)||
|&emsp;&emsp;updateTime|试卷更新时间|string(date-time)||
|&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"createTime": "",
		"updateTime": "",
		"questions": [
			{
				"id": 1,
				"title": "以下关于Java面向对象编程的说法正确的是？",
				"type": "CHOICE",
				"multi": false,
				"categoryId": 1,
				"difficulty": "MEDIUM",
				"score": 5,
				"paperScore": 10,
				"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
				"createTime": "",
				"updateTime": "",
				"choices": [
					{
						"id": 1,
						"questionId": 1,
						"content": "Java是面向对象编程语言",
						"isCorrect": true,
						"sort": 1
					}
				],
				"answer": {
					"id": 1,
					"questionId": 1,
					"answer": "正确",
					"keywords": "面向对象,封装,继承,多态"
				},
				"category": {
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{
							"id": 1,
							"name": "Java基础",
							"parentId": 0,
							"sort": 1,
							"children": [
								{}
							],
							"count": 25
						}
					],
					"count": 25
				}
			}
		]
	}
}
```


## 更新试卷信息


**接口地址**:`/api/papers/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>更新试卷的基本信息和题目配置</p>



**请求示例**:


```javascript
{
  "name": "Java基础知识测试卷",
  "description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
  "duration": 120,
  "questions": {
    "1": 5,
    "2": 10,
    "3": 15
  }
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|试卷ID|path|true|integer(int32)||
|paperDto|试卷创建请求参数|body|true|PaperDto|PaperDto|
|&emsp;&emsp;name|试卷名称||true|string||
|&emsp;&emsp;description|试卷描述说明||false|string||
|&emsp;&emsp;duration|考试时长（分钟）||false|integer(int32)||
|&emsp;&emsp;questions|试卷题目配置，Key为题目ID，Value为该题分数||false|object||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultPaper|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Paper|Paper|
|&emsp;&emsp;id|试卷ID，唯一标识|integer(int32)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;createTime|试卷创建时间|string(date-time)||
|&emsp;&emsp;updateTime|试卷更新时间|string(date-time)||
|&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"createTime": "",
		"updateTime": "",
		"questions": [
			{
				"id": 1,
				"title": "以下关于Java面向对象编程的说法正确的是？",
				"type": "CHOICE",
				"multi": false,
				"categoryId": 1,
				"difficulty": "MEDIUM",
				"score": 5,
				"paperScore": 10,
				"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
				"createTime": "",
				"updateTime": "",
				"choices": [
					{
						"id": 1,
						"questionId": 1,
						"content": "Java是面向对象编程语言",
						"isCorrect": true,
						"sort": 1
					}
				],
				"answer": {
					"id": 1,
					"questionId": 1,
					"answer": "正确",
					"keywords": "面向对象,封装,继承,多态"
				},
				"category": {
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{
							"id": 1,
							"name": "Java基础",
							"parentId": 0,
							"sort": 1,
							"children": [
								{}
							],
							"count": 25
						}
					],
					"count": 25
				}
			}
		]
	}
}
```


## 删除试卷


**接口地址**:`/api/papers/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定的试卷，注意：已发布的试卷不能删除</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|试卷ID|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 手动创建试卷


**接口地址**:`/api/papers`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>通过手动选择题目的方式创建试卷</p>



**请求示例**:


```javascript
{
  "name": "Java基础知识测试卷",
  "description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
  "duration": 120,
  "questions": {
    "1": 5,
    "2": 10,
    "3": 15
  }
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|paperDto|试卷创建请求参数|body|true|PaperDto|PaperDto|
|&emsp;&emsp;name|试卷名称||true|string||
|&emsp;&emsp;description|试卷描述说明||false|string||
|&emsp;&emsp;duration|考试时长（分钟）||false|integer(int32)||
|&emsp;&emsp;questions|试卷题目配置，Key为题目ID，Value为该题分数||false|object||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultPaper|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Paper|Paper|
|&emsp;&emsp;id|试卷ID，唯一标识|integer(int32)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;createTime|试卷创建时间|string(date-time)||
|&emsp;&emsp;updateTime|试卷更新时间|string(date-time)||
|&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"createTime": "",
		"updateTime": "",
		"questions": [
			{
				"id": 1,
				"title": "以下关于Java面向对象编程的说法正确的是？",
				"type": "CHOICE",
				"multi": false,
				"categoryId": 1,
				"difficulty": "MEDIUM",
				"score": 5,
				"paperScore": 10,
				"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
				"createTime": "",
				"updateTime": "",
				"choices": [
					{
						"id": 1,
						"questionId": 1,
						"content": "Java是面向对象编程语言",
						"isCorrect": true,
						"sort": 1
					}
				],
				"answer": {
					"id": 1,
					"questionId": 1,
					"answer": "正确",
					"keywords": "面向对象,封装,继承,多态"
				},
				"category": {
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{
							"id": 1,
							"name": "Java基础",
							"parentId": 0,
							"sort": 1,
							"children": [
								{}
							],
							"count": 25
						}
					],
					"count": 25
				}
			}
		]
	}
}
```


## 更新试卷状态


**接口地址**:`/api/papers/{id}/status`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>修改试卷状态：发布试卷供学生考试或停止试卷禁止考试</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|试卷ID|path|true|integer(int32)||
|status|新的状态，可选值：PUBLISHED/STOPPED|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## AI智能组卷


**接口地址**:`/api/papers/ai`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>基于设定的规则（题型分布、难度配比等）使用AI自动生成试卷</p>



**请求示例**:


```javascript
{
  "name": "Java高级编程考试",
  "description": "本试卷由AI根据组卷规则自动生成，涵盖多个知识点",
  "duration": 90,
  "rules": [
    {
      "type": "CHOICE",
      "categoryIds": "[1,2,3]",
      "count": 10,
      "score": 5
    }
  ]
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|aiPaperDto|AI智能组卷请求参数|body|true|AiPaperDto|AiPaperDto|
|&emsp;&emsp;name|试卷名称||true|string||
|&emsp;&emsp;description|试卷描述||false|string||
|&emsp;&emsp;duration|考试时长（分钟）||false|integer(int32)||
|&emsp;&emsp;rules|AI组卷规则配置||true|array|RuleDto|
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT,CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;&emsp;&emsp;categoryIds|指定的题目分类ID列表，为空则不限制分类||false|array|integer|
|&emsp;&emsp;&emsp;&emsp;count|需要抽取的题目数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;score|每道题目的分数||false|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultPaper|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Paper|Paper|
|&emsp;&emsp;id|试卷ID，唯一标识|integer(int32)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;createTime|试卷创建时间|string(date-time)||
|&emsp;&emsp;updateTime|试卷更新时间|string(date-time)||
|&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"createTime": "",
		"updateTime": "",
		"questions": [
			{
				"id": 1,
				"title": "以下关于Java面向对象编程的说法正确的是？",
				"type": "CHOICE",
				"multi": false,
				"categoryId": 1,
				"difficulty": "MEDIUM",
				"score": 5,
				"paperScore": 10,
				"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
				"createTime": "",
				"updateTime": "",
				"choices": [
					{
						"id": 1,
						"questionId": 1,
						"content": "Java是面向对象编程语言",
						"isCorrect": true,
						"sort": 1
					}
				],
				"answer": {
					"id": 1,
					"questionId": 1,
					"answer": "正确",
					"keywords": "面向对象,封装,继承,多态"
				},
				"category": {
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{
							"id": 1,
							"name": "Java基础",
							"parentId": 0,
							"sort": 1,
							"children": [
								{}
							],
							"count": 25
						}
					],
					"count": 25
				}
			}
		]
	}
}
```


## 获取试卷列表


**接口地址**:`/api/papers/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>支持按名称模糊搜索和状态筛选的试卷列表查询</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|name|试卷名称，支持模糊查询|query|false|string||
|status|试卷状态，可选值：DRAFT/PUBLISHED/STOPPED|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListPaper|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Paper|
|&emsp;&emsp;id|试卷ID，唯一标识|integer(int32)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;createTime|试卷创建时间|string(date-time)||
|&emsp;&emsp;updateTime|试卷更新时间|string(date-time)||
|&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"createTime": "",
			"updateTime": "",
			"questions": [
				{
					"id": 1,
					"title": "以下关于Java面向对象编程的说法正确的是？",
					"type": "CHOICE",
					"multi": false,
					"categoryId": 1,
					"difficulty": "MEDIUM",
					"score": 5,
					"paperScore": 10,
					"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
					"createTime": "",
					"updateTime": "",
					"choices": [
						{
							"id": 1,
							"questionId": 1,
							"content": "Java是面向对象编程语言",
							"isCorrect": true,
							"sort": 1
						}
					],
					"answer": {
						"id": 1,
						"questionId": 1,
						"answer": "正确",
						"keywords": "面向对象,封装,继承,多态"
					},
					"category": {
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{
								"id": 1,
								"name": "Java基础",
								"parentId": 0,
								"sort": 1,
								"children": [
									{}
								],
								"count": 25
							}
						],
						"count": 25
					}
				}
			]
		}
	]
}
```


# 考试记录管理


## 获取考试记录详情


**接口地址**:`/api/exam-records/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据记录ID获取考试记录的详细信息，包括试卷内容和答题情况</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|考试记录ID|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultExamRecord|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||ExamRecord|ExamRecord|
|&emsp;&emsp;id|考试记录ID，唯一标识|integer(int32)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;createTime|记录创建时间|string(date-time)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|答题记录ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|试卷ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|试卷创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|试卷更新时间|string||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"createTime": "",
		"answerRecords": [
			{
				"id": 1,
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 1,
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"createTime": "",
			"updateTime": "",
			"questions": [
				{
					"id": 1,
					"title": "以下关于Java面向对象编程的说法正确的是？",
					"type": "CHOICE",
					"multi": false,
					"categoryId": 1,
					"difficulty": "MEDIUM",
					"score": 5,
					"paperScore": 10,
					"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
					"createTime": "",
					"updateTime": "",
					"choices": [
						{
							"id": 1,
							"questionId": 1,
							"content": "Java是面向对象编程语言",
							"isCorrect": true,
							"sort": 1
						}
					],
					"answer": {
						"id": 1,
						"questionId": 1,
						"answer": "正确",
						"keywords": "面向对象,封装,继承,多态"
					},
					"category": {
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{
								"id": 1,
								"name": "Java基础",
								"parentId": 0,
								"sort": 1,
								"children": [
									{}
								],
								"count": 25
							}
						],
						"count": 25
					}
				}
			]
		}
	}
}
```


## 删除考试记录


**接口地址**:`/api/exam-records/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除指定的考试记录</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|考试记录ID|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 获取考试排行榜


**接口地址**:`/api/exam-records/ranking`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取考试成绩排行榜，支持按试卷筛选和限制显示数量，使用优化的SQL关联查询提升性能</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|paperId|试卷ID，可选，不传则显示所有试卷的排行|query|false|integer(int32)||
|limit|显示数量限制，可选，不传则返回所有记录|query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListExamRankingVO|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|ExamRankingVO|
|&emsp;&emsp;id|考试记录ID|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;examId|试卷ID|integer(int32)||
|&emsp;&emsp;paperName|试卷名称|string||
|&emsp;&emsp;paperTotalScore|试卷总分|number||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;duration|考试用时（分钟）|integer(int64)||
|&emsp;&emsp;paper|试卷信息对象（兼容前端）|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"studentName": "张三",
			"score": 85,
			"examId": 1,
			"paperName": "Java基础知识测试",
			"paperTotalScore": 100,
			"startTime": "",
			"endTime": "",
			"duration": 120,
			"paper": {}
		}
	]
}
```


## 分页查询考试记录


**接口地址**:`/api/exam-records/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>支持多条件筛选的考试记录分页查询，包括按姓名、状态、时间范围等筛选</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码，从1开始|query|false|integer(int32)||
|size|每页显示数量|query|false|integer(int32)||
|studentName|学生姓名筛选条件|query|false|string||
|studentNumber|学号筛选条件|query|false|string||
|status|考试状态，0-进行中，1-已完成，2-已批阅|query|false|integer(int32)||
|startDate|开始日期，格式：yyyy-MM-dd|query|false|string||
|endDate|结束日期，格式：yyyy-MM-dd|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultPageExamRecord|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||PageExamRecord|PageExamRecord|
|&emsp;&emsp;records|考试记录信息|array|ExamRecord|
|&emsp;&emsp;&emsp;&emsp;id|考试记录ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer||
|&emsp;&emsp;&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;&emsp;&emsp;score|考试得分|integer||
|&emsp;&emsp;&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;&emsp;&emsp;startTime|考试开始时间|string||
|&emsp;&emsp;&emsp;&emsp;endTime|考试结束时间|string||
|&emsp;&emsp;&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|记录创建时间|string||
|&emsp;&emsp;&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答题记录ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|试卷ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|试卷创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime|试卷更新时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;optimizeJoinOfCountSql||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;countId||string||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"records": [
			{
				"id": 1,
				"examId": 1,
				"studentName": "张三",
				"score": 85,
				"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
				"startTime": "",
				"endTime": "",
				"status": "已批阅",
				"windowSwitches": 2,
				"createTime": "",
				"answerRecords": [
					{
						"id": 1,
						"examRecordId": 1,
						"questionId": 5,
						"userAnswer": "A",
						"score": 5,
						"isCorrect": 1,
						"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
					}
				],
				"paper": {
					"id": 1,
					"name": "Java基础知识测试",
					"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
					"status": "PUBLISHED",
					"totalScore": 100,
					"questionCount": 20,
					"duration": 120,
					"createTime": "",
					"updateTime": "",
					"questions": [
						{
							"id": 1,
							"title": "以下关于Java面向对象编程的说法正确的是？",
							"type": "CHOICE",
							"multi": false,
							"categoryId": 1,
							"difficulty": "MEDIUM",
							"score": 5,
							"paperScore": 10,
							"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
							"createTime": "",
							"updateTime": "",
							"choices": [
								{
									"id": 1,
									"questionId": 1,
									"content": "Java是面向对象编程语言",
									"isCorrect": true,
									"sort": 1
								}
							],
							"answer": {
								"id": 1,
								"questionId": 1,
								"answer": "正确",
								"keywords": "面向对象,封装,继承,多态"
							},
							"category": {
								"id": 1,
								"name": "Java基础",
								"parentId": 0,
								"sort": 1,
								"children": [
									{
										"id": 1,
										"name": "Java基础",
										"parentId": 0,
										"sort": 1,
										"children": [
											{}
										],
										"count": 25
									}
								],
								"count": 25
							}
						}
					]
				}
			}
		],
		"total": 0,
		"size": 0,
		"current": 0,
		"orders": [
			{
				"column": "",
				"asc": true
			}
		],
		"optimizeCountSql": true,
		"searchCount": true,
		"optimizeJoinOfCountSql": true,
		"maxLimit": 0,
		"countId": "",
		"pages": 0
	}
}
```


# 题目管理


## 根据ID查询题目详情


**接口地址**:`/api/questions/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定ID的题目完整信息，包括题目内容、选项、答案等详细数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|题目ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Question|Question|
|&emsp;&emsp;id|题目ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;createTime|题目创建时间|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间|string(date-time)||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"title": "以下关于Java面向对象编程的说法正确的是？",
		"type": "CHOICE",
		"multi": false,
		"categoryId": 1,
		"difficulty": "MEDIUM",
		"score": 5,
		"paperScore": 10,
		"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
		"createTime": "",
		"updateTime": "",
		"choices": [
			{
				"id": 1,
				"questionId": 1,
				"content": "Java是面向对象编程语言",
				"isCorrect": true,
				"sort": 1
			}
		],
		"answer": {
			"id": 1,
			"questionId": 1,
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		},
		"category": {
			"id": 1,
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{}
					],
					"count": 25
				}
			],
			"count": 25
		}
	}
}
```


## 更新题目信息


**接口地址**:`/api/questions/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改指定题目的内容、选项、答案等信息</p>



**请求示例**:


```javascript
{
  "id": 1,
  "title": "以下关于Java面向对象编程的说法正确的是？",
  "type": "CHOICE",
  "multi": false,
  "categoryId": 1,
  "difficulty": "MEDIUM",
  "score": 5,
  "paperScore": 10,
  "analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
  "createTime": "",
  "updateTime": "",
  "choices": [
    {
      "id": 1,
      "questionId": 1,
      "content": "Java是面向对象编程语言",
      "isCorrect": true,
      "sort": 1
    }
  ],
  "answer": {
    "id": 1,
    "questionId": 1,
    "answer": "正确",
    "keywords": "面向对象,封装,继承,多态"
  },
  "category": {
    "id": 1,
    "name": "Java基础",
    "parentId": 0,
    "sort": 1,
    "children": [
      {
        "id": 1,
        "name": "Java基础",
        "parentId": 0,
        "sort": 1,
        "children": [
          {}
        ],
        "count": 25
      }
    ],
    "count": 25
  }
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|题目ID|path|true|integer(int64)||
|question|题目信息|body|true|Question|Question|
|&emsp;&emsp;id|题目ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;title|题目标题内容||false|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效||false|boolean||
|&emsp;&emsp;categoryId|题目分类ID||false|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD||false|string||
|&emsp;&emsp;score|题目默认分值||false|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值||false|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明||false|string||
|&emsp;&emsp;createTime|题目创建时间||false|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间||false|string(date-time)||
|&emsp;&emsp;choices|题目选项信息||false|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识||false|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容||false|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案||false|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号||false|integer||
|&emsp;&emsp;answer|题目答案信息||false|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识||false|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容||false|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分||false|string||
|&emsp;&emsp;category|题目分类信息||false|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识||false|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称||false|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0||false|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前||false|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构||false|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量||false|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Question|Question|
|&emsp;&emsp;id|题目ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;createTime|题目创建时间|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间|string(date-time)||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"title": "以下关于Java面向对象编程的说法正确的是？",
		"type": "CHOICE",
		"multi": false,
		"categoryId": 1,
		"difficulty": "MEDIUM",
		"score": 5,
		"paperScore": 10,
		"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
		"createTime": "",
		"updateTime": "",
		"choices": [
			{
				"id": 1,
				"questionId": 1,
				"content": "Java是面向对象编程语言",
				"isCorrect": true,
				"sort": 1
			}
		],
		"answer": {
			"id": 1,
			"questionId": 1,
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		},
		"category": {
			"id": 1,
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{}
					],
					"count": 25
				}
			],
			"count": 25
		}
	}
}
```


## 删除题目


**接口地址**:`/api/questions/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除指定的题目，包括关联的选项和答案数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|题目ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 创建新题目


**接口地址**:`/api/questions`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加新的考试题目，支持选择题、判断题、简答题等多种题型</p>



**请求示例**:


```javascript
{
  "id": 1,
  "title": "以下关于Java面向对象编程的说法正确的是？",
  "type": "CHOICE",
  "multi": false,
  "categoryId": 1,
  "difficulty": "MEDIUM",
  "score": 5,
  "paperScore": 10,
  "analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
  "createTime": "",
  "updateTime": "",
  "choices": [
    {
      "id": 1,
      "questionId": 1,
      "content": "Java是面向对象编程语言",
      "isCorrect": true,
      "sort": 1
    }
  ],
  "answer": {
    "id": 1,
    "questionId": 1,
    "answer": "正确",
    "keywords": "面向对象,封装,继承,多态"
  },
  "category": {
    "id": 1,
    "name": "Java基础",
    "parentId": 0,
    "sort": 1,
    "children": [
      {
        "id": 1,
        "name": "Java基础",
        "parentId": 0,
        "sort": 1,
        "children": [
          {}
        ],
        "count": 25
      }
    ],
    "count": 25
  }
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|question|题目信息|body|true|Question|Question|
|&emsp;&emsp;id|题目ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;title|题目标题内容||false|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效||false|boolean||
|&emsp;&emsp;categoryId|题目分类ID||false|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD||false|string||
|&emsp;&emsp;score|题目默认分值||false|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值||false|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明||false|string||
|&emsp;&emsp;createTime|题目创建时间||false|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间||false|string(date-time)||
|&emsp;&emsp;choices|题目选项信息||false|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识||false|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容||false|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案||false|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号||false|integer||
|&emsp;&emsp;answer|题目答案信息||false|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识||false|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容||false|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分||false|string||
|&emsp;&emsp;category|题目分类信息||false|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识||false|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称||false|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0||false|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前||false|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构||false|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量||false|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Question|Question|
|&emsp;&emsp;id|题目ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;createTime|题目创建时间|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间|string(date-time)||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"title": "以下关于Java面向对象编程的说法正确的是？",
		"type": "CHOICE",
		"multi": false,
		"categoryId": 1,
		"difficulty": "MEDIUM",
		"score": 5,
		"paperScore": 10,
		"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
		"createTime": "",
		"updateTime": "",
		"choices": [
			{
				"id": 1,
				"questionId": 1,
				"content": "Java是面向对象编程语言",
				"isCorrect": true,
				"sort": 1
			}
		],
		"answer": {
			"id": 1,
			"questionId": 1,
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		},
		"category": {
			"id": 1,
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{}
					],
					"count": 25
				}
			],
			"count": 25
		}
	}
}
```


## 刷新热门题目缓存


**接口地址**:`/api/questions/popular/refresh`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>管理员功能，重置或初始化热门题目的访问计数</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultInteger|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": 0
}
```


## 随机获取题目


**接口地址**:`/api/questions/random`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>按指定条件随机抽取题目，用于智能组卷功能</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|count|需要获取的题目数量|query|false|integer(int32)||
|categoryId|分类ID限制条件，可选|query|false|integer(int64)||
|difficulty|难度限制条件，可选值：EASY/MEDIUM/HARD|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Question|
|&emsp;&emsp;id|题目ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;createTime|题目创建时间|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间|string(date-time)||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "以下关于Java面向对象编程的说法正确的是？",
			"type": "CHOICE",
			"multi": false,
			"categoryId": 1,
			"difficulty": "MEDIUM",
			"score": 5,
			"paperScore": 10,
			"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
			"createTime": "",
			"updateTime": "",
			"choices": [
				{
					"id": 1,
					"questionId": 1,
					"content": "Java是面向对象编程语言",
					"isCorrect": true,
					"sort": 1
				}
			],
			"answer": {
				"id": 1,
				"questionId": 1,
				"answer": "正确",
				"keywords": "面向对象,封装,继承,多态"
			},
			"category": {
				"id": 1,
				"name": "Java基础",
				"parentId": 0,
				"sort": 1,
				"children": [
					{
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{}
						],
						"count": 25
					}
				],
				"count": 25
			}
		}
	]
}
```


## 获取热门题目


**接口地址**:`/api/questions/popular`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取访问次数最多的热门题目，用于首页推荐展示</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|size|返回题目数量|query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Question|
|&emsp;&emsp;id|题目ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;createTime|题目创建时间|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间|string(date-time)||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "以下关于Java面向对象编程的说法正确的是？",
			"type": "CHOICE",
			"multi": false,
			"categoryId": 1,
			"difficulty": "MEDIUM",
			"score": 5,
			"paperScore": 10,
			"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
			"createTime": "",
			"updateTime": "",
			"choices": [
				{
					"id": 1,
					"questionId": 1,
					"content": "Java是面向对象编程语言",
					"isCorrect": true,
					"sort": 1
				}
			],
			"answer": {
				"id": 1,
				"questionId": 1,
				"answer": "正确",
				"keywords": "面向对象,封装,继承,多态"
			},
			"category": {
				"id": 1,
				"name": "Java基础",
				"parentId": 0,
				"sort": 1,
				"children": [
					{
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{}
						],
						"count": 25
					}
				],
				"count": 25
			}
		}
	]
}
```


## 分页查询题目列表


**接口地址**:`/api/questions/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>支持按分类、难度、题型、关键词进行多条件筛选的分页查询</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码，从1开始|query|false|integer(int32)||
|size|每页显示数量|query|false|integer(int32)||
|categoryId|分类ID筛选条件|query|false|integer(int64)||
|difficulty|难度筛选条件，可选值：EASY/MEDIUM/HARD|query|false|string||
|type|题型筛选条件，可选值：CHOICE/JUDGE/TEXT|query|false|string||
|keyword|关键词搜索，对题目标题进行模糊查询|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultPageQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||PageQuestion|PageQuestion|
|&emsp;&emsp;records|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;id|题目ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;createTime|题目创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updateTime|题目更新时间|string||
|&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;optimizeJoinOfCountSql||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;countId||string||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"records": [
			{
				"id": 1,
				"title": "以下关于Java面向对象编程的说法正确的是？",
				"type": "CHOICE",
				"multi": false,
				"categoryId": 1,
				"difficulty": "MEDIUM",
				"score": 5,
				"paperScore": 10,
				"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
				"createTime": "",
				"updateTime": "",
				"choices": [
					{
						"id": 1,
						"questionId": 1,
						"content": "Java是面向对象编程语言",
						"isCorrect": true,
						"sort": 1
					}
				],
				"answer": {
					"id": 1,
					"questionId": 1,
					"answer": "正确",
					"keywords": "面向对象,封装,继承,多态"
				},
				"category": {
					"id": 1,
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{
							"id": 1,
							"name": "Java基础",
							"parentId": 0,
							"sort": 1,
							"children": [
								{}
							],
							"count": 25
						}
					],
					"count": 25
				}
			}
		],
		"total": 0,
		"size": 0,
		"current": 0,
		"orders": [
			{
				"column": "",
				"asc": true
			}
		],
		"optimizeCountSql": true,
		"searchCount": true,
		"optimizeJoinOfCountSql": true,
		"maxLimit": 0,
		"countId": "",
		"pages": 0
	}
}
```


## 按难度查询题目


**接口地址**:`/api/questions/difficulty/{difficulty}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定难度等级的题目列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|difficulty|难度等级，可选值：EASY(简单)/MEDIUM(中等)/HARD(困难)|path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Question|
|&emsp;&emsp;id|题目ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;createTime|题目创建时间|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间|string(date-time)||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "以下关于Java面向对象编程的说法正确的是？",
			"type": "CHOICE",
			"multi": false,
			"categoryId": 1,
			"difficulty": "MEDIUM",
			"score": 5,
			"paperScore": 10,
			"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
			"createTime": "",
			"updateTime": "",
			"choices": [
				{
					"id": 1,
					"questionId": 1,
					"content": "Java是面向对象编程语言",
					"isCorrect": true,
					"sort": 1
				}
			],
			"answer": {
				"id": 1,
				"questionId": 1,
				"answer": "正确",
				"keywords": "面向对象,封装,继承,多态"
			},
			"category": {
				"id": 1,
				"name": "Java基础",
				"parentId": 0,
				"sort": 1,
				"children": [
					{
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{}
						],
						"count": 25
					}
				],
				"count": 25
			}
		}
	]
}
```


## 按分类查询题目


**接口地址**:`/api/questions/category/{categoryId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定分类下的所有题目列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryId|分类ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListQuestion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Question|
|&emsp;&emsp;id|题目ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;createTime|题目创建时间|string(date-time)||
|&emsp;&emsp;updateTime|题目更新时间|string(date-time)||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|选项ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|答案ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|分类ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父分类ID，顶级分类为0|integer||
|&emsp;&emsp;&emsp;&emsp;sort|排序序号，数字越小越靠前|integer||
|&emsp;&emsp;&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|Category|
|&emsp;&emsp;&emsp;&emsp;count|该分类下的题目数量|integer||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "以下关于Java面向对象编程的说法正确的是？",
			"type": "CHOICE",
			"multi": false,
			"categoryId": 1,
			"difficulty": "MEDIUM",
			"score": 5,
			"paperScore": 10,
			"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
			"createTime": "",
			"updateTime": "",
			"choices": [
				{
					"id": 1,
					"questionId": 1,
					"content": "Java是面向对象编程语言",
					"isCorrect": true,
					"sort": 1
				}
			],
			"answer": {
				"id": 1,
				"questionId": 1,
				"answer": "正确",
				"keywords": "面向对象,封装,继承,多态"
			},
			"category": {
				"id": 1,
				"name": "Java基础",
				"parentId": 0,
				"sort": 1,
				"children": [
					{
						"id": 1,
						"name": "Java基础",
						"parentId": 0,
						"sort": 1,
						"children": [
							{}
						],
						"count": 25
					}
				],
				"count": 25
			}
		}
	]
}
```


# 视频管理(用户端)


## 记录视频观看


**接口地址**:`/api/videos/{videoId}/view`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>记录用户观看视频的行为和时长</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoId|视频ID|path|true|integer(int64)||
|viewDuration|观看时长（秒）|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 切换点赞状态


**接口地址**:`/api/videos/{videoId}/like`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>切换用户对视频的点赞状态，返回当前状态</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoId|视频ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 用户投稿视频


**接口地址**:`/api/videos/submit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户上传视频进行投稿，需要等待审核</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|title|视频标题|query|true|string||
|categoryId|分类ID|query|true|integer(int64)||
|uploaderName|上传者名称|query|true|string||
|duration|视频时长（秒）|query|true|integer(int32)||
|videoFile|视频文件|query|true|file||
|description|视频描述|query|false|string||
|tags|标签|query|false|string||
|coverFile|封面文件|query|false|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 获取视频列表


**接口地址**:`/api/videos`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取已发布的视频列表，支持按分类和关键字筛选</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|页码，默认1|query|false|integer(int32)||
|size|每页大小，默认10|query|false|integer(int32)||
|categoryId|分类ID|query|false|integer(int64)||
|keyword|搜索关键字|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultIPageVideo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||IPageVideo|IPageVideo|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;records|视频信息|array|Video|
|&emsp;&emsp;&emsp;&emsp;id|视频ID，唯一标识|integer||
|&emsp;&emsp;&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;&emsp;&emsp;description|视频描述|string||
|&emsp;&emsp;&emsp;&emsp;categoryId|分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;fileUrl|视频文件URL|string||
|&emsp;&emsp;&emsp;&emsp;coverUrl|封面图片URL|string||
|&emsp;&emsp;&emsp;&emsp;duration|视频时长（秒）|integer||
|&emsp;&emsp;&emsp;&emsp;fileSize|文件大小（字节）|integer||
|&emsp;&emsp;&emsp;&emsp;uploaderName|上传者名称|string||
|&emsp;&emsp;&emsp;&emsp;uploaderType|上传者类型：1-用户投稿，2-管理员上传|integer||
|&emsp;&emsp;&emsp;&emsp;userId|上传用户ID（用户投稿时）|integer||
|&emsp;&emsp;&emsp;&emsp;adminId|管理员ID（管理员上传时）|integer||
|&emsp;&emsp;&emsp;&emsp;status|状态：0-待审核，1-已发布，2-已拒绝，3-已下架|integer||
|&emsp;&emsp;&emsp;&emsp;auditAdminId|审核管理员ID|integer||
|&emsp;&emsp;&emsp;&emsp;auditTime|审核时间|string||
|&emsp;&emsp;&emsp;&emsp;auditReason|审核原因（拒绝时）|string||
|&emsp;&emsp;&emsp;&emsp;viewCount|观看次数|integer||
|&emsp;&emsp;&emsp;&emsp;likeCount|点赞次数|integer||
|&emsp;&emsp;&emsp;&emsp;tags|标签，逗号分隔|string||
|&emsp;&emsp;&emsp;&emsp;createdAt|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;updatedAt|更新时间|string||
|&emsp;&emsp;&emsp;&emsp;categoryName|分类名称|string||
|&emsp;&emsp;&emsp;&emsp;uploaderTypeText|上传者类型文本|string||
|&emsp;&emsp;&emsp;&emsp;statusText|状态文本|string||
|&emsp;&emsp;&emsp;&emsp;auditAdminName|审核管理员名称|string||
|&emsp;&emsp;&emsp;&emsp;durationText|视频时长格式化文本|string||
|&emsp;&emsp;&emsp;&emsp;fileSizeText|文件大小格式化文本|string||
|&emsp;&emsp;&emsp;&emsp;isLiked|当前用户是否已点赞|boolean||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"size": 0,
		"records": [
			{
				"id": 1,
				"title": "Java基础语法入门",
				"description": "从零开始学习Java编程语言，掌握基本语法和编程思想",
				"categoryId": 11,
				"fileUrl": "http://localhost:9000/videos/original/java-basic-syntax.mp4",
				"coverUrl": "http://localhost:9000/videos/covers/java-basic-syntax.jpg",
				"duration": 1800,
				"fileSize": 104857600,
				"uploaderName": "技术讲师",
				"uploaderType": 2,
				"userId": 123,
				"adminId": 1,
				"status": 1,
				"auditAdminId": 1,
				"auditTime": "",
				"auditReason": "内容不符合平台规范",
				"viewCount": 156,
				"likeCount": 23,
				"tags": "Java,基础,语法,入门",
				"createdAt": "",
				"updatedAt": "",
				"categoryName": "Java基础",
				"uploaderTypeText": "管理员上传",
				"statusText": "已发布",
				"auditAdminName": "管理员",
				"durationText": "30:00",
				"fileSizeText": "100MB",
				"isLiked": true
			}
		],
		"current": 0,
		"total": 0,
		"pages": 0
	}
}
```


## 获取视频详情


**接口地址**:`/api/videos/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据视频ID获取详细信息，包含用户点赞状态</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|视频ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVideo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Video|Video|
|&emsp;&emsp;id|视频ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;description|视频描述|string||
|&emsp;&emsp;categoryId|分类ID|integer(int64)||
|&emsp;&emsp;fileUrl|视频文件URL|string||
|&emsp;&emsp;coverUrl|封面图片URL|string||
|&emsp;&emsp;duration|视频时长（秒）|integer(int32)||
|&emsp;&emsp;fileSize|文件大小（字节）|integer(int64)||
|&emsp;&emsp;uploaderName|上传者名称|string||
|&emsp;&emsp;uploaderType|上传者类型：1-用户投稿，2-管理员上传|integer(int32)||
|&emsp;&emsp;userId|上传用户ID（用户投稿时）|integer(int64)||
|&emsp;&emsp;adminId|管理员ID（管理员上传时）|integer(int64)||
|&emsp;&emsp;status|状态：0-待审核，1-已发布，2-已拒绝，3-已下架|integer(int32)||
|&emsp;&emsp;auditAdminId|审核管理员ID|integer(int64)||
|&emsp;&emsp;auditTime|审核时间|string(date-time)||
|&emsp;&emsp;auditReason|审核原因（拒绝时）|string||
|&emsp;&emsp;viewCount|观看次数|integer(int64)||
|&emsp;&emsp;likeCount|点赞次数|integer(int64)||
|&emsp;&emsp;tags|标签，逗号分隔|string||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;categoryName|分类名称|string||
|&emsp;&emsp;uploaderTypeText|上传者类型文本|string||
|&emsp;&emsp;statusText|状态文本|string||
|&emsp;&emsp;auditAdminName|审核管理员名称|string||
|&emsp;&emsp;durationText|视频时长格式化文本|string||
|&emsp;&emsp;fileSizeText|文件大小格式化文本|string||
|&emsp;&emsp;isLiked|当前用户是否已点赞|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"title": "Java基础语法入门",
		"description": "从零开始学习Java编程语言，掌握基本语法和编程思想",
		"categoryId": 11,
		"fileUrl": "http://localhost:9000/videos/original/java-basic-syntax.mp4",
		"coverUrl": "http://localhost:9000/videos/covers/java-basic-syntax.jpg",
		"duration": 1800,
		"fileSize": 104857600,
		"uploaderName": "技术讲师",
		"uploaderType": 2,
		"userId": 123,
		"adminId": 1,
		"status": 1,
		"auditAdminId": 1,
		"auditTime": "",
		"auditReason": "内容不符合平台规范",
		"viewCount": 156,
		"likeCount": 23,
		"tags": "Java,基础,语法,入门",
		"createdAt": "",
		"updatedAt": "",
		"categoryName": "Java基础",
		"uploaderTypeText": "管理员上传",
		"statusText": "已发布",
		"auditAdminName": "管理员",
		"durationText": "30:00",
		"fileSizeText": "100MB",
		"isLiked": true
	}
}
```


## 获取热门视频


**接口地址**:`/api/videos/popular`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据观看次数获取热门视频列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|limit|限制数量，默认10|query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListVideo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Video|
|&emsp;&emsp;id|视频ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;description|视频描述|string||
|&emsp;&emsp;categoryId|分类ID|integer(int64)||
|&emsp;&emsp;fileUrl|视频文件URL|string||
|&emsp;&emsp;coverUrl|封面图片URL|string||
|&emsp;&emsp;duration|视频时长（秒）|integer(int32)||
|&emsp;&emsp;fileSize|文件大小（字节）|integer(int64)||
|&emsp;&emsp;uploaderName|上传者名称|string||
|&emsp;&emsp;uploaderType|上传者类型：1-用户投稿，2-管理员上传|integer(int32)||
|&emsp;&emsp;userId|上传用户ID（用户投稿时）|integer(int64)||
|&emsp;&emsp;adminId|管理员ID（管理员上传时）|integer(int64)||
|&emsp;&emsp;status|状态：0-待审核，1-已发布，2-已拒绝，3-已下架|integer(int32)||
|&emsp;&emsp;auditAdminId|审核管理员ID|integer(int64)||
|&emsp;&emsp;auditTime|审核时间|string(date-time)||
|&emsp;&emsp;auditReason|审核原因（拒绝时）|string||
|&emsp;&emsp;viewCount|观看次数|integer(int64)||
|&emsp;&emsp;likeCount|点赞次数|integer(int64)||
|&emsp;&emsp;tags|标签，逗号分隔|string||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;categoryName|分类名称|string||
|&emsp;&emsp;uploaderTypeText|上传者类型文本|string||
|&emsp;&emsp;statusText|状态文本|string||
|&emsp;&emsp;auditAdminName|审核管理员名称|string||
|&emsp;&emsp;durationText|视频时长格式化文本|string||
|&emsp;&emsp;fileSizeText|文件大小格式化文本|string||
|&emsp;&emsp;isLiked|当前用户是否已点赞|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "Java基础语法入门",
			"description": "从零开始学习Java编程语言，掌握基本语法和编程思想",
			"categoryId": 11,
			"fileUrl": "http://localhost:9000/videos/original/java-basic-syntax.mp4",
			"coverUrl": "http://localhost:9000/videos/covers/java-basic-syntax.jpg",
			"duration": 1800,
			"fileSize": 104857600,
			"uploaderName": "技术讲师",
			"uploaderType": 2,
			"userId": 123,
			"adminId": 1,
			"status": 1,
			"auditAdminId": 1,
			"auditTime": "",
			"auditReason": "内容不符合平台规范",
			"viewCount": 156,
			"likeCount": 23,
			"tags": "Java,基础,语法,入门",
			"createdAt": "",
			"updatedAt": "",
			"categoryName": "Java基础",
			"uploaderTypeText": "管理员上传",
			"statusText": "已发布",
			"auditAdminName": "管理员",
			"durationText": "30:00",
			"fileSizeText": "100MB",
			"isLiked": true
		}
	]
}
```


## 获取最新视频


**接口地址**:`/api/videos/latest`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据发布时间获取最新视频列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|limit|限制数量，默认10|query|false|integer(int32)||

**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListVideo|

**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Video|
|&emsp;&emsp;id|视频ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;description|视频描述|string||
|&emsp;&emsp;categoryId|分类ID|integer(int64)||
|&emsp;&emsp;fileUrl|视频文件URL|string||
|&emsp;&emsp;coverUrl|封面图片URL|string||
|&emsp;&emsp;duration|视频时长（秒）|integer(int32)||
|&emsp;&emsp;fileSize|文件大小（字节）|integer(int64)||
|&emsp;&emsp;uploaderName|上传者名称|string||
|&emsp;&emsp;uploaderType|上传者类型：1-用户投稿，2-管理员上传|integer(int32)||
|&emsp;&emsp;userId|上传用户ID（用户投稿时）|integer(int64)||
|&emsp;&emsp;adminId|管理员ID（管理员上传时）|integer(int64)||
|&emsp;&emsp;status|状态：0-待审核，1-已发布，2-已拒绝，3-已下架|integer(int32)||
|&emsp;&emsp;auditAdminId|审核管理员ID|integer(int64)||
|&emsp;&emsp;auditTime|审核时间|string(date-time)||
|&emsp;&emsp;auditReason|审核原因（拒绝时）|string||
|&emsp;&emsp;viewCount|观看次数|integer(int64)||
|&emsp;&emsp;likeCount|点赞次数|integer(int64)||
|&emsp;&emsp;tags|标签，逗号分隔|string||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;categoryName|分类名称|string||
|&emsp;&emsp;uploaderTypeText|上传者类型文本|string||
|&emsp;&emsp;statusText|状态文本|string||
|&emsp;&emsp;auditAdminName|审核管理员名称|string||
|&emsp;&emsp;durationText|视频时长格式化文本|string||
|&emsp;&emsp;fileSizeText|文件大小格式化文本|string||
|&emsp;&emsp;isLiked|当前用户是否已点赞|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "Java基础语法入门",
			"description": "从零开始学习Java编程语言，掌握基本语法和编程思想",
			"categoryId": 11,
			"fileUrl": "http://localhost:9000/videos/original/java-basic-syntax.mp4",
			"coverUrl": "http://localhost:9000/videos/covers/java-basic-syntax.jpg",
			"duration": 1800,
			"fileSize": 104857600,
			"uploaderName": "技术讲师",
			"uploaderType": 2,
			"userId": 123,
			"adminId": 1,
			"status": 1,
			"auditAdminId": 1,
			"auditTime": "",
			"auditReason": "内容不符合平台规范",
			"viewCount": 156,
			"likeCount": 23,
			"tags": "Java,基础,语法,入门",
			"createdAt": "",
			"updatedAt": "",
			"categoryName": "Java基础",
			"uploaderTypeText": "管理员上传",
			"statusText": "已发布",
			"auditAdminName": "管理员",
			"durationText": "30:00",
			"fileSizeText": "100MB",
			"isLiked": true
		}
	]
}
```


# 轮播图管理


## 更新轮播图


**接口地址**:`/api/banners/update`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>更新轮播图的信息，包括图片、标题、跳转链接、排序等</p>



**请求示例**:


```javascript
{
  "id": 1,
  "title": "智能考试系统介绍",
  "description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
  "imageUrl": "https://example.com/images/banner1.jpg",
  "linkUrl": "https://example.com/about",
  "sortOrder": 1,
  "isActive": true,
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|banner|轮播图信息|body|true|Banner|Banner|
|&emsp;&emsp;id|轮播图ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;title|轮播图标题||false|string||
|&emsp;&emsp;description|轮播图描述内容||false|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址||false|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选||false|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||
|&emsp;&emsp;createTime|轮播图创建时间||false|string(date-time)||
|&emsp;&emsp;updateTime|轮播图更新时间||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 切换轮播图状态


**接口地址**:`/api/banners/toggle/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>启用或禁用指定的轮播图，禁用后不会在前台显示</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|轮播图ID|path|true|integer(int64)||
|isActive|是否启用，true为启用，false为禁用|query|true|boolean||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 上传轮播图图片


**接口地址**:`/api/banners/upload-image`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>将图片文件上传到MinIO服务器，返回可访问的图片URL</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file|要上传的图片文件，支持jpg、png、gif等格式，大小限制5MB|query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 添加轮播图


**接口地址**:`/api/banners/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>创建新的轮播图，需要提供图片URL、标题、跳转链接等信息</p>



**请求示例**:


```javascript
{
  "id": 1,
  "title": "智能考试系统介绍",
  "description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
  "imageUrl": "https://example.com/images/banner1.jpg",
  "linkUrl": "https://example.com/about",
  "sortOrder": 1,
  "isActive": true,
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|banner|轮播图信息|body|true|Banner|Banner|
|&emsp;&emsp;id|轮播图ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;title|轮播图标题||false|string||
|&emsp;&emsp;description|轮播图描述内容||false|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址||false|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选||false|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||
|&emsp;&emsp;createTime|轮播图创建时间||false|string(date-time)||
|&emsp;&emsp;updateTime|轮播图更新时间||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 根据ID获取轮播图


**接口地址**:`/api/banners/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据轮播图ID获取单个轮播图的详细信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|轮播图ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultBanner|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||Banner|Banner|
|&emsp;&emsp;id|轮播图ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|轮播图标题|string||
|&emsp;&emsp;description|轮播图描述内容|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||
|&emsp;&emsp;createTime|轮播图创建时间|string(date-time)||
|&emsp;&emsp;updateTime|轮播图更新时间|string(date-time)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"title": "智能考试系统介绍",
		"description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
		"imageUrl": "https://example.com/images/banner1.jpg",
		"linkUrl": "https://example.com/about",
		"sortOrder": 1,
		"isActive": true,
		"createTime": "",
		"updateTime": ""
	}
}
```


## 获取所有轮播图


**接口地址**:`/api/banners/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有轮播图列表，包括启用和禁用的，供管理后台使用</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListBanner|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Banner|
|&emsp;&emsp;id|轮播图ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|轮播图标题|string||
|&emsp;&emsp;description|轮播图描述内容|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||
|&emsp;&emsp;createTime|轮播图创建时间|string(date-time)||
|&emsp;&emsp;updateTime|轮播图更新时间|string(date-time)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "智能考试系统介绍",
			"description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
			"imageUrl": "https://example.com/images/banner1.jpg",
			"linkUrl": "https://example.com/about",
			"sortOrder": 1,
			"isActive": true,
			"createTime": "",
			"updateTime": ""
		}
	]
}
```


## 获取启用的轮播图


**接口地址**:`/api/banners/active`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取状态为启用的轮播图列表，供前台首页展示使用</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListBanner|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|Banner|
|&emsp;&emsp;id|轮播图ID，唯一标识|integer(int64)||
|&emsp;&emsp;title|轮播图标题|string||
|&emsp;&emsp;description|轮播图描述内容|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||
|&emsp;&emsp;createTime|轮播图创建时间|string(date-time)||
|&emsp;&emsp;updateTime|轮播图更新时间|string(date-time)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"title": "智能考试系统介绍",
			"description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
			"imageUrl": "https://example.com/images/banner1.jpg",
			"linkUrl": "https://example.com/about",
			"sortOrder": 1,
			"isActive": true,
			"createTime": "",
			"updateTime": ""
		}
	]
}
```


## 删除轮播图


**接口地址**:`/api/banners/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除指定的轮播图</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|轮播图ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


# 视频分类管理


## 获取分类列表


**接口地址**:`/api/video-categories`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有视频分类列表，包含每个分类下的视频数量统计</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListVideoCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|VideoCategory|
|&emsp;&emsp;id|分类ID，唯一标识|integer(int64)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;description|分类描述|string||
|&emsp;&emsp;parentId|父级分类ID，0为顶级分类|integer(int64)||
|&emsp;&emsp;sortOrder|排序权重，数字越小越靠前|integer(int32)||
|&emsp;&emsp;status|状态：1-启用，0-禁用|integer(int32)||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|VideoCategory|
|&emsp;&emsp;videoCount|该分类下的视频数量|integer(int64)||
|&emsp;&emsp;parentName|父级分类名称|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"name": "Java基础",
			"description": "Java编程语言基础知识讲解",
			"parentId": 0,
			"sortOrder": 1,
			"status": 1,
			"createdAt": "",
			"updatedAt": "",
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"description": "Java编程语言基础知识讲解",
					"parentId": 0,
					"sortOrder": 1,
					"status": 1,
					"createdAt": "",
					"updatedAt": "",
					"children": [
						{}
					],
					"videoCount": 15,
					"parentName": "编程技术"
				}
			],
			"videoCount": 15,
			"parentName": "编程技术"
		}
	]
}
```


## 添加新分类


**接口地址**:`/api/video-categories`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>创建新的视频分类，支持设置父分类实现层级结构</p>



**请求示例**:


```javascript
{
  "id": 1,
  "name": "Java基础",
  "description": "Java编程语言基础知识讲解",
  "parentId": 0,
  "sortOrder": 1,
  "status": 1,
  "createdAt": "",
  "updatedAt": "",
  "children": [
    {
      "id": 1,
      "name": "Java基础",
      "description": "Java编程语言基础知识讲解",
      "parentId": 0,
      "sortOrder": 1,
      "status": 1,
      "createdAt": "",
      "updatedAt": "",
      "children": [
        {
          "id": 1,
          "name": "Java基础",
          "description": "Java编程语言基础知识讲解",
          "parentId": 0,
          "sortOrder": 1,
          "status": 1,
          "createdAt": "",
          "updatedAt": "",
          "children": [
            {}
          ],
          "videoCount": 15,
          "parentName": "编程技术"
        }
      ],
      "videoCount": 15,
      "parentName": "编程技术"
    }
  ],
  "videoCount": 15,
  "parentName": "编程技术"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoCategory|视频分类信息|body|true|VideoCategory|VideoCategory|
|&emsp;&emsp;id|分类ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;name|分类名称||false|string||
|&emsp;&emsp;description|分类描述||false|string||
|&emsp;&emsp;parentId|父级分类ID，0为顶级分类||false|integer(int64)||
|&emsp;&emsp;sortOrder|排序权重，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;status|状态：1-启用，0-禁用||false|integer(int32)||
|&emsp;&emsp;createdAt|创建时间||false|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间||false|string(date-time)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构||false|array|VideoCategory|
|&emsp;&emsp;videoCount|该分类下的视频数量||false|integer(int64)||
|&emsp;&emsp;parentName|父级分类名称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 更新分类信息


**接口地址**:`/api/video-categories`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改分类的名称、描述、排序等信息</p>



**请求示例**:


```javascript
{
  "id": 1,
  "name": "Java基础",
  "description": "Java编程语言基础知识讲解",
  "parentId": 0,
  "sortOrder": 1,
  "status": 1,
  "createdAt": "",
  "updatedAt": "",
  "children": [
    {
      "id": 1,
      "name": "Java基础",
      "description": "Java编程语言基础知识讲解",
      "parentId": 0,
      "sortOrder": 1,
      "status": 1,
      "createdAt": "",
      "updatedAt": "",
      "children": [
        {
          "id": 1,
          "name": "Java基础",
          "description": "Java编程语言基础知识讲解",
          "parentId": 0,
          "sortOrder": 1,
          "status": 1,
          "createdAt": "",
          "updatedAt": "",
          "children": [
            {}
          ],
          "videoCount": 15,
          "parentName": "编程技术"
        }
      ],
      "videoCount": 15,
      "parentName": "编程技术"
    }
  ],
  "videoCount": 15,
  "parentName": "编程技术"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoCategory|视频分类信息|body|true|VideoCategory|VideoCategory|
|&emsp;&emsp;id|分类ID，唯一标识||false|integer(int64)||
|&emsp;&emsp;name|分类名称||false|string||
|&emsp;&emsp;description|分类描述||false|string||
|&emsp;&emsp;parentId|父级分类ID，0为顶级分类||false|integer(int64)||
|&emsp;&emsp;sortOrder|排序权重，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;status|状态：1-启用，0-禁用||false|integer(int32)||
|&emsp;&emsp;createdAt|创建时间||false|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间||false|string(date-time)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构||false|array|VideoCategory|
|&emsp;&emsp;videoCount|该分类下的视频数量||false|integer(int64)||
|&emsp;&emsp;parentName|父级分类名称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 获取分类详情


**接口地址**:`/api/video-categories/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据分类ID获取详细信息，包含视频数量和父级分类名称</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|分类ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVideoCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||VideoCategory|VideoCategory|
|&emsp;&emsp;id|分类ID，唯一标识|integer(int64)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;description|分类描述|string||
|&emsp;&emsp;parentId|父级分类ID，0为顶级分类|integer(int64)||
|&emsp;&emsp;sortOrder|排序权重，数字越小越靠前|integer(int32)||
|&emsp;&emsp;status|状态：1-启用，0-禁用|integer(int32)||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|VideoCategory|
|&emsp;&emsp;videoCount|该分类下的视频数量|integer(int64)||
|&emsp;&emsp;parentName|父级分类名称|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 1,
		"name": "Java基础",
		"description": "Java编程语言基础知识讲解",
		"parentId": 0,
		"sortOrder": 1,
		"status": 1,
		"createdAt": "",
		"updatedAt": "",
		"children": [
			{
				"id": 1,
				"name": "Java基础",
				"description": "Java编程语言基础知识讲解",
				"parentId": 0,
				"sortOrder": 1,
				"status": 1,
				"createdAt": "",
				"updatedAt": "",
				"children": [
					{}
				],
				"videoCount": 15,
				"parentName": "编程技术"
			}
		],
		"videoCount": 15,
		"parentName": "编程技术"
	}
}
```


## 删除分类


**接口地址**:`/api/video-categories/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定的视频分类，注意：删除前需确保分类下没有视频</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|分类ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultVoid|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```


## 获取分类树形结构


**接口地址**:`/api/video-categories/tree`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取视频分类的树形层级结构，用于前端组件展示</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListVideoCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|VideoCategory|
|&emsp;&emsp;id|分类ID，唯一标识|integer(int64)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;description|分类描述|string||
|&emsp;&emsp;parentId|父级分类ID，0为顶级分类|integer(int64)||
|&emsp;&emsp;sortOrder|排序权重，数字越小越靠前|integer(int32)||
|&emsp;&emsp;status|状态：1-启用，0-禁用|integer(int32)||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|VideoCategory|
|&emsp;&emsp;videoCount|该分类下的视频数量|integer(int64)||
|&emsp;&emsp;parentName|父级分类名称|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"name": "Java基础",
			"description": "Java编程语言基础知识讲解",
			"parentId": 0,
			"sortOrder": 1,
			"status": 1,
			"createdAt": "",
			"updatedAt": "",
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"description": "Java编程语言基础知识讲解",
					"parentId": 0,
					"sortOrder": 1,
					"status": 1,
					"createdAt": "",
					"updatedAt": "",
					"children": [
						{}
					],
					"videoCount": 15,
					"parentName": "编程技术"
				}
			],
			"videoCount": 15,
			"parentName": "编程技术"
		}
	]
}
```


## 获取顶级分类


**接口地址**:`/api/video-categories/top`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有启用的顶级分类，用于导航菜单</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListVideoCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|VideoCategory|
|&emsp;&emsp;id|分类ID，唯一标识|integer(int64)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;description|分类描述|string||
|&emsp;&emsp;parentId|父级分类ID，0为顶级分类|integer(int64)||
|&emsp;&emsp;sortOrder|排序权重，数字越小越靠前|integer(int32)||
|&emsp;&emsp;status|状态：1-启用，0-禁用|integer(int32)||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|VideoCategory|
|&emsp;&emsp;videoCount|该分类下的视频数量|integer(int64)||
|&emsp;&emsp;parentName|父级分类名称|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"name": "Java基础",
			"description": "Java编程语言基础知识讲解",
			"parentId": 0,
			"sortOrder": 1,
			"status": 1,
			"createdAt": "",
			"updatedAt": "",
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"description": "Java编程语言基础知识讲解",
					"parentId": 0,
					"sortOrder": 1,
					"status": 1,
					"createdAt": "",
					"updatedAt": "",
					"children": [
						{}
					],
					"videoCount": 15,
					"parentName": "编程技术"
				}
			],
			"videoCount": 15,
			"parentName": "编程技术"
		}
	]
}
```


## 获取子分类


**接口地址**:`/api/video-categories/children/{parentId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据父级分类ID获取其下的子分类列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|parentId|父级分类ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListVideoCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|VideoCategory|
|&emsp;&emsp;id|分类ID，唯一标识|integer(int64)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;description|分类描述|string||
|&emsp;&emsp;parentId|父级分类ID，0为顶级分类|integer(int64)||
|&emsp;&emsp;sortOrder|排序权重，数字越小越靠前|integer(int32)||
|&emsp;&emsp;status|状态：1-启用，0-禁用|integer(int32)||
|&emsp;&emsp;createdAt|创建时间|string(date-time)||
|&emsp;&emsp;updatedAt|更新时间|string(date-time)||
|&emsp;&emsp;children|子分类列表，用于构建分类树结构|array|VideoCategory|
|&emsp;&emsp;videoCount|该分类下的视频数量|integer(int64)||
|&emsp;&emsp;parentName|父级分类名称|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 1,
			"name": "Java基础",
			"description": "Java编程语言基础知识讲解",
			"parentId": 0,
			"sortOrder": 1,
			"status": 1,
			"createdAt": "",
			"updatedAt": "",
			"children": [
				{
					"id": 1,
					"name": "Java基础",
					"description": "Java编程语言基础知识讲解",
					"parentId": 0,
					"sortOrder": 1,
					"status": 1,
					"createdAt": "",
					"updatedAt": "",
					"children": [
						{}
					],
					"videoCount": 15,
					"parentName": "编程技术"
				}
			],
			"videoCount": 15,
			"parentName": "编程技术"
		}
	]
}
```


# 数据统计


## 测试数据库连接


**接口地址**:`/api/stats/test`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>测试系统数据库连接状态，用于系统健康检查</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 获取系统概览统计


**接口地址**:`/api/stats/overview`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取系统的概览统计数据，包括题目、用户、考试等各项数量统计</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultStatsDto|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data||StatsDto|StatsDto|
|&emsp;&emsp;questionCount|题目总数|integer(int64)||
|&emsp;&emsp;userCount|用户总数|integer(int64)||
|&emsp;&emsp;examCount|考试总场次|integer(int64)||
|&emsp;&emsp;todayExamCount|今日考试次数|integer(int64)||
|&emsp;&emsp;categoryCount|分类总数|integer(int64)||
|&emsp;&emsp;paperCount|试卷总数|integer(int64)||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"questionCount": 150,
		"userCount": 50,
		"examCount": 25,
		"todayExamCount": 5,
		"categoryCount": 8,
		"paperCount": 12
	}
}
```


# 题目批量操作


## 验证题目数据


**接口地址**:`/api/questions/batch/validate`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>验证题目数据的完整性和格式正确性，返回验证结果和错误信息</p>



**请求示例**:


```javascript
[
  {
    "title": "以下关于Java面向对象编程的说法正确的是？",
    "type": "CHOICE",
    "multi": false,
    "categoryId": 1,
    "categoryName": "Java基础",
    "difficulty": "MEDIUM",
    "score": 5,
    "analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
    "choices": [
      {
        "content": "Java支持多重继承",
        "isCorrect": false,
        "sort": 1
      }
    ],
    "answer": "正确",
    "keywords": "面向对象,封装,继承,多态"
  }
]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|questionImportDtos|题目导入数据传输对象|body|true|array|QuestionImportDto|
|&emsp;&emsp;title|题目标题内容||true|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;multi|是否为多选题（仅选择题有效）||false|boolean||
|&emsp;&emsp;categoryId|题目分类ID||false|integer(int64)||
|&emsp;&emsp;categoryName|分类名称（仅用于显示，不会保存到数据库）||false|string||
|&emsp;&emsp;difficulty|题目难度级别,可用值:EASY,MEDIUM,HARD||false|string||
|&emsp;&emsp;score|题目默认分值||false|integer(int32)||
|&emsp;&emsp;analysis|题目解析说明||false|string||
|&emsp;&emsp;choices|选择题选项数据||false|array|ChoiceImportDto|
|&emsp;&emsp;&emsp;&emsp;content|选项内容||true|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案||false|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号||false|integer||
|&emsp;&emsp;answer|题目答案（判断题和简答题使用）||false|string||
|&emsp;&emsp;keywords|答题关键词（用于简答题AI评分）||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 预览Excel文件内容


**接口地址**:`/api/questions/batch/preview-excel`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>解析并预览Excel文件中的题目内容，不会导入到数据库</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file|Excel文件，支持.xls和.xlsx格式|query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListQuestionImportDto|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|QuestionImportDto|
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题（仅选择题有效）|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;categoryName|分类名称（仅用于显示，不会保存到数据库）|string||
|&emsp;&emsp;difficulty|题目难度级别,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;analysis|题目解析说明|string||
|&emsp;&emsp;choices|选择题选项数据|array|ChoiceImportDto|
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案（判断题和简答题使用）|string||
|&emsp;&emsp;keywords|答题关键词（用于简答题AI评分）|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"title": "以下关于Java面向对象编程的说法正确的是？",
			"type": "CHOICE",
			"multi": false,
			"categoryId": 1,
			"categoryName": "Java基础",
			"difficulty": "MEDIUM",
			"score": 5,
			"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
			"choices": [
				{
					"content": "Java支持多重继承",
					"isCorrect": false,
					"sort": 1
				}
			],
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		}
	]
}
```


## 批量导入题目


**接口地址**:`/api/questions/batch/import-questions`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>将题目列表批量导入到数据库，支持Excel解析后的导入或AI生成后的确认导入</p>



**请求示例**:


```javascript
[
  {
    "title": "以下关于Java面向对象编程的说法正确的是？",
    "type": "CHOICE",
    "multi": false,
    "categoryId": 1,
    "categoryName": "Java基础",
    "difficulty": "MEDIUM",
    "score": 5,
    "analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
    "choices": [
      {
        "content": "Java支持多重继承",
        "isCorrect": false,
        "sort": 1
      }
    ],
    "answer": "正确",
    "keywords": "面向对象,封装,继承,多态"
  }
]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|questionImportDtos|题目导入数据传输对象|body|true|array|QuestionImportDto|
|&emsp;&emsp;title|题目标题内容||true|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;multi|是否为多选题（仅选择题有效）||false|boolean||
|&emsp;&emsp;categoryId|题目分类ID||false|integer(int64)||
|&emsp;&emsp;categoryName|分类名称（仅用于显示，不会保存到数据库）||false|string||
|&emsp;&emsp;difficulty|题目难度级别,可用值:EASY,MEDIUM,HARD||false|string||
|&emsp;&emsp;score|题目默认分值||false|integer(int32)||
|&emsp;&emsp;analysis|题目解析说明||false|string||
|&emsp;&emsp;choices|选择题选项数据||false|array|ChoiceImportDto|
|&emsp;&emsp;&emsp;&emsp;content|选项内容||true|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案||false|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号||false|integer||
|&emsp;&emsp;answer|题目答案（判断题和简答题使用）||false|string||
|&emsp;&emsp;keywords|答题关键词（用于简答题AI评分）||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## 从Excel文件批量导入题目


**接口地址**:`/api/questions/batch/import-excel`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>解析Excel文件并将题目批量导入到数据库</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file|Excel文件，包含题目数据|query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": ""
}
```


## AI智能生成题目


**接口地址**:`/api/questions/batch/ai-generate`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>使用AI技术根据指定主题和要求智能生成题目，支持预览后再决定是否导入</p>



**请求示例**:


```javascript
{
  "topic": "Java面向对象编程",
  "count": 5,
  "types": "CHOICE,JUDGE,TEXT",
  "difficulty": "MEDIUM",
  "categoryId": 1,
  "includeMultiple": false,
  "requirements": "重点考察实际应用，包含代码示例"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|aiGenerateRequestDto|AI智能生成题目请求参数|body|true|AiGenerateRequestDto|AiGenerateRequestDto|
|&emsp;&emsp;topic|生成题目的主题||true|string||
|&emsp;&emsp;count|生成题目的数量，范围1-20||true|integer(int32)||
|&emsp;&emsp;types|题目类型，多个用逗号分隔,可用值:CHOICE,JUDGE,TEXT,CHOICE,JUDGE,CHOICE,TEXT,JUDGE,TEXT,CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD||false|string||
|&emsp;&emsp;categoryId|题目分类ID，可以指定生成题目所属的分类||false|integer(int64)||
|&emsp;&emsp;includeMultiple|是否包含多选题，仅在题目类型包含CHOICE时有效||false|boolean||
|&emsp;&emsp;requirements|额外的生成要求和说明||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListQuestionImportDto|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|QuestionImportDto|
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题（仅选择题有效）|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;categoryName|分类名称（仅用于显示，不会保存到数据库）|string||
|&emsp;&emsp;difficulty|题目难度级别,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;analysis|题目解析说明|string||
|&emsp;&emsp;choices|选择题选项数据|array|ChoiceImportDto|
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案（判断题和简答题使用）|string||
|&emsp;&emsp;keywords|答题关键词（用于简答题AI评分）|string||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"title": "以下关于Java面向对象编程的说法正确的是？",
			"type": "CHOICE",
			"multi": false,
			"categoryId": 1,
			"categoryName": "Java基础",
			"difficulty": "MEDIUM",
			"score": 5,
			"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
			"choices": [
				{
					"content": "Java支持多重继承",
					"isCorrect": false,
					"sort": 1
				}
			],
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		}
	]
}
```


## 下载Excel导入模板


**接口地址**:`/api/questions/batch/template`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>下载题目批量导入的Excel模板文件</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# 文件处理


## getFile


**接口地址**:`/files/**`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# 访问测试


## debugExamRecord

**接口地址**:`/api/debug/exam-record/{examRecordId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|examRecordId||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|object||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {}
}
```