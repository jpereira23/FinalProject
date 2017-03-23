package examples.csci567.finalproject;

/**
 * Created by jpereira on 3/22/17.
 */

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Entity extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private String pictureName;

    public String getName() {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setPictureName(String pictureName)
    {
        this.pictureName = pictureName;
    }

    public String getPictureName()
    {
        return this.pictureName;
    }


}
