package com.codurance.training.tasks;

import java.util.Map;

public class FindProjectByTask {
    Map<String, Project> addedProjects ;

    public FindProjectByTask(Map<String, Project> addedProjects) {
        this.addedProjects = addedProjects;
    }

    public Project getProject(String taskId){
        for (Map.Entry<String, Project> project : addedProjects.entrySet()) {
            for (Task task : project.getValue().getTasks()) {
                if (task.getId().equals(taskId)) {
                    return project.getValue();
                }
            }
        }
        System.out.println("No project found with this task.");
        return null;
    }
}
