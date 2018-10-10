import java.util.ArrayList;

public class Task {

    private int taskID;
    private String name;
    private String taskCondition;
    private String deadline;
    private String points;
    ArrayList<CompletedTask> taskCompletedTasks = new ArrayList();

    public Task(int id, String name, String taskCondition, String deadline, String points) {
        this.taskID = id;
        this.name = name;
        this.taskCondition = taskCondition;
        this.deadline = deadline;
        this.points = points;
    }

    public int getTaskID() {
        return taskID;
    }

    public void addTaskCompletedTask(CompletedTask completedTask) {
        this.taskCompletedTasks.add(completedTask);
    }

    public void printTaskCompletedTasks() {
        for (CompletedTask completedTask : this.taskCompletedTasks
        ) {
            System.out.println(completedTask);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", taskCondition='" + taskCondition + '\'' +
                ", deadline='" + deadline + '\'' +
                ", points='" + points + '\'' +
                '}';
    }
}
