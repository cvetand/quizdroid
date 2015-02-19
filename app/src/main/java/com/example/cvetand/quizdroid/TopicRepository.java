package com.example.cvetand.quizdroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cvetand on 2/17/15.
 */
public interface TopicRepository {

    //CRUD - Create, Read, Update, Delete
    void addTopic(Topic topicToAdd);

    Topic getTopic(String topicName);

    void replaceTopic(String oldTopic, Topic newTopic);

    void deleteTopic(String topicToDelete);

    int getTopicCount();

}

