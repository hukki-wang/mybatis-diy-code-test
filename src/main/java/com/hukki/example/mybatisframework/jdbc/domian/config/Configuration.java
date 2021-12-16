package com.hukki.example.mybatisframework.jdbc.domian.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghui
 * @date 2021/12/15 4:47 下午
 * @des 存放sqlMapConfig.xml内容
 */
public class Configuration {

    private DataSource dataSource;
    /**
     * key:statementid:就是namespace+id标识一个sql标签，value:封装好的MappedStatement 对象
     */
    private Map<String,MappedStatement> mappedStatementMap = new HashMap<>();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dataSource=" + dataSource +
                ", mappedStatementMap=" + mappedStatementMap +
                '}';
    }
}
