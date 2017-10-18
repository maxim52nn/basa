package test;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class Main {
    public static void main(String[] args) {
//        int i = 0;
//        String value = "b";
//        StringTree tree = new StringTree(i++,value);
//        tree.add(1,"e");
//        tree.add(2,"a");
//        tree.add(3,"c");
//        tree.add(4,"d");
//        tree.add(5,"e");
//        tree.delete("f");
//        System.out.println(tree);
//        tree.delete("a");
//        System.out.println(tree);
//        tree.delete("e");
//        System.out.println(tree);

        bookObject b1 = new bookObject("Преступление и наказание","Федор","Достоевский",1866);
        bookObject b2 = new bookObject("Преступление и наказание","Федор","Достоевский",1866);
        bookObject b3 = new bookObject("Война и мир","Лев","Толстой",1873);
        System.out.println(b1.getId());
        System.out.println(b2.getId());
        System.out.println(b3.getId());
    }
}
