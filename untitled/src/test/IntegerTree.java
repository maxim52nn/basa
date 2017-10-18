package test;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DaiBogh on 10.10.17.
 */
public class IntegerTree implements Serializable{
    private long id;
    private Integer value;
    private static IntegerTree root;
    private IntegerTree left, right;

    public IntegerTree getLeft() {
        return left;
    }

    public void setLeft(IntegerTree left) {
        this.left = left;
    }

    public IntegerTree getRight() {
        return right;
    }

    public void setRight(IntegerTree right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "IntegerTree{" +
                "id=" + id +
                ", value=" + value +
                ", left=\n\t" + left +
                ", right=\n\t" + right +
                '}';
    }

    public long getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public IntegerTree(int id, Integer value) {
        this.id = id;
        this.value = value;
        if (IntegerTree.root == null) {
            IntegerTree.root = this;
        }
    }

    public ArrayList<Long> search(Integer query) {
        ArrayList<Long> queryList = new ArrayList<>();
        IntegerTree root = IntegerTree.root;
        while (true) {
            if (root.getValue().equals(query)) {
                queryList.add(root.getId());
            }
            if (query.compareTo(root.getValue()) >= 0) {
                if (root.getRight() == null) {
                    break;
                }
                root = root.getRight();
            } else {
                if (root.getLeft() == null) {
                    break;
                }
                root = root.getLeft();
            }
        }
        return queryList;
    }


    public void add(IntegerTree node) {
        IntegerTree root = IntegerTree.root;
        if (node == null)
            return;
        while (true) {

            if (node.getValue().compareTo(root.getValue()) >= 0) {
                if (root.getRight() == null) {
                    root.setRight(node);
                    break;
                } else {
                    root = root.getRight();
                }
            } else {
                if (root.getLeft() == null) {
                    root.setLeft(node);
                    break;
                } else {
                    root = root.getLeft();
                }
            }
        }
    }

    public void add(int id, Integer value) {
        add(new IntegerTree(id, value));
    }

    public void delete(Integer value) {
        IntegerTree root = IntegerTree.root;
        IntegerTree previousNode = null;
        while (true) {
            System.out.println(root.getId());
            if (root.getValue().equals(value)) {
//                System.out.println("i am here");
                if (previousNode == null) {//если null значит root - самый главный корень дерева
                    if (root.getRight() != null) {
                        IntegerTree.root = root.getRight();
                        root.add(root.getLeft());
                    } else if (root.getLeft() != null) {
                        IntegerTree.root = root.getLeft();
                        root.add(root.getRight());
                    } else {
                        IntegerTree.root = null;
                        return;
                    }
                } else {
                    System.out.println("i am here");
                    if (previousNode.getRight().getValue().equals(root.getValue()))
                        previousNode.setRight(null);
                    else previousNode.setLeft(null);
                    previousNode.add(root.getRight());
                    previousNode.add(root.getLeft());
                    root = previousNode;
                }

            } else if (value.compareTo(root.getValue()) > 0) {
                previousNode = root;
                root = root.getRight();
                if (root == null) {
                    break;
                }
            } else {
                previousNode = root;
                root = root.getLeft();
                if (root == null) {
                    break;
                }
            }

        }
    }

}
