package com.example.peera_000.cashkeeper.Rowdata;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class Outcome_data {
    int OutcomePic;
    String OutcomeName;
    private String OutcomeIDpic;
    private String OutcomeNameId;

    public Outcome_data(int OutcomePic, String OutcomeName, String outcomeIDpic, String outcomeNameId) {
        this.OutcomePic = OutcomePic;
        this.OutcomeName = OutcomeName;
        OutcomeIDpic = outcomeIDpic;
        OutcomeNameId = outcomeNameId;
    }

    public String getOutcomeIDpic() {
        return OutcomeIDpic;
    }

    public void setOutcomeIDpic(String outcomeIDpic) {
        OutcomeIDpic = outcomeIDpic;
    }

    public String getOutcomeNameId() {
        return OutcomeNameId;
    }

    public void setOutcomeNameId(String outcomeNameId) {
        OutcomeNameId = outcomeNameId;
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
