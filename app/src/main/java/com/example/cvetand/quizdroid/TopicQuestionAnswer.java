package com.example.cvetand.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class TopicQuestionAnswer extends ActionBarActivity {

    private QuizTopic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_question_answer);

        //grabs the topic selected from the intent
        topic = (QuizTopic) getIntent().getSerializableExtra("topic");


        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        TopicOverviewFragment tof = new TopicOverviewFragment();

        Bundle topicBundle = new Bundle();
        topicBundle.putSerializable("topic", topic);

        tof.setArguments(topicBundle);
        FT.add(R.id.FragContainer, tof);
        FT.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_question_answer, menu);
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

    public void nextQuestion(){
        Log.i("TQA", "getting next Question and loading fragment");
        QuizQuestion nextQuestion = topic.getQuestion();
        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        QuestionFragment qf = new QuestionFragment();

        Bundle questionBundle = new Bundle();
        questionBundle.putSerializable("question", nextQuestion);

        qf.setArguments(questionBundle);
        FT.replace(R.id.FragContainer, qf).addToBackStack("tag").commit();
    }

    public void showQuestionAnswer(QuizQuestion question, String selectedAnswer){
        Log.i("showQuestionAnswer", "working so far");
        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        AnswerFragment af = new AnswerFragment();

        Bundle answerBundle = new Bundle();
        answerBundle.putSerializable("topic", topic);
        answerBundle.putSerializable("question", question);
        answerBundle.putString("selectedAnswer", selectedAnswer);

        af.setArguments(answerBundle);
        FT.replace(R.id.FragContainer, af);
        FT.commit();
    }

    public void backToMain(){
        Intent intent = new Intent(TopicQuestionAnswer.this, MainScreen.class);
        startActivity(intent);
    }

}
