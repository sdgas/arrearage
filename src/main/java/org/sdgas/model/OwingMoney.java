package org.sdgas.model;

import org.sdgas.util.ExcelResources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015/9/7.
 */
@Entity
public class OwingMoney {

    /**
     * 标识列/序号
     */
    private int id;

    /**
     * 用户号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 镇街
     */
    private String town;

    /**
     * 欠费金额
     */
    private double owingMoney;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 录入日期
     */
    private String enterDate;

    /**
     * 录入人
     */
    private String enterUser;

    /**
     * 欠费内容
     */
    private String content;

    /**
     * 是否缴费
     */
    private int pay;

    /**
     * 缴费日期
     */
    private String payDate;

    /**
     * 欠费账期
     */
    private int accountPeriod;

    /**
     * 备注
     */
    private String remark;

    /**
     * 账期数
     */
    private String period;


    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ExcelResources(order = 1, title = "用户号")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ExcelResources(order = 2, title = "用户名")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelResources(order = 3, title = "镇街")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @ExcelResources(order = 4, title = "欠费金额")
    public double getOwingMoney() {
        return owingMoney;
    }

    public void setOwingMoney(double owingMoney) {
        this.owingMoney = owingMoney;
    }

    @ExcelResources(order = 5, title = "联系电话")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @ExcelResources(order = 6, title = "录入日期")
    public String getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    @ExcelResources(order = 7, title = "录入人")
    public String getEnterUser() {
        return enterUser;
    }

    public void setEnterUser(String enterUser) {
        this.enterUser = enterUser;
    }

    @ExcelResources(order = 8, title = "欠费内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ExcelResources(order = 9, title = "已缴费")
    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    @ExcelResources(order = 10, title = "缴费日期")
    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    @ExcelResources(order = 11, title = "欠费账期")
    public int getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(int accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    @ExcelResources(order = 12, title = "备注")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ExcelResources(order = 13, title = "账期数")
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
