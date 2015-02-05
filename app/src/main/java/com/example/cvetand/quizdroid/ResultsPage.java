package com.example.cvetand.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultsPage extends ActionBarActivity {
    //private QuizTopic topic;
    private final static String EXTRA_MESSAGE = "java/com.example.cvetand.quizdroid/ResultsPage.java";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        QuizTopic topic = (QuizTopic) getIntent().getSerializableExtra(QuestionPage.EXTRA_MESSAGE+"/topic");
        QuizQuestion question = topic.getCurrentQuestion();
                //(QuizQuestion) getIntent().getSerializableExtra(QuestionPage.EXTRA_MESSAGE+"/question");
        String selectedAnswer = (String) getIntent().getSerializableExtra(QuestionPage.EXTRA_MESSAGE+"/selected");
        if (question.getCorrectAns().equals(selectedAnswer)){
            topic.correctAnsGiven();
        }

        TextView questionField = (TextView) findViewById(R.id.questionField);
        TextView selection = (TextView) findViewById(R.id.selectedAnswer);
        TextView correct = (TextView) findViewById(R.id.correctAnswer);
        TextView score = (TextView) findViewById(R.id.score);
        Button next = (Button) findViewById(R.id.nextButton);

        questionField.setText(question.getQuestion());
        selection.setText(selectedAnswer);
        correct.setText(question.getCorrectAns());

        Log.i("Current Question", topic.getCurrentQuestion().getQuestion());
        Log.i("Next Index", ""+topic.getNextQuestionIndex());
        Log.i("Correct Ans", ""+topic.getCorrectAnswers());

        score.setText(topic.getCorrectAnswers() +" out of "+topic.getNextQuestionIndex());

        final Intent intent;
        if(!topic.moreQuestions()){
            next.setText("Finish");
            intent = new Intent(ResultsPage.this, MainScreen.class);

        }else{
            intent = new Intent(ResultsPage.this, QuestionPage.class);
            intent.putExtra("topic", topic);
        }

        View.OnClickListener buttonListener = new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        };

        next.setOnClickListener(buttonListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results_page, menu);
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
