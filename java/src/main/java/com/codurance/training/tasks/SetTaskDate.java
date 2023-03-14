package com.codurance.training.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetTaskDate {
    Task task ;

    public SetTaskDate(Task task) {
        this.task = task;
    }

    public void setCreatedDate(String createdDate){
        try {
            Date createdDt = new SimpleDateFormat("dd-MM-yyyy").parse(createdDate);
            task.setCreatedDate(createdDt);
        }catch(ParseException e){
            System.out.println(e.toString());
        }
    }
}
