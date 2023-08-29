package com.kyleknight.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    
    public static List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (tasks.isEmpty()) {
            tasks.add(new Task("Task1", "Description1", Task.State.NEW));
            tasks.add(new Task("Task2", "Description2", Task.State.IN_PROGRESS));
            tasks.add(new Task("Task3", "Description3", Task.State.COMPLETED));
        }

        ImageView settingsButton = findViewById(R.id.MainActivitySettingsButton);
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });

        Button addTaskButton = findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTasks.class);
            startActivity(intent);
        });

        Button allTaskButton = findViewById(R.id.all_tasks_button);
        allTaskButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AllTasks.class);
            startActivity(intent);
        });

        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TaskAdapter taskAdapter = new TaskAdapter(tasks);  // Use the static tasks list here
        tasksRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString(UserProfileActivity.USER_NICKNAME_TAG, "User");
        TextView usernameDisplay = findViewById(R.id.usernameDisplay);
        usernameDisplay.setText(username + "’s tasks");

        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.getAdapter().notifyDataSetChanged();
    }
}