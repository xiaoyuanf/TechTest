package main.ui;

import main.model.Record;
import main.model.TreeNode;
import main.model.TreeUtil;
import main.persistence.Reader;
import main.persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private Scanner input;
    private List<String[]> records = null;
    private TreeNode root = new TreeNode("root");
    private TreeUtil util = new TreeUtil();

    public MainApp() {
        runApp();
    }

    private void runApp() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("See you!");
    }
    private void processCommand(String command) {
        if (command.equals("a")) {
            creatTree();
            saveTree();
        } else if (command.equals("b")) {
            queryTree();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tA -> read raw_fee.csv");
        System.out.println("\tB -> do query");
        System.out.println("\tQ -> quit");
    }

    private void creatTree() {
        try {
            records = Reader.readCSV(new File("./data/raw_fees.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < records.size(); i++) {
            Record r = new Record(records.get(i));
            util.updateTree(root, r, new String[]{r.getDepartment(), r.getCategory(), r.getSubCategory(), r.getType()}, 0);
        }
    }

    private void saveTree() {
        try {
            Writer writer = new Writer(new File("./data/output.txt"));
            writer.write(root);
            writer.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void queryTree() {
        System.out.print("Enter query path (eg. 'Support/Tier 2/Cat1'): ");
        String s = getPath();

        TreeNode root = null;
        try {
            root = Reader.readJSON(new File("./data/output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String path[] = s.split("/");
        util.queryTree(root, path, 0);
    }

    private String getPath() {
        String path = "";
        while (path.length() == 0) {
            path = input.nextLine();
        }
        return path;
    }
}
