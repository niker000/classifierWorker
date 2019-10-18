package com.task.classifierWorker.model;

public enum Role {
    USER(1),
    ADMIN(2);

    private int id;
    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
