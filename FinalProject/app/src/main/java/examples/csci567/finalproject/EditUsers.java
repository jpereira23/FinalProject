package examples.csci567.finalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditUsers extends AppCompatActivity {
    private ListView mListView;
    private List<String> listItems;
    private AdapterView.OnItemClickListener onItemClickListener;
    private ArrayAdapter<String> adapter;

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

        // Initializes Database for EditUsers to access

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Entity> query = realm.where(Entity.class);
        RealmResults<Entity> results = query.findAll();

        // Configure ListView for Realm to list out its results for

        mListView = (ListView) findViewById(R.id.listView);

        listItems = new ArrayList<String>();
        for(int i = 0; i < results.size(); i++)
        {
            listItems.add(i, results.get(i).getName());
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, listItems);
        mListView.setAdapter(adapter);


        Button doneButton = (Button) findViewById(R.id.button4);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                finish();
            }
        });

        Button removeButton = (Button) findViewById(R.id.button6);
        removeButton.setOnClickListener(new View.OnClickListener() {
           @Override public void onClick(View view)
           {
               Realm realm = Realm.getDefaultInstance();
               //For now we are just gonna delete an individual item to see if this approach actually works
               realm.executeTransaction(new Realm.Transaction() {
                   @Override
                   public void execute(Realm realm1) {
                       RealmQuery<Entity> query = realm1.where(Entity.class);
                       RealmResults<Entity> results = query.findAll();

                       int pos = mListView.getCheckedItemPosition();
                       if(pos > -1)
                       {
                           Entity entity = results.get(pos);
                           adapter.remove(listItems.get(pos));
                           adapter.notifyDataSetChanged();

                           Log.d("count of artist:", String.valueOf(listItems.size()));

                           entity.deleteFromRealm();
                           mListView.setItemChecked(pos, false);
                       }




                   }

               });
           }
        });
    }


}
