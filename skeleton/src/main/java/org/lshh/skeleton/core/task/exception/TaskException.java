package org.lshh.skeleton.core.task.exception;

public class TaskException extends RuntimeException{
    public TaskException(String message) {
        super(message);
    }

    public static TaskException of(String message){
        return new TaskException(message);
    }
}
