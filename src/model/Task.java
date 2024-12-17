package model;

public class Task{
    public String name;
    public String description;
    public TaskStatus status;
    public Integer id;

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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
        return "ID: " + id +
                "; Наименование: " + name +
                "; Описание: " + description +
                "; Статус: " + status;
    }

    public void setId(int id) {
        this.id = id;
    }
}
