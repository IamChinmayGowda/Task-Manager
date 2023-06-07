import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Categories {
    private String category;
    private ArrayList<TaskBean> taskList;
    private static LinkedHashSet<String> categoryList;

    public String getCatName() {
        return category;
    }

    public ArrayList<TaskBean> displayTasks() {
        return taskList;
    }

    public String addTask(TaskBean task) {
        taskList.add(task);
        return "Task has been added successfully";
    }

    public String toString() {
        return "categories [categoryName=" + category + "]";
    }

    public Categories(String categoryName) {
        super();
        this.category = categoryName;
        this.taskList = new ArrayList<TaskBean>();
    }
}
