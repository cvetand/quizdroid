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

public class Topic implements Serializable{
    private String title;
    private String shortDescription;
    private String longDescription;
    private ArrayList<QuizQuestion> questions;

    public Topic(String name){
        this(name, "", "", new ArrayList<QuizQuestion>());
    }

    public Topic(String name, String shortDescription, String longDescription, ArrayList<QuizQuestion> questions) {
        this.title = name;
        this.questions = questions;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public void setTitle(String newTitle){ this.title = newTitle; }
    public void setQuestions(ArrayList<QuizQuestion> questions){ this.questions = questions; }
    public void addQuestion(QuizQuestion question){
        questions.add(question);
    }
    public void setShortDescription(String shortDesc){ this.shortDescription = shortDesc; }
    public void setLongDescription(String longDesc){ this.longDescription = longDesc; }

    public String getTitle(){ return title; }
    public String getShortDescription(){ return shortDescription; }
    public String getLongDescription(){ return longDescription; }
    public ArrayList<QuizQuestion> getQuestions(){ return questions; }

    public int getQuestionCount(){ return questions.size(); }


    public QuizQuestion getQuestion(int indexOfQuestion){
        return questions.get(indexOfQuestion);
    }
}
