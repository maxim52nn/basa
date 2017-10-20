package test;

import java.util.ArrayList;

/**
 * Created by User on 18.10.2017.
 */
public class BookNames implements StringTree{
    private long id;
    private String value;
    private static BookNames root;
    private BookNames left, right;

    public BookNames getLeft() {
        return left;
    }

    public void setLeft(BookNames left) {
        this.left = left;
    }

    public BookNames getRight() {
        return right;
    }

    public void setRight(BookNames right) {
        this.right = right;
    }

    public static BookNames getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "BookNames{" +
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

    public BookNames(long id, String value) {
        this.id = id;
        this.value = value;
        if (BookNames.root == null) {
            BookNames.root = this;
            System.out.println("root is " + root);
        }
    }

    public ArrayList<Long> search(String query) {
        ArrayList<Long> queryList = new ArrayList<>();
        BookNames root = BookNames.root;
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


    public void add(BookNames node) {
        BookNames root = BookNames.root;
        if (node == null)
            return;
        while (true) {

            if (node.getValue().compareTo(root.getValue()) >= 0) {
                if (root.getRight() == null) {
                    System.out.println("right");
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
        add(new BookNames(id, value));
    }

    public ArrayList<Long> delete(String value) {
        ArrayList<Long> querylist = new ArrayList<>();
        BookNames root = BookNames.root;
        BookNames previousNode = null;
        while (true) {
            //System.out.println(root.getId());
            if (root.getValue().equals(value)) {
//                System.out.println("i am here");
                querylist.add(root.getId());
                if (previousNode == null) {//если null значит root - самый главный корень дерева
//                    System.out.println("it was a root!!!");
                    if (root.getRight() != null) {
                        BookNames.root = root.getRight();
                        root.add(root.getLeft());
                    } else if (root.getLeft() != null) {
                        BookNames.root = root.getLeft();
                        root.add(root.getRight());
                    } else {
                        BookNames.root = null;
                        return querylist;
                    }
                 root = root.getRight();
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


    public static void setRoot(BookNames root) {
        BookNames.root = root;
    }

    public void delete(long id, String value) {
        System.out.println("delete bookname "+id+" "+value);
        BookNames root = BookNames.root;
        BookNames previousNode = null;
        while (true) {
            System.out.println(root.getId());
            if (root.getValue().equals(value) && root.getId() == id) {
//                System.out.println("i am here");
                if (previousNode == null) {//если null значит root - самый главный корень дерева
                    System.out.println("удаляю корень букнейма");
                    if (root.getRight() != null) {
                        BookNames.root = root.getRight();
                        root.add(root.getLeft());
                    } else if (root.getLeft() != null) {
                        BookNames.root = root.getLeft();
                        root.add(root.getRight());
                    } else {
                        BookNames.root = null;
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
