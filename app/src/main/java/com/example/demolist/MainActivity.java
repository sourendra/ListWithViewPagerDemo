package com.example.demolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.demolist.model.Question;
import com.example.demolist.util.CustomViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener{

    ArrayList<Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadData();
        replaceFragment(ListFragment.newInstance(questionList));
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
//        transaction.addToBackStack();
        transaction.commit();
    }

    private void addFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }

    private void loadData() {
        String jsonString = "[\n" +
                "{\"question\":\"Who is the Director General of Aeronautical Systems in DRDO and is also known as Missile Woman of India?\",\n" +
                "\"option_a\":\"Dr.Tessy Thomas\",\n" +
                "\"option_b\":\"Ritu Karidhal\",\n" +
                "\"option_c\":\"Muthiah Vanitha\",\n" +
                "\"option_d\":\"Anuradha T K\",\n" +
                "\"answer\":\"A [Dr.Tessy Thomas]\"},\n" +
                "{\"question\":\"Qassim Soleimani ,who was killed in the US air strike was the Military Commander of which country?\",\n" +
                "\"option_a\":\"Iraq\",\n" +
                "\"option_b\":\"Iran\",\n" +
                "\"option_c\":\"Israel\",\n" +
                "\"option_d\":\"Syria\",\n" +
                "\"answer\":\"B [Iran]\"},\n" +
                "{\"question\":\"The Prime Minister of India recently inaugurated five Young Scientists Labs of which organisation?\",\n" +
                "\"option_a\":\"Indian Space Research Organisation\",\n" +
                "\"option_b\":\"Defence Research and Development Organisation\",\n" +
                "\"option_c\":\"Bhabha Atomic Research Centre\",\n" +
                "\"option_d\":\"Department of Atomic Energy\",\n" +
                "\"answer\":\"B [Defence Research and Development Organisation\"},\n" +
                "{\"question\":\"On January 2, 2020, Prakash Parv was celebrated as birth anniversary of which Sikh Guru?\",\n" +
                "\"option_a\":\"Guru Harkrishan Sahib\",\n" +
                "\"option_b\":\"Guru Har Rai\",\n" +
                "\"option_c\":\"Guru Gobind Singh\",\n" +
                "\"option_d\":\"Guru Teg Bahadur\",\n" +
                "\"answer\":\"C [Guru Gobind Singh]\"},\n" +
                "{\"question\":\"The agreement on the prohibition of attack against nuclear installations and facilities was signed in 1988 between which two countries?\",\n" +
                "\"option_a\":\"US and USSR\",\n" +
                "\"option_b\":\"USSR and China\",\n" +
                "\"option_c\":\"India and Pakistan\",\n" +
                "\"option_d\":\"India and USSR\",\n" +
                "\"answer\":\"C [India and Pakistan]\"},\n" +
                "{\"question\":\"The government had recently launched central Equipment Identity Register portal. It operates under which department?\",\n" +
                "\"option_a\":\"Department of Science and Technology\",\n" +
                "\"option_b\":\"Department of Telecom\",\n" +
                "\"option_c\":\"Department of Biotechnology\",\n" +
                "\"option_d\":\"Department of Public Enterprise\",\n" +
                "\"answer\":\"B [Department of Telecom]\"},\n" +
                "{\"question\":\"China had recently launched its heaviest satellite, Shijian-20, on which rocket?\",\n" +
                "\"option_a\":\"Long March 2E\",\n" +
                "\"option_b\":\"Long March 4C\",\n" +
                "\"option_c\":\"Long March 3B\",\n" +
                "\"option_d\":\"Long March 5\",\n" +
                "\"answer\":\"D [Long March 5]\"},\n" +
                "{\"question\":\"Which fighter aircraft, that was recently retired, is also known as Bahadur?\",\n" +
                "\"option_a\":\"MiG 27\",\n" +
                "\"option_b\":\"Tejas\",\n" +
                "\"option_c\":\"Mirage 2000\",\n" +
                "\"option_d\":\"Gulfstream\",\n" +
                "\"answer\":\"A [MiG 27]\"},\n" +
                "{\"question\":\"Which state is going to observe 2020 as Susashan Sankalp Varsh?\",\n" +
                "\"option_a\":\"Uttar Pradesh\",\n" +
                "\"option_b\":\"Gujarat\",\n" +
                "\"option_c\":\"Haryana\",\n" +
                "\"option_d\":\"Madhya Pradesh\",\n" +
                "\"answer\":\"C [Haryana]\"},\n" +
                "{\"question\":\"When is “Good Governance Day” observed?\",\n" +
                "\"option_a\":\"December 24\",\n" +
                "\"option_b\":\"December 25\",\n" +
                "\"option_c\":\"December 26\",\n" +
                "\"option_d\":\"December 27\",\n" +
                "\"answer\":\"B [December 25]\"}\n" +
                "]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {
        }.getType();
        questionList = gson.fromJson(jsonString, type);
    }

    @Override
    public void onQuestionClick(int index) {
        addFragment(QuestionPagerFragment.newInstance(index, questionList));
    }
}
