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

    // EFFECTS: processes user input on the main menu level
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

    // EFFECTS: processes user command
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

    // EFFECTS: displays main menu of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tA -> read raw_fee.csv");
        System.out.println("\tB -> do query");
        System.out.println("\tQ -> quit");
    }

    //EFFECTS: reads the csv file and create a tree with total fees at each node
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

    //EFFECTS: saves the tree as a json file
    private void saveTree() {
        try {
            Writer writer = new Writer(new File("./data/output.txt"));
            writer.write(root);
            writer.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: makes query of fees of nodes on a given path
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

    // EFFECTS: gets user input of path
    private String getPath() {
        String path = "";
        while (path.length() == 0) {
            path = input.nextLine();
        }
        return path;
    }
}
