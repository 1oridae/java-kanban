import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epic = new HashMap<>();
    int id = 1;

    public void manager(){
        while (true) {
            printMenu();

            int number = scanner.nextInt();

            switch (number){
                case 1:
                    addTask(); // исполнено
                    break;
                case 2:
                    addEpic(); // исполнено
                    break;
                case 3:
                    changingTask();// исполнено
                    break;
                case 4:
                    changingEpic(); // исполнено
                    break;
                case 5:
                    printForIdTask(); // исполнено
                    break;
                case 6:
                    printTasks(); // исполнено
                    break;
                case 7:
                    tasks.clear();
                    epic.clear();
                    id = 1;
                    break;
                case 8:
                    return;
            }
        }
    }

    private void changingEpic() {
        System.out.println("Введите номер изменяемого эпика:");
        int numberEpic = scanner.nextInt();
        System.out.println("Действие с эпиком:");
        System.out.println("1. Обновление эпика;");
        System.out.println("2. Обновление подзадачи;");
        int epicOrSubtusk = scanner.nextInt();
        switch (epicOrSubtusk){
            case 1:
                epic.get(numberEpic).changeEpic(scanner);
            case 2:
                epic.get(numberEpic).changeEpicSubtask(scanner);
        }
    }

    private void printForIdTask() {
        System.out.println("Введите id задачи, эпика или подзадачи:");
        int findId = scanner.nextInt();
        for (Integer keyTasks : tasks.keySet()) {
            if (findId == keyTasks){
                System.out.println(tasks.get(keyTasks));
                return;
            }
        }
        for (Integer keyEpics : epic.keySet()) {
            if (findId == keyEpics) {
                System.out.println(epic.get(keyEpics));
                return;
            }
            for (Integer keyEpicsSubtask : epic.get(keyEpics).subTask.keySet()) {
                if (keyEpicsSubtask == findId) {
                    System.out.println(epic.get(keyEpics).subTask.get(keyEpicsSubtask));
                    return;
                }
            }
        }
        System.out.println("Не удалось найти задачу по введенному вами id");
    }

    private void changingTask() {
        System.out.println("Введите номер задачи, которую требуется обновить:");
        int taskNumber = scanner.nextInt();
        tasks.get(taskNumber).setName(scanner);
        tasks.get(taskNumber).setDescription(scanner);
        tasks.get(taskNumber).setStatus(scanner);
    }

    public void printMenu(){
        System.out.println("1. Добавить задачу");
        System.out.println("2. Добавить эпик");
        System.out.println("3. Обновление задачи");
        System.out.println("4. Обновление эпика");
        System.out.println("5. Показать задачу по идентификатору");
        System.out.println("6. Показать все задачи");
        System.out.println("7. Удалить все задачи");
        System.out.println("8. Выход");
    }

    public void addTask(){
        System.out.println("Введите наименование задачи:");
        String name = scanner.next();
        System.out.println("Введите описание задачи:");
        String description = scanner.next();
        System.out.println("Введите статус задачи:");
        System.out.println("1. NEW");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. DONE");
        int statusNumber = scanner.nextInt();
        TaskStatus status;
        if (statusNumber == 1) {
            status = TaskStatus.NEW;
        } else if (statusNumber == 2) {
            status = TaskStatus.IN_PROGRESS;
        } else if (statusNumber == 3) {
            status = TaskStatus.DONE;
        } else {
            System.out.println("Вы ввели некорректный номер статуса");
            return;
        }
        tasks.put(id, new Task(id, name, description, status));

        System.out.println("Задача :" + tasks.get(id).toString() + " добавлена!");
        id++;
    }

    public void printTasks(){
        System.out.println("Задачи:");
        for (Integer keyTasks : tasks.keySet()){
            System.out.println(tasks.get(keyTasks));
        }
        System.out.println("Эпики:");
        for (Integer keyEpic : epic.keySet()) {
            System.out.println(epic.get(keyEpic));
            System.out.println("----Подзадачи:");
            for (Integer keyEpicSubtask : epic.get(keyEpic).subTask.keySet()){
                System.out.println("----" + epic.get(keyEpic).subTask.get(keyEpicSubtask));
            }
        }
    }

    public void addEpic(){
        System.out.println("Введите наименование эпика:");
        String nameEpic = scanner.next();
        System.out.println("Введите описание эпика:");
        String descriptionEpic = scanner.next();
        HashMap<Integer, SubTask> subTask = new HashMap<>();
        int epicId = id;
        id++;

        while (true) {
            System.out.println("Введите подзадачи (если подзадач более не имеется введите 0):");
            String name = scanner.next();
            if (name.equals("0")) {
                break;
            }
            System.out.println("Введите описание подзадачи:");
            String description = scanner.next();
            System.out.println("Введите № статуса подзадачи:");
            System.out.println("1. NEW");
            System.out.println("2. IN_PROGRESS");
            System.out.println("3. DONE");
            int statusNumber = scanner.nextInt();
            TaskStatus status = switch (statusNumber) {
                case 2 -> TaskStatus.IN_PROGRESS;
                case 3 -> TaskStatus.DONE;
                default -> TaskStatus.NEW;
            };
            subTask.put(id ,new SubTask(id, name, description, status, epicId));
            id++;
        }
        epic.put(epicId, new Epic(epicId, nameEpic, descriptionEpic, subTask));
    }
}