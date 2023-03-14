package com.codurance.training.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SetDeadlineDate {
    Task task ;

    public SetDeadlineDate(Task task) {
        this.task = task;
    }

    public void setDeadline(String deadline){
        try {
            Date deadLineDate = new SimpleDateFormat("dd-MM-yyyy").parse(deadline);
            task.setDeadline(deadLineDate);
        }catch(ParseException e){
            System.out.println(e.toString());
        }
    }
}
