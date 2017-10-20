package test;

import java.util.ArrayList;

/**
 * Created by User on 18.10.2017.
 */
public class AuthorFirstNames implements StringTree {

    private long id;
    private String value;
    private static AuthorFirstNames root;
    private AuthorFirstNames left, right;

    public static void setRoot(AuthorFirstNames root) {
        AuthorFirstNames.root = root;
    }

    public AuthorFirstNames getLeft() {
        return left;
    }

    public void setLeft(AuthorFirstNames left) {
        this.left = left;
    }

    public AuthorFirstNames getRight() {
        return right;
    }

    public void setRight(AuthorFirstNames right) {
        this.right = right;
    }

    public static AuthorFirstNames getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "AuthorFirstNames{" +
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

    public AuthorFirstNames(long id, String value) {
        this.id = id;
        this.value = value;
        if (AuthorFirstNames.root == null) {
            AuthorFirstNames.root = this;
            System.out.println("root is " + root);
        }
    }

    public ArrayList<Long> search(String query) {
        ArrayList<Long> queryList = new ArrayList<>();
        AuthorFirstNames root = AuthorFirstNames.root;
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


    public void add(AuthorFirstNames node) {
        AuthorFirstNames root = AuthorFirstNames.root;
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
        add(new AuthorFirstNames(id, value));
    }

    public ArrayList<Long> delete(String value) {
        System.out.println(AuthorFirstNames.getRoot());
        ArrayList<Long> querylist = new ArrayList<>();
        AuthorFirstNames root = AuthorFirstNames.root;
        AuthorFirstNames previousNode = null;
        while (true) {
            System.out.println(root.getId());
            if (root.getValue().equals(value)) {
                System.out.println("i am here");
                querylist.add(root.getId());
                if (previousNode == null) {//если null значит root - самый главный корень дерева
                    if (root.getRight() != null) {
                        AuthorFirstNames.root = root.getRight();
                        AuthorFirstNames.root.add(root.getLeft());
                        root = root.getRight();
                    } else if (root.getLeft() != null) {
                        AuthorFirstNames.root = root.getLeft();
                        AuthorFirstNames.root.add(root.getRight());
                        root = root.getLeft();
                    } else {
                        AuthorFirstNames.root = null;
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


    public void delete(long id, String value) {

        AuthorFirstNames root = AuthorFirstNames.root;
        AuthorFirstNames previousNode = null;
        while (true) {
            System.out.println(root.getId());
            if (root.getValue().equals(value) && root.getId() == id) {
//                System.out.println("i am here");
                if (previousNode == null) {//если null значит root - самый главный корень дерева
                    if (root.getRight() != null) {
                        AuthorFirstNames.root = root.getRight();
                        root.add(root.getLeft());
                    } else if (root.getLeft() != null) {
                        AuthorFirstNames.root = root.getLeft();
                        root.add(root.getRight());
                    } else {
                        AuthorFirstNames.root = null;
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
