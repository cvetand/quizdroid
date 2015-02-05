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


public class TopicOverview extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "java/com.example.cvetand.quizdroid/TopicOverview.java";
    private static QuizTopic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);

        //gathers different text sections to be accessible and able to be modified
        TextView title = (TextView) findViewById(R.id.topicTitle);
        TextView description = (TextView) findViewById(R.id.topicDesc);
        TextView questionCount = (TextView) findViewById(R.id.questionCount);

        //grabs the topic selected from the intent
        topic = (QuizTopic) getIntent().getSerializableExtra("topic");

        //sets the text sections to display what the topic contains
        title.setText(topic.getTopicName());
        description.setText(topic.getDescription());
        questionCount.setText(topic.getQuestionCount() + " questions");

        //adds functionality to the begin button
        Button begin = (Button) findViewById(R.id.beginButton);
        View.OnClickListener buttonListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TopicOverview.this, QuestionPage.class);
                intent.putExtra("topic", topic);
                startActivity(intent);
            }
        };

        begin.setOnClickListener(buttonListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
