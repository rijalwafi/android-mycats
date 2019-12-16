package com.reg.home.kucingjoko.TopCatFood;

public class ListTopConfig {
    private String cat_type;
    private String amount;

    public ListTopConfig(String cat_type,String amount){
        this.cat_type=cat_type;
        this.amount=amount;
    }

    public String getCat_type() {
        return cat_type;
    }

    public String getAmount() {
        return amount;
    }
}
