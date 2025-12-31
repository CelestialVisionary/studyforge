# 硅谷AI链习室API文档


[TOC]


# 一、轮播图管理(实现)


## 更新轮播图


**接口地址**:`/api/banners/update`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>更新轮播图的信息，包括图片、标题、跳转链接、排序等</p>



**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "title": "智能考试系统介绍",
  "description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
  "imageUrl": "https://example.com/images/banner1.jpg",
  "linkUrl": "https://example.com/about",
  "sortOrder": 1,
  "isActive": true
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|banner|轮播图信息|body|true|Banner|Banner|
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
|&emsp;&emsp;title|轮播图标题||false|string||
|&emsp;&emsp;description|轮播图描述内容||false|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址||false|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选||false|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||


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


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>将图片文件上传到MinIO服务器，返回可访问的图片URL</p>



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


## 添加轮播图


**接口地址**:`/api/banners/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>创建新的轮播图，需要提供图片URL、标题、跳转链接等信息</p>



**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "title": "智能考试系统介绍",
  "description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
  "imageUrl": "https://example.com/images/banner1.jpg",
  "linkUrl": "https://example.com/about",
  "sortOrder": 1,
  "isActive": true
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|banner|轮播图信息|body|true|Banner|Banner|
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
|&emsp;&emsp;title|轮播图标题||false|string||
|&emsp;&emsp;description|轮播图描述内容||false|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址||false|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选||false|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||


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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|轮播图标题|string||
|&emsp;&emsp;description|轮播图描述内容|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"title": "智能考试系统介绍",
		"description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
		"imageUrl": "https://example.com/images/banner1.jpg",
		"linkUrl": "https://example.com/about",
		"sortOrder": 1,
		"isActive": true
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|轮播图标题|string||
|&emsp;&emsp;description|轮播图描述内容|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"title": "智能考试系统介绍",
			"description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
			"imageUrl": "https://example.com/images/banner1.jpg",
			"linkUrl": "https://example.com/about",
			"sortOrder": 1,
			"isActive": true
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|轮播图标题|string||
|&emsp;&emsp;description|轮播图描述内容|string||
|&emsp;&emsp;imageUrl|轮播图片URL地址|string||
|&emsp;&emsp;linkUrl|点击跳转链接，可选|string||
|&emsp;&emsp;sortOrder|排序顺序，数字越小越靠前|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"title": "智能考试系统介绍",
			"description": "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能",
			"imageUrl": "https://example.com/images/banner1.jpg",
			"linkUrl": "https://example.com/about",
			"sortOrder": 1,
			"isActive": true
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


# 二、公告管理（不实现）


## 更新公告信息


**接口地址**:`/api/notices/update`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改公告的内容、标题、类型等信息</p>



**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "title": "系统维护通知",
  "content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
  "type": "SYSTEM",
  "priority": 1,
  "isActive": true
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|notice|系统公告信息|body|true|Notice|Notice|
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
|&emsp;&emsp;title|公告标题||false|string||
|&emsp;&emsp;content|公告内容详情||false|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE||false|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||


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
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "title": "系统维护通知",
  "content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
  "type": "SYSTEM",
  "priority": 1,
  "isActive": true
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|notice|系统公告信息|body|true|Notice|Notice|
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
|&emsp;&emsp;title|公告标题||false|string||
|&emsp;&emsp;content|公告内容详情||false|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE||false|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2||false|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示||false|boolean||


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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"title": "系统维护通知",
		"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
		"type": "SYSTEM",
		"priority": 1,
		"isActive": true
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"title": "系统维护通知",
			"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
			"type": "SYSTEM",
			"priority": 1,
			"isActive": true
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"title": "系统维护通知",
			"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
			"type": "SYSTEM",
			"priority": 1,
			"isActive": true
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|公告标题|string||
|&emsp;&emsp;content|公告内容详情|string||
|&emsp;&emsp;type|公告类型,可用值:SYSTEM,FEATURE,NOTICE|string||
|&emsp;&emsp;priority|优先级级别,可用值:0,1,2|integer(int32)||
|&emsp;&emsp;isActive|是否启用显示|boolean||


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"title": "系统维护通知",
			"content": "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...",
			"type": "SYSTEM",
			"priority": 1,
			"isActive": true
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