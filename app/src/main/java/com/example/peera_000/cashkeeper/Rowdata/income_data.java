package com.example.peera_000.cashkeeper.Rowdata;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class income_data {
    String incomePic;
    String incomeName;

    public income_data(String incomePic, String incomeName) {
        this.incomePic = incomePic;
        this.incomeName = incomeName;
    }

    public String getIncomePic() {
        return incomePic;
    }

    public void setIncomePic(String incomePic) {
        this.incomePic = incomePic;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }
}
