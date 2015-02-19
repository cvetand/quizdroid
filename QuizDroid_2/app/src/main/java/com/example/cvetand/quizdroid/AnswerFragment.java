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


public class AnswerFragment extends Fragment {

    private TopicQuestionAnswer hostActivity;
    private QuizTopic topic;
    private QuizQuestion question;
    private String selectedAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.topic = (QuizTopic) getArguments().getSerializable("topic");
            this.question = (QuizQuestion) getArguments().getSerializable("question");
            this.selectedAnswer = getArguments().getString("selectedAnswer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_answer, container, false);

        if (question.getCorrectAns().equals(selectedAnswer)){
            topic.correctAnsGiven();
        }

        TextView questionField = (TextView) view.findViewById(R.id.questionField);
        TextView selection = (TextView) view.findViewById(R.id.selectedAnswer);
        TextView correct = (TextView) view.findViewById(R.id.correctAnswer);
        TextView score = (TextView) view.findViewById(R.id.score);
        Button next = (Button) view.findViewById(R.id.nextButton);

        questionField.setText(question.getQuestion());
        selection.setText(selectedAnswer);
        correct.setText(question.getCorrectAns());


        score.setText(topic.getCorrectAnswers() +" out of "+topic.getNextQuestionIndex());
        View.OnClickListener buttonListener;
        if(!topic.moreQuestions()){
            next.setText("Finish");
            buttonListener = new View.OnClickListener(){
                public void onClick(View v) {
                    hostActivity.backToMain();
                }
            };
        } else {
            buttonListener = new View.OnClickListener(){
                public void onClick(View v) {
                    hostActivity.nextQuestion();
                }
            };
        }

        next.setOnClickListener(buttonListener);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.hostActivity = (TopicQuestionAnswer) activity;
    }
}
