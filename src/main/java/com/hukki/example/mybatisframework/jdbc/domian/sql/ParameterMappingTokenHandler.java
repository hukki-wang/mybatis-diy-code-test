package com.hukki.example.mybatisframework.jdbc.domian.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 5:08 下午
 * @des
 */
public class ParameterMappingTokenHandler implements TokenHandler{
    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content)); // 用问号替换
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }
    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }
    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
