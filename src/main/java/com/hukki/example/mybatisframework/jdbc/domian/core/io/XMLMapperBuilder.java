package com.hukki.example.mybatisframework.jdbc.domian.core.io;

import com.hukki.example.mybatisframework.jdbc.domian.config.Configuration;
import com.hukki.example.mybatisframework.jdbc.domian.config.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/15 4:53 下午
 * @des 解析mapper.xml来解析出来封装到MappedStatement类中，最后封装到configuration里面的map
 */
public class XMLMapperBuilder {

    // 依赖注入
    private Configuration configuration;
    // 构造创建
    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.elements("select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }

}
