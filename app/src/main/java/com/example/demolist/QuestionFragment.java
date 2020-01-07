package com.example.demolist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.demolist.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int count;
    private Question question;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.tv_question)
    TextView tv_question;
    @BindView(R.id.rg_answers)
    RadioGroup rg_answers;
    @BindView(R.id.rb_option_a)
    RadioButton rb_option_a;
    @BindView(R.id.rb_option_b)
    RadioButton rb_option_b;
    @BindView(R.id.rb_option_c)
    RadioButton rb_option_c;
    @BindView(R.id.rb_option_d)
    RadioButton rb_option_d;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_previous)
    Button btn_previous;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(int param1, Question param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt(ARG_PARAM1);
            question = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);
        setData(question);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNextClick(count+1);
            }
        });
        rg_answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btn_next.setClickable(true);
                btn_next.setEnabled(true);
            }
        });
        btn_previous.setOnClickListener(v -> {
            mListener.onPreviousClick(count-1);
        });
        return view;
    }

    private void setData(Question question) {
        tv_question.setText(String.valueOf(count + 1) + ". " + question.getTitle());
        rb_option_a.setText(question.getOptionA());
        rb_option_b.setText(question.getOptionB());
        rb_option_c.setText(question.getOptionC());
        rb_option_d.setText(question.getOptionD());
        btn_next.setClickable(false);
        btn_next.setEnabled(false);
        if (count > 8) {
            btn_next.setVisibility(View.GONE);
        }
        if (count < 1){
            btn_previous.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) getParentFragment();
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
        void onNextClick(int count);
        void onPreviousClick(int count);
    }
}
