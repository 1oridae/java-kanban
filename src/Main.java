import model.TaskStatus;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        System.out.println("Задачи:");
        manager.addTask("Задача №1", "Описание задачи №1", TaskStatus.NEW);
        System.out.println(manager.printTaskForId(manager.getId()-1));
        manager.addTask("Задача №2", "Описание задачи №2", TaskStatus.IN_PROGRESS);
        System.out.println(manager.printTaskForId(manager.getId()-1));
        System.out.println("Эпики с подзадачами:");
        manager.addEpic("Эпик №2", "Описание эпика №2");
        System.out.println(manager.printEpicForId(manager.getId()-1));

        manager.addSubtask("Подзадача №3", "Описание подзадачи №3", TaskStatus.NEW, 3);
        System.out.println(manager.printSubtaskForId(manager.getId()-1));

        manager.addEpic("Эпик №1", "Описание эпика №1");
        System.out.println(manager.printEpicForId(manager.getId()-1));

        manager.addSubtask("Подзадача №1", "Описание подзадачи №1", TaskStatus.NEW, 5);
        System.out.println(manager.printSubtaskForId(manager.getId()-1));

        manager.addSubtask("Подзадача №2", "Описание подзадачи №2", TaskStatus.DONE, 5);
        System.out.println(manager.printSubtaskForId(manager.getId()-1));

        System.out.println(manager.printEpicForId(5));

        System.out.println("Список задач:");
        System.out.println(manager.printAllTasks());
        System.out.println("Список эпиков:");
        System.out.println(manager.printAllEpics());
        System.out.println("Список подзадач:");
        System.out.println(manager.printAllSubtasks());

        System.out.println("Задача до изменения:");
        System.out.println(manager.printTaskForId(1));
        System.out.println("Задача после изменения:");
        manager.changeTask(1, "Задача №1", "Описание задачи №1 дополнено", TaskStatus.IN_PROGRESS);
        System.out.println(manager.printTaskForId(1));

        System.out.println("Список задач до удаления задачи ID: 1");
        System.out.println(manager.printAllTasks());
        manager.deleteTaskForId(1);
        System.out.println("Список задач после удаления:");
        System.out.println(manager.printAllTasks());

        System.out.println("Список подзадач Эпика ID: 5");
        manager.addSubtask("Подзадача №3", "Описание подзадачи №3", TaskStatus.IN_PROGRESS, 5);
        System.out.println(manager.printEpicForId(5));
        manager.printEpicSubtasks(5);
        manager.deleteSubtaskForId(7);
        System.out.println("Список подзадач Эпика ID: 5 после удаления подзадачи ID: 7");
        System.out.println(manager.printEpicSubtasks(5));

        System.out.println("Эпик ID:5 до изменения статуса подзадачи ID 8");
        System.out.println(manager.printEpicForId(5));
        manager.changeSubtask(8,"Подзадача №3", "Описание подзадачи №3", TaskStatus.NEW, 5);
        System.out.println("Эпик ID:5 после изменения статуса подзадачи ID 8");
        System.out.println(manager.printEpicForId(5));

        System.out.println(manager.printAllTasksEpicsSubtasks());
        System.out.println(manager.printEpicSubtasks(5));
        manager.changeEpic(5, "Эпик №1_1", "Описание эпика №1_1");

        System.out.println(manager.printAllTasksEpicsSubtasks());
        System.out.println(manager.printEpicSubtasks(5));
    }
}
