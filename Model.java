import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Model {
    static ArrayList<String> list = new ArrayList<String>();
    static ArrayList<String> list1 = new ArrayList<String>();
    static LinkedHashMap<String, String> map = new LinkedHashMap<>();
    private static ArrayList<Categories> categoryObjectList = new ArrayList<Categories>();
    static String path = "/home/omkar/Files/";

    public static ArrayList<Categories> getCategoryObjectList() {
        return categoryObjectList;
    }

    public static ArrayList<String> todayslist(String str) {
        list1.add(str);
        return list1;
    }

    public static LinkedHashMap<Integer, String> getCategories() {
        File f = new File(path);
        File[] file = f.listFiles();
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        for (int i = 0; i < file.length; i++) {
            String s = String.valueOf(file[i]);
            map.put(i + 1, s.substring(s.lastIndexOf("\\") + 1, s.lastIndexOf(".")));
        }
        return map;
    }

    public static boolean instantiateCategories(String categoryName) {
        Categories ctgry = new Categories(categoryName);
        LinkedHashMap<Integer, String> LHM = readTasks(categoryName);
        Set<Map.Entry<Integer, String>> s = LHM.entrySet();
        for (Map.Entry<Integer, String> e : s) {
            ctgry.addTask(instantiateTasks(e));
        }
        categoryObjectList.add(ctgry);
        return true;
    }

    public static TaskBean instantiateTasks(Map.Entry<Integer, String> entryset) {
        String[] srr = entryset.getValue().split(":");
        TaskBean tb = new TaskBean(srr[0], srr[1], LocalDate.parse(srr[2]), Boolean.parseBoolean(srr[3]), srr[4],
                LocalDate.parse(srr[5]));
        //Logger.getInstance().log("Instantiating Tasks");
        return tb;
    }

    public static LinkedHashMap<Integer, String> readTasks(String category) {
        BufferedReader br = null;
        LinkedHashMap<Integer, String> task = new LinkedHashMap<>();
        File file = new File(String.valueOf(path) + "\\" + category.toLowerCase() + ".txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                String line = null;
                int i = 1;
                while ((line = br.readLine()) != null) {
                    task.put(i++, line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return task;
    }

    public static void readCategory(String categoryName) {
        BufferedReader br = null;
        String line = null;
        try {
            br = new BufferedReader(new FileReader(String.valueOf(path) + "\\" + categoryName.toLowerCase() + ".txt"));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Invalid Input");
        }
    }

    public static Boolean createCategory(String category) {
        File f = new File(String.valueOf(path) + "\\" + category.toLowerCase() + ".txt");
        Boolean created = null;
        if (!f.exists()) {
            try {
                created = f.createNewFile();

                instantiateCategories(category);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return created;
    }

    public static String deleteCategory(String category) {
        File f = new File(path + "\\" + category.toLowerCase() + ".txt");
        if (f.exists()) {
            f.deleteOnExit();
            //Logger.getInstance().log("deleting category");
            return "Category Deleted";
        } else
            return "Category doesn't exists";
    }

    public static String writeTask() {
        BufferedWriter bw = null;
        String written = null;
        try {
            for (Categories c : categoryObjectList) {
                try {
                    bw = new BufferedWriter(new FileWriter(
                            new File(String.valueOf(path) + "\\" + c.getCatName().toLowerCase() + ".txt")));
                    for (TaskBean tb : c.displayTasks()) {
                        String line = tb.getName() + ":" + tb.getDescription() + ":" + tb.getDate() + ":"
                                + tb.getStatus() + ":" + tb.getPriority() + ":" + tb.getCreatedDate();
                        if (line != null) {
                            bw.write(line);
                            bw.flush();
                            bw.newLine();
                            //Logger.getInstance().log("Writing tasks");
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bw != null) {
                        bw.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Task added successfully";
    }

}
