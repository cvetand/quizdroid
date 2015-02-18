package com.example.cvetand.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class MainScreen extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "java/com.example.cvetand.quizdroid/MainScreen.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        View.OnClickListener buttonListener = new View.OnClickListener() {
            Intent intent = new Intent(MainScreen.this, TopicQuestionAnswer.class);
            public void onClick(View v) {
                QuizTopic topic;
                switch (v.getId()) {
                    case R.id.MathText:
                        QuizTopic mathTopic = createMath();
                        intent.putExtra("topic", mathTopic);
                        break;
                    case R.id.PhysicsText:
                        QuizTopic physicsTopic = createPhysics();
                        intent.putExtra("topic", physicsTopic);
                        break;
                    case R.id.MarvelText:
                        QuizTopic MSHTopic = createMSH();
                        intent.putExtra("topic", MSHTopic);
                        break;
                    case R.id.FormulaText:
                        QuizTopic F1Topic = createF1();
                        intent.putExtra("topic", F1Topic);
                        break;

                }
                Log.i("MainScreen.java", "starting activity with " + intent.getSerializableExtra("topic").toString());
                startActivity(intent);
            }
        };


        findViewById(R.id.MathText).setOnClickListener(buttonListener);
        findViewById(R.id.PhysicsText).setOnClickListener(buttonListener);
        findViewById(R.id.MarvelText).setOnClickListener(buttonListener);
        findViewById(R.id.FormulaText).setOnClickListener(buttonListener);
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

    private QuizTopic createMath(){
        String description = "The study of topics such as quantity (numbers), structure, space, and change.";
        QuizTopic topic = new QuizTopic("Math", description);

        String problem = "5+5-6+1";
        ArrayList<String> answers = new ArrayList<String>();
        answers.add("5");
        answers.add("4");
        answers.add("6");
        answers.add("2");

        QuizQuestion question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        problem = "2*3-5";
        answers = new ArrayList<String>();
        answers.add("1");
        answers.add("3");
        answers.add("2");
        answers.add("4");

        question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        problem = "9/3+1";
        answers = new ArrayList<String>();
        answers.add("4");
        answers.add("3");
        answers.add("2");
        answers.add("5");

        question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        return topic;
    }

    private QuizTopic createPhysics(){
        String description = "The natural science that involves the study of matter and its motion through space and time, along with related concepts such as energy and force.";
        QuizTopic topic = new QuizTopic("Physics", description);

        String problem = "A car travels 90. meters due north in 15 seconds. Then the car turns around and travels 40. meters due south in 5.0 seconds. What is the magnitude of the average velocity of the car during this 20.-second interval?";
        ArrayList<String> answers = new ArrayList<String>();
        answers.add("2.5 m/s");
        answers.add("5.0 m/s");
        answers.add("6.5 m/s");
        answers.add("7.0 m/s");

        QuizQuestion question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        problem = "How far will a brick starting from rest fall freely in 3.0 seconds?";
        answers = new ArrayList<String>();
        answers.add("44 m");
        answers.add("88 m");
        answers.add("15 m");
        answers.add("39 m");

        question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        problem = "An object weighing 15 newtons is lifted from the ground to a height of 0.22 meter. The increase in the object’s gravitational potential energy is approximately";
        answers = new ArrayList<String>();
        answers.add("3.3 J");
        answers.add("33 J");
        answers.add("0.33 J");
        answers.add("33.3 J");

        question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        return topic;
    }

    private QuizTopic createMSH(){
        String description = "A comic book series and specials published by Marvel Comics";
        QuizTopic topic = new QuizTopic("Marvel Super Heroes", description);

        String problem = "Which of these is not a Titanic Team";
        ArrayList<String> answers = new ArrayList<String>();
        answers.add("The A-Team");
        answers.add("The Avengers");
        answers.add("X-Men");
        answers.add("Guardians of the Galaxy");

        QuizQuestion question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        return topic;
    }

    private QuizTopic createF1(){
        String description = "The highest class of single-seat auto racing";
        QuizTopic topic = new QuizTopic("Formula 1", description);

        String problem = "Who won the 2014 drivers championship";
        ArrayList<String> answers = new ArrayList<String>();
        answers.add("Lewis Hamilton");
        answers.add("Nico Rosberg");
        answers.add("Sebastian Vettel");
        answers.add("Fernando Alonso");

        QuizQuestion question = new QuizQuestion(problem, answers);
        topic.addQuestion(question);

        return topic;
    }

}
