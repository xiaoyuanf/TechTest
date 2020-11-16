package test.model;

import main.model.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeNodeTest {
    private TreeNode root;
    @BeforeEach
    public void setup() {
        root = new TreeNode("root");
    }

    @Test
    public void testTreeNode() {
        assertEquals(root.getName(), "root");
        assertEquals(root.getTotal(), 0);
        assertEquals(root.getChildren().size(), 0);
    }

    @Test
    public void testAddChild() {
        TreeNode childA = new TreeNode("childA");
        TreeNode childB = new TreeNode("childB");
        root.addChild(childA);
        root.addChild(childB);
        assertEquals(root.getChildren().size(), 2);
        assertEquals(childA.getParent(), "root");
    }

    @Test
    public void testGetChildren() {
        TreeNode childC = new TreeNode("childC");
        TreeNode grandchild = new TreeNode("grandchild");
        root.addChild(childC);
        childC.addChild(grandchild);
        assertEquals(root.getChildren().get(0).getName(), "childC");
        assertEquals(root.getChildren().get(0).getChildren().get(0).getName(), "grandchild");
        assertEquals(grandchild.getParent(), "childC");
    }

    @Test
    public void testSetTotal() {
        TreeNode childD = new TreeNode("childD");
        childD.setTotal(1.23);
        root.setTotal(3.45);
        root.addChild(childD);
        assertEquals(childD.getTotal(), 1.23);
        assertEquals(root.getTotal(), 3.45);
    }
}
