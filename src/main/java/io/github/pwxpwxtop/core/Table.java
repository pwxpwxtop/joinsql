package io.github.pwxpwxtop.core;


import io.github.pwxpwxtop.enums.LOGIC;
import io.github.pwxpwxtop.utils.SqlUtils;
import io.github.pwxpwxtop.utils.Transform;
import lombok.Data;


/**
 * Description:
 * Author:         PWX
 * CreateDate:     2024/4/6 9:59
 */
@Data
public class Table {

    //表别名
    private String alias;

    //表名称
    private String tableName;

    //所有列
    private String [] columns;


    private StringBuilder select = new StringBuilder("select ");

    private StringBuilder from = new StringBuilder();

    private StringBuilder where = new StringBuilder(" where 1=1");

    public static Table gen(Class<?> cls, String alias){
        Table table = new Table();
        table.setTableName(Transform.toUnderLine(cls.getSimpleName()));
        table.setColumns(Transform.listToArray(SqlUtils.columns(cls)));
        if (alias == null){
            table.setAlias(Transform.getAlias(cls.getSimpleName()));//获取别名
        }else{
            table.setAlias(alias);
        }
        table.select.append(Transform.splitJoin(table.getAlias(), table.getColumns()));
        return table;
    }

    public static Table gen(Class<?> cls) {
        return gen(cls, null);
    }

    private Table join(Table table, String key1, String key2, boolean isAuto, String join){
        if (isAuto) {
            from.append(join).append(table.getTableName())
                    .append(" as ").append(table.getAlias())
                    .append(" on ")
                    .append(alias).append(".").append(key1)
                    .append(" = ")
                    .append(table.getAlias()).append(".").append(key2);
        }else{
            from.append(" left join ").append(table.getTableName())
                    .append(" as ").append(table.getAlias())
                    .append(" on ")
                    .append(key1)
                    .append(" = ")
                    .append(key2);
        }
        return this;
    }

    public Table leftJoin(Table table, String key1, String key2, boolean isAuto){
        join(table, key1, key2, isAuto, " left join ");
        return this;
    }

    public Table leftJoin(Table table, String key1, String key2){
        leftJoin(table, key1, key2, true);
        return this;
    }

    //左查询
    public Table rightJoin(Table table, String key1, String key2, boolean isAuto){
        join(table, key1, key2, isAuto, " right join ");
        return this;
    }

    public Table rightJoin(Table table, String key1, String key2){
        rightJoin(table, key1, key2, true);
        return this;
    }

    public Table innerJoin(Table table, String key1, String key2, boolean isAuto){
        join(table, key1, key2, isAuto, " inner join ");
        return this;
    }

    public Table innerJoin(Table table, String key1, String key2){
        innerJoin(table, key1, key2, true);
        return this;
    }



    private void append(LOGIC logic){
        switch (logic){
            case OR:
                where.append(" or ");
                break;
            case AND:
                where.append(" and ");
                break;
            case NOT:
                where.append(" not ");
                break;
        }
    }

    public Table eq(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" = ").append(value);
        }else {
            where.append(key).append(" = ").append(value);
        }

        return this;
    }

    public Table eq(String key, LOGIC logic, String value){
        eq(key,value, logic, true);
        return this;
    }

    public Table eq(String key, String value){
        eq(key,value, LOGIC.AND, true);
        return this;
    }

    public Table lt(String key, String value, LOGIC logic) {
        lt(key, value, logic, true);
        return this;
    }

    public Table lt(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);

        if (isAuto){
            where.append(alias).append(".").append(key).append(" < '").append(value).append("'");
        } else {
            where.append(key).append(" < '").append(value).append("'");
        }

        return this;
    }

    public Table lt(String key, String value){
        lt(key,value, LOGIC.AND, true);
        return this;
    }

    public Table le(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" <= '").append(value).append("'");
        } else {
            where.append(key).append(" <= '").append(value).append("'");
        }

        return this;
    }

    public Table le(String key, LOGIC logic, String value){
        le(key,value, logic, true);
        return this;
    }

    public Table le(String key, String value){
        le(key, value, LOGIC.AND, true);
        return this;
    }

    public Table gt(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" > '").append(value).append("'");
        } else {
            where.append(key).append(" > '").append(value).append("'");
        }
        return this;
    }


    public Table gt(String key, LOGIC logic, String value){
        gt(key,value, logic, true);
        return this;
    }

    public Table gt(String key, String value){
        gt(key, value, LOGIC.AND, true);
        return this;
    }

    public Table ge(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" >= '").append(value).append("'");
        } else {
            where.append(key).append(" >= '").append(value).append("'");
        }
        return this;
    }


    public Table ge(String key, LOGIC logic, String value){
        ge(key,value, logic, true);
        return this;
    }
    public Table ge(String key, String value){
        ge(key, value, LOGIC.AND, true);
        return this;
    }


    public Table isNotNull(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" is not null");
        } else {
            where.append(key).append("  is not null ");
        }
        return this;
    }

    public Table isNotNull(String key, String value, LOGIC logic) {
        isNotNull(key, value, logic, true);
        return this;
    }

    public Table isNotNull(String key, String value){
        isNotNull(key, value, LOGIC.AND, true);
        return this;
    }


    public Table isNull(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" is null");
        } else {
            where.append(key).append("  is null ");
        }
        return this;
    }


    public Table isNull(String key, String value, LOGIC logic) {
        isNull(key, value, logic, true);
        return this;
    }

    public Table isNull(String key, String value){
        isNull(key, value, LOGIC.AND, true);
        return this;
    }

    public Table like(String key, String value, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" like '%").append(value).append("%'");
        } else {
            where.append(key).append(" like '%").append(value).append("%'");
        }
        return this;
    }

    public Table like(String key, String value, LOGIC logic){
        like(key, value, logic, true);
        return this;
    }

    public Table like(String key, String value){
        like(key, value, LOGIC.AND, true);
        return this;
    }


    public Table in(String key, String[] values, LOGIC logic, boolean isAuto){
        append(logic);
        if (isAuto){
            where.append(alias).append(".").append(key).append(" in ").append(Transform.arrToJoinStr(values));
        } else {
            where.append(key).append(" in ").append(Transform.arrToJoinStr(values));
        }
        return this;
    }

    public Table in(String key, String[] values, LOGIC logic){
        in(key, values, logic, true);
        return this;
    }

    public Table in(String key, String[] values){
        in(key, values, LOGIC.AND, true);
        return this;
    }


    public String getSql(){
        from.insert(0, " from " + tableName + " as " + alias);
        return select + from.toString() + where;
    }

    public String getSqlUpperCase(){
        return getSql().toUpperCase();
    }

}
