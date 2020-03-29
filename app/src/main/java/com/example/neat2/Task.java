package com.example.neat2;

public class Task {

    private String mTaskName;
    private String mTaskDate;

    public Task(String name, String date) {
        mTaskName = name;
        mTaskDate = date;
    }

    public String getName() {
        return mTaskName;
    }

    public String getDate() {
        return mTaskDate;
    }
}
