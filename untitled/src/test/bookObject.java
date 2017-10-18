package test;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class bookObject {
    private String bookName;
    private String AuthorFirstName;
    private String AuthorLastName;
    private Integer year;
    private long id;

    public bookObject(String bookName, String authorFirstName, String authorLastName, Integer year) {
        this.bookName = bookName;
        AuthorFirstName = authorFirstName;
        AuthorLastName = authorLastName;
        this.year = year;
        this.id = createId();
    }
    public long createId(){
        return longHash( bookName + AuthorFirstName + AuthorLastName);
    }
    public static long longHash(String string) {
        long h = 98764321261L;
        int l = string.length();
        char[] chars = string.toCharArray();

        for (int i = 0; i < l; i++) {
            h = 31*h + chars[i];
        }
        return h;
    }

    public long getId() {
        return id;
    }
}
