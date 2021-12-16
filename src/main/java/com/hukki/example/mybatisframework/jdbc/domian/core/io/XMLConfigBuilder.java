package com.hukki.example.mybatisframework.jdbc.domian.core.io;

import com.hukki.example.mybatisframework.jdbc.domian.config.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author wanghui
 * @date 2021/12/15 4:52 下午
 * @des 使用dom4j解析xml文件，封装到对应的类中
 */
public class XMLConfigBuilder {

    private Configuration configuration;
    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        Element datasource = rootElement.element("datasource");
        List<Element> list = datasource.elements("property");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);

        List<Element> mapperList = rootElement.elements("mapper");

        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }

}
