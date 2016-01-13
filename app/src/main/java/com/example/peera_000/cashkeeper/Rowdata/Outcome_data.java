package com.example.peera_000.cashkeeper.Rowdata;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class Outcome_data {
    int OutcomePic;
    String OutcomeName;

    public Outcome_data(int OutcomePic, String OutcomeName) {
        this.OutcomePic = OutcomePic;
        this.OutcomeName = OutcomeName;
    }

    public int getOutcomePic() {
        return OutcomePic;
    }

    public void setOutcomePic(int OutcomePic) {
        this.OutcomePic = OutcomePic;
    }

    public String getOutcomeName() {
        return OutcomeName;
    }

    public void setOutcomeName(String OutcomeName) {
        this.OutcomeName = OutcomeName;
    }
}
