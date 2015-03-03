package com.example.cvetand.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;


public class TopicQuestionAnswer extends ActionBarActivity {

    private Topic topic;
    private FragmentManager FM;
    QuizApp qa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_question_answer);
        qa = (QuizApp) getApplicationContext();

        topic = qa.getSelectedTopic();

        FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        TopicOverviewFragment tof = new TopicOverviewFragment();

        FT.add(R.id.FragContainer, tof);
        FT.commit();
    }

    public void nextQuestion(){
        FragmentTransaction FT = FM.beginTransaction();
        QuestionFragment qf = new QuestionFragment();

        qa.setCurrentQuestion(qa.getCurrentQuestion()+1);

        FT.replace(R.id.FragContainer, qf).addToBackStack("tag").commit();
    }

    public void showQuestionAnswer(){
        Log.i("showQuestionAnswer", "working so far");
        FragmentTransaction FT = FM.beginTransaction();
        AnswerFragment af = new AnswerFragment();

        FT.replace(R.id.FragContainer, af);
        FT.commit();
    }

    public void backToMain(){
        Intent intent = new Intent(TopicQuestionAnswer.this, MainScreen.class);
        startActivity(intent);
    }

}
