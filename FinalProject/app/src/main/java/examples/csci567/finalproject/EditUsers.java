package examples.csci567.finalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.util.ArrayList;

public class EditUsers extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);
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

        mListView = (ListView) findViewById(R.id.listView);

        String[] listItems = new String[results.size()];
        for(int i = 0; i < results.size(); i++)
        {
            listItems[i] = results.get(i).getName();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

        Button doneButton = (Button) findViewById(R.id.button4);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                finish();
            }
        });
    }

}
