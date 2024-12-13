import java.util.HashMap;
import java.util.Scanner;

public class Epic extends Task{

    HashMap<Integer, SubTask> subTask;

    public Epic(int id, String name, String description,
                HashMap<Integer, SubTask> subTask) {
        super(id, name, description);
        this.subTask = subTask;
        updateStatus();
    }

    public void printSubTask(){
        System.out.println("Подзадачи эпика: " + this);
        for(Integer keySubtask : subTask.keySet()) {
            System.out.println(subTask.get(keySubtask));
        }
    }

    public void updateStatus() {
        int newSum = 0;
        int doneSum = 0;
        for (Integer keySubtask : subTask.keySet()) {
            if (subTask.get(keySubtask).status == TaskStatus.NEW) {
                newSum++;
            } else if (subTask.get(keySubtask).status == TaskStatus.DONE) {
                doneSum++;
            }
        }
        if (doneSum == subTask.size()) {
            this.status = TaskStatus.DONE;
        } else if (newSum == subTask.size()) {
            this.status = TaskStatus.NEW;
        } else {
            this.status = TaskStatus.IN_PROGRESS;
        }
    }

    public void changeEpicSubtask(Scanner scanner){
        printSubTask();
        System.out.println("Выберите № подзадачи, которую требуется обновить:");
        int keySubtask = scanner.nextInt();
        subTask.get(keySubtask).setName(scanner);
        subTask.get(keySubtask).setDescription(scanner);
        subTask.get(keySubtask).setStatus(scanner);
        updateStatus();
    }
    public void changeEpic(Scanner scanner){
        setName(scanner);
        setDescription(scanner);
    }
}
