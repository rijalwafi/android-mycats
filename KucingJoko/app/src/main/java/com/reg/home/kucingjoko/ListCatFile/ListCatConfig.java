package com.reg.home.kucingjoko.ListCatFile;

public class ListCatConfig {
    private int id_user;
    private String cat_name;
    private String cat_gender;
    private String cat_type;
    private String cat_colour;
    private String cat_food;
    public ListCatConfig(int id_user,String cat_name,String cat_gender,String cat_type,String cat_colour,String cat_food){
        this.id_user=id_user;
        this.cat_name=cat_name;
        this.cat_gender=cat_gender;
        this.cat_type=cat_type;
        this.cat_colour=cat_colour;
        this.cat_food=cat_food;
    }

    public int getId_user() {
        return id_user;
    }

    public String getCat_name() {
        return cat_name;
    }

    public String getCat_gender() {
        return cat_gender;
    }

    public String getCat_type() {
        return cat_type;
    }

    public String getCat_colour() {
        return cat_colour;
    }

    public String getCat_food() {
        return cat_food;
    }
}