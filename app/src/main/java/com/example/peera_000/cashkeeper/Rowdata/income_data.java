package com.example.peera_000.cashkeeper.Rowdata;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class Income_data {
    private int IncomePic;
    private String IncomeName;
    private String IncomeIDpic;
    private String IncomeNameId;

    public Income_data(int incomePic, String incomeName, String incomeIDpic, String incomeNameId) {
        IncomePic = incomePic;
        IncomeName = incomeName;
        IncomeIDpic = incomeIDpic;
        IncomeNameId = incomeNameId;
    }

    public String getIncomeNameId() {
        return IncomeNameId;
    }

    public void setIncomeNameId(String incomeNameId) {
        IncomeNameId = incomeNameId;
    }

    public String getIncomeIDpic() {
        return IncomeIDpic;
    }

    public void setIncomeIDpic(String incomeIDpic) {
        IncomeIDpic = incomeIDpic;
    }

    public int getIncomePic() {
        return IncomePic;
    }

    public void setIncomePic(int incomePic) {
        IncomePic = incomePic;
    }

    public String getIncomeName() {
        return IncomeName;
    }

    public void setIncomeName(String incomeName) {
        IncomeName = incomeName;
    }
}