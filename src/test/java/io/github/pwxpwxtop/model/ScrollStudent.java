package io.github.pwxpwxtop.model;

import lombok.Data;

/**
 * Description:
 * Author:         PWX
 * CreateDate:     2024/4/6 10:49
 */
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
