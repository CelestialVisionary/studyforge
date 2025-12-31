
# Java领域模型（POJO / Entity / VO/ DTO / BO）


## 一、为什么需要领域模型？
在Java开发中，数据在不同场景（如数据库交互、前后端传输、前端展示、业务处理）中承担的角色不同：  
- 数据库存储需要与表结构严格对应；  
- 前后端传输需要精简敏感信息；  
- 前端展示需要格式化数据；  
- 业务处理需要组合多源数据。  

**领域模型的作用**：通过“给数据贴标签”，明确数据在不同阶段的职责，避免混乱（如直接用数据库实体返回给前端可能暴露密码等敏感字段）。  


## 二、核心领域模型对比总表
| 模型名称   | 全称                  | 核心定位                       | 应用场景                            | 数据流向              | 是否含业务逻辑 |
| ---------- | --------------------- | ------------------------------ | ----------------------------------- | --------------------- | -------------- |
| **POJO**   | Plain Old Java Object | 最基础的Java实体，无任何约束   | 所有模型的“基类”概念                | 无特定流向            | 无             |
| **Entity** | 实体类                | 映射数据库表结构的“数据库对象” | 与数据库交互（ORM框架）             | 数据库↔后端（持久化） | 无             |
| **DTO**    | Data Transfer Object  | 封装数据传输的“快递盒”         | 前后端交互、服务间调用（传参/返回） | 前端→后端、服务→服务  | 无             |
| **VO**     | Value （View）Object  | 封装前端展示数据的“展示板”     | 后端返回给前端的展示数据            | 后端→前端（仅输出）   | 无             |
| **BO**     | Business Object       | 封装业务逻辑的“业务载体”       | 服务层（Service）内部业务处理       | 后端内部流转          | 有（业务计算） |


## 三、分模型详解（附示例）

### 1. POJO（最基础的“裸数据”）
- **全称**：Plain Old Java Object（普通老式Java对象）  
- **核心定位**：不依赖任何框架、无特殊约束的基础Java类，仅包含属性、get/set方法和构造方法。  
- **本质**：所有领域模型的“最小单元”——Entity、DTO、VO等本质上都是POJO的“特殊形态”（增加了场景约束）。  

**示例代码**：  
```java
// 纯POJO，无任何注解/继承，仅存数据
public class UserPojo {
    private Long id;
    private String name;
    private Integer age;

    // 必须有：无参构造、get/set方法
    public UserPojo() {}
    // get/set省略
}
```

**关键特点**：  
- 不继承任何特定类（如`Serializable`可选，非必须）；  
- 不实现任何框架接口（如MyBatis的`BaseMapper`）；  
- 仅用于承载数据，无业务逻辑方法。  


### 2. Entity（数据库的“镜像”）
- **全称**：实体类（无缩写，强调“与数据库关联”）  
- **核心定位**：与数据库表**一一映射**的实体，是“数据库数据在Java中的化身”。  
- **核心场景**：通过MyBatis、JPA等ORM框架操作数据库（查询、新增、修改）。  

**示例代码**：  
```java
// 与数据库user表严格映射
@TableName("user") // MyBatis注解：指定对应表名
public class UserEntity {
    @TableId(type = IdType.AUTO) // 主键+自增策略
    private Long id; // 对应表中id字段（bigint类型）
    
    @TableField("username") // 字段名与表一致（若相同可省略）
    private String username; // 对应表中username字段（varchar）
    
    private String password; // 对应表中password字段（敏感字段，需加密存储）
    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime; // 对应表中create_time字段（datetime）

    // 无参构造、get/set方法
}
```

**关键特点**：  
- 字段名、类型与数据库表字段完全一致（含主键、索引、自增等配置）；  
- 带有ORM框架注解（如`@TableName`、`@Id`），明确与数据库的映射关系；  
- 包含数据库层面的属性（如`createTime`、`updateTime`），但**无业务逻辑**。  


### 3. DTO（数据传输的“快递盒”）
- **全称**：Data Transfer Object（数据传输对象）  
- **核心定位**：专门用于**数据传输**的载体，解决“传输效率”和“数据安全”问题。  
- **核心场景**：  
  - 前端→后端：接收前端提交的参数（如登录、表单提交）；  
  - 服务间调用：微服务A向微服务B传递数据（仅传必要字段）。  

**示例1：接收前端登录参数**  
```java
// 前端登录时传递的参数（仅包含必要字段）
public class LoginDTO {
    @NotBlank(message = "用户名不能为空") // 可加参数校验注解
    private String username;
    
    @NotBlank(message = "密码不能为空")
    private String password;

    // get/set方法
}
```

**示例2：服务间传输用户简信息**  
```java
// 微服务间传输的用户数据（屏蔽敏感字段）
public class UserSimpleDTO {
    private Long id;
    private String username; // 仅传用户名，不含password
    private String role; // 仅传必要的角色信息

    // get/set方法
}
```

**关键特点**：  
- 字段按需设计（仅包含传输所需的字段，避免冗余）；  
- 可包含参数校验注解（如`@NotBlank`），用于前端传参校验；  
- 不包含数据库相关字段（如`createTime`），与数据库解耦。  


### 4. VO（前端展示的“展示板”）
- **全称**：Value Object（值对象）  
- **核心定位**：专门为**前端展示**准备的数据，是“后端给前端的最终数据包装”。  
- **核心场景**：后端接口返回给前端的数据（如页面列表、详情页展示）。  

**示例代码**：  
```java
// 前端用户列表页展示的数据（组合多表字段+格式化）
public class UserListVO {
    private Long userId; // 前端习惯用userId，而非id
    
    private String username; // 用户名（来自UserEntity）
    
    private String roleName; // 角色名称（来自RoleEntity，UserEntity中无此字段）
    
    private String createTimeStr; // 格式化后的时间（如"2024-05-01"，前端直接展示）

    // get/set方法
}
```

**关键特点**：  
- 字段名可贴合前端习惯（如`userId`而非`id`）；  
- 可组合多个Entity的字段（如用户信息+角色信息）；  
- 包含格式化后的字段（如日期转字符串），减轻前端处理成本；  
- **绝对不含敏感字段**（如`password`绝不会出现在VO中）。  


### 5. BO（业务处理的“计算器”）
- **全称**：Business Object（业务对象）  
- **核心定位**：服务层（Service）内部使用的“业务数据载体”，包含业务逻辑处理结果。  
- **核心场景**：复杂业务计算（如订单金额计算、用户积分统计）。  

**示例代码**：  
```java
// 订单业务处理的BO（包含业务计算逻辑）
public class OrderBO {
    // 基础数据（来自Entity）
    private OrderEntity order; // 订单基本信息
    private List<OrderItemEntity> items; // 订单项列表
    
    // 业务计算结果（非数据库字段）
    private BigDecimal totalAmount; // 总金额（计算：sum(单价×数量)）
    private BigDecimal discountAmount; // 折扣金额（根据会员等级计算）
    private BigDecimal payAmount; // 实付金额（totalAmount - discountAmount）

    // 业务方法：计算金额（包含核心业务逻辑）
    public void calculateAmount(UserEntity user) {
        // 1. 计算总金额
        this.totalAmount = items.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // 2. 计算折扣（会员等级越高，折扣越大）
        this.discountAmount = calculateDiscount(user.getVipLevel(), totalAmount);
        
        // 3. 计算实付金额
        this.payAmount = totalAmount.subtract(discountAmount);
    }
    
    // 私有辅助方法（业务逻辑细节）
    private BigDecimal calculateDiscount(Integer vipLevel, BigDecimal total) {
        if (vipLevel >= 3) return total.multiply(new BigDecimal("0.1")); // 10%折扣
        if (vipLevel >= 2) return total.multiply(new BigDecimal("0.05")); // 5%折扣
        return BigDecimal.ZERO;
    }

    // get/set方法
}
```

**关键特点**：  
- 可组合多个Entity或DTO的数据（如订单+订单项+用户信息）；  
- 包含业务计算结果（非数据库存储的临时字段）；  
- 有业务逻辑方法（如`calculateAmount`），是“业务逻辑的载体”；  
- 仅在服务层内部使用，不暴露给前端或其他服务。  


## 四、场景串联：一个流程看懂所有模型
以“用户查询订单详情”为例，看各模型如何配合：  
1. **数据库查询**：通过`OrderEntity`和`OrderItemEntity`从数据库获取原始订单数据；  
2. **业务处理**：在Service层用`OrderBO`组合上述Entity，计算`totalAmount`、`discountAmount`等业务数据；  
3. **传输准备**：若需要调用“用户服务”获取会员信息，用`UserSimpleDTO`传输精简的用户数据；  
4. **前端展示**：将`OrderBO`中的核心信息（过滤敏感字段、格式化日期）封装到`OrderDetailVO`，返回给前端展示。  

整个过程中，POJO是所有模型的基础（上述模型均符合POJO的基本结构）。  


## 五、核心原则总结
1. **各司其职**：  
   - 数据库交互用Entity，传输数据用DTO，展示用VO，业务处理用BO，避免混用（如禁止用Entity直接返回给前端）。  
2. **按需设计**：  
   - 简单项目可简化（如只用Entity+DTO），复杂项目必须严格区分（如电商系统的订单模块）。  
3. **转换工具**：  
   - 用MapStruct、BeanUtils等工具实现模型间转换（如Entity→BO、BO→VO），减少重复代码。  


通过本教案，可清晰掌握各领域模型的定位和用法，在实际开发中做到“数据流转有章法，代码逻辑更清晰”。