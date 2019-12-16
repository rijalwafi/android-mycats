package com.reg.home.kucingjoko.ShowFoodList;

public class ShowFoodConfig {
    private String cat_type;
    private String cat_food;
    private String amount;
    public ShowFoodConfig(String cat_type,String cat_food,String amount){
        this.cat_type=cat_type;
        this.cat_food=cat_food;
        this.amount=amount;
    }

    public String getCat_type() {
        return cat_type;
    }

    public String getCat_food() {
        return cat_food;
    }

    public String getAmount() {
        return amount;
    }
}
