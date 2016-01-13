package com.example.peera_000.cashkeeper.Rowdata;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class Income_data {
    private int IncomePic;

    public Income_data(int incomePic, String incomeName) {
        IncomePic = incomePic;
        IncomeName = incomeName;
    }

    private String IncomeName;

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