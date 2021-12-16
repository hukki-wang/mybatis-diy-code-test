package com.hukki.example.mybatisframework.jdbc.domian.config;

import com.hukki.example.mybatisframework.jdbc.domian.sql.ParameterMapping;

import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 5:39 下午
 * @des
 */
public class BoundSql {

    private String sqlText;

    private List<ParameterMapping> parameterMappingList;

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
