package ru.yandex;

import ru.yandex.http.HTTPTaskManager;
import ru.yandex.http.HttpTaskServer;
import ru.yandex.http.KVServer;
import ru.yandex.managers.FileBackedTasksManager;
import ru.yandex.managers.Managers;
import ru.yandex.managers.TaskManager;
import ru.yandex.task.Epic;
import ru.yandex.task.Subtask;
import ru.yandex.task.Task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Запуск KVServer");
        new KVServer().start();
        System.out.println("Запуск httpTaskServer");

        TaskManager manager = Managers.getDefault();

        Task task1 = new Task("Задача 1", "Описание задачи 1", LocalDateTime.of(2022,8,12,10,25),20);
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1", LocalDateTime.of(2022,8,12,11,25),20, epic1);
        Subtask subtask2 = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2", LocalDateTime.of(2022,8,12,10,25),20, epic1);
        Subtask subtask3 = new Subtask("Подзадача 3 эпика 1", "Описание подзадачи 3", LocalDateTime.of(2022,8,12,10,25),20, epic1);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2");

        manager.addNewTask(task1);
        manager.addNewTask(epic1);
        manager.addNewTask(subtask1);
        manager.addNewTask(subtask2);
        manager.addNewTask(subtask3);
        manager.addNewTask(epic2);

        manager.getTaskForId(0);
        manager.getEpicForId(0);
        manager.getSubtaskForId(0);
        manager.getSubtaskForId(1);
        manager.getSubtaskForId(2);
        manager.getEpicForId(1);

        HttpTaskServer server = new HttpTaskServer(manager);
        server.start();

        System.out.println("Создаем новый экземпляр HTTPTaskManager taskManager");
        HTTPTaskManager taskManager = new HTTPTaskManager(true);

        System.out.println("Печать восстановленных данных");
        taskManager.getTasks().forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println("Печать восстановленной истории");
        System.out.println(taskManager.getHistoryManager().getHistory());

        System.out.println(taskManager.getPrioritizedTasks());



        /*TaskManager manager = Managers.getDefault();

        Task task1 = new Task("Задача 1", "Описание задачи 1", LocalDateTime.of(2022,8,12,10,25),20);
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        Subtask subtask1 = new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1", LocalDateTime.of(2022,8,12,11,25),20, epic1);
        Subtask subtask2 = new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2", LocalDateTime.of(2022,8,12,10,25),20, epic1);
        Subtask subtask3 = new Subtask("Подзадача 3 эпика 1", "Описание подзадачи 3", LocalDateTime.of(2022,8,12,10,25),20, epic1);
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2");
        manager.addNewTask(task1);
        manager.addNewTask(epic1);
        manager.addNewTask(subtask1);
        manager.addNewTask(subtask2);
        manager.addNewTask(subtask3);
        manager.addNewTask(epic2);

        manager.printAll();

        System.out.println("================================");

        manager.getTaskForId(0);
        manager.getEpicForId(0);
        manager.getSubtaskForId(0);
        manager.getSubtaskForId(1);
        manager.getSubtaskForId(2);
        manager.getEpicForId(1);

        List<Task> openTask = manager.getHistoryManager().getHistory();
        for (Task task : openTask) {
            System.out.println(task.getId());
        }

        manager.deleteEpicForId(0);

        System.out.println("================================");

        FileBackedTasksManager fileBacked = new FileBackedTasksManager(new File("src/main/resources/tasks.csv"));

        fileBacked.addNewTask(task1);
        fileBacked.addNewTask(epic1);
        fileBacked.addNewTask(epic2);

        fileBacked.getTaskForId(0);
        fileBacked.getEpicForId(1);

        fileBacked.loadFromFile(new File("src/main/resources/tasks.csv"));*/
    }
}
