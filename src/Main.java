import java.io.*;
import java.util.*;

/**
 * @author Stanislav Beloshitsky
 * FilesTask
 * 23.05.2022
 **/

public class Main {

    //директория, где нужно искать файлы
    private static String folder = "";

    private static ArrayList<File> filesList = new ArrayList<>();

    public static void main(String[] args) {

        searchFiles(new File(folder), filesList);

        sort();

        write();

        System.out.println("Новый файл создан в директории " + folder);
    }

    public static void searchFiles(File root, List<File> filesList) {
        if (root.isDirectory()) {
            File[] directoryFiles = root.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, filesList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".txt")) {
                            filesList.add(file);
                        }
                    }
                }
            }
        }
    }

    public static void sort() {
        Comparator<File> comparator = Comparator.comparing(File::getName);

        filesList.sort(comparator);
    }

    public static void write() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(folder + "\\result.txt"))) {
            filesList.forEach(file -> {
                try (BufferedReader reader = new BufferedReader(new FileReader(file.toString()))) {
                    while (reader.ready()) {
                        writer.write(reader.readLine());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
