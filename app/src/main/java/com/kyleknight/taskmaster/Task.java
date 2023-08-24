package com.kyleknight.taskmaster;

public class Task {
    private String title;
    private String description;
    private State state;

    public enum State {
        NEW, ASSIGNED, IN_PROGRESS, COMPLETED
    }

    public Task(String title, String description, State state) {
        this.title = title;
        this.description = description;
        this.state = state;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for state
    public State getState() {
        return state;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Setter for state
    public void setState(State state) {
        this.state = state;
    }
}
