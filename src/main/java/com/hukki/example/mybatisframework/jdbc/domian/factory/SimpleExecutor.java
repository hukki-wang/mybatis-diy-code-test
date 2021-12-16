package com.hukki.example.mybatisframework.jdbc.domian.factory;

import com.hukki.example.mybatisframework.jdbc.domian.config.BoundSql;
import com.hukki.example.mybatisframework.jdbc.domian.config.Configuration;
import com.hukki.example.mybatisframework.jdbc.domian.config.MappedStatement;
import com.hukki.example.mybatisframework.jdbc.domian.sql.GenericTokenParser;
import com.hukki.example.mybatisframework.jdbc.domian.sql.ParameterMapping;
import com.hukki.example.mybatisframework.jdbc.domian.sql.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 5:03 下午
 * @des
 */
public class SimpleExecutor implements Executor {
    private Connection connection = null;

    @Override
    public void close() throws SQLException {
        if (connection != null) connection.close();
    }

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] param) throws Exception{
        //1、注册驱动，获取连接池里面的连接
        connection = configuration.getDataSource().getConnection();
        //2、获取sql语句:select * from user where id = #{id} and username = #{username}
        String sql = mappedStatement.getSql();
        //转换字符把 #{} 和 ${} 转换为 ? ，并且获取 #{} 和 ${}中间的字符串，封装方法到getBoundSql
        //转换sql语句: select * from user where id = ? and username = ? ，转换成这个样是jdbc要的，你可以去看jdbc的操作
        // 转换的过程中，还需要对#{}里面的值进行解析存储
        BoundSql boundsql = getBoundSql(sql);

        // 3.获取预处理对象:preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundsql.getSqlText() );

        // 4. 设置参数
        List<ParameterMapping> parameterMappingList = boundsql.getParameterMappingList();

        // 获取到了参数的全路径
        String parameterType = mappedStatement.getParamterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get( i );
            String content = parameterMapping.getContent();
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get( param[0] );
            preparedStatement.setObject(i+1,o);
        }

        //5.处理结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        while(resultSet.next()){
            Object o = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName( i );
                Object value = resultSet.getObject( columnName );
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);
            }
            objects.add( o );
        }
        return (List<E>) objects;
    }

    private Class<?> getClassType(String parameterTypeClass) throws ClassNotFoundException {
        if(parameterTypeClass != null){
            Class<?> aClass = Class.forName(parameterTypeClass);
            return aClass;
        }
        return null;
    }


    /**
     * 完成对#{} 的解析工作，1 将#{} 使用? 代替， 2。解析出#{} 里面的值进行存储 * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql){
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser( "#{", "}", parameterMappingTokenHandler);
        String parseSql = genericTokenParser.parse( sql );
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parseSql,parameterMappings);
    }

}
