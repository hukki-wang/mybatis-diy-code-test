package com.hukki.example.mybatisframework.jdbc.domian.core.io;

/**
 * @author wanghui
 * @date 2021/12/15 4:39 下午
 * @des
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 加载资源
     * @param location
     * @return
     */
    Resource getResource(String location);
}
