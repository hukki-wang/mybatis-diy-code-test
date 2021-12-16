package com.hukki.example.mybatisframework.jdbc.domian.core.io;

import com.hukki.example.mybatisframework.jdbc.domian.config.Configuration;
import com.hukki.example.mybatisframework.jdbc.domian.factory.DefaultSqlSessionFactory;
import com.hukki.example.mybatisframework.jdbc.domian.factory.SqlSessionFactory;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author wanghui
 * @date 2021/12/15 4:50 下午
 * @des 工厂建造者
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);
        System.out.println("封装出来的配置文件信息:" + configuration);

        DefaultSqlSessionFactory factory = new DefaultSqlSessionFactory(configuration);
        return factory;
    }
}
