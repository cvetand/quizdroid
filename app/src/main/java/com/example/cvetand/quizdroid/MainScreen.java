package com.example.cvetand.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class MainScreen extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "MainScreen.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        final QuizApp qa = (QuizApp) getApplicationContext();

        View.OnClickListener buttonListener = new View.OnClickListener() {
            Intent intent = new Intent(MainScreen.this, TopicQuestionAnswer.class);
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Math:
                        qa.setSelectedTopic("Math");
                        break;
                    case R.id.Physics:
                        qa.setSelectedTopic("Physics");
                        break;
                    case R.id.Marvel:
                        qa.setSelectedTopic("Marvel Super Heroes");
                        break;
                    case R.id.Formula1:
                        qa.setSelectedTopic("Formula 1");
                        break;

                }
                qa.setCurrentQuestion(-1);
                qa.setNumCorrect(0);
                startActivity(intent);
            }
        };


        findViewById(R.id.Math).setOnClickListener(buttonListener);
        findViewById(R.id.Physics).setOnClickListener(buttonListener);
        findViewById(R.id.Marvel).setOnClickListener(buttonListener);
        findViewById(R.id.Formula1).setOnClickListener(buttonListener);

        TextView MathTitle = (TextView) findViewById(R.id.MathText);
        TextView PhysicsTitle = (TextView) findViewById(R.id.PhysicsText);
        TextView MarvelTitle = (TextView) findViewById(R.id.MarvelText);
        TextView F1Title = (TextView) findViewById(R.id.FormulaText);

        MathTitle.setText(qa.getTopic("Math").getTitle());
        PhysicsTitle.setText(qa.getTopic("Physics").getTitle());
        MarvelTitle.setText(qa.getTopic("Marvel Super Heroes").getTitle());
        F1Title.setText(qa.getTopic("Formula 1").getTitle());

        TextView mathDesc = (TextView) findViewById(R.id.mathShortDesc);
        mathDesc.setText(qa.getTopic("Math").getShortDescription());
        TextView physicsDesc = (TextView) findViewById(R.id.physicsShortDesc);
        physicsDesc.setText(qa.getTopic("Physics").getShortDescription());
        TextView mshDesc = (TextView) findViewById(R.id.mshShortDesc);
        mshDesc.setText(qa.getTopic("Marvel Super Heroes").getShortDescription());
        TextView f1Desc = (TextView) findViewById(R.id.f1ShortDesc);
        f1Desc.setText(qa.getTopic("Formula 1").getShortDescription());

        Log.i("MainScreen.java", "click listeners set");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
