package com.example.demolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demolist.model.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.MyViewholder> {

    List<Question> questionList = new ArrayList<>();

    OnListItemClickListener listener;

    public QuestionListAdapter(List<Question> questionList, OnListItemClickListener listener) {
        this.questionList = questionList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.tv_question.setText(String.valueOf(position + 1) + ". " + questionList.get(position).getTitle());
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(position + 1);
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_question)
        TextView tv_question;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnListItemClickListener {
        void onItemClick(int position);
    }
}
