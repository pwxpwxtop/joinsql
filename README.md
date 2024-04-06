# 这是一个可以通过java代码凭借sql语句



#### 🚀maven引入

```xml
<dependency>
    <groupId>io.github.pwxpwxtop</groupId>
    <artifactId>joinsql</artifactId>
    <version>1.0.0</version>
</dependency>
```



### 这是我创建的ScrollStuendt.java实体类

```java
@Data
public class ScrollStudent {

    //学生id
    private String id;

    //学上姓名
    private String name;

    //学生年龄
    private Integer age;

    //学生班级id
    private String classId;

    //学校id
    private String scrollId;
}
```



### mysql数据库表

```sql
CREATE TABLE `scroll_student` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  `scroll_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```



### 调用工具类Table.gen(Class cls)

```java
Table table = Table.gen(ScrollStudent.class);
System.out.println(table.getSql());

打印如下：
select ss.id, ss.name, ss.age, ss.class_id, ss.scroll_id from scroll_student as ss where 1=1    
```



### 多个表查询

#### 创建Scroll.java

```java
@Data
public class Scroll {

    //学校id
    private String id;

    //学校名称
    private String name;

}
```



```sql
CREATE TABLE `scroll` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```



#### 使用方法1

```java
Table table1 = Table.gen(ScrollStudent.class);
Table table2 = Table.gen(Scroll.class);
System.out.println(
    table1.leftJoin(
        table2, "scroll_id", "id")
    .eq("name", "张三")
    .getSql()
);

打印结果：
select ss.id, ss.name, ss.age, ss.class_id, ss.scroll_id from scroll_student as ss left join scroll as s on ss.scroll_id = s.id where 1=1  and ss.name = '张三' 
```



项目待更新。。。
