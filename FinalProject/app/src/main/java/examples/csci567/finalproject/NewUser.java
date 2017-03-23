package examples.csci567.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class NewUser extends AppCompatActivity {
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Entity> query = realm.where(Entity.class);
        RealmResults<Entity> results = query.findAll();
        count = results.size();

        Button createUser = (Button) findViewById(R.id.button);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                EditText text = (EditText) findViewById(R.id.editText);
                String aText = text.getText().toString();
                User user = new User();
                user.setName(text.getText().toString());
                TextView text1 = (TextView) findViewById(R.id.textView);
                TextView text2 = (TextView) findViewById(R.id.textView2);
                text1.setText(user.getName());
                text2.setText(user.getPicture());
                sendToDatabase(user.getName(), user.getPicture());
                finish();
            }

        });
    }

    private void sendToDatabase(String name, String pictureName)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        count += 1;
        Entity entity = realm.createObject(Entity.class, count);
        entity.setName(name);
        entity.setPictureName(pictureName);
        realm.commitTransaction();
    }



    private void createNewUser()
    {
        Button addUser = (Button) findViewById(R.id.button);
        addUser.setOnClickListener( new View.OnClickListener() {
            @Override public void onClick(View v) {

            }

        });
    }

}
