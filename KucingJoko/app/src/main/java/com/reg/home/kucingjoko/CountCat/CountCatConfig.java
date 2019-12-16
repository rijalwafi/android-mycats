package com.reg.home.kucingjoko.CountCat;

public class CountCatConfig {

    private String cat_type;
    private String cat_gender;
    private String number_of_cat;
    public CountCatConfig ( String cat_type,String cat_gender,String number_of_cat){

        this.cat_type=cat_type;
        this.cat_gender=cat_gender;
        this.number_of_cat=number_of_cat;
    }



    public String getCat_type() {
        return cat_type;
    }

    public String getCat_gender() {
        return cat_gender;
    }

    public String getNumber_of_cat() {
        return number_of_cat;
    }
}
