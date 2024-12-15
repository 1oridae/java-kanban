package model;

import java.util.ArrayList;

public class Epic extends Task {

    public ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(int id,
                String name,
                String description) {
        super(id, name, description);
    }

    public Epic(int id,
                String name,
                String description,
                ArrayList<Integer> subtaskIds){
        super(id, name, description);
        this.subtaskIds = subtaskIds;
    }
}
