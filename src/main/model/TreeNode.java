package main.model;

import com.google.gson.Gson;
import main.persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
// represents a node in the fee tree. total is the total fees of the node
public class TreeNode implements Saveable {
    private String name;
    private String parent;
    private double total;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(String name) {
        this.name = name;
    }
    public void addChild(TreeNode node) {
        children.add(node);
        node.parent = this.name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public double getTotal() {
        return total;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public void save(PrintWriter printWriter, TreeNode node) {
        Gson gson = new Gson();
        printWriter.print(gson.toJson(node));
    }
}
