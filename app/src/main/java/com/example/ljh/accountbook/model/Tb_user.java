package com.example.ljh.accountbook.model;

/**用户信息类
 * Created by Administrator on 2017/5/13 0013.
 */
public class Tb_user {

    private String username; //用户帐号
    private String password; //用户密码
    private double incomeToday; //当天收入
    private double spendingToday; //当天支出
    private double incomeMonth; //当月收入
    private double spendingMonth; //当月支出


    public Tb_user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Tb_user() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getIncomeToday() {
        return incomeToday;
    }

    public void setIncomeToday(double incomeToday) {
        this.incomeToday = incomeToday;
    }

    public double getSpendingToday() {
        return spendingToday;
    }

    public void setSpendingToday(double spendingToday) {
        this.spendingToday = spendingToday;
    }

    public double getIncomeMonth() {
        return incomeMonth;
    }

    public void setIncomeMonth(double incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    public double getSpendingMonth() {
        return spendingMonth;
    }

    public void setSpendingMonth(double spendingMonth) {
        this.spendingMonth = spendingMonth;
    }
}
