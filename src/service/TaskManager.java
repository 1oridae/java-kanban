package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
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
    public void addTask(Task newTask){
        newTask.setId(this.id);
        tasks.put(this.id, newTask);
        this.id++;
    }
    // Добавление эпика
    public void addEpic(Epic newEpic){
        newEpic.setId(this.id);
        epics.put(this.id, newEpic);
        this.id++;
    }
    // Добавление подзадачи

    public void addSubtask(Subtask newSubtask){
            newSubtask.setId(this.id);
            subtasks.put(this.id, newSubtask);
            epics.get(newSubtask.getEpicId()).subtaskIds.add(this.id);
            this.id++;
    }

    public Task getTaskForId(int id) {
        return tasks.get(id);
    }

    public Epic getEpicForId(int id) {
        updateStatusEpic(id);
        return epics.get(id);
    }

    public Subtask getSubtaskForId(int id) {
        return subtasks.get(id);
    }

    public void deleteTaskForId(int id) {
        tasks.remove(id);
    }

    public void deleteEpicForId(int id) {
        // Удаляем все подзадачи эпика
        ArrayList<Integer> idSubtaskToDelete = new ArrayList<>();
        for (Integer keySubtasks : subtasks.keySet()) {
            if (subtasks.get(keySubtasks).getEpicId() == id) {
                idSubtaskToDelete.add(keySubtasks);
            }
        }
        for (Integer idSubtask : idSubtaskToDelete) {
            subtasks.remove(idSubtask);
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

    public void changeTask(int id, Task newTask) {
        newTask.setId(id);
        tasks.replace(id, newTask);
    }

    public void changeEpic(int id, Epic newEpic) {
        newEpic.setId(id);
        newEpic.subtaskIds = epics.get(id).subtaskIds;
        epics.replace(id, newEpic);
    }

    public void changeSubtask(int id, Subtask newSubtask) {
        newSubtask.setId(id);
        subtasks.replace(id, newSubtask);
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
    
    public ArrayList<Task> getTasks(){
        ArrayList<Task> allTasks = new ArrayList<>();
        for (Integer tasksKey : tasks.keySet()) {
            allTasks.add(tasks.get(tasksKey));
        }
        return allTasks;
    }

    public ArrayList<Epic> getEpics(){
        ArrayList<Epic> allEpics = new ArrayList<>();
        for (Integer epicsKey : epics.keySet()) {
            updateStatusEpic(epicsKey);
            allEpics.add(epics.get(epicsKey));
        }
        return allEpics;
    }

    public ArrayList<Subtask> getSubtasks(){
        ArrayList<Subtask> allSubtasks = new ArrayList<>();
        for (Integer subtasksKey : subtasks.keySet()) {
            allSubtasks.add(subtasks.get(subtasksKey));
        }
        return  allSubtasks;
    }

    public ArrayList<Subtask> getEpicSubtasks(int epicId){
        ArrayList<Subtask> epicSubtasks = new ArrayList<>();
        for (Integer subtasksKey : subtasks.keySet()){
            if (epics.get(epicId).subtaskIds.contains(subtasksKey)){
                epicSubtasks.add(subtasks.get(subtasksKey));
            }
        }
        return epicSubtasks;
    }

    public ArrayList<Object> getAllTasksEpicsSubtasks(){
        ArrayList<Object> allTasksEpicsSubtasks = new ArrayList<>();
        allTasksEpicsSubtasks.add(getEpics());
        allTasksEpicsSubtasks.add(getTasks());
        allTasksEpicsSubtasks.add(getSubtasks());
        return allTasksEpicsSubtasks;
    }



}