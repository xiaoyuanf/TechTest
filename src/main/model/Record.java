package main.model;

// represents a record of fee
public class Record {
    private String id;
    private String name;
    private String description;
    private String department;
    private String category;
    private String subCategory;
    private String type;
    private String quantity;
    private String unitPrice;

    public Record(String[] s) {
        id = s[0];
        name = s[1];
        description = s[2];
        department = s[3];
        category = s[4];
        subCategory = s[5];
        type = s[6];
        quantity = s[7];
        unitPrice = s[8];
    }

    public String getDepartment() {
        return department;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getType() {
        return type;
    }

    public double getUnitPrice() {
        return Double.parseDouble(unitPrice);
    }

    public int getQuantity() {
        return Integer.parseInt(quantity);
    }
}
