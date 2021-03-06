package test;

import java.io.Serializable;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class BookObject implements Serializable{
    private String bookName;
    private String AuthorFirstName;
    private String AuthorLastName;
    private Integer year;
    private long id;

    public String getBookName() {
        return bookName;
    }

    public String getAuthorFirstName() {
        return AuthorFirstName;
    }

    public String getAuthorLastName() {
        return AuthorLastName;
    }

    public Integer getYear() {
        return year;
    }

    public BookObject(String bookName, String authorFirstName, String authorLastName, Integer year) {
        this.bookName = bookName;
        AuthorFirstName = authorFirstName;
        AuthorLastName = authorLastName;
        this.year = year;
        this.id = createId();
    }
    public long createId(){
        return longHash( bookName + AuthorFirstName + AuthorLastName);
    }
    public static long createId(String bookName, String AuthorFirstName, String AuthorLastName){
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

    @Override
    public String toString() {
        return "BookObject{" +
                "bookName='" + bookName + '\'' +
                ", AuthorFirstName='" + AuthorFirstName + '\'' +
                ", AuthorLastName='" + AuthorLastName + '\'' +
                ", year=" + year +
                ", id=" + id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        AuthorFirstName = authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) {
        AuthorLastName = authorLastName;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
