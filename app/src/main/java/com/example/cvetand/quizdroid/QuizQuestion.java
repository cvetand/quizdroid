package com.example.cvetand.quizdroid;

import java.io.Serializable;

/**
 * Created by cvetand on 2/3/15.
 */
public class QuizQuestion implements Serializable{
    private String question;
    private String[] answers;
    private int indexOfAns;

    public QuizQuestion(String question, String[] answers, int indexOfAns){
        setQuestion(question);
        setAnswers(answers, indexOfAns);

    }

    public void setQuestion(String question){ this.question = question; }
    public void setAnswers(String[] answers, int indexOfAns){
        if(answers.length != 4){
            throw new IllegalArgumentException("QuizQuestion needs exactly 4 answers");
        }
        this.answers = answers;
        this.indexOfAns = indexOfAns;
    }


    public String getQuestion(){
        return question;
    }
    public String[] getAnswers(){ return answers; }
    public int getIndexOfAns(){
        return indexOfAns;
    }

}
