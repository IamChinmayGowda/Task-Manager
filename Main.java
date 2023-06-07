import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);


        final String pathName = "/home/omkar/Files/";


        System.out.println("---------------Welcome to TaskManager---------------");
        int ch = 0;
        while (ch!=5){
            System.out.println("1 Create Category");
            System.out.println("2 Load Category");
            System.out.println("3 Search");
            System.out.println("4 List");
            System.out.println("5 Exit");
            try {
                ch = sc.nextInt();
            } catch (InputMismatchException m) {
                sc.nextLine();
                System.out.println("Only Integer Input is allowed");
            }



            switch (ch){
                case 1:
                    System.out.println("Enter the category Name");
                    String categoryName = br.readLine();
                    File myObj = new File(pathName+categoryName+".txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }

                    int i1 = 0;
                    while (i1 != 6){
                        System.out.println("1 Add a Task");
                        System.out.println("2 Edit a task");
                        System.out.println("3 Remove a Task");
                        System.out.println("4 List The task");
                        System.out.println("5 Search");
                        System.out.println("6 Go Back");

                        try {
                            i1 = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException m) {
                            sc.nextLine();
                            System.out.println("Only Integer Input is allowed");
                        }
                        switch (i1){
                            case 1:{
                                System.out.println("Enter the file name you want to write to");
                                String fileName = br.readLine();

                                String name = null;
                                String description = null;
                                String date = null;
                                String status = null;
                                String priority = null;
                                LocalDate createdDate = null;
                                TaskBean taskBean = new TaskBean();

                                System.out.println("Enter the task name ");
                                taskBean.setName(br.readLine());
                                System.out.println("Enter task description");
                                taskBean.setDescription(br.readLine());
                                System.out.println("Enter end date for completion: YYYY-MM-DD");
                                taskBean.setDate(LocalDate.parse(br.readLine()));
                                System.out.println("Set Priority: High/Medium/Low");
                                System.out.println("Press 1 for High");
                                System.out.println("Press 2 for Medium");
                                System.out.println("Press 3 for Low");
                                int p = 0;
                                try {
                                    p = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException m) {
                                    sc.nextLine();
                                    System.out.println("Only Integer Input is allowed");
                                }
                                priority = null;
                                switch (p) {
                                    case 1:
                                        priority = Constants.High;
                                        break;
                                    case 2:
                                        priority = Constants.Medium;
                                        break;
                                    case 3:
                                        priority = Constants.Low;
                                        break;
                                    default:
                                        System.out.println("Invalid Input");
                                        break;
                                }
                                taskBean.setPriority(priority);
                                taskBean.setStatus(false);
                                taskBean.setCreatedDate(LocalDate.now());

                                //Calling the method Add Task
                                taskBean.addTask(taskBean,fileName);
                                break;
                            }

                            case 2: {
                                String s = String.valueOf(Model.getCategories().values());
                                s = s.substring(s.indexOf("[") + 1, s.lastIndexOf("]"));
                                String[] srr = s.split(", ");

                                System.out.println("Enter task name");
                                sc.nextLine();
                                String taskname = sc.next();
                                ArrayList<TaskBean> al;
                                for (int i = 0; i < Model.getCategoryObjectList().size(); i++) {
                                    if (categoryName.equalsIgnoreCase(srr[i])) {
                                        al = Model.getCategoryObjectList().get(i).displayTasks();
                                        for (TaskBean tb : al) {
                                            if (tb.getName().equalsIgnoreCase(taskname)) {
                                                System.out.println("Press 1 to update name");
                                                System.out.println("Press 2 to update description");
                                                System.out.println("Press 3 to update date");
                                                System.out.println("Press 4 to update status");
                                                System.out.println("Press 5 to update priority");
                                                System.out.println("Press 6 to go back");
                                                System.out.println("Press 7 to exit");
                                                int u1 = 0;
                                                while (u1 != 6) {
                                                    try {
                                                        u1 = sc.nextInt();
                                                        sc.nextLine();
                                                    } catch (InputMismatchException m) {
                                                        System.out.println("Only Integer Input is allowed");
                                                        sc.nextLine();
                                                    }
                                                    switch (u1) {
                                                        case 1:
                                                            System.out.println("Enter updated taskname");
                                                            String updatedname = sc.next();
                                                            sc.nextLine();
                                                            tb.setName(updatedname);
                                                            break;
                                                        case 2:
                                                            System.out.println("Enter updated description");
                                                            String updatedescription = sc.nextLine();
                                                            tb.setDescription(updatedescription);
                                                            break;
                                                        case 3:
                                                            System.out.println(
                                                                    "Enter updated end date for completion: YYYY-MM-DD");

                                                            tb.setDate((LocalDate.parse(br.readLine())));

                                                            break;
                                                        case 4:
                                                            System.out.println("Enter updated status");
                                                            System.out.println("Press 1 if task is completed");
                                                            System.out.println("Press 2 if task is not completed");
                                                            int status1 = 0;
                                                            try {
                                                                status1 = sc.nextInt();
                                                                sc.nextLine();
                                                            } catch (InputMismatchException m) {
                                                                System.out.println("Only Integer Input is allowed");
                                                                sc.nextLine();
                                                            }
                                                            String status2 = null;
                                                            switch (status1) {
                                                                case 1:
                                                                    status2 = Constants.true1;
                                                                    tb.setStatus(true);
                                                                    break;
                                                                case 2:
                                                                    status2 = Constants.false1;
                                                                    tb.setStatus(false);
                                                                    break;
                                                                default:
                                                                    System.out.println("Invalid Input");
                                                                    break;
                                                            }
                                                            break;
                                                        case 5:
                                                            System.out
                                                                    .println("Enter updated priority: High/Medium/Low");
                                                            System.out.println("Press 1 for High");
                                                            System.out.println("Press 2 for Medium");
                                                            System.out.println("Press 3 for Low");
                                                            int p1 = 0;
                                                            try {
                                                                p1 = sc.nextInt();
                                                                sc.nextLine();
                                                            } catch (InputMismatchException m) {
                                                                System.out.println("Only Integer Input is allowed");
                                                                sc.nextLine();
                                                            }
                                                            String priority1 = null;
                                                            switch (p1) {
                                                                case 1:
                                                                    priority1 = Constants.High;
                                                                    break;
                                                                case 2:
                                                                    priority1 = Constants.Medium;
                                                                    break;
                                                                case 3:
                                                                    priority1 = Constants.Low;
                                                                    break;
                                                                default:
                                                                    System.out.println("Invalid Input");
                                                                    break;
                                                            }
                                                            tb.setPriority(priority1);
                                                            break;
                                                        case 6:
                                                            Model.writeTask();
                                                            System.out.println("Thank you");
                                                            System.exit(1);
                                                            break;
                                                    }
                                                    break;
                                                }
                                                System.out.println("TaskName -- " + tb.getName()
                                                        + " ,Description --" + tb.getDescription()
                                                        + " ,End Date for completion -- " + tb.getDate()
                                                        + " ,Status -- " + tb.getStatus() + " ,Priority -- "
                                                        + tb.getPriority() + " ,CreatedDate -- "
                                                        + tb.getCreatedDate());
                                            }
                                        }
                                    }
                                }
                                break;
                            }







                        }

                    }

            }


        }



























    }
}