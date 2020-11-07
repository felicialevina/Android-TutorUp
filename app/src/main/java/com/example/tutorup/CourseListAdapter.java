package com.example.tutorup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    private final LayoutInflater mInflater;
    private List<Course> mCourses; // Cached copy of courses
    private Integer[] ImgArr = new Integer[3];
    ImageView pic;

    CourseListAdapter(Context context, Integer[] ImgArr) { mInflater = LayoutInflater.from(context); this.ImgArr = ImgArr;}

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CourseViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            holder.courseItemView.setText(current.getCourse());
            holder.image.setImageResource(ImgArr[position]);
        } else {
            // Covers the case of data not being ready yet.
            holder.courseItemView.setText("No Course");
        }
    }

    void setCourses(List<Course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private final TextView courseItemView;
        ImageView image;
        final CourseListAdapter aAdapter;

        private CourseViewHolder(View itemView, CourseListAdapter adapter) {
            super(itemView);
            image = itemView.findViewById(R.id.courseImg);
            courseItemView = itemView.findViewById(R.id.textView);
            this.aAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();

            Intent intent = new Intent(v.getContext(), TutorList.class);
            intent.putExtra("name", mCourses.get(position).getCourse());
            //intent.putExtra("img", artists.get(position).getImg());
            v.getContext().startActivity(intent);
            aAdapter.notifyDataSetChanged();
        }
    }
}