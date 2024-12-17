package model;

public class Subtask extends Task {

    private Integer epicId;

    public Subtask(String name, String description, TaskStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }



    @Override
    public String toString() {
        return "ID: " + id +
                "; Epic ID: " + epicId +
                "; Наименование: " + name +
                "; Описание: " + description +
                "; Статус: " + status + ".";
    }
}
