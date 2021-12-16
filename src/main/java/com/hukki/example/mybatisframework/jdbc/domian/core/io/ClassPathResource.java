package com.hukki.example.mybatisframework.jdbc.domian.core.io;

import cn.hutool.core.lang.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wanghui
 * @date 2021/12/15 4:40 下午
 * @des
 */
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;
    public ClassPathResource(String path){
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader){
        Assert.notNull(path,"Path must not be null");
        this.path = path;
        this.classLoader = classLoader!=null?classLoader: Thread.currentThread().getContextClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
