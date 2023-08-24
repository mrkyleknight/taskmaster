package com.kyleknight.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddTasks extends AppCompatActivity {

    private EditText taskTitleInput;
    private EditText taskDescriptionInput;
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskTitleInput = findViewById(R.id.TaskTitleInput);
        taskDescriptionInput = findViewById(R.id.taskDescriptionInput);
        counterTextView = findViewById(R.id.counterTextView);

        Button addTaskButton = findViewById(R.id.AddTaskActivityButton);
        addTaskButton.setOnClickListener(v -> {
            String title = taskTitleInput.getText().toString().trim();
            String body = taskDescriptionInput.getText().toString().trim();


            Task newTask = new Task(title, body, Task.State.NEW);

            MainActivity.tasks.add(newTask); // Add task to the list in MainActivity


            counterTextView.setText("Total task: " + MainActivity.tasks.size());


            taskTitleInput.setText("");
            taskDescriptionInput.setText("");
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
