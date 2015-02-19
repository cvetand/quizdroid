package com.example.cvetand.quizdroid;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by cvetand on 2/17/15.
 */
public class QuizApp extends Application implements TopicRepository {
    private static QuizApp instance;
    private static HashMap<String, Topic> topicRepo;

    private Topic topic;
    private int currentQuestion;
    private int numCorrect;
    private int indexOfSelected;

    public QuizApp(){
        instance = getApplication();
        if(instance == null){
            instance = this;
        }

    }


    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("QuizApp", "QuizApp is loaded and run");
        topicRepo = new HashMap<String, Topic>();
        instance.createTopics();

    }

    public boolean isTopicExhausted(){
        return getSelectedTopic().getQuestionCount()-1<=currentQuestion;
    }
    public void setSelectedTopic(String topic) {
        this.topic = getTopic(topic);
    }

    public Topic getSelectedTopic() {
        return topic;
    }

    public QuizApp getApplication(){
        return this.instance;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getIndexOfSelected() {
        return indexOfSelected;
    }

    public void setIndexOfSelected(int indexOfSelected) {
        this.indexOfSelected = indexOfSelected;
    }

    public void addTopic(Topic topicToAdd){
        if(!topicRepo.containsKey(topicToAdd.getTitle())){
            topicRepo.put(topicToAdd.getTitle(), topicToAdd);
        }
    }

    public Topic getTopic(String topicName){
        return topicRepo.get(topicName);
    }

    public void replaceTopic(String oldTopic, Topic newTopic){
        topicRepo.remove(oldTopic);
        topicRepo.put(newTopic.getTitle(), newTopic);
    }

    public void deleteTopic(String topicToDelete){
        topicRepo.remove(topicToDelete);
    }

    public int getTopicCount(){
        return topicRepo.keySet().size();
    }

    private void createTopics(){
        //create Math
        Topic math = new Topic("Math");
        math.setShortDescription("The thing that calculators do");
        math.setLongDescription("Addition, Subtraction, and other hard stuff that is never used");
        math.addQuestion(new QuizQuestion(
                "5+5-6+1",
                new String[]{"5", "4", "6", "2"},
                0));

        math.addQuestion(new QuizQuestion(
                "2*3-5",
                new String[]{"1", "2", "3", "4"},
                0));

        math.addQuestion(new QuizQuestion(
                "(9/3)+1",
                new String[]{"1", "2", "3", "4"},
                3));

        //create Physics
        Topic physics = new Topic("Physics");
        physics.setShortDescription("Thing goes up, thing comes down");
        physics.setLongDescription("This has too much math so the only people that enjoy this is nerds and dweebs");
        physics.addQuestion(new QuizQuestion(
                "A car travels 90. meters due north in 15 seconds. Then the car turns around and travels 40. meters due south in 5.0 seconds. What is the magnitude of the average velocity of the car during this 20.-second interval?",
                new String[]{"2.5 m/s", "5.0 m/s", "6.5 m/s", "7.0 m/s"},
                0));

        physics.addQuestion(new QuizQuestion(
                "How far will a brick starting from rest fall freely in 3.0 seconds?",
                new String[]{"44", "35", "51", "39"},
                0));

        physics.addQuestion(new QuizQuestion(
                "An object weighing 15 newtons is lifted from the ground to a height of 0.22 meter. The increase in the object’s gravitational potential energy is approximately",
                new String[]{"3.3 J", "33 J", "0.33 J", "33.3 J"},
                3));



        //create Marvel Super Heroes
        Topic msh = new Topic("Marvel Super Heroes");
        msh.setShortDescription("Obviously none of that DC crap in here");
        msh.setLongDescription("Who reads comic books anyway? Well we're about to figure out if you do!");
        msh.addQuestion(new QuizQuestion(
                "Which of these is not a Titanic Team",
                new String[]{"The A-Team", "The Avengers", "X-Men", "Guardians of the Galaxy"},
                0));


        //create Formula 1
        Topic f1 = new Topic("Formula 1");
        f1.setShortDescription("Fast cars going around a (non-circular) circle");
        f1.setLongDescription("The highest class of single-seat auto racing where men are paired with engineering to fight it out");
        f1.addQuestion(new QuizQuestion(
                "Who won the 2014 drivers championship?",
                new String[]{"Sebastian Vettel", "Fernando Alonso", "Lewis Hamilton", "Nico Rosberg"},
                2));
        f1.addQuestion(new QuizQuestion(
                "Who won the 2013 drivers championship?",
                new String[]{"Sebastian Vettel", "Fernando Alonso", "Lewis Hamilton", "Nico Rosberg"},
                0));
        f1.addQuestion(new QuizQuestion(
                "In what year have most lap-time records been set?",
                new String[]{"2006", "2004", "2008", "2010"},
                1));

        //add to repository
        addTopic(math);
        addTopic(physics);
        addTopic(msh);
        addTopic(f1);
    }
}
