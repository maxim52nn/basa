package test;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class DBTable {
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
}
