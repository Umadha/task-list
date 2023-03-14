package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindTaskById {
    Map<String, Project> addedProjects ;

    public FindTaskById(Map<String, Project> addedProjects) {
        this.addedProjects = addedProjects;
    }

    public Task getTask(String taskId){
        for (Map.Entry<String, Project> project : addedProjects.entrySet()) {
            for (Task task : project.getValue().getTasks()) {
                if (task.getId().equals(taskId)) {
                    return task;
                }
            }
        }
        System.out.printf("Could not find a task with an ID of %s.", taskId);
        System.out.println();
        return null;
    }
}
