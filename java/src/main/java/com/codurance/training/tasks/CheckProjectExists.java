package com.codurance.training.tasks;

import java.util.Map;

public class CheckProjectExists {
    Map<String, Project> addedProjects ;

    public CheckProjectExists(Map<String, Project> addedProjects) {
        this.addedProjects = addedProjects;
    }

    public Project getProject(String projectName){
        if (addedProjects.get(projectName) == null) {
            System.out.printf("Could not find a project with the name \"%s\".", projectName);
            System.out.println();
            return null;
        }else {
            return addedProjects.get(projectName);
        }
    }
}
