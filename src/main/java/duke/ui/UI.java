package duke.ui;

import java.util.ArrayList;
//import java.util.Scanner;

import duke.task.Task;
import duke.task.CommandManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * UI controls the GUI of bot.
 *
 * @author  Nicole Ang
 */
public class UI extends Application {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected CommandManager taskManager = new CommandManager();

    @Override
    public void start(Stage stage) {
    }

    /**
     * Takes user inputted tasks and passes them to the TaskManager.
     *
     * @param input Input taken in through GUI.
     */
    public String getResponse(String input) {
        return taskManager.takeCommand(input, tasks);
    }
}

