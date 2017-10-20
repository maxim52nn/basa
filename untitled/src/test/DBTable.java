package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class DBTable implements Serializable{
    private Years yearTree;
    private BookNames bookNames;
    private AuthorFirstNames authorFirstNames;
    private AuthorLastNames authorLastNames;
    private DBHashTable dbHashTable;

    public DBTable(Years integerTree,
                   BookNames bookNames,
                   AuthorFirstNames authorFirstNames,
                   AuthorLastNames authorLastNames,
                   DBHashTable dbHashTable) {
        this.yearTree = integerTree;
        this.bookNames = bookNames;
        this.authorFirstNames = authorFirstNames;
        this.authorLastNames = authorLastNames;
        this.dbHashTable = dbHashTable;
    }

    public DBTable() {
        this.dbHashTable = new DBHashTable();
    }

    public AuthorFirstNames getAuthorFirstNames() {
        return authorFirstNames;
    }

    public Collection<BookObject> getAll(){
        return dbHashTable.getBookTable().values();
    }

    public Years getYearTree() {
        return yearTree;
    }

    public BookNames getBookNames() {
        return bookNames;
    }

    public AuthorLastNames getAuthorLastNames() {
        return authorLastNames;
    }

    public void add(BookObject obj){
        if (dbHashTable.search(obj.getId()) != null){
            System.out.println("such element exists");
            return;
        }
        dbHashTable.add(obj);


        if (yearTree != null){
            System.out.println("year tree is not null");
            yearTree.add(obj.getId(),obj.getYear());
        }else {
            System.out.println("year tree is null");
            yearTree = new Years(obj.getId(),obj.getYear());
        }


        if (bookNames != null){
            bookNames.add(obj.getId(),obj.getBookName());
        }else {
            bookNames = new BookNames(obj.getId(),obj.getBookName());
        }

        if (authorFirstNames != null){
            authorFirstNames.add(obj.getId(),obj.getAuthorFirstName());
        }else {
            System.out.println("first create");
            authorFirstNames = new AuthorFirstNames(obj.getId(),obj.getAuthorFirstName());
        }


        if (authorLastNames != null){
            authorLastNames.add(obj.getId(),obj.getAuthorLastName());
        }else {
            authorLastNames = new AuthorLastNames(obj.getId(),obj.getAuthorLastName());
        }
    }

    public BookObject search(long id){
        return dbHashTable.search(id);
    }
    public BookObject search(String bookName, String authorFirstName, String authorLastName){
        return dbHashTable.search(bookName,authorFirstName,authorLastName);
    }
    public ArrayList<BookObject> stringSearch(StringTree tree, String name){
        System.out.println(tree);
        ArrayList<Long> hashes = tree.search(name);
        System.out.println(hashes);
        ArrayList<BookObject> result = new ArrayList<>();
        for (Long id : hashes){
            result.add(dbHashTable.search(id));
        }
        return result;
    }
    public ArrayList<BookObject> intSearch(IntegerTree tree, Integer year){
        ArrayList<Long> hashes = tree.search(year);
        ArrayList<BookObject> result = new ArrayList<>();
        for (Long id : hashes){
            result.add(dbHashTable.search(id));
        }
        return result;
    }
    public ArrayList<BookObject> searchBookNames(String bookName){

        return stringSearch(this.bookNames, bookName);
    }
    public ArrayList<BookObject> searchAuthorFirstNames(String firstName){
        return stringSearch(this.authorFirstNames, firstName);
    }
    public ArrayList<BookObject> searchAuthorLastNames(String lastName){
        return stringSearch(this.authorLastNames, lastName);
    }
    public ArrayList<BookObject> searchYears(int year){
        return intSearch(this.yearTree, year);
    }

    @Override
    public String toString() {
        return "DBTable{" +
                "yearTree=" + yearTree +
                ", bookNames=" + bookNames +
                ", authorFirstNames=" + authorFirstNames +
                ", authorLastNames=" + authorLastNames +
                ", dbHashTable=" + dbHashTable +
                '}';
    }

    public void delete(long id){
        BookObject obj = this.dbHashTable.search(id);
        this.bookNames.delete(id,obj.getBookName());
        this.authorFirstNames.delete(id,obj.getAuthorFirstName());
        this.authorLastNames.delete(id,obj.getAuthorLastName());
        this.yearTree.delete(id,obj.getYear());
        this.dbHashTable.delete(id);
    }
    public void delete(String bookName, String authorFirstName, String authorLastName){
        delete(BookObject.createId(bookName,authorFirstName,authorLastName));
    }

    public void deletebyAuthorFirstName(String value){
        ArrayList<Long> hashes = this.authorFirstNames.delete(value);
        for (Long id : hashes){
            delete(id);
        }
    }
    public void deletebyAuthorLastName(String value){
        ArrayList<Long> hashes = this.authorLastNames.delete(value);
        for (Long id: hashes){
            delete(id);
        }
    }
    public void deletebyBookName(String value){
        ArrayList<Long> hashes = this.bookNames.delete(value);
        for (Long id: hashes){
            delete(id);
        }
    }
    public void deletebyYear(int value){
        ArrayList<Long> hashes = this.yearTree.delete(value);
        for (Long id: hashes){
            delete(id);
        }
    }
    public void edit(long id, BookObject obj){
        BookObject oldObj = search(id);
        boolean bookNameEdited,
                authorFirstNameEdited,
                authorLastNameEdited,
                yearEdited;
        bookNameEdited = obj.getBookName().equals(oldObj.getBookName());
        authorFirstNameEdited = obj.getAuthorFirstName().equals(oldObj.getAuthorFirstName());
        authorLastNameEdited = obj.getAuthorLastName().equals(oldObj.getAuthorLastName());
        yearEdited = obj.getYear().equals(oldObj.getYear());
        System.out.println(bookNameEdited);
        System.out.println(authorFirstNameEdited);
        System.out.println(authorLastNameEdited);
        System.out.println(yearEdited);
        if (authorFirstNameEdited || authorLastNameEdited || bookNameEdited){
            delete(id);
            add(obj);
        }else if (yearEdited){

            yearTree.delete(id,oldObj.getYear());
            yearTree.add(obj.getId(),obj.getYear());
        }else {
            System.out.println("there is no changes!");
        }
    }

}

