package com.example.ljh.accountbook.model;

/**金额信息类
 * Created by Administrator on 2017/5/13 0013.
 */
public class Tb_accout {

    private double money;//金额
    private String data;//日期
    private String note;//备注

    public Tb_accout(double money, String data, String note) {
        this.money = money;
        this.data = data;
        this.note = note;
    }

    public Tb_accout(double money, String data) {
        this.money = money;
        this.data = data;
    }

    public Tb_accout() {
        super();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
