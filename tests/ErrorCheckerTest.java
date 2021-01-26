import duke.Duke;
import duke.error.ErrorChecker;
import duke.error.IllegalTaskException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ErrorCheckerTest {
    @Test
    void testToDo_missingTodoTask_exceptionThrown() {
        ErrorChecker errorChecker = new ErrorChecker("todo ", new ArrayList<>());

        boolean hasNoError = errorChecker.check();
        Assert.assertEquals(false, hasNoError);
    }

    @Test
    void testDeadline_missingDeadlineTask_exceptionThrown() {
        ErrorChecker errorChecker = new ErrorChecker("deadline ", new ArrayList<>());

        boolean hasNoError = errorChecker.check();
        Assert.assertEquals(false, hasNoError);
    }

    @Test
    void testDeadline_missingEventTask_exceptionThrown() {
        ErrorChecker errorChecker = new ErrorChecker("event ", new ArrayList<>());

        boolean hasNoError = errorChecker.check();
        Assert.assertEquals(false, hasNoError);
    }
}