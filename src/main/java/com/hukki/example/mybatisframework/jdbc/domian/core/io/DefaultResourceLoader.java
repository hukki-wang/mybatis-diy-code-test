package com.hukki.example.mybatisframework.jdbc.domian.core.io;

import cn.hutool.core.lang.Assert;

/**
 * @author wanghui
 * @date 2021/12/15 4:45 下午
 * @des
 */
public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
    }
}
