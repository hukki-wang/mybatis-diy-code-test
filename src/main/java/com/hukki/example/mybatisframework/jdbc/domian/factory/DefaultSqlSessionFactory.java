package com.hukki.example.mybatisframework.jdbc.domian.factory;

import com.hukki.example.mybatisframework.jdbc.domian.config.Configuration;

/**
 * @author wanghui
 * @date 2021/12/16 4:53 下午
 * @des
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
