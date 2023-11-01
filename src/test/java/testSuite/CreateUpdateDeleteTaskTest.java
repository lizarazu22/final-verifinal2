package testSuite;

import activities.whenDo.AddTaskScreen;
import activities.whenDo.PopUpConfirmDelete;
import activities.whenDo.PrincipalScreen;
import activities.whenDo.UpdateTaskScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import session.Session;

import java.util.Date;

public class CreateUpdateDeleteTaskTest {
    PrincipalScreen principalScreen = new PrincipalScreen();
    AddTaskScreen addTaskScreen = new AddTaskScreen();
    UpdateTaskScreen updateTaskScreen = new UpdateTaskScreen();
    PopUpConfirmDelete popUpConfirmDelete = new PopUpConfirmDelete();
    String taskName = "Testeo" + new Date().getTime();
    String updateTaskName = "Final Testing QA" + new Date().getTime();
    @Test
    public void verifyCreateUpdateDeleteTask(){
        //Create task
        principalScreen.addTaskButton.click();
        addTaskScreen.titleTaskTextBox.setText(taskName);
        addTaskScreen.saveTaskButton.click();
        Assertions.assertTrue(principalScreen.taskItem(taskName).isControlDisplayed(), "ERROR! La tarea no fue creada exitosamente.");

        //Update task
        principalScreen.taskItem(taskName).click();
        updateTaskScreen.titleTaskTextBox.setText(updateTaskName);
        updateTaskScreen.saveTaskButton.click();
        Assertions.assertTrue(principalScreen.taskItem(updateTaskName).isControlDisplayed(), "ERROR! La tarea no se actualizo correctamente.");

        //Delete task
        principalScreen.taskItem(updateTaskName).click();
        updateTaskScreen.deleteTaskButton.click();
        popUpConfirmDelete.confirmDeleteButton.click();
        Assertions.assertFalse(principalScreen.taskItem(updateTaskName).isControlDisplayed(), "ERROR! La tarea no se elimino correctamente.");

    }

    @AfterEach
    public void closeApp(){
        Session.getSession().closeApp();
    }
}
