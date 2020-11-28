package com.example.tutorup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopicsListAdapter extends RecyclerView.Adapter<TopicsListAdapter.TopicsViewHolder> {

    private final LayoutInflater mInflater;
    private ArrayList<Topics> mTopics; // Cached copy of topics

    TopicsListAdapter(Context context, ArrayList<Topics> mTopics) { mInflater = LayoutInflater.from(context); this.mTopics = mTopics;}

    @Override
    public TopicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item3, parent, false);
        return new TopicsViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(TopicsViewHolder holder, int position) {
        if (mTopics != null) {
            Topics current = mTopics.get(position);
            holder.topicsItemView.setText(current.getTopicName());
            holder.booksItemView.setText(current.getBookName());
        } else {
            // Covers the case of data not being ready yet.
            holder.topicsItemView.setText("No Topics");
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // mTopics has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTopics != null)
            return mTopics.size();
        else return 0;
    }

    class TopicsViewHolder extends RecyclerView.ViewHolder {
        private final TextView topicsItemView;
        TextView booksItemView;
        final TopicsListAdapter aAdapter;

        private TopicsViewHolder(View itemView, TopicsListAdapter adapter) {
            super(itemView);
            topicsItemView = itemView.findViewById(R.id.topicName);
            booksItemView = itemView.findViewById((R.id.bookName));
            this.aAdapter = adapter;
        }

    }
}
