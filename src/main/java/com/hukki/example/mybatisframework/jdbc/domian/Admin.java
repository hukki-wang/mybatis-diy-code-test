package com.hukki.example.mybatisframework.jdbc.domian;

import java.util.Date;

/**
 * @author wanghui
 * @date 2021/12/15 2:52 下午
 * @des
 */
public class Admin {

    private String aid;

    private String password;

    private Date lastDate;

    private Integer flag;

    private Integer status;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid='" + aid + '\'' +
                ", password='" + password + '\'' +
                ", lastDate=" + lastDate +
                ", flag=" + flag +
                ", status=" + status +
                '}';
    }
}
