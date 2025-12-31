# 硅谷AI链习室API文档


[TOC]






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


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>用户上传视频进行投稿，需要等待审核</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|title|视频标题|query|true|string||
|categoryId|分类ID|query|true|integer(int64)||
|uploaderName|上传者名称|query|true|string||
|duration|视频时长（秒）|query|true|integer(int32)||
|description|视频描述|query|false|string||
|tags|标签|query|false|string||


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


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>管理员直接上传视频，无需审核直接发布</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|title|视频标题|query|true|string||
|categoryId|分类ID|query|true|integer(int64)||
|uploaderName|上传者名称|query|true|string||
|description|视频描述|query|false|string||
|tags|标签|query|false|string||
|duration|视频时长（秒）|query|false|integer(int32)||


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