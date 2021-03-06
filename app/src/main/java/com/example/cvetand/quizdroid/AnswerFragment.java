package com.example.cvetand.quizdroid;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AnswerFragment extends Fragment {

    private TopicQuestionAnswer hostActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_answer, container, false);
        final QuizApp qa = (QuizApp) hostActivity.getApplicationContext();
        QuizQuestion question = qa.getSelectedTopic().getQuestion(qa.getCurrentQuestion());


        if(qa.getIndexOfSelected() == question.getIndexOfAns()){
            qa.setNumCorrect(qa.getNumCorrect()+1);
        }

        TextView questionField = (TextView) view.findViewById(R.id.questionField);
        TextView selection = (TextView) view.findViewById(R.id.selectedAnswer);
        TextView correct = (TextView) view.findViewById(R.id.correctAnswer);
        TextView score = (TextView) view.findViewById(R.id.score);
        Button next = (Button) view.findViewById(R.id.nextButton);

        questionField.setText(question.getQuestion());
        selection.setText(question.getAnswers()[qa.getIndexOfSelected()]);
        correct.setText(question.getAnswers()[question.getIndexOfAns()]);


        score.setText(qa.getNumCorrect() +" out of "+(qa.getCurrentQuestion()+1));
        View.OnClickListener buttonListener;
        if(qa.isTopicExhausted()){
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
