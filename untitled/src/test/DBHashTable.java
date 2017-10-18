package test;

import java.io.*;
import java.util.HashMap;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class DBHashTable {
    private HashMap<Long,bookObject> bookTable;
    private String DBName;


    public DBHashTable(HashMap<Long, bookObject> bookTable) {
        this.bookTable = bookTable;
    }
    public bookObject search(long id){
        return this.bookTable.get(id);
    }
    public bookObject search(String bookName, String AuthorFirstName, String AuthorLastName){
        return search(bookObject.createId(bookName,AuthorFirstName,AuthorLastName));
    }

    public DBHashTable() {
        this.bookTable = new HashMap<>();
    }
    public void add(bookObject obj){
        this.bookTable.put(obj.getId(),obj);
    }
}
