package com.example.cvetand.quizdroid;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TopicOverviewFragment extends Fragment {

    private QuizTopic topic;
    private TopicQuestionAnswer hostActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topic = (QuizTopic) getArguments().getSerializable("topic");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //insert fragment layout into container
        View view = inflater.inflate(R.layout.fragment_topic, container, false);

        //gathers different text sections to be accessible and able to be modified
        TextView title = (TextView) view.findViewById(R.id.topicTitle);
        TextView description = (TextView) view.findViewById(R.id.topicDesc);
        TextView questionCount = (TextView) view.findViewById(R.id.questionCount);

        //sets the text sections to display what the topic contains
        title.setText(topic.getTopicName());
        description.setText(topic.getDescription());
        questionCount.setText(topic.getQuestionCount() + " questions");

        //adds functionality to the begin button
        Button begin = (Button) view.findViewById(R.id.beginButton);
        View.OnClickListener buttonListener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("Topic Overview", "Begin button pressed");
                hostActivity.nextQuestion();
            }
        };

        begin.setOnClickListener(buttonListener);

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.hostActivity = (TopicQuestionAnswer) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
