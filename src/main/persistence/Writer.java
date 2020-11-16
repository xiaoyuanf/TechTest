package main.persistence;

import main.model.TreeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: Construct a writer that writes data to file
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // EFFECTS: write saveable to file
    public void write(Saveable saveable) {
        saveable.save(printWriter, (TreeNode) saveable);
    }

    public void close() {
        this.printWriter.close();
    }
}
