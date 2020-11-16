package main.model;

public class TreeUtil {
    /* the method recursively update total value of all nodes from department to type of input record
     * @param root: the root node of the tree
     * @param r: the fee record
     * @param path: department, category, subcategory and type of the record
     * e.g. {"Support", "Tier 2", "Cat1", "TypeB"}
     */
    public void updateTree(TreeNode root, Record r, String[] path, int start) {
        String dep = r.getDepartment();
        double surcharge = 0;
        switch (dep) {
            case "Development":
                surcharge = 1.2;
                break;
            case "Operations":
                surcharge = 0.85;
                break;
            case "Marketing":
                surcharge = 1.1;
                break;
            case "Sales":
                surcharge = 1.15;
                break;
            case "Support":
                surcharge = 0.95;
                break;
        }
        double fee = r.getUnitPrice() * r.getQuantity() * surcharge;

        if (start < path.length) {
            String subtypes = path[start];
            TreeNode n = null;
            for (TreeNode child : root.getChildren()) {
                double total = child.getTotal();
                if (child.getName().equals(subtypes)) {
                    total += fee;
                    child.setTotal(total);
                    n = child;
                    break;
                }
            }
            if (n == null) {
                n = new TreeNode(subtypes);
                n.setTotal(fee);
                root.addChild(n);
            }
            updateTree(n, r, path, start+1);
        }
    }

    // EFFECTS: recursively prints fees of each node on a given path
    public void queryTree(TreeNode root, String[] path, int start) {
        if (start < path.length) {
            String subtypes = path[start];
            TreeNode n = null;
            for (TreeNode child : root.getChildren()) {
                if (child.getName().equals(subtypes)) {
                    System.out.println(child.getName() + ": " + child.getTotal());
                    n = child;
                    break;
                }
            }
            if (n == null) {
                System.out.println("invalid category: " + subtypes);
            }
            queryTree(n, path, start + 1);
        }
    }
}
