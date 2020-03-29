package com.example.neat2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView taskName;
        public TextView taskDate;
        public Button removeButton;

        public ViewHolder(View itemView) {
            super(itemView);

            taskName = (TextView) itemView.findViewById(R.id.task_name);
            taskDate = (TextView) itemView.findViewById(R.id.task_date);
            removeButton = (Button) itemView.findViewById(R.id.remove_button);
        }
    }

    private List<Task> mTask;

    public MyAdapter(List<Task> tasks) {
        mTask = tasks;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View taskView = inflater.inflate(R.layout.item_task,parent,false);

        ViewHolder viewHolder = new ViewHolder(taskView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int position) {
        Task tasks = mTask.get(position);

        TextView taskName = viewHolder.taskName;
        taskName.setText(tasks.getName());

        TextView taskDate = viewHolder.taskDate;
        taskDate.setText(tasks.getDate());

        Button removeButton = viewHolder.removeButton;
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.get(position);
                mTask.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mTask.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }
}
{
}
