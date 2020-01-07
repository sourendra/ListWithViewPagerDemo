package com.example.demolist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demolist.model.Question;
import com.example.demolist.util.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionPagerFragment extends Fragment implements QuestionFragment.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int position;
    private List<Question> questionList;

    @BindView(R.id.view_pager)
    CustomViewPager customViewPager;

    private OnFragmentInteractionListener mListener;

    public QuestionPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param questions Parameter 2.
     * @return A new instance of fragment QuestionPagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionPagerFragment newInstance(int param1, ArrayList<Question> questions) {
        QuestionPagerFragment fragment = new QuestionPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putParcelableArrayList(ARG_PARAM1, questions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM1);
            questionList = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_pager, container, false);
        ButterKnife.bind(this, view);
        customViewPager.setPagingEnabled(false);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getChildFragmentManager(), questionList);
        customViewPager.setAdapter(pagerAdapter);
        customViewPager.setCurrentItem(position);
        return view;
    }

    @Override
    public void onNextClick(int count) {
        customViewPager.setCurrentItem(count);
    }

    @Override
    public void onPreviousClick(int count) {
        customViewPager.setCurrentItem(count);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 10;

        List<Question> questionList = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fragmentManager, List<Question> questionList) {
            super(fragmentManager);
            this.questionList = questionList;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return QuestionFragment.newInstance(position, questionList.get(position));
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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

    }
}
