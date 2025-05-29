package intern1;

import java.io.*;

public class Main1 {

    public static void main(String[] args) {
        String fileName = "output.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose an option: \n1. Write to file \n2. Read from file \n3. Modify file");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter content to write to the file:");
                    String content = reader.readLine();
                    writeToFile(fileName, content);
                    break;
                case 2:
                    readFromFile(fileName);
                    break;
                case 3:
                    System.out.println("Enter the text to replace:");
                    String oldText = reader.readLine();
                    System.out.println("Enter the new text:");
                    String newText = reader.readLine();
                    modifyFile(fileName, oldText, newText);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void modifyFile(String fileName, String oldText, String newText) {
        File file = new File(fileName);
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(oldText)) {
                    line = line.replace(oldText, newText);
                }
                updatedContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file during modification: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(updatedContent.toString());
        } catch (IOException e) {
            System.err.println("Error writing to the file during modification: " + e.getMessage());
        }
    }
}
