package com.example.cvetand.quizdroid;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by cvetand on 2/3/15.
 */

public class QuizTopic implements Serializable{
    private String topicName;
    private String description;
    private ArrayList<QuizQuestion> questions;
    private int nextQuestionIndex;
    private int correctAnswers;

    public QuizTopic(String name, String description){
        this(name, description, new ArrayList<QuizQuestion>());
    }

    public QuizTopic(String name, String description, ArrayList<QuizQuestion> questions) {
        this.topicName = name;
        this.questions = questions;
        this.description = description;
        Collections.shuffle(this.questions);
        nextQuestionIndex = 0;
        correctAnswers = 0;
    }

    public String getTopicName(){ return topicName; }
    public String getDescription(){ return description; }
    public int getQuestionCount(){ return questions.size(); }
    public int getNextQuestionIndex(){return nextQuestionIndex;}
    public QuizQuestion getCurrentQuestion(){ return questions.get(nextQuestionIndex-1); }
    public int getCorrectAnswers(){ return correctAnswers; }
    public void addQuestion(QuizQuestion question){
        questions.add(question);
    }
    public void correctAnsGiven(){ correctAnswers++; }
    public boolean moreQuestions(){ return nextQuestionIndex<getQuestionCount(); }
    public QuizQuestion getQuestion(){
        Log.i("getQuestion called", "");
        if(nextQuestionIndex < questions.size()) {
            QuizQuestion next = questions.get(nextQuestionIndex);
            next.visit();
            nextQuestionIndex++;
            return next;
        } else {
            return null;
        }
    }
}
