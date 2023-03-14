package com.codurance.training.tasks;

public class Helper {
    public void help() {
        System.out.println("Commands:");
        System.out.println("  show");
        System.out.println("  add project <project name>");
        System.out.println("  add task <project name> <task description> <task Id>");
        System.out.println("  check <task ID>");
        System.out.println("  deadline <task ID> <deadlineDate>");
        System.out.println("  setDate <task ID> <createdDate>");
        System.out.println("  view_by_deadline <deadlineDate>");
        System.out.println("  view_by_date <createdDate>");
        System.out.println("  view_by_project <project name>");
        System.out.println("  delete <task ID>");
        System.out.println("  today");
        System.out.println("  uncheck <task ID>");
        System.out.println();
    }
}
