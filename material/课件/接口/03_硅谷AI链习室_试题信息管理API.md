# 硅谷AI链习室API文档


[TOC]


# 一、分类管理


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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
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
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 0,
					"createTime": "",
					"isDeleted": "",
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
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "name": "Java基础",
  "parentId": 0,
  "sort": 1,
  "children": [
    {
      "id": 0,
      "createTime": "",
      "isDeleted": "",
      "name": "Java基础",
      "parentId": 0,
      "sort": 1,
      "children": [
        {
          "id": 0,
          "createTime": "",
          "isDeleted": "",
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
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
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
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "name": "Java基础",
  "parentId": 0,
  "sort": 1,
  "children": [
    {
      "id": 0,
      "createTime": "",
      "isDeleted": "",
      "name": "Java基础",
      "parentId": 0,
      "sort": 1,
      "children": [
        {
          "id": 0,
          "createTime": "",
          "isDeleted": "",
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
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
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
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 0,
					"createTime": "",
					"isDeleted": "",
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


# 二、题目管理


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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"title": "以下关于Java面向对象编程的说法正确的是？",
		"type": "CHOICE",
		"multi": false,
		"categoryId": 1,
		"difficulty": "MEDIUM",
		"score": 5,
		"paperScore": 10,
		"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
		"choices": [
			{
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"questionId": 1,
				"content": "Java是面向对象编程语言",
				"isCorrect": true,
				"sort": 1
			}
		],
		"answer": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"questionId": 1,
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		},
		"category": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 0,
					"createTime": "",
					"isDeleted": "",
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
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "title": "以下关于Java面向对象编程的说法正确的是？",
  "type": "CHOICE",
  "multi": false,
  "categoryId": 1,
  "difficulty": "MEDIUM",
  "score": 5,
  "paperScore": 10,
  "analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
  "choices": [
    {
      "id": 0,
      "createTime": "",
      "isDeleted": "",
      "questionId": 1,
      "content": "Java是面向对象编程语言",
      "isCorrect": true,
      "sort": 1
    }
  ],
  "answer": {
    "id": 0,
    "createTime": "",
    "isDeleted": "",
    "questionId": 1,
    "answer": "正确",
    "keywords": "面向对象,封装,继承,多态"
  },
  "category": {
    "id": 0,
    "createTime": "",
    "isDeleted": "",
    "name": "Java基础",
    "parentId": 0,
    "sort": 1,
    "children": [
      {
        "id": 0,
        "createTime": "",
        "isDeleted": "",
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
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
|&emsp;&emsp;title|题目标题内容||false|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效||false|boolean||
|&emsp;&emsp;categoryId|题目分类ID||false|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD||false|string||
|&emsp;&emsp;score|题目默认分值||false|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值||false|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明||false|string||
|&emsp;&emsp;choices|题目选项信息||false|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|主键||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除||false|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容||false|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案||false|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号||false|integer||
|&emsp;&emsp;answer|题目答案信息||false|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|主键||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除||false|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容||false|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分||false|string||
|&emsp;&emsp;category|题目分类信息||false|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|主键||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除||false|string||
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"title": "以下关于Java面向对象编程的说法正确的是？",
		"type": "CHOICE",
		"multi": false,
		"categoryId": 1,
		"difficulty": "MEDIUM",
		"score": 5,
		"paperScore": 10,
		"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
		"choices": [
			{
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"questionId": 1,
				"content": "Java是面向对象编程语言",
				"isCorrect": true,
				"sort": 1
			}
		],
		"answer": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"questionId": 1,
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		},
		"category": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 0,
					"createTime": "",
					"isDeleted": "",
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
  "id": 0,
  "createTime": "",
  "isDeleted": "",
  "title": "以下关于Java面向对象编程的说法正确的是？",
  "type": "CHOICE",
  "multi": false,
  "categoryId": 1,
  "difficulty": "MEDIUM",
  "score": 5,
  "paperScore": 10,
  "analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
  "choices": [
    {
      "id": 0,
      "createTime": "",
      "isDeleted": "",
      "questionId": 1,
      "content": "Java是面向对象编程语言",
      "isCorrect": true,
      "sort": 1
    }
  ],
  "answer": {
    "id": 0,
    "createTime": "",
    "isDeleted": "",
    "questionId": 1,
    "answer": "正确",
    "keywords": "面向对象,封装,继承,多态"
  },
  "category": {
    "id": 0,
    "createTime": "",
    "isDeleted": "",
    "name": "Java基础",
    "parentId": 0,
    "sort": 1,
    "children": [
      {
        "id": 0,
        "createTime": "",
        "isDeleted": "",
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
|&emsp;&emsp;id|主键||false|integer(int64)||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除||false|string(byte)||
|&emsp;&emsp;title|题目标题内容||false|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT||false|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效||false|boolean||
|&emsp;&emsp;categoryId|题目分类ID||false|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD||false|string||
|&emsp;&emsp;score|题目默认分值||false|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值||false|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明||false|string||
|&emsp;&emsp;choices|题目选项信息||false|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|主键||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除||false|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容||false|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案||false|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号||false|integer||
|&emsp;&emsp;answer|题目答案信息||false|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|主键||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除||false|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容||false|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分||false|string||
|&emsp;&emsp;category|题目分类信息||false|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|主键||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除||false|string||
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"title": "以下关于Java面向对象编程的说法正确的是？",
		"type": "CHOICE",
		"multi": false,
		"categoryId": 1,
		"difficulty": "MEDIUM",
		"score": 5,
		"paperScore": 10,
		"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
		"choices": [
			{
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"questionId": 1,
				"content": "Java是面向对象编程语言",
				"isCorrect": true,
				"sort": 1
			}
		],
		"answer": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"questionId": 1,
			"answer": "正确",
			"keywords": "面向对象,封装,继承,多态"
		},
		"category": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础",
			"parentId": 0,
			"sort": 1,
			"children": [
				{
					"id": 0,
					"createTime": "",
					"isDeleted": "",
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;categoryId|题目分类ID|integer(int64)||
|&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;score|题目默认分值|integer(int32)||
|&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"title": "以下关于Java面向对象编程的说法正确的是？",
			"type": "CHOICE",
			"multi": false,
			"categoryId": 1,
			"difficulty": "MEDIUM",
			"score": 5,
			"paperScore": 10,
			"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
			"choices": [
				{
					"id": 0,
					"createTime": "",
					"isDeleted": "",
					"questionId": 1,
					"content": "Java是面向对象编程语言",
					"isCorrect": true,
					"sort": 1
				}
			],
			"answer": {
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"questionId": 1,
				"answer": "正确",
				"keywords": "面向对象,封装,继承,多态"
			},
			"category": {
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"name": "Java基础",
				"parentId": 0,
				"sort": 1,
				"children": [
					{
						"id": 0,
						"createTime": "",
						"isDeleted": "",
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
|categoryId||query|false|integer(int64)||
|difficulty||query|false|string||
|type||query|false|string||
|keyword||query|false|string||


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
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"title": "以下关于Java面向对象编程的说法正确的是？",
				"type": "CHOICE",
				"multi": false,
				"categoryId": 1,
				"difficulty": "MEDIUM",
				"score": 5,
				"paperScore": 10,
				"analysis": "Java是面向对象编程语言，支持封装、继承、多态三大特性...",
				"choices": [
					{
						"id": 0,
						"createTime": "",
						"isDeleted": "",
						"questionId": 1,
						"content": "Java是面向对象编程语言",
						"isCorrect": true,
						"sort": 1
					}
				],
				"answer": {
					"id": 0,
					"createTime": "",
					"isDeleted": "",
					"questionId": 1,
					"answer": "正确",
					"keywords": "面向对象,封装,继承,多态"
				},
				"category": {
					"id": 0,
					"createTime": "",
					"isDeleted": "",
					"name": "Java基础",
					"parentId": 0,
					"sort": 1,
					"children": [
						{
							"id": 0,
							"createTime": "",
							"isDeleted": "",
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


# 三、题目批量操作


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
|questionImportVos|题目导入数据传输对象|body|true|array|QuestionImportVo|
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


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>解析并预览Excel文件中的题目内容，不会导入到数据库</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|ResultListQuestionImportVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|QuestionImportVo|
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


**请求数据类型**:`query key = value  param key= value `


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
|questionImportVos|题目导入数据传输对象|body|true|array|QuestionImportVo|
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


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>解析Excel文件并将题目批量导入到数据库</p>



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
|aiGenerateRequestVo|AI智能生成题目请求参数|body|true|AiGenerateRequestVo|AiGenerateRequestVo|
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
|200|OK|ResultListQuestionImportVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|业务状态码,可用值:200,400,401,403,404,500|integer(int32)|integer(int32)|
|message|响应消息，成功时描述操作结果，失败时描述错误原因|string||
|data|响应数据，具体的业务数据内容|array|QuestionImportVo|
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