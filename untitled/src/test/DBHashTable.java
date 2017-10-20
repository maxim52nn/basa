package test;

import java.io.*;
import java.util.HashMap;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class DBHashTable implements Serializable {
    private HashMap<Long, BookObject> bookTable;

    public HashMap<Long, BookObject> getBookTable() {
        return bookTable;
    }

    public DBHashTable(HashMap<Long, BookObject> bookTable) {
        this.bookTable = bookTable;
    }
    public BookObject search(long id){
        return this.bookTable.get(id);
    }
    public BookObject search(String bookName, String AuthorFirstName, String AuthorLastName){
        return search(BookObject.createId(bookName,AuthorFirstName,AuthorLastName));
    }

    public DBHashTable() {
        this.bookTable = new HashMap<>();
    }
    public void add(BookObject obj){
        if (bookTable.get(obj.getId()) != null){
            System.out.println("такая запись уже есть!!!");
            return;
        }
        this.bookTable.put(obj.getId(),obj);
    }
    public void delete(long id){
        this.bookTable.remove(id);
    }
}
