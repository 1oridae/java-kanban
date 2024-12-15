package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int id = 1;

    public int getId() {
        return id;
    }
    // Обновление эпика
    public void updateStatusEpic(int epicId) {
        int newSum = 0;
        int doneSum = 0;
        for (Integer keySubtask : epics.get(epicId).subtaskIds) {
            if (subtasks.get(keySubtask).getStatus() == TaskStatus.NEW) {
                newSum++;
            } else if (subtasks.get(keySubtask).getStatus() == TaskStatus.DONE) {
                doneSum++;
            }
        }

        if (epics.get(epicId).subtaskIds.isEmpty()) {
            epics.get(epicId).setStatus(TaskStatus.NEW);
        } else if (newSum == epics.get(epicId).subtaskIds.size()) {
            epics.get(epicId).setStatus(TaskStatus.NEW);
        } else if (doneSum == epics.get(epicId).subtaskIds.size()){
            epics.get(epicId).setStatus(TaskStatus.DONE);
        }else {
            epics.get(epicId).setStatus(TaskStatus.IN_PROGRESS);
        }
    }
    // Добавление задачи
    public void addTask(String name, String description, TaskStatus taskStatus){
        Task newTask = new Task(this.id, name, description, taskStatus);
        tasks.put(this.id, newTask);
        this.id++;
    }
    // Добавление эпика
    public void addEpic(String name, String description){
        Epic newEpic = new Epic(this.id, name, description);
        epics.put(this.id, newEpic);
        this.id++;
    }
    // Добавление подзадачи

    public void addSubtask(String name, String description, TaskStatus subtaskStatus, int epicId){
        if (epics.containsKey(epicId)) {
            Subtask newSubtask = new Subtask(this.id, name, description, subtaskStatus, epicId);
            subtasks.put(this.id, newSubtask);
            epics.get(epicId).subtaskIds.add(this.id);
            this.id++;
        } else {
            System.out.println("Эпик под введенным id не создан или введен некорректный id");
        }
    }

    public String printTaskForId(int id) {
        return tasks.get(id).toString();
    }

    public String printEpicForId(int id) {
        updateStatusEpic(id);
        return epics.get(id).toString();
    }

    public String printSubtaskForId(int id) {
        return subtasks.get(id).toString();
    }

    public void deleteTaskForId(int id) {
        tasks.remove(id);
    }

    public void deleteEpicForId(int id) {
        // Удаляем все подзадачи эпика
        for (int i = 0; i < subtasks.size(); i++) {
            if (subtasks.get(i).getEpicId() == id) {
                subtasks.remove(i);
            }
        }
        // Удаляем сам эпик
        epics.remove(id);
    }

    public void deleteSubtaskForId(int id) {
        // Удаляем идентификатор подзадачи в эпике
        epics.get(subtasks.get(id).getEpicId()).subtaskIds.remove((Integer) id);
        // Удаляем саму подзадачу
        subtasks.remove(id);

    }

    public void changeTask(int id, String name, String description, TaskStatus taskStatus) {
        tasks.put(id, new Task(id, name, description, taskStatus));
    }

    public void changeEpic(int id, String name, String description) {
        // Интересно, успеет ли передасться массив
        epics.put(id, new Epic(id, name, description, epics.get(id).subtaskIds));
    }

    public void changeSubtask(int id, String name, String description, TaskStatus taskStatus, int epicId) {
        subtasks.put(id, new Subtask(id, name, description, taskStatus, epicId));
    }

    public void deleteAllTasks(){
        tasks.clear();
    }

    public void deleteAllEpics(){
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtasks(){
        // Удаление id subtasks в epics
        for (Integer keyEpics : epics.keySet()) {
            epics.get(keyEpics).subtaskIds.clear();
        }
        subtasks.clear();
    }
    
    public void printAllTasks(){
        for (Integer tasksKey : tasks.keySet()) {
            System.out.println(tasks.get(tasksKey).toString());
        }
    }

    public void printAllEpics(){
        for (Integer keyEpics : epics.keySet()) {
            updateStatusEpic(keyEpics);
        }

        for (Integer epicsKey : epics.keySet()) {
            System.out.println(epics.get(epicsKey).toString());
        }
    }

    public void printAllSubtasks(){
        for (Integer subtasksKey : subtasks.keySet()) {
            System.out.println(subtasks.get(subtasksKey).toString());
        }
    }

    public void printEpicSubtasks(int epicId){
        for (Integer subtasksKey : subtasks.keySet()){
            if (epics.get(epicId).subtaskIds.contains(subtasksKey)){
                System.out.println(subtasks.get(subtasksKey).toString());
            }
        }
    }

}