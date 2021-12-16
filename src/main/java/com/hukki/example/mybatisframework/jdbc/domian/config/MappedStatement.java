package com.hukki.example.mybatisframework.jdbc.domian.config;

/**
 * @author wanghui
 * @date 2021/12/15 4:47 下午
 * @des 存放mapper和xml的映射关系
 */
public class MappedStatement {

    // id标识
    private String id;
    // 返回值类型
    private String resultType;
    // 参数值类型
    private String paramterType;
    // sql语句
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
