package model;

import java.util.ArrayList;

public class Epic extends Task {

    public ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name,
                String description) {
        super(name, description);
    }

    public String toString() {
        return "ID: " + id +
                "; Наименование: " + name +
                "; Описание: " + description +
                "; Статус: " + status +
                "; ID подзадач:" + subtaskIds;
    }


}
