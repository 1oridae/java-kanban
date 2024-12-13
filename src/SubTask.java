public class SubTask extends Task{

    int epicId;

    public SubTask(int id, String name, String description, TaskStatus status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "№ " + id +
                "; № Эпика:" + epicId +
                "; Наименование: " + name +
                "; Описание: " + description +
                "; Статус: " + status + ".";
    }
}
