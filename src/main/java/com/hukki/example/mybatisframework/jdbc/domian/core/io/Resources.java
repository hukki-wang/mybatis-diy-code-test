package com.hukki.example.mybatisframework.jdbc.domian.core.io;

import java.io.InputStream;

/**
 * @author wanghui
 * @date 2021/12/15 5:06 下午
 * @des
 */
public class Resources {

    public static InputStream getResourceAsStream(String path){
    InputStream resourceAsStream =
            Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
