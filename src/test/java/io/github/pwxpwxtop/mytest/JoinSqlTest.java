package io.github.pwxpwxtop.mytest;

import io.github.pwxpwxtop.core.Table;

import io.github.pwxpwxtop.model.Scroll;
import io.github.pwxpwxtop.model.ScrollStudent;

/**
 * Description:
 * Author:         PWX
 * CreateDate:     2024/4/6 15:52
 */
public class JoinSqlTest {

    public static void main(String[] args) {
        Table table1 = Table.gen(ScrollStudent.class);
        Table table2 = Table.gen(Scroll.class);
        System.out.println(table1.leftJoin(
                table2, "scroll_id", "id")
                .eq("name", "张三")
                .getSql());
    }

}
