package duke.task;

import duke.Duke;
import duke.datetime.DateTimeConverter;
import duke.error.ErrorChecker;
import duke.file.FileSaver;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected FileSaver fileSaver;

    public TaskManager() {
        tasks = new ArrayList<>();
        fileSaver = new FileSaver();
    }

    public void takeEvent(String input, ArrayList<Task> tasks) {
        this.tasks = tasks;
        ErrorChecker e = new ErrorChecker(input, tasks);

        if (input.equals("list")) {
            listEvents();
            return;
        } else if (e.isValid()) {
            if (input.startsWith("done")) {
                markDone(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                addNewTask(input);
            }
        }

    }

    public void markDone(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Good job! You got " + task.description
                + " done!\n" + Duke.LINE);
    }

    public void addNewTask(String input) {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new TodoTask(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] inputSplit = input.split("/");
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate());
        } else {
            String[] inputSplit = input.split("/");
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate(), dateTimeConverter.convertTime("from"),
                    dateTimeConverter.convertTime("to"));
        }
        tasks.add(newTask);

        try {
            fileSaver.saveToFile("DukeList.txt", tasks);
        } catch (IOException ex) {
            System.out.println("File path not found: " + ex.getMessage());
        }

        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Added: " + newTask.toString() + "\n" + Duke.LINE);
    }

    public void deleteTask(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(7)) - 1);
        tasks.remove(Integer.parseInt(input.substring(7)) - 1);

        try {
            fileSaver.saveToFile("DukeList.txt", tasks);
        } catch (IOException ex) {
            System.out.println("File path not found: " + ex.getMessage());
        }

        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Deleted: " + task.toString() + "\n" + Duke.LINE);
    }

    public void listEvents() {
        System.out.println(Duke.LINE + "\n" + (char) 9 + (char) 9 + "Here is a list of your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("" + (char) 9 + (char) 9 + (char) 9 + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(Duke.LINE);
    }
}
