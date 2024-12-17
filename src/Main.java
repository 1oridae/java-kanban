import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("TaskName1", "TaskDescription1", TaskStatus.NEW);
        Task task2 = new Task("TaskName2", "TaskDescription2", TaskStatus.IN_PROGRESS);
        Task task3 = new Task("TaskName3", "TaskDescription3", TaskStatus.DONE);
        Task task31 = new Task("TaskName3_1", "TaskDescription3_1", TaskStatus.NEW);

        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);


        Epic epic1 = new Epic("EpicName1", "EpicDescription1");
        Epic epic2 = new Epic("EpicName2", "EpicDescription2");
        Epic epic3 = new Epic("EpicName2_1", "EpicDescription2_1");

        manager.addEpic(epic1);
        manager.addEpic(epic2);

        Subtask subtask1 = new Subtask("SubtaskName1", "SubtaskDescription1", TaskStatus.NEW, 4);
        Subtask subtask2 = new Subtask("SubtaskName2", "SubtaskDescription2", TaskStatus.IN_PROGRESS, 4);
        Subtask subtask3 = new Subtask("SubtaskName3", "SubtaskDescription3", TaskStatus.DONE, 4);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);
        manager.addSubtask(subtask3);
        Subtask subtask4 = new Subtask("SubtaskName4", "SubtaskDescription4", TaskStatus.NEW, 5);
        Subtask subtask5 = new Subtask("SubtaskName5", "SubtaskDescription5", TaskStatus.NEW, 5);
        Subtask subtask6 = new Subtask("SubtaskName6", "SubtaskDescription6", TaskStatus.NEW, 5);
        manager.addSubtask(subtask4);
        manager.addSubtask(subtask5);
        manager.addSubtask(subtask6);

        System.out.println(manager.getEpicSubtasks(4));

    }
}
