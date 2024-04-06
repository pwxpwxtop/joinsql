package io.github.pwxpwxtop.utils;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Author:         PWX
 * CreateDate:     2024/4/6 11:03
 */
public class Transform {

    //驼峰转换成下划线
    public static String toUnderLine(String line){
        line = line.substring(0,1).toLowerCase()+line.substring(1);
        Matcher matcher = Pattern.compile("([A-Z])").matcher(line.trim());
        int count = 0;
        while (matcher.find()) {
            String str = matcher.group();
            line = line.replace(str, "_" + str.toLowerCase());
        }
        return line;
    }

    public static String [] listToArray(List<String> list){
        String [] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return  array;
    }


    public static String splitJoin(String joinStr, String [] array){
        StringJoiner joiner = new StringJoiner(", ");
        for (String s : array) {
            joiner.add(joinStr + "." + s);
        }
        return joiner.toString();
    }

    public static String arrToJoinStr(String [] array){
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (String s : array) {
            joiner.add(s);
        }
        return joiner.toString();
    }

    public static String getAlias(String name){
        //方法一，用Array.charAt(i)方法依次取字符,用编码进行判断
        StringBuilder alias = new StringBuilder();
        for (int i=0; i< name.length(); i++)
        {
            char c = name.charAt(i);
            if (c >= 'A' && c <= 'Z'){
                alias.append(c);
            }
        }

        if ("".equals(alias.toString())){
            return name.toLowerCase();
        }else {
            return alias.toString().toLowerCase();
        }
    }

}
