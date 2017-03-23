package examples.csci567.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Button login = (Button) findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                EditText username = (EditText) findViewById(R.id.editText2);
                EditText password = (EditText) findViewById(R.id.editText3);
                if(username.getText().toString().equals("chico") && password.getText().toString().equals("student"))
                {
                    launchMainMenu();
                }
                else
                {
                    TextView usernameText = (TextView) findViewById(R.id.textView3);
                    Spannable WordToSpan = new SpannableString("Error: username or password not valid.");
                    WordToSpan.setSpan(new ForegroundColorSpan(Color.RED), 0, 38, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    usernameText.setText(WordToSpan);

                }
            }

        });

    }

    private void launchMainMenu()
    {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
