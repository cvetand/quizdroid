package com.example.cvetand.quizdroid;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class QuestionPage extends ActionBarActivity {
    private static QuizTopic topic;
    public static String EXTRA_MESSAGE = "java/com.example.cvetand.quizdroid/TopicOverview.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        topic = (QuizTopic) getIntent().getSerializableExtra("topic");


        final QuizQuestion question = topic.getQuestion();

        TextView questionField = (TextView) findViewById(R.id.questionField);
        questionField.setText(question.getQuestion());

        RadioButton r1 = (RadioButton) findViewById(R.id.option1);
        RadioButton r2 = (RadioButton) findViewById(R.id.option2);
        RadioButton r3 = (RadioButton) findViewById(R.id.option3);
        RadioButton r4 = (RadioButton) findViewById(R.id.option4);
        final Button submit = (Button) findViewById(R.id.submitButton);

        View.OnClickListener radioListener = new View.OnClickListener(){
            public void onClick(View v) {
                submit.setVisibility(View.VISIBLE);
            }
        };

        r1.setOnClickListener(radioListener);
        r2.setOnClickListener(radioListener);
        r3.setOnClickListener(radioListener);
        r4.setOnClickListener(radioListener);

        r1.setText(question.getAnswers().get(0));
        r2.setText(question.getAnswers().get(1));
        r3.setText(question.getAnswers().get(2));
        r4.setText(question.getAnswers().get(3));

//Button functionality to get the answer selected and launch ResultsPage with the topic and selected answer
        View.OnClickListener buttonListener = new View.OnClickListener() {
            public void onClick(View v) {

                RadioGroup optionsGroup = (RadioGroup) findViewById(R.id.optionsGroup);
                String selectedAnswer = ((RadioButton) optionsGroup.findViewById(optionsGroup.getCheckedRadioButtonId())).getText().toString();

                Intent intent = new Intent(QuestionPage.this, ResultsPage.class);
                intent.putExtra(EXTRA_MESSAGE+"/topic", topic);
                //intent.putExtra(EXTRA_MESSAGE+"/question", question);
                intent.putExtra(EXTRA_MESSAGE+"/selected", selectedAnswer);
                startActivity(intent);
            }
        };

        submit.setOnClickListener(buttonListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_page, menu);
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
