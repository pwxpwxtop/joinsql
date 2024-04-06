package io.github.pwxpwxtop.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:         PWX
 * CreateDate:     2024/4/6 10:47
 */
public class SqlUtils {


    public  static List<String> columns(Class<?> cls ){
        List<String> list = new ArrayList<>();
        Field[] fields =  cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            list.add(Transform.toUnderLine(field.getName()));
        }
        Class<?> superClass = cls.getSuperclass();
        if (superClass != null){
            List<String> list1 = columns(superClass);
            list.addAll(list1);
        }
        return list;
    }
}
