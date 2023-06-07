import java.io.*;
import java.time.LocalDate;

public class TaskBean {

    private String name;
    private String description;
    private LocalDate date;
    private boolean status;
    private String priority;
    private LocalDate createdDate;

    final String pathName = "/home/omkar/Files/";


    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public Boolean getStatus(){
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


    public TaskBean(String name, String description, LocalDate date, boolean status, String priority, LocalDate createdDate) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
    }

    public TaskBean(){

    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", priority='" + priority + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }




    //Methods
    public String addTask(TaskBean t,String catName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName+catName,true));

        writer.write(t.getName());
        writer.write(":");
        writer.write(t.getDescription());
        writer.write(":");
        writer.write(t.getPriority());
        writer.write(":");
        writer.write(String.valueOf(t.getStatus()));
        writer.write(":");
        writer.write(String.valueOf(t.getDate()));
        writer.write(":");
        writer.write(String.valueOf(t.getCreatedDate()));
        writer.write(":");
        writer.close();
        return "added";
    }



//    public String update(TaskBean oldBean, TaskBean newBean){
//
//    }


}
