package com.hukki.example.mybatisframework.jdbc.domian.factory;

import java.util.List;

/**
 * @author wanghui
 * @date 2021/12/16 4:55 下午
 * @des
 */
public interface SqlSession {

    /**
     * 查询集合
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E>  selectList(String statementId,Object... params) throws Exception;

    /**
     * 查询一个对象
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E>  E selectOne(String statementId,Object... params) throws Exception;


    /**
     * 生成代理最终执行
     * @param mapperClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> mapperClass);
}
