package main.persistence;

import com.google.gson.Gson;
import main.model.TreeNode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    // a reader that reads csv file and returns a list of String[]
    public static List<String[]> readCSV(File file) throws IOException {
        List<String[]> res = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        CSVParser parser = CSVParser.parse(br, CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();
        for (CSVRecord r : records) {
            String[] line = new String[r.size()];
            for (int i = 0; i < r.size(); i++) {
                line[i] = r.get(i);
            }
            res.add(line);
        }
        return res;
    }

    public static TreeNode readJSON(File file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        TreeNode root = gson.fromJson(br, TreeNode.class);
        return root;
    }
}
