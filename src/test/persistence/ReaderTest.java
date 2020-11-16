package test.persistence;

import main.model.TreeNode;
import main.persistence.Reader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    private static final String TEST_JSON_FILE = "./data/testJSON.txt";
    private static final String TEST_CSV_FILE = "./data/test_fees.csv";
    private List<String[]> rowList;
    private TreeNode root;

    /*
    Development	Coding	Cat1	TypeB	10	33.08
    Development	Coding	Cat1	TypeC	9	28.35
    Marketing	ABM	Cat2	TypeA	4	83.92
    Operations	HR	Cat1	TypeA	3	39.29
    Operations	Quality Assurance	Cat2	TypeA	1	97.03
    Sales	Sales Engineering	Cat2	TypeC	2	98.89
    Support	Tier 2	Cat1	TypeB	9	2.82
    Support	Tier 2	Cat1	TypeB	8	82.99
    Support	Tier 2	Cat3	TypeB	6	11.2
     */
    @Test
    public void testReadCSV() {
        try {
            rowList = Reader.readCSV(new File(TEST_CSV_FILE));
            assertEquals(rowList.get(0)[3], "Department__c");
            assertEquals(rowList.get(7)[0], "a00P0000006KXnPIAW");
            assertEquals(rowList.get(5)[4], "Quality Assurance");
            assertEquals(rowList.size(), 10);
        } catch (IOException e) {
            fail("Shouldn't catch IOException");
        }
        try {
            List<String[]> rowList = Reader.readCSV(new File("./data/not_exist.csv"));
            fail("Failed to catch IOException");
        } catch (IOException e) {
            // as expected
        }
    }

    @Test
    public void testReadJSON() {
        try {
            root = Reader.readJSON(new File(TEST_JSON_FILE));
        } catch (FileNotFoundException e) {
            fail("shouldn't catch exception");
        }

        try {
            root = Reader.readJSON(new File("./data/not_exist.txt"));
            fail("Failed to catch IOException");
        } catch (FileNotFoundException e) {
            //as expected
        }
        assertEquals(root.getTotal(), 0);
        assertEquals(root.getChildren().size(), 2);
        assertEquals(root.getChildren().get(0).getName(), "childA");
    }
}
