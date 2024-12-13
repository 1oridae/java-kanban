import java.util.Scanner;

public class Task{
    String name;
    String description;
    TaskStatus status;
    int id;

    public Task(int id, String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setStatus(Scanner scanner) {
        System.out.println("Введите № обновленного статуса:");
        System.out.println("1. NEW");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. DONE");
        int statusNumber = scanner.nextInt();
        TaskStatus newTaskStatus = TaskStatus.NEW;
        switch (statusNumber){
            case 1:
                newTaskStatus = TaskStatus.NEW;
                break;
            case 2:
                newTaskStatus = TaskStatus.IN_PROGRESS;
                break;
            case 3:
                newTaskStatus = TaskStatus.DONE;
                break;
        }
        this.status = newTaskStatus;
    }

    public void setName(Scanner scanner) {
        System.out.println("Введите обновленное наименование " +
                "(если изменений не требуется - введете 0):");
        String newTaskName = scanner.next();
        if (!newTaskName.equals("0")) {
            this.name = newTaskName;
        }

    }

    public void setDescription(Scanner scanner) {
        System.out.println("Введите обновленное описание " +
                "(если изменений не требуется - введите 0):");
        String newTaskDescription = scanner.next();
        if (!newTaskDescription.equals("0")) {
            this.description = newTaskDescription;
        }
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if (name != null) {
            hash = hash + name.hashCode();
        }
        hash = hash * 31;

        if (name != null) {
            hash = hash + description.hashCode();
        }

        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Task otherTask = (Task) obj;
        return id == otherTask.id;
    }

    @Override
    public String toString() {
        return "№ " + id +
                "; Наименование: " + name +
                "; Описание: " + description +
                "; Статус: " + status;
    }

}
