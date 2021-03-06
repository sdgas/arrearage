package org.sdgas.model;

import javax.persistence.*;

/**
 * Created by 120378 on 2015-03-03.
 */
@Entity
public class User {

    /**
     * 工号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String pwd;

    /**
     * 备注
     */
    private String remark;

    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(length = 15, nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(length = 16, nullable = false)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(length = 50)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
