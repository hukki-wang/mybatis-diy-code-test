package com.hukki.example.mybatisframework.jdbc.domian.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wanghui
 * @date 2021/12/15 4:38 下午
 * @des
 */
public interface Resource {

    /**
     * 获取资源输入流
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

}
