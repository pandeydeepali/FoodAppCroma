package com.croma.app.foodApp;

import java.io.Serializable;

/**
 * Created by suppi on 03/07/16.
 */
public class FoodItem {


    public String itemName;
    public String subItemName;
    public String image;
    public String  leftImage;
    public String itemdeliever;
    public String delieverAddress;
    //-----CONSTRAUCTOR DEFINE OF CLASS FOOTITEM----//
    FoodItem(String itemName, String subItemName, String image, String leftImage, String itemdeliever, String delieverAddress){

        this.itemName = itemName;
        this.subItemName = subItemName;
        this.image = image;
        this.leftImage=leftImage;
        this.itemdeliever=itemdeliever;
        this.delieverAddress=delieverAddress;

    }
}
