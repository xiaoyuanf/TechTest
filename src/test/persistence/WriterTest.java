package test.persistence;

import main.model.TreeNode;
import main.persistence.Reader;
import main.persistence.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterTest {
    private Writer testWriter;
    private static final String TEST_FILE = "./data/testJSON.txt";
    private TreeNode root;
    private TreeNode childA;
    private TreeNode childB;

    @BeforeEach
    public void runBefore() {
        root = new TreeNode("root");
        childA = new TreeNode("childA");
        childB = new TreeNode("childB");
        childA.setTotal(1.11);
        root.addChild(childA);
        root.addChild(childB);
        try {
            testWriter = new Writer(new File(TEST_FILE));
        } catch (FileNotFoundException e) {
            fail("shouldn't catch exception");
        } catch (UnsupportedEncodingException e) {
            fail("shouldn't catch exception");
        }
    }

    @Test
    public void testWriteTreeNode() {
        testWriter.write(root);
        testWriter.close();
        TreeNode node = null;
        try {
            node = Reader.readJSON(new File(TEST_FILE));
        } catch (FileNotFoundException e) {
            fail("shouldn't catch exception");
        }
        assertEquals(node.getName(), "root");
        assertEquals(node.getTotal(), 0);
        assertEquals(node.getChildren().get(0).getName(), "childA");
        assertEquals(node.getChildren().get(0).getTotal(), 1.11);
        assertEquals(node.getChildren().get(1).getParent(), "root");
    }
}
