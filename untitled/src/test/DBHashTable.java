package test;

import java.io.*;
import java.util.HashMap;

/**
 * Created by DaiBogh on 11.10.17.
 */
public class DBHashTable {
    private HashMap<Long,bookObject> bookTable;
    private String DBName;

    public void save(){
        try (FileOutputStream fos = new FileOutputStream("./DB/"+DBName+"/hashtable.db");
             ObjectOutputStream oos = new ObjectOutputStream(fos);){

            oos.writeObject(bookTable);
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        }catch (IOException e){
            System.out.println("Something wrong with IO!");
        }
    }
    public void load(){
        try(FileInputStream fis = new FileInputStream("./DB/"+DBName+"/hashtable.db");
            ObjectInputStream oin = new ObjectInputStream(fis)){

            bookTable = (HashMap<Long, bookObject>) oin.readObject();
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        }catch (IOException e){
            System.out.println("Something wrong with IO!");
        }catch (ClassNotFoundException e){
            System.out.println("Your class wasn't found!");
        }
    }

    public DBHashTable(String DBName) {
        this.DBName = DBName;
    }
}
