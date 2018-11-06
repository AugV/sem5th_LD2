package entities;

import utilities.DuplicateChecker;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Indexable, Serializable {
    private int courseID;
    private String name;
    private String information;
    private ArrayList<Task> courseTasks = new ArrayList();


    public Course(int courseID, String name, String information) {
        this.courseID = courseID;
        this.name = name;
        this.information = information;
    }

    public Course(int courseID, String name, String information, Teacher teacher, Group group) {
        this.courseID = courseID;
        this.name = name;
        this.information = information;
        teacher.addTeacherCourses(this);
        group.addGroupCourse(this);
    }

    public int getId() {
        return courseID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCourseInformation() {
        return information;
    }

    public void addCourseTask(Task task) {
        if (!new DuplicateChecker().isDuplicateById(task, courseTasks)) {
            courseTasks.add(task);
        } else {
            System.out.println(" !entities.Task already exists!");
        }
    }

    public void addCompletedTaskToCourseTaskById(int taskId, CompletedTask newCompletedTask){
            for (Task task : courseTasks) {
                if(task.getId()== taskId){task.addTaskCompletedTask(newCompletedTask);}
            }
    }

    public CompletedTask getCompletedTaskById(int completedTaskId){
        CompletedTask completedTask= null;
        for (Task task : courseTasks) {
            completedTask = task.getCompletedTaskById(completedTaskId);
        }
        return completedTask;
    }

    public Task getCourseTaskByID(int id) {
        Task taskMatch = null;
        for (Task task : this.getCourseTasks()) {
            if (task.getId() == id) {
                taskMatch = task;
                break;
            }
        }
        return taskMatch;
    }

    public ArrayList<Task> getCourseTasks() {
        return courseTasks;
    }

    @Override
    public String toString() {
        return "entities.Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", courseTasks=" + courseTasks +
                '}';
    }
}