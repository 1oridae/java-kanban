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
            Subtask newSubtask = new Subtask(this.id, name, description, subtaskStatus, epicId);
            subtasks.put(this.id, newSubtask);
            epics.get(epicId).subtaskIds.add(this.id);
            this.id++;
    }

    public Task printTaskForId(int id) {
        return tasks.get(id);
    }

    public Epic printEpicForId(int id) {
        updateStatusEpic(id);
        return epics.get(id);
    }

    public Subtask printSubtaskForId(int id) {
        return subtasks.get(id);
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
    
    public StringBuilder printAllTasks(){
        StringBuilder allTasks = new StringBuilder("Все задачи: \n");
        for (Integer tasksKey : tasks.keySet()) {
            allTasks.append(tasks.get(tasksKey).toString()).append("\n");
        }
        return allTasks;
    }

    public StringBuilder printAllEpics(){
        StringBuilder allEpics = new StringBuilder("Все эпики: \n");
        for (Integer keyEpics : epics.keySet()) {
            updateStatusEpic(keyEpics);
            allEpics.append(epics.get(keyEpics).toString()).append("\n");
        }
        return allEpics;
    }

    public StringBuilder printAllSubtasks(){
        StringBuilder allSubtasks = new StringBuilder("Все подзадачи: \n");
        for (Integer subtasksKey : subtasks.keySet()) {
            allSubtasks.append(subtasks.get(subtasksKey).toString()).append("\n");
        }
        return  allSubtasks;
    }

    public StringBuilder printEpicSubtasks(int epicId){
        StringBuilder epicSubtasks = new StringBuilder("Подзадачи эпика: \n");
        for (Integer subtasksKey : subtasks.keySet()){
            if (epics.get(epicId).subtaskIds.contains(subtasksKey)){
                epicSubtasks.append(subtasks.get(subtasksKey).toString()).append("\n");
            }
        }
        return epicSubtasks;
    }

    public StringBuilder printAllTasksEpicsSubtasks(){
        StringBuilder allTasksEpicsSubtasks = printAllTasks().append(printAllSubtasks().append(printAllEpics()));
        return allTasksEpicsSubtasks;
    }



}