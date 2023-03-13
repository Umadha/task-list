package com.codurance.training.tasks;

import java.util.List;

public class DisplayTasks {
    List<Task> tasks;

    public DisplayTasks(List<Task> tasks){
        this.tasks = tasks;
    }

    public void display(){
        if(!tasks.isEmpty()){
            for(Task t: tasks){
                System.out.println(t.getDescription());
            }
        }
    }
}
