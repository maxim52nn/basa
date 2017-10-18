import java.util.ArrayList;

/**
 * Created by DaiBogh on 10.10.17.
 */
public class IntegerTree {
    private Integer value;
    private int id;
    private IntegerTree left;
    private IntegerTree right;
    //    private static int rootId;
    private static IntegerTree root;

    //    private static T rootValue;
    public static IntegerTree getRoot() {
        return IntegerTree.root;
    }

    public static void setRoot(IntegerTree root) {
        IntegerTree.root = root;
    }

    public Integer getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public IntegerTree getLeft() {
        return left;
    }

    public IntegerTree getRight() {
        return right;
    }

    public IntegerTree(Integer value, int id) {
        this.value = value;
        this.id = id;
    }

    private void setRight(IntegerTree right) {
        this.right = right;
    }

    private void setLeft(IntegerTree left) {
        this.left = left;
    }

    ArrayList<Integer> search(Integer query) {
        ArrayList<Integer> queryList = new ArrayList<>();
        IntegerTree root = this;
        boolean breakFlag = false;
        while (!breakFlag) {
            if (query.equals(root.getValue())) {
                queryList.add(root.getId());
            }
            if (query.compareTo(root.getValue()) >= 0) {
                root = root.getRight();

            } else {
                root = root.getLeft();
            }
            if (root == null) breakFlag = true;
        }

        return queryList;
    }

    public void add(Integer value, int id) {
        boolean breakFlag = false;
        IntegerTree root = IntegerTree.getRoot();
        while (!breakFlag) {
            if (value.compareTo(root.getValue()) >= 0) {
                if (root.getRight() == null) {
                    root.setRight(new IntegerTree(value, id));
                    breakFlag = true;
                } else {
                    root = root.getRight();
                }
            } else {
                if (root.getLeft() == null) {
                    root.setLeft(new IntegerTree(value, id));
                    breakFlag = true;
                } else {
                    root = root.getLeft();
                }
            }
        }
    }

    public void add(IntegerTree node) {
        boolean breakFlag = false;
        IntegerTree root = IntegerTree.getRoot();
        Integer value = node.getValue();
        while (!breakFlag) {
            if (value.compareTo(root.getValue()) >= 0) {
                if (root.getRight() == null) {
                    root.setRight(node);
                    breakFlag = true;
                } else {
                    root = root.getRight();
                }
            } else {
                if (root.getLeft() == null) {
                    root.setLeft(node);
                    breakFlag = true;
                } else {
                    root = root.getLeft();
                }
            }
        }
    }

    public void delete(IntegerTree node) {
        if (node.equals(IntegerTree.getRoot())) {
            IntegerTree.setRoot(node.getRight());
            node.getRight().add(node.getLeft());
        } else {
        }
    }
}
