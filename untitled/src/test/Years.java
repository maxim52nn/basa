package test;

import java.util.ArrayList;

/**
 * Created by User on 18.10.2017.
 */
public class Years implements IntegerTree{
    private long id;
    private Integer value;
    private static Years root;
    private Years left, right;

    public Years getLeft() {
        return left;
    }

    public void setLeft(Years left) {
        this.left = left;
    }

    public Years getRight() {
        return right;
    }

    public void setRight(Years right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Years{" +
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

    public Years(long id, Integer value) {
        this.id = id;
        this.value = value;
        if (Years.root == null) {
            Years.root = this;
        }
    }


    public ArrayList<Long> search(Integer query) {
        ArrayList<Long> queryList = new ArrayList<>();
        Years root = Years.root;
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


    public static void setRoot(Years root) {
        Years.root = root;
    }

    public void add(Years node) {
        Years root = Years.root;
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

    public void add(long id, Integer value) {
        add(new Years(id, value));
    }

    public ArrayList<Long> delete(Integer value) {
        ArrayList<Long> querylist = new ArrayList<>();
        Years root = Years.root;
        Years previousNode = null;
        while (true) {
            System.out.println(root.getId());
            if (root.getValue().equals(value)) {
//                System.out.println("i am here");
                if (previousNode == null) {//если null значит root - самый главный корень дерева
                    if (root.getRight() != null) {
                        Years.root = root.getRight();
                        root.add(root.getLeft());
                    } else if (root.getLeft() != null) {
                        Years.root = root.getLeft();
                        root.add(root.getRight());
                    } else {
                        Years.root = null;
                        return querylist;
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
        return querylist;
    }

    public static Years getRoot() {
        return root;
    }

    public void delete(long id, Integer value){
        Years root = Years.root;
        Years previousNode = null;
        while (true) {
            System.out.println(root.getId());
            if (root.getValue().equals(value) && root.getId() == id) {
//                System.out.println("i am here");
                if (previousNode == null) {//если null значит root - самый главный корень дерева
                    if (root.getRight() != null) {
                        Years.root = root.getRight();
                        root.add(root.getLeft());
                    } else if (root.getLeft() != null) {
                        Years.root = root.getLeft();
                        root.add(root.getRight());
                    } else {
                        Years.root = null;
                    }
                    return;
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
