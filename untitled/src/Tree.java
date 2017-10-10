import java.util.ArrayList;

public class Tree<T> {
    private T value;
    private int id;
    private Tree left;
    private Tree right;

    public T getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    ArrayList<Integer> search(T query){
        ArrayList<Integer> queryList = new ArrayList<>();
        Tree root = this;
        boolean breakFlag = false;
        while (breakFlag){
            if (query.equals(root.getValue())){
                queryList.add(root.getId());
            }else {
                if (query.)
            }
        }
        return
    }
}
