package com.example.alex.bitsandpizzas;

/**
 * Created by Alex on 14.03.2016.
 */
public class Cocktail {
    private String name;
    private int imageResourceId;
    int imageDescription;
    public static final Cocktail[] cocktails = {
                                                    new Cocktail("Watermelon Mint Cooler", R.drawable.watermelon, R.drawable.watermelon_summer_shake),
                                                    new Cocktail("Bentley", R.drawable.bentley, R.drawable.bentley),
                                                    new Cocktail("Blueberry milk", R.drawable.blueberrymilk, R.drawable.blueberrymilk)
                                                };
    private Cocktail(String name, int imageResourceId, int imageDescription) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.imageDescription = imageDescription;
    }
    public String getName(){
        return name;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
    public int getImageDescription() {
        return imageDescription;
    }
}
