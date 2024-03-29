package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.yandex.managers.InMemoryTaskManager;
import ru.yandex.task.Epic;
import ru.yandex.task.Subtask;
import ru.yandex.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {
    InMemoryTaskManager manager = new InMemoryTaskManager();

    @AfterEach
    void deleteAllTaskAfterEachTest() {
        manager.deleteAll();
    }

    @Test
    void testEpicWithoutSubtasks() {
        Epic epic = new Epic("Epic 1","DescrEpic1");
        manager.addNewTask(epic);

        assertEquals(TaskStatus.NEW, epic.getStatusTask());
    }

    @Test
    void testEpicWithAllSubtasksAreNew() {
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1", epic1);
        Subtask subtask2 = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2", epic1);
        Subtask subtask3 = new Subtask("Подзадача 3 эпика 1", "Описание подзадачи 3", epic1);
        manager.addNewTask(epic1);
        manager.addNewTask(subtask1);
        manager.addNewTask(subtask2);
        manager.addNewTask(subtask3);

        assertEquals(TaskStatus.NEW, epic1.getStatusTask());
    }

    @Test
    void testEpicWithSubtasksAreDone() {
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1", epic1, TaskStatus.DONE);
        Subtask subtask2 = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2", epic1, TaskStatus.DONE);
        manager.addNewTask(epic1);
        manager.addNewTask(subtask1);
        manager.addNewTask(subtask2);

        assertEquals(TaskStatus.DONE, epic1.getStatusTask());
    }

    @Test
    void testEpicWithSubtasksAreDoneOrNew() {
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1", epic1, TaskStatus.DONE);
        Subtask subtask2 = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2", epic1, TaskStatus.NEW);
        manager.addNewTask(epic1);
        manager.addNewTask(subtask1);
        manager.addNewTask(subtask2);

        assertEquals(TaskStatus.IN_PROGRESS, epic1.getStatusTask());
    }

    @Test
    void testEpicWithSubtasksAreInProgress() {
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1", epic1, TaskStatus.IN_PROGRESS);
        Subtask subtask2 = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2", epic1, TaskStatus.IN_PROGRESS);
        manager.addNewTask(epic1);
        manager.addNewTask(subtask1);
        manager.addNewTask(subtask2);

        assertEquals(TaskStatus.IN_PROGRESS, epic1.getStatusTask());
    }
}
