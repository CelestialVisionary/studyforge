# 硅谷AI链习室API文档


[TOC]






# 一、考试管理


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
|submitAnswerVos|考试答案提交数据|body|true|array|SubmitAnswerVo|
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"answerRecords": [
			{
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"questions": [
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
|startExamVo|开始考试请求参数|body|true|StartExamVo|StartExamVo|
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"answerRecords": [
			{
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"questions": [
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"answerRecords": [
			{
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"questions": [
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"examId": 1,
			"studentName": "张三",
			"score": 85,
			"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
			"startTime": "",
			"endTime": "",
			"status": "已批阅",
			"windowSwitches": 2,
			"answerRecords": [
				{
					"id": 0,
					"createTime": "",
					"isDeleted": "",
					"examRecordId": 1,
					"questionId": 5,
					"userAnswer": "A",
					"score": 5,
					"isCorrect": 1,
					"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
				}
			],
			"paper": {
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"name": "Java基础知识测试",
				"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
				"status": "PUBLISHED",
				"totalScore": 100,
				"questionCount": 20,
				"duration": 120,
				"questions": [
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
		}
	]
}
```


# 二、试卷管理（实现）


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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;questions|题目信息|array|Question|
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


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"questions": [
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
|paperVo|试卷创建请求参数|body|true|PaperVo|PaperVo|
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;questions|题目信息|array|Question|
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


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"questions": [
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
|paperVo|试卷创建请求参数|body|true|PaperVo|PaperVo|
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;questions|题目信息|array|Question|
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


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"questions": [
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
|aiPaperVo|AI智能组卷请求参数|body|true|AiPaperVo|AiPaperVo|
|&emsp;&emsp;name|试卷名称||true|string||
|&emsp;&emsp;description|试卷描述||false|string||
|&emsp;&emsp;duration|考试时长（分钟）||false|integer(int32)||
|&emsp;&emsp;rules|AI组卷规则配置||true|array|RuleVo|
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;questions|题目信息|array|Question|
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


**响应示例**:
```javascript
{
	"code": 200,
	"message": "操作成功",
	"data": {
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"name": "Java基础知识测试",
		"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
		"status": "PUBLISHED",
		"totalScore": 100,
		"questionCount": 20,
		"duration": 120,
		"questions": [
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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;questionCount|题目数量|integer(int32)||
|&emsp;&emsp;duration|考试时长，单位：分钟|integer(int32)||
|&emsp;&emsp;questions|题目信息|array|Question|
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
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"questions": [
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
	]
}
```


# 三、考试记录管理


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
|&emsp;&emsp;id|主键|integer(int64)||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;isDeleted|逻辑删除|string(byte)||
|&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer(int32)||
|&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;score|考试得分|integer(int32)||
|&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;startTime|考试开始时间|string(date-time)||
|&emsp;&emsp;endTime|考试结束时间|string(date-time)||
|&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer(int32)||
|&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
		"id": 0,
		"createTime": "",
		"isDeleted": "",
		"examId": 1,
		"studentName": "张三",
		"score": 85,
		"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
		"startTime": "",
		"endTime": "",
		"status": "已批阅",
		"windowSwitches": 2,
		"answerRecords": [
			{
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"examRecordId": 1,
				"questionId": 5,
				"userAnswer": "A",
				"score": 5,
				"isCorrect": 1,
				"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
			}
		],
		"paper": {
			"id": 0,
			"createTime": "",
			"isDeleted": "",
			"name": "Java基础知识测试",
			"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
			"status": "PUBLISHED",
			"totalScore": 100,
			"questionCount": 20,
			"duration": 120,
			"questions": [
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
|&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;examId|试卷ID，关联的考试试卷|integer||
|&emsp;&emsp;&emsp;&emsp;studentName|考生姓名|string||
|&emsp;&emsp;&emsp;&emsp;score|考试得分|integer||
|&emsp;&emsp;&emsp;&emsp;answers|答题记录，JSON格式存储所有答题内容|string||
|&emsp;&emsp;&emsp;&emsp;startTime|考试开始时间|string||
|&emsp;&emsp;&emsp;&emsp;endTime|考试结束时间|string||
|&emsp;&emsp;&emsp;&emsp;status|考试状态,可用值:进行中,已完成,已批阅|string||
|&emsp;&emsp;&emsp;&emsp;windowSwitches|窗口切换次数，用于监控考试过程中的异常行为|integer||
|&emsp;&emsp;&emsp;&emsp;answerRecords|答题记录信息|array|AnswerRecord|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;examRecordId|关联的考试记录ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userAnswer|学生提交的答案|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|该题得分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|答题正确性,可用值:0,1,2|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;aiCorrection|AI智能批改的评价意见|string||
|&emsp;&emsp;&emsp;&emsp;paper|试卷信息|Paper|Paper|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|试卷名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|试卷描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;status|试卷状态,可用值:DRAFT,PUBLISHED,STOPPED|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;totalScore|试卷总分|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionCount|题目数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;duration|考试时长，单位：分钟|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questions|题目信息|array|Question|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;title|题目标题内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|题目类型,可用值:CHOICE,JUDGE,TEXT|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;multi|是否为多选题，仅选择题有效|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;categoryId|题目分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;difficulty|题目难度等级,可用值:EASY,MEDIUM,HARD|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;score|题目默认分值|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;paperScore|在特定试卷中的分值|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;analysis|题目解析，详细的答案说明|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;choices|题目选项信息|array|QuestionChoice|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;content|选项内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isCorrect|是否为正确答案|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sort|选项排序序号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|题目答案信息|QuestionAnswer|QuestionAnswer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;questionId|关联的题目ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;answer|标准答案内容|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;keywords|评分关键词，用于简答题AI评分|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;category|题目分类信息|Category|Category|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|主键|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDeleted|逻辑删除|string||
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
				"id": 0,
				"createTime": "",
				"isDeleted": "",
				"examId": 1,
				"studentName": "张三",
				"score": 85,
				"answers": "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]",
				"startTime": "",
				"endTime": "",
				"status": "已批阅",
				"windowSwitches": 2,
				"answerRecords": [
					{
						"id": 0,
						"createTime": "",
						"isDeleted": "",
						"examRecordId": 1,
						"questionId": 5,
						"userAnswer": "A",
						"score": 5,
						"isCorrect": 1,
						"aiCorrection": "答案基本正确，但缺少关键概念的解释..."
					}
				],
				"paper": {
					"id": 0,
					"createTime": "",
					"isDeleted": "",
					"name": "Java基础知识测试",
					"description": "本试卷主要考察Java基础语法、面向对象编程等知识点",
					"status": "PUBLISHED",
					"totalScore": 100,
					"questionCount": 20,
					"duration": 120,
					"questions": [
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