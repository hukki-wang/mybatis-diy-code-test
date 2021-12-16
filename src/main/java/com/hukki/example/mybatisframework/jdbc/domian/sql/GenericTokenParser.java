package com.hukki.example.mybatisframework.jdbc.domian.sql;

/**
 * @author wanghui
 * @date 2021/12/16 5:09 下午
 * @des
 */
public class GenericTokenParser {

    private final String openToken; //开始标记
    private final String closeToken; //结束标记
    private final TokenHandler handler; //标记处理器

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;
    }

    /**
     * 解析${}和#{}
     * @param text
     * @return
     */
    public String parse(String text) {
        // 验证参数问题，如果是null，就返回空字符串。
        if (text == null || text.isEmpty()) {
            return "";
        }
        int start = text.indexOf(openToken, 0);
        if (start == -1) {
            return text;
        }

        char[] src = text.toCharArray();
        int offset = 0;
        final StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;

        while (start > -1) {
            if (start > 0 && src[start - 1] == '\\') {
                builder.append(src, offset, start - offset - 1).append(openToken);
                offset = start + openToken.length();
            } else {
                if (expression == null) {
                    expression = new StringBuilder();
                } else {
                    expression.setLength(0);
                }
                builder.append(src, offset, start - offset);
                offset = start + openToken.length();
                int end = text.indexOf(closeToken, offset);
                while (end > -1) {//存在结束标记时
                    if (end > offset && src[end - 1] == '\\') {
                        expression.append(src, offset, end - offset - 1).append(closeToken);
                        offset = end + closeToken.length();
                        end = text.indexOf(closeToken, offset);
                    } else {//不存在转义字符，即需要作为参数进行处理
                        expression.append(src, offset, end - offset);
                        offset = end + closeToken.length();
                        break;
                    }
                }
                if (end == -1) {
                    builder.append(src, start, src.length - start);
                    offset = src.length;
                } else {
                    builder.append(handler.handleToken(expression.toString()));
                    offset = end + closeToken.length();
                }
            }
            start = text.indexOf(openToken, offset);
        }
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }
        return builder.toString();
    }
}
