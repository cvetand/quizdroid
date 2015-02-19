package com.example.cvetand.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cvetand on 2/3/15.
 */
public class QuizQuestion implements Serializable{
    private String question;
    private ArrayList<String> answers;
    private String correctAns;
    private boolean visited;

    public QuizQuestion(String question, ArrayList<String> answers){
        this(question, answers, 0);
    }

    public QuizQuestion(String question, ArrayList<String> answers, int correctAns){
        this.question = question;
        this.answers = answers;
        this.correctAns = answers.get(correctAns);
        this.visited = false;
        Collections.shuffle(this.answers);
    }

    public String getQuestion(){
        return question;
    }
    public void visit(){ visited=true;}
    public String getCorrectAns(){
        return correctAns;
    }

    public ArrayList<String> getAnswers(){
        return answers;
    }

}
