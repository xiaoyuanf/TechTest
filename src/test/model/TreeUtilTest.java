package test.model;

import main.model.Record;
import main.model.TreeNode;
import main.model.TreeUtil;
import main.persistence.Reader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeUtilTest {
    private TreeNode root;
    private static final String TEST_CSV_FILE = "./data/test_fees.csv";
    private List<String[]> records;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /*
    Development	Coding	            Cat1(703.14)	TypeB(396.96)	10	33.08
    Development	Coding	            Cat1	        TypeC	9	28.35
    Marketing	ABM	                Cat2	TypeA	4	83.92
    Operations	HR	                Cat1	TypeA	3	39.29
    Operations	Quality Assurance	Cat2	TypeA	1	97.03
    Sales	    Sales Engineering	Cat2	TypeC(227.447)	2	98.89
    Support	    Tier 2(718.675)	    Cat1	TypeB(654.835)	9	2.82
    Support	    Tier 2	            Cat1	TypeB	        8	82.99
    Support	    Tier 2	            Cat3	TypeB(63.84)	6	11.2
     */
    @BeforeEach
    public void runBefore() {
        System.setOut(new PrintStream(outputStreamCaptor));
        root = new TreeNode("root");
        try {
            records = Reader.readCSV(new File(TEST_CSV_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateTree() {
        Record testRecord1 = new Record(records.get(1));
        Record testRecord2 = new Record(records.get(2));
        Record testRecord6 = new Record(records.get(6));
        TreeUtil.updateTree(root, testRecord1, new String[]{testRecord1.getDepartment(),
                testRecord1.getCategory(), testRecord1.getSubCategory(), testRecord1.getType()}, 0);
        assertEquals(root.getChildren().get(0).getTotal(), 396.96, 0.0001);
        TreeUtil.updateTree(root, testRecord2, new String[]{testRecord2.getDepartment(),
                testRecord2.getCategory(), testRecord2.getSubCategory(), testRecord2.getType()}, 0);
        assertEquals(root.getChildren().get(0).getTotal(), 703.14, 0.0001);
        TreeUtil.updateTree(root, testRecord6, new String[]{testRecord6.getDepartment(),
                testRecord6.getCategory(), testRecord6.getSubCategory(), testRecord6.getType()}, 0);
        assertEquals(root.getChildren().get(1).getTotal(), 227.447, 0.0001);
    }

    @Test
    public void testQueryTree() {
        for (int i = 1; i < records.size(); i++) {
            Record r = new Record(records.get(i));
            TreeUtil.updateTree(root, r, new String[]{r.getDepartment(),
                    r.getCategory(), r.getSubCategory(), r.getType()}, 0);
        }
        TreeUtil.queryTree(root, new String[]{"Support", "Tier 2"}, 0);
        assertEquals("Support: 718.675\n" + "Tier 2: 718.675",
                outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
