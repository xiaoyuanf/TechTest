package main.ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new MainApp();
//        List<String[]> records = null;
//        TreeNode root = new TreeNode("root");
//        TreeUtil util = new TreeUtil();
//        try {
//            records = Reader.readCSV(new File("./data/raw_fees.csv"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 1; i < records.size(); i++) {
//            Record r = new Record(records.get(i));
//            util.updateTree(root, r, new String[]{r.getDepartment(), r.getCategory(), r.getSubCategory(), r.getType()}, 0);
//        }
//        //TreeNode root = Reader.readJSON(new File("./data/output.txt"));
//        //util.queryTree(root, new String[]{"Support", "Tier 2", "Cat1"}, 0);
//
//        try {
//            Writer writer = new Writer(new File("./data/output2.txt"));
//            writer.write(root);
//            writer.close();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }
}
