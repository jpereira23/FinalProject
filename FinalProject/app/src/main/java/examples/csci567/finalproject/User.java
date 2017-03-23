package examples.csci567.finalproject;

import android.text.Editable;

/**
 * Created by jpereira on 3/22/17.
 */

public class User {
    private String name;
    private String picture;

    public void setName(String name)
    {
        this.name = name;
        this.setPictureName();
    }

    private void setPictureName()
    {
        this.picture = this.name + "-picture.jpg";
    }

    public String getName()
    {
        return this.name;
    }

    public String getPicture()
    {
        return this.picture;
    }



}
