package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    private final Map<String, Project> addedProjects = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                String[] subcommandRest = commandRest[1].split(" ", 2);
                String subcommand = subcommandRest[0];

                if (subcommand.equals("project")) {
                    addedProjects.put(subcommandRest[1], new Project(subcommandRest[1]));
                } else if (subcommand.equals("task")) {
                    String[] projectTask = subcommandRest[1].split(" ", 2);
                    CheckProjectExists checkProjectExists = new CheckProjectExists(addedProjects);
                    Project project = checkProjectExists.getProject(projectTask[0]);

                    if(project != null){
                        String[] taskCmd = projectTask[1].split(" ", 2);
                        String verifiedTaskId = taskCmd[1].replaceAll("[^a-zA-Z0-9]", "");
                        project.getTasks().add(new Task( verifiedTaskId, taskCmd[0], false));
                    }
                }
                break;
            case "check":
            case "uncheck":
                FindTaskById findTaskById =  new FindTaskById(addedProjects);
                Task task = findTaskById.getTask(commandRest[1]) ;
                if(task != null) {
                    CheckUnCheckTask checkTask = new CheckUnCheckTask(task);
                    if(command.equals("check"))
                        checkTask.setDoneUndone(true);
                    else
                        checkTask.setDoneUndone(false);
                }
                break;
            case "deadline":
                String[] cmd2 = commandRest[1].split(" ", 2);
                FindTaskById taskById =  new FindTaskById(addedProjects);

                Task task1 = taskById.getTask(cmd2[0]) ;
                new SetDeadlineDate(task1).setDeadline(cmd2[1]);
                break;
            case "setDate":
                String[] cmd3 = commandRest[1].split(" ", 2);
                FindTaskById dateTask =  new FindTaskById(addedProjects);
                Task taskDate = dateTask.getTask(cmd3[0]) ;

                new SetTaskDate(taskDate).setCreatedDate(cmd3[1]);
                break;
            case "today":
                DueTask dueTask =  new DueTask(addedProjects);
                List<Task> tasks = dueTask.getTaskDueToday();
                new DisplayTasks(tasks).display();
                break;
            case "delete":
                FindProjectByTask findTask =  new FindProjectByTask(addedProjects);
                Project projectTaskToDel = findTask.getProject(commandRest[1]);
                if(projectTaskToDel != null){
                    DeleteTask deleteTask = new DeleteTask(projectTaskToDel);
                    deleteTask.deleteTaskFromProject(commandRest[1]);
                }
                break;
            case "view_by_deadline":
                try {
                    Date deadLineDate = new SimpleDateFormat("dd-MM-yyyy").parse(commandRest[1]);
                    TaskByDeadline deadLineTask =  new TaskByDeadline(addedProjects);
                    new DisplayTasks(deadLineTask.getTaskByDeadLine(deadLineDate)).display();
                }catch(ParseException e){
                    System.out.println(e.toString());
                }
                break;
            case "view_by_date":
                try {
                    Date createdDt = new SimpleDateFormat("dd-MM-yyyy").parse(commandRest[1]);
                    TaskByDate createdTask =  new TaskByDate(addedProjects);
                    new DisplayTasks(createdTask.getTaskByDate(createdDt)).display();
                }catch(ParseException e){
                    System.out.println(e.toString());
                }
                break;
            case "view_by_project":
                TasksByProject tasksByProject =  new TasksByProject(addedProjects);
                List<Task> tasks_project = tasksByProject.getTaskByProject(commandRest[1]);
                new DisplayTasks(tasks_project).display();
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        new DisplayProjectsAndTasks(addedProjects).display();
    }

    private void help() {
        new Helper().help();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
