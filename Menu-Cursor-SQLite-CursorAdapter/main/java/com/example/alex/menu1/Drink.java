package com.example.alex.menu1;

/**
 * Created by Alex on 03.03.2016.
 */
public class Drink {
    private String name;
    private String description;
    private int imageResourceId;
    public static final Drink[] drinks = { new Drink("Latte","A couple of espresso shots with steamed milk",R.drawable.latte),
            new Drink("Capuccino","Espresso, hot milk, and a steamed milk foam",R.drawable.latte),
            new Drink("Filter","Highest quality beans roasted and brewed fresh",R.drawable.latte)};
    private Drink(String name, String description, int imageResourceId){
        this.name=name;
        this.description=description;
        this.imageResourceId=imageResourceId;
    }
    public String getDescription(){
        return description;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return this.name;
    }
}
