package com.example.ljh.accountbook.model;

/**金额信息类
 * Created by Administrator on 2017/5/13 0013.
 */
public class Tb_accout {

    private String money;//金额
    private String data;//日期
    private String note;//备注
    private String type;//类别

    public Tb_accout(String money, String data, String note, String type) {
        this.money = money;
        this.data = data;
        this.note = note;
        this.type = type;
    }




    public Tb_accout(String money, String data) {
        this.money = money;
        this.data = data;
    }

    public Tb_accout() {

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
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
