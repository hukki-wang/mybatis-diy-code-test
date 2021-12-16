package com.hukki.example.mybatisframework.jdbc.domian.factory;

import com.hukki.example.mybatisframework.jdbc.domian.config.Configuration;
import com.hukki.example.mybatisframework.jdbc.domian.config.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 5:00 下午
 * @des
 */
public class DefaultSqlSession implements SqlSession{

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>)list;
    }

    @Override
    public <E> E selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if(objects.size() == 1){
            return (E) objects.get(0);
        }else if(objects.size() == 0){
            return null;
        }else {
            throw new RuntimeException("Expected one result (or null) to be returned by selectOne(),but found:" + objects.size());
        }
    }

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(),new Class[]{mapperClass},(proxy,method,args)->{
            String methodName = method.getName();
            String className = method.getDeclaringClass().getName(); // 拼接参数1
            String statementId = className + "." + methodName; // 准备参数2:就是参数，其实就是传递多来的args
            Type genericReturnType = method.getGenericReturnType();
            if(genericReturnType instanceof ParameterizedType){
                List<Object> objects = selectList(statementId, args); return objects;
            }
            return selectOne(statementId,args);
        });
        return (T) proxyInstance;
    }
}
