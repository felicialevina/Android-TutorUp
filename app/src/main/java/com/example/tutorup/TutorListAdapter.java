package com.example.tutorup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TutorListAdapter extends RecyclerView.Adapter<TutorListAdapter.TutorViewHolder> {

    private final LayoutInflater mInflater;
    private ArrayList<Tutor> mTutors; // Cached copy of tutors

    TutorListAdapter(Context context, ArrayList<Tutor> mTutors) { mInflater = LayoutInflater.from(context); this.mTutors = mTutors;}

    @Override
    public TutorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item2, parent, false);
        return new TutorViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(TutorViewHolder holder, int position) {
        if (mTutors != null) {
            Tutor current = mTutors.get(position);
            holder.tutorName.setText(current.getName());
            String info = "Degree: " + current.getDegree() + "\n\nFee: $" + current.getFee() + "\n\nRating: " + current.getRating();
            holder.tutorInfo.setText(info);
        } else {
            // Covers the case of data not being ready yet.
            holder.tutorName.setText("No Tutor");
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // mTutors has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTutors != null)
            return mTutors.size();
        else return 0;
    }

    class TutorViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private final TextView tutorName;
        private final TextView tutorInfo;
        final TutorListAdapter aAdapter;

        private TutorViewHolder(View itemView, TutorListAdapter adapter) {
            super(itemView);
            tutorName = itemView.findViewById(R.id.tutorName);
            tutorInfo = itemView.findViewById(R.id.tutorInfo);
            this.aAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();

            Intent intent = new Intent(v.getContext(), Hire.class);
            intent.putExtra("name", mTutors.get(position).getName());
            intent.putExtra("email", mTutors.get(position).getEmail());
            intent.putExtra("fee", mTutors.get(position).getFee());
            intent.putExtra("degree", mTutors.get(position).getDegree());
            intent.putExtra("course", mTutors.get(position).getCourse());

            v.getContext().startActivity(intent);
            aAdapter.notifyDataSetChanged();
        }
    }
}