package io.github.pwxpwxtop.mytest;

import io.github.pwxpwxtop.core.Table;

import io.github.pwxpwxtop.model.Scroll;

/**
 * Description:
 * Author:         PWX
 * CreateDate:     2024/4/6 15:52
 */
public class JoinSqlTest {

    public static void main(String[] args) {
        Table.gen(Scroll.class);
        System.out.println(Table.gen(Scroll.class).getSql());
    }

}
