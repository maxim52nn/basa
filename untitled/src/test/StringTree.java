package test;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DaiBogh on 10.10.17.
 */
public class StringTree implements Serializable {
    private long id;
    private String value;
    private static StringTree root;
    private StringTree left, right;

    public StringTree getLeft() {
        return left;
    }

    public void setLeft(StringTree left) {
        this.left = left;
    }

    public StringTree getRight() {
        return right;
    }

    public void setRight(StringTree right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "StringTree{" +
                "id=" + id +
                ", value=" + value +
                ", left=\n\t" + left +
                ", right=\n\t" + right +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public StringTree(long id, String value) {
        this.id = id;
        this.value = value;
        if (StringTree.root == null) {
            StringTree.root = this;
        }
    }

    public ArrayList<Long> search(String query) {
        ArrayList<Long> queryList = new ArrayList<>();
        StringTree root = StringTree.root;
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


    public void add(StringTree node) {
        StringTree root = StringTree.root;
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



    public void add(long id, String value) {
        add(new StringTree(id, value));
    }

    public void delete(String value) {
        StringTree root = StringTree.root;
        StringTree previousNode = null;
        while (true) {
            System.out.println(root.getId());
            if (root.getValue().equals(value)) {
//                System.out.println("i am here");
                if (previousNode == null) {//если null значит root - самый главный корень дерева
                    if (root.getRight() != null) {
                        StringTree.root = root.getRight();
                        root.add(root.getLeft());
                    } else if (root.getLeft() != null) {
                        StringTree.root = root.getLeft();
                        root.add(root.getRight());
                    } else {
                        StringTree.root = null;
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
