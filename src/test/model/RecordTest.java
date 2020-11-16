package test.model;

import main.model.Record;
import main.persistence.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordTest {
    private List<String[]> csv;

    @BeforeEach
    public void setup() {
        try {
            csv = Reader.readCSV(new File("./data/test_fees.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* csv.get(0) is the header
    Development	Coding	            Cat1	TypeB	10	33.08
    Development	Coding	            Cat1	TypeC	9	28.35
    Marketing	ABM	                Cat2	TypeA	4	83.92
    Operations	HR	                Cat1	TypeA	3	39.29
    Operations	Quality Assurance	Cat2	TypeA	1	97.03
    Sales	Sales Engineering	    Cat2	TypeC	2	98.89
    Support	Tier 2	                Cat1	TypeB	9	2.82
    Support	Tier 2	                Cat1	TypeB	8	82.99
    Support	Tier 2	                Cat3	TypeB	6	11.2
     */

    @Test
    public void testRecordConstructor() {
        Record testRecord = new Record(csv.get(2));
        assertEquals(testRecord.getDepartment(), "Development");
        assertEquals(testRecord.getUnitPrice(), 28.35);
        assertEquals(testRecord.getCategory(), "Coding");
        assertEquals(testRecord.getQuantity(), 9);
        assertEquals(testRecord.getType(), "TypeC");
        assertEquals(testRecord.getSubCategory(), "Cat1");
    }
}
