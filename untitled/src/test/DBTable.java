package test;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class DBTable implements Serializable{
    private IntegerTree yearTree;
    private StringTree bookNames;
    private StringTree authorFirstNames;
    private StringTree authorLastNames;
    private DBHashTable dbHashTable;

    public DBTable(IntegerTree integerTree,
                   StringTree bookNames,
                   StringTree authorFirstNames,
                   StringTree authorLastNames,
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
    public void add(bookObject obj){

        dbHashTable.add(obj);


        if (yearTree != null){
            yearTree.add(obj.getId(),obj.getYear());
        }else {
            yearTree = new IntegerTree(obj.getId(),obj.getYear());
        }


        if (bookNames != null){
            bookNames.add(obj.getId(),obj.getBookName());
        }else {
            bookNames = new StringTree(obj.getId(),obj.getBookName());
        }

        if (authorFirstNames != null){
            authorFirstNames.add(obj.getId(),obj.getBookName());
        }else {
            authorFirstNames = new StringTree(obj.getId(),obj.getBookName());
        }


        if (authorLastNames != null){
            authorLastNames.add(obj.getId(),obj.getBookName());
        }else {
            authorLastNames = new StringTree(obj.getId(),obj.getBookName());
        }
    }

    public bookObject search(long id){
        return dbHashTable.search(id);
    }
    public bookObject search(String bookName, String authorFirstName, String authorLastName){
        return dbHashTable.search(bookName,authorFirstName,authorLastName);
    }
    public ArrayList<bookObject> stringSearch(StringTree tree,String name){
        ArrayList<Long> hashes = tree.search(name);
        ArrayList<bookObject> result = new ArrayList<>();
        for (Long id : hashes){
            result.add(dbHashTable.search(id));
        }
        return result;
    }
    public ArrayList<bookObject> intSearch(IntegerTree tree,Integer year){
        ArrayList<Long> hashes = tree.search(year);
        ArrayList<bookObject> result = new ArrayList<>();
        for (Long id : hashes){
            result.add(dbHashTable.search(id));
        }
        return result;
    }
    public ArrayList<bookObject> searchBookNames(String bookName){
        return stringSearch(this.bookNames, bookName);
    }
    public ArrayList<bookObject> searchAuthorFirstNames(String firstName){
        return stringSearch(this.authorFirstNames, firstName);
    }public ArrayList<bookObject> searchAuthorLastNames(String lastName){
        return stringSearch(this.authorLastNames, lastName);
    }
    public ArrayList<bookObject> searchYears(int year){
        return intSearch(this.yearTree, year);
    }

    public void delete(long id){
        bookObject obj = this.dbHashTable.search(id);
        this.dbHashTable.delete(id);
        this.bookNames.delete(id,obj.getBookName());
        this.authorFirstNames.delete(id,obj.getAuthorFirstName());
        this.authorLastNames.delete(id,obj.getAuthorLastName());
        this.yearTree.delete(id,obj.getYear());
    }
    public void delete(String bookName, String authorFirstName, String authorLastName){
        delete(bookObject.createId(bookName,authorFirstName,authorLastName));
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
    public void edit(bookObject obj){
        bookObject oldObj = search(obj.getId());
        boolean bookNameEdited,
                authorFirstNameEdited,
                authorLastNameEdited,
                yearEdited;
        bookNameEdited = obj.getBookName().equals(oldObj.getBookName());
        authorFirstNameEdited = obj.getAuthorFirstName().equals(oldObj.getAuthorFirstName());
        authorLastNameEdited = obj.getAuthorLastName().equals(oldObj.getAuthorLastName());
        yearEdited = obj.getYear().equals(oldObj.getYear());

        if (authorFirstNameEdited || authorLastNameEdited || bookNameEdited){
            delete(obj.getId());
            add(obj);
        }else if (yearEdited){

            yearTree.delete(oldObj.getId(),oldObj.getYear());
            yearTree.add(obj.getId(),obj.getYear());
        }else {
            System.out.println("there is no changes!");
        }
    }

}

