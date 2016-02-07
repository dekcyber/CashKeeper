package com.example.peera_000.cashkeeper.Rowdata;

import android.graphics.Bitmap;

/**
 * Created by peera_000 on 31/12/2558.
 */
public class RowData {
    private int Photo;
    private String Money;
    private String Date;
    private String Cate;
    private String Note;
    private String MoneyOut;
    private String Summoney;
    private String Id;

    public RowData(int photo, String money, String date, String cate, String note, String moneyOut,String sumMoney,String RowId) {
        Photo = photo;
        Money = money;
        Date = date;
        Cate = cate;
        Note = note;
        Summoney = sumMoney;
        MoneyOut = moneyOut;
        Id = RowId;
    }//Constructor

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSummoney() {
        return Summoney;
    }

    public void setSummoney(String summoney) {
        Summoney = summoney;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public String getCate() {
        return Cate;
    }

    public void setCate(String cate) {
        Cate = cate;
    }

    public String getMoneyOut() {
        return MoneyOut;
    }

    public void setMoneyOut(String moneyOut) {
        MoneyOut = moneyOut;
    }
}//Main Class
