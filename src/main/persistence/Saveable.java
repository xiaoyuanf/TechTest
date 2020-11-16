package main.persistence;

import main.model.TreeNode;

import java.io.PrintWriter;

public interface Saveable {
    void save(PrintWriter printWriter, TreeNode node);
}
