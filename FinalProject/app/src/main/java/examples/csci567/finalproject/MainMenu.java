package examples.csci567.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button launchNewUser = (Button) findViewById(R.id.button3);

        launchNewUser.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                launchActivity();
            }
        });

        Button launchEditUsers = (Button) findViewById(R.id.button5);

        launchEditUsers.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                launchUsers();
            }
        });
    }

    private void launchActivity()
    {
        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }

    public void launchUsers()
    {
        Intent intent = new Intent(this, EditUsers.class);
        startActivity(intent);
    }
}
