package com.hukki.example.mybatisframework.jdbc.domian.factory;

import com.hukki.example.mybatisframework.jdbc.domian.config.Configuration;
import com.hukki.example.mybatisframework.jdbc.domian.config.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 5:11 下午
 * @des 执行器接口
 */
public interface Executor {

    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object...params) throws Exception;
    void close() throws SQLException;

}
