package com.codurance.training.tasks;

import java.util.Map;

public class DisplayProjectsAndTasks {
    Map<String, Project> addedProjects ;

    public DisplayProjectsAndTasks(Map<String, Project> addedProjects) {
        this.addedProjects = addedProjects;
    }

    public void display(){
        for (Map.Entry<String, Project> project : addedProjects.entrySet()) {
            System.out.println(project.getKey());
            for (Task task : project.getValue().getTasks()) {
                System.out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            System.out.println();
        }
    }
}
