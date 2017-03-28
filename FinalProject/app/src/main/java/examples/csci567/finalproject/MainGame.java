package examples.csci567.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.R.attr.button;
import static android.R.attr.x;

public class MainGame extends AppCompatActivity {

    private Hashtable studentList = new Hashtable(); //hash table of student name and file source of their picutre
    private ArrayList listUnguessed = new ArrayList(); //student nameds that have yet to be correctly guessed
    private ArrayList listComplete = new ArrayList(); //complete list of students names, never edited

    private ImageView picture;
    private Button button1;
    private Button button2;
    private Button button3;

    private String picName;
    private TextView correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);


        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Entity> query = realm.where(Entity.class);
        RealmResults<Entity> results = query.findAll();

        String picFile; //file source for current student picture

        for (int i = 0; i < results.size(); i++) {
            studentList.put(results.get(i).getName(), results.get(i).getPictureName());
            listUnguessed.add(results.get(i).getName());
            listComplete.add(results.get(i).getName());

        }

        picture = (ImageView) findViewById(R.id.student_image);
        button1 = (Button) findViewById(R.id.choice1);
        button2 = (Button) findViewById(R.id.choice2);
        button3 = (Button) findViewById(R.id.choice3);
        final TextView numLeft = (TextView) findViewById(R.id.numLeft);
        correct = (TextView) findViewById(R.id.correct);

        nextPicture();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrect(button1.getText().toString());
                numLeft.setText(Integer.toString(listComplete.size() - listUnguessed.size() ) + "/" + Integer.toString(listComplete.size()));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrect(button2.getText().toString());
                numLeft.setText(Integer.toString(listComplete.size() - listUnguessed.size()) + "/" + Integer.toString(listComplete.size()));

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrect(button3.getText().toString());
                numLeft.setText(Integer.toString(listComplete.size() - listUnguessed.size()) + "/" + Integer.toString(listComplete.size()));

            }
        });

        android.view.ViewGroup.LayoutParams layout = picture.getLayoutParams();

        layout.height = 1000;
        picture.setLayoutParams(layout);


    }

    public String nextPicture() //gets the picutre of a unguessed student and sets the ImageView to it
    {
        int rand;
        String curStudent;
        String path;
        File tmpFile;
        Bitmap myBit;


        Random random = new Random();
        rand = random.nextInt(listUnguessed.size());
        setButtons(listUnguessed.get(rand).toString());


        curStudent = listUnguessed.get(rand).toString();
        tmpFile = new File(studentList.get(curStudent).toString());
        picName = curStudent;
        myBit = BitmapFactory.decodeFile(tmpFile.getAbsolutePath());
        picture.setImageBitmap(myBit);

        return curStudent;
    }

    public void checkCorrect(String answer)
    {
        Log.e("answer", answer);
        Log.e("picName", picName);
        if(answer == picName && listUnguessed.size() != 0) //answer is correct
        {
            Log.e("correct", "CORRECT");

            listUnguessed.remove(answer);

            if(listUnguessed.size() == 0) //if game is finished
            {
                correct.setText("GAME IS FINISHED");
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
            }
            else
            {
                correct.setText("Correct");

                setButtons(nextPicture());
            }
        }
        else //answer is incorrect
        {
            correct.setText("Incorrect");
            setButtons(nextPicture());


        }
    }

    public void setButtons(String answer) { //pass name of student in picture and randomly sets the other buttons
        Random rand = new Random();
        int randInt;
        String name;
        String name2 = null;
        ArrayList<Integer> list = new ArrayList<Integer>(3);
        list.add(0);
        list.add(1);
        list.add(2);

        randInt = rand.nextInt(list.size());
        setIndividualButton(randInt, answer);
        list.remove(randInt);

        while (list.size() > 0) {

            randInt = rand.nextInt(listComplete.size());
            name = listComplete.get(randInt).toString();


            while (name.equals(answer) || name.equals(name2) ) {
                randInt = rand.nextInt(listComplete.size());
                name = listComplete.get(randInt).toString();
            }

            randInt = rand.nextInt(list.size());

            name2 = name; //used to stop duplicate buttons

            setIndividualButton(list.get(randInt), name);
            list.remove(randInt);

        }
    }

    public void setIndividualButton(int index, String text) {
        if (index == 0)
            button1.setText(text);
        else if (index == 1)
            button2.setText(text);
        else
            button3.setText(text);
    }
}
