package com.example.cvetand.quizdroid;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

    private TopicQuestionAnswer hostActivity;
    private QuizQuestion question;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.question = (QuizQuestion) getArguments().getSerializable("question");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        TextView questionField = (TextView) view.findViewById(R.id.questionField);
        questionField.setText(question.getQuestion());

        RadioButton r1 = (RadioButton) view.findViewById(R.id.option1);
        RadioButton r2 = (RadioButton) view.findViewById(R.id.option2);
        RadioButton r3 = (RadioButton) view.findViewById(R.id.option3);
        RadioButton r4 = (RadioButton) view.findViewById(R.id.option4);
        final Button submit = (Button) view.findViewById(R.id.submitButton);

        View.OnClickListener radioListener = new View.OnClickListener() {
            public void onClick(View v) {
                submit.setVisibility(View.VISIBLE);
            }
        };

        r1.setOnClickListener(radioListener);
        r2.setOnClickListener(radioListener);
        r3.setOnClickListener(radioListener);
        r4.setOnClickListener(radioListener);

        r1.setText(question.getAnswers().get(0));
        r2.setText(question.getAnswers().get(1));
        r3.setText(question.getAnswers().get(2));
        r4.setText(question.getAnswers().get(3));

        //Button functionality to get the answer selected and launch ResultsPage with the topic and selected answer
        View.OnClickListener buttonListener = new View.OnClickListener() {
            public void onClick(View v) {

                RadioGroup optionsGroup = (RadioGroup) view.findViewById(R.id.optionsGroup);
                String selectedAnswer = ((RadioButton) optionsGroup.findViewById(optionsGroup.getCheckedRadioButtonId())).getText().toString();
                Log.i("QuestionFragment", "submit button pressed");
                hostActivity.showQuestionAnswer(question, selectedAnswer);

            }
        };

        submit.setOnClickListener(buttonListener);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.hostActivity = (TopicQuestionAnswer) activity;
    }


}