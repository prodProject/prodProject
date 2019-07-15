/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.SQLQuery;

import com.prod.prodServer.CommonCode.Lists;
import com.prod.prodServer.CommonCode.Strings;
import java.util.List;

/**
 *
 * @author shubham
 */
public class SqlMaker {

    public static final String NULL = "NULL";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";

    private String m_query;

    // Constructor---------------------------------------------------------------------------------------
    private SqlMaker() {
        m_query = "";
    }

    private SqlMaker(Object query) {
        m_query = makeString(query);
    }

    // FactoryMethod--------------------------------------------------------------------------------------
    public static SqlMaker builder() {
        return new SqlMaker();
    }

    public static SqlMaker builder(Object query) {
        return new SqlMaker(query);
    }

    // Query----------------------------------------------------------------------------------------------
    public SqlMaker WITH(SqlMaker... queries) {
        return WITH(Lists.newArrayList(queries));
    }

    public SqlMaker WITH(List<SqlMaker> queries) {
        m_query = String.format("%s WITH %s", m_query, makeString(queries, ", "));
        return this;
    }

    public SqlMaker SELECT(Object... columns) {
        return SELECT(Lists.newArrayList(columns));
    }

    public SqlMaker SELECT(List columns) {
        m_query = String.format("%s SELECT %s", m_query, makeString(columns, ", "));
        return this;
    }

    public SqlMaker SELECT_DISTINCT(Object... columns) {
        return SELECT_DISTINCT(Lists.newArrayList(columns));
    }

    public SqlMaker SELECT_DISTINCT(List columns) {
        m_query = String.format("%s SELECT DISTINCT %s", m_query, makeString(columns, ", "));
        return this;
    }

    public SqlMaker FROM(Object from) {
        m_query = String.format("%s FROM %s", m_query, makeString(from));
        return this;
    }

    public SqlMaker WHERE(SqlMaker condition) {
        m_query = String.format("%s WHERE %s", m_query, makeString(condition));
        return this;
    }

    public SqlMaker EXISTS(SqlMaker query) {
        m_query = String.format("%s EXISTS (%s)", m_query, makeString(query));
        return this;
    }

    public SqlMaker NOT_EXISTS(SqlMaker query) {
        m_query = String.format("%s NOT EXISTS (%s)", m_query, makeString(query));
        return this;
    }

    public SqlMaker AS(Object alias) {
        m_query = String.format("%s AS %s", m_query, makeString(alias));
        return this;
    }

    public SqlMaker JOIN(Object... data) {
        return JOIN(Lists.newArrayList(data));
    }

    public SqlMaker JOIN(List data) {
        m_query = makeString(this, data, " JOIN ");
        return this;
    }

    public SqlMaker CROSS_JOIN(Object... data) {
        return CROSS_JOIN(Lists.newArrayList(data));
    }

    public SqlMaker CROSS_JOIN(List data) {
        m_query = makeString(this, data, " CROSS JOIN ");
        return this;
    }

    public SqlMaker FULL_JOIN(Object... data) {
        return FULL_JOIN(Lists.newArrayList(data));
    }

    public SqlMaker FULL_JOIN(List data) {
        m_query = makeString(this, data, " FULL JOIN ");
        return this;
    }

    public SqlMaker LEFT_JOIN(Object... data) {
        return LEFT_JOIN(Lists.newArrayList(data));
    }

    public SqlMaker LEFT_JOIN(List data) {
        m_query = makeString(this, data, " LEFT JOIN ");
        return this;
    }

    public SqlMaker RIGHT_JOIN(Object... data) {
        return RIGHT_JOIN(Lists.newArrayList(data));
    }

    public SqlMaker RIGHT_JOIN(List data) {
        m_query = makeString(this, data, " RIGHT JOIN ");
        return this;
    }

    public SqlMaker USING(Object... columns) {
        return USING(Lists.newArrayList(columns));
    }

    public SqlMaker USING(List columns) {
        m_query = String.format("%s USING (%s)", m_query, makeString(columns, ", "));
        return this;
    }

    public SqlMaker ON(SqlMaker condition) {
        m_query = String.format("%s ON %s", m_query, makeString(condition));
        return this;
    }

    public SqlMaker UNION_ALL(SqlMaker... data) {
        return UNION_ALL(Lists.newArrayList(data));
    }

    public SqlMaker UNION_ALL(List<SqlMaker> data) {
        m_query = makeString(this, data, " UNION ALL ");
        return this;
    }

    public SqlMaker UNION_DISTINCT(SqlMaker... data) {
        return UNION_DISTINCT(Lists.newArrayList(data));
    }

    public SqlMaker UNION_DISTINCT(List<SqlMaker> data) {
        m_query = makeString(this, data, " UNION DISTINCT ");
        return this;
    }

    public SqlMaker IN_S(Object... values) {
        return IN_S(Lists.newArrayList(values));
    }

    public SqlMaker IN_S(List values) {
        m_query = String.format("%s IN ('%s')", m_query, makeString(values, "', '"));
        return this;
    }

    public SqlMaker IN(Object... values) {
        return IN(Lists.newArrayList(values));
    }

    public SqlMaker IN(List values) {
        m_query = String.format("%s IN (%s)", m_query, makeString(values, ", "));
        return this;
    }

    public SqlMaker IN_UNNEST(SqlMaker array) {
        m_query = String.format("%s IN UNNEST(%s)", m_query, makeString(array));
        return this;
    }

    public SqlMaker IS(String value) {
        m_query = String.format("%s IS %s", m_query, value);
        return this;
    }

    public SqlMaker IS_NOT(String value) {
        m_query = String.format("%s IS NOT %s", m_query, value);
        return this;
    }

    public SqlMaker NOT() {
        m_query = String.format("%s NOT", m_query);
        return this;
    }

    public SqlMaker AND(Object... values) {
        return AND(Lists.newArrayList(values));
    }

    public SqlMaker AND(List values) {
        m_query = makeString(this, values, " AND ");
        return this;
    }

    public SqlMaker OR(Object... values) {
        return OR(Lists.newArrayList(values));
    }

    public SqlMaker OR(List values) {
        m_query = makeString(this, values, " OR ");
        return this;
    }

    public SqlMaker BETWEEN_S(Object start, Object end) {
        m_query = String.format("%s BETWEEN '%s' AND '%s'", m_query, makeString(start),
                makeString(end));
        return this;
    }

    public SqlMaker BETWEEN(Object start, Object end) {
        m_query = String.format("%s BETWEEN %s AND %s", m_query, makeString(start), makeString(end));
        return this;
    }

    public SqlMaker GROUP_BY(Object... expressions) {
        return GROUP_BY(Lists.newArrayList(expressions));
    }

    public SqlMaker GROUP_BY(List expressions) {
        m_query = String.format("%s GROUP BY %s", m_query, makeString(expressions, ", "));
        return this;
    }

    public SqlMaker GROUP_BY_ROLLUP(Object... expressions) {
        return GROUP_BY_ROLLUP(Lists.newArrayList(expressions));
    }

    public SqlMaker GROUP_BY_ROLLUP(List expressions) {
        m_query = String.format("%s GROUP BY ROLLUP (%s)", m_query, makeString(expressions, ", "));
        return this;
    }

    public SqlMaker OVER(SqlMaker expression) {
        m_query = String.format("%s OVER (%s)", m_query, makeString(expression));
        return this;
    }

    public SqlMaker HAVING(SqlMaker condition) {
        m_query = String.format("%s HAVING %s", m_query, makeString(condition));
        return this;
    }

    public SqlMaker PARTITION_BY(Object... expressions) {
        return PARTITION_BY(Lists.newArrayList(expressions));
    }

    public SqlMaker PARTITION_BY(List expressions) {
        m_query = String.format("%s PARTITION BY %s", m_query, makeString(expressions, ", "));
        return this;
    }

    public SqlMaker ORDER_BY(SqlMaker... expressions) {
        return ORDER_BY(Lists.newArrayList(expressions));
    }

    public SqlMaker ORDER_BY(List<SqlMaker> expressions) {
        m_query = String.format("%s ORDER BY %s", m_query, makeString(expressions, ", "));
        return this;
    }

    public SqlMaker ASC(Object... expressions) {
        return ASC(Lists.newArrayList(expressions));
    }

    public SqlMaker ASC(List expressions) {
        List<String> orderList = Lists.newArrayList();
        for (String exp : makeString(expressions)) {
            orderList.add(String.format("%s ASC", exp));
        }
        m_query = String.format("%s %s", m_query, makeString(orderList, ", "));
        return this;
    }

    public SqlMaker DESC(Object... expressions) {
        return DESC(Lists.newArrayList(expressions));
    }

    public SqlMaker DESC(List expressions) {
        List<String> orderList = Lists.newArrayList();
        for (String exp : makeString(expressions)) {
            orderList.add(String.format("%s DESC", exp));
        }
        m_query = String.format("%s %s", m_query, makeString(orderList, ", "));
        return this;
    }

    public SqlMaker LIMIT(int limit) {
        if (limit > 0) {
            m_query = String.format("%s LIMIT %d", m_query, limit);
        }
        return this;
    }

    public SqlMaker CASE() {
        m_query = String.format("%s CASE", m_query);
        return this;
    }

    public SqlMaker CASE(Object expression) {
        m_query = String.format("%s CASE %s", m_query, makeString(expression));
        return this;
    }

    public SqlMaker WHEN_S(Object expression) {
        m_query = String.format("%s WHEN '%s'", m_query, makeString(expression));
        return this;
    }

    public SqlMaker WHEN(Object expression) {
        m_query = String.format("%s WHEN %s", m_query, makeString(expression));
        return this;
    }

    public SqlMaker THEN_S(Object expression) {
        m_query = String.format("%s THEN '%s'", m_query, makeString(expression));
        return this;
    }

    public SqlMaker THEN(Object expression) {
        m_query = String.format("%s THEN %s", m_query, makeString(expression));
        return this;
    }

    public SqlMaker ELSE_S(Object expression) {
        m_query = String.format("%s ELSE '%s'", m_query, makeString(expression));
        return this;
    }

    public SqlMaker ELSE(Object expression) {
        m_query = String.format("%s ELSE %s", m_query, makeString(expression));
        return this;
    }

    public SqlMaker END() {
        m_query = String.format("%s END", m_query);
        return this;
    }

    public SqlMaker UNNEST(SqlMaker array) {
        m_query = String.format("%s UNNEST(%s)", m_query, makeString(array));
        return this;
    }

    public SqlMaker WITH_OFFSET() {
        m_query = String.format("%s WITH OFFSET", m_query);
        return this;
    }

    public SqlMaker STRUCT(Object... values) {
        return STRUCT(Lists.newArrayList(values));
    }

    public SqlMaker STRUCT(List values) {
        m_query = String.format("%s STRUCT(%s)", m_query, makeString(values, ", "));
        return this;
    }

    public SqlMaker ARRAY(Object... values) {
        return ARRAY(Lists.newArrayList(values));
    }

    public SqlMaker ARRAY(List values) {
        m_query = String.format("%s ARRAY[%s]", m_query, makeString(values, ", "));
        return this;
    }

    public SqlMaker FROM(List values) {
        Object obj = "";
        boolean insertComma = false;
        for (Object object : values) {
            if (insertComma) {
                obj = obj + "," + object;
            } else {
                obj = object;
                insertComma = true;
            }
        }
        return SqlMaker.builder().FROM(obj);
    }

    public SqlMaker FROM(Object... values) {
        return FROM(Lists.newArrayList(values));
    }

    public SqlMaker SUM(Object column) {
        return SqlMaker.builder("SUM(%s)").format(column);
    }

    public SqlMaker PERCENT(Object entity, Object total) {
        return SqlMaker.builder("CAST(IFNULL(%s * 100 / NULLIF(%s, 0), 0) AS INT64)").format(entity,
                total);
    }

    public SqlMaker FULL_OUTER_JOIN(SqlMaker entity, SqlMaker cond) {
        return SqlMaker.builder("FULL OUTER JOIN %s ON %s").format(entity, cond);
    }

    public SqlMaker FULL_JOIN(SqlMaker entity, SqlMaker cond) {
        return SqlMaker.builder("FULL JOIN %s ON %s").format(entity, cond);
    }

    public SqlMaker IS_NOT_NULL(Object... objects) {
        return SqlMaker.builder("%s IS NOT NULL").format(objects);
    }

    public SqlMaker COUNT(SqlMaker col) {
        return SqlMaker.builder("COUNT(%s)").format(col);
    }

    public SqlMaker COUNTIF(Object values) {
        return SqlMaker.builder("COUNTIF(%s)").format(values);
    }

    public SqlMaker COUNTIF(SqlMaker values) {
        return SqlMaker.builder("COUNTIF(%s)").format(values);
    }

    public SqlMaker COUNTIF_AND(Object... values) {
        return SqlMaker.builder("COUNTIF(%s)").format(SqlMaker.builder().AND(Lists.newArrayList(values)));
    }

    public SqlMaker COUNTIF_AND(List values) {
        return SqlMaker.builder("COUNTIF(%s)").format(SqlMaker.builder().AND(values));
    }

    public SqlMaker COUNTIF_OR(Object... values) {
        return SqlMaker.builder("COUNTIF(%s)").format(SqlMaker.builder().OR(Lists.newArrayList(values)));
    }

    public SqlMaker COUNTIF_OR(List values) {
        return SqlMaker.builder("COUNTIF(%s)").format(SqlMaker.builder().OR(values));
    }

    public SqlMaker getLessThenEqual(SqlMaker query1, SqlMaker query2) {
        return SqlMaker.builder(query1 + "<=" + query2);
    }

    public SqlMaker getGreaterThenEqual(SqlMaker query1, SqlMaker query2) {
        return SqlMaker.builder(query1 + ">=" + query2);
    }

    public SqlMaker getLessThen(SqlMaker query1, SqlMaker query2) {
        return SqlMaker.builder(query1 + "<" + query2);
    }

    public SqlMaker getGreaterThen(SqlMaker query1, SqlMaker query2) {
        return SqlMaker.builder(query1 + ">" + query2);
    }

    public SqlMaker getEqualsTo(SqlMaker query1, SqlMaker query2) {
        return SqlMaker.builder(query1 + "=" + query2);
    }

    public SqlMaker getNotEqualsTo(SqlMaker query1, SqlMaker query2) {
        return SqlMaker.builder(query1 + "!=" + query2);
    }

    public SqlMaker AVG(Object value) {
        return SqlMaker.builder("AVG(%s)").format(value);
    }

    public SqlMaker IFNULL(SqlMaker entity) {
        return SqlMaker.builder("IFNULL(%s , 0)").format(entity);
    }

    public SqlMaker PERCENT(SqlMaker entity, SqlMaker total) {
        return SqlMaker.builder("CAST(IFNULL(%s * 100 / NULLIF(%s, 0), 0) AS INT64)").format(entity,
                total);
    }

    public SqlMaker JOIN_ALL(List<SqlMaker> join) {
        SqlMaker joinStatement = SqlMaker.builder();
        for (SqlMaker bQuery : join) {
            joinStatement.append(bQuery);
        }
        return joinStatement;
    }

    public SqlMaker JOIN_ALL(SqlMaker... join) {
        SqlMaker joinStatement = SqlMaker.builder();
        for (SqlMaker bQuery : join) {
            joinStatement.append(bQuery);
        }
        return joinStatement;
    }

    // Builder--------------------------------------------------------------------------------------
    public String build() {
        return String.format("%s;", buildPartial());
    }

    public String buildPartial() {
        return m_query.trim();
    }

    // Helper----------------------------------------------------------------------------------------
    public SqlMaker append(Object value) {
        m_query = String.format("%s %s", m_query, makeString(value));
        return this;
    }

    public SqlMaker parenthesis() {
        m_query = String.format("(%s)", buildPartial());
        return this;
    }

    public SqlMaker singleQuoted() {
        m_query = String.format("'%s'", buildPartial());
        return this;
    }

    public SqlMaker backTicked() {
        m_query = String.format("`%s`", buildPartial());
        return this;
    }

    // Formatter--------------------------------------------------------------------------------------
    public SqlMaker format(Object... args) {
        Object[] argsArray = new Object[args.length];
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof SqlMaker) {
                argsArray[i] = ((SqlMaker) args[i]).buildPartial();
            } else {
                argsArray[i] = args[i];
            }
        }
        m_query = String.format(m_query, argsArray);
        return this;
    }

    @Override
    public String toString() {
        return m_query;
    }

    // Private-----------------------------------------------------------------------------------------
    private String makeString(Object obj) {
        if (obj instanceof SqlMaker) {
            return ((SqlMaker) obj).buildPartial();
        }
        return obj.toString();
    }

    private List<String> makeString(List objs) {
        List<Object> correctedList = Lists.newArrayList();
        for (Object obj : objs) {
            if (obj instanceof List) {
                correctedList.addAll((List) obj);
            } else {
                correctedList.add(obj);
            }
        }
        List<String> list = Lists.newArrayList();
        for (Object obj : correctedList) {
            String str = makeString(obj);
            if (Strings.notEmpty(str)) {
                list.add(str);
            }
        }
        return list;
    }

    private String makeString(List objs, String join) {
        return Strings.join(makeString(objs), join);
    }

    private String makeString(Object obj, List objs, String join) {
        List<Object> list = Lists.newArrayList();
        list.add(obj);
        list.addAll(objs);
        return makeString(list, join);
    }

    public String getQuotedValue(String data) {
        return "'" + data + "'";
    }
}
