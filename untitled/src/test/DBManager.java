package test;

import java.io.*;

/**
 * Created by DaiBogh on 18.10.17.
 */
public class DBManager {
    private DBTable table;
    private String fileName;

    public DBManager(String fileName) {
        this.fileName = fileName;
    }
    public boolean load(){
        return load(this.fileName);
    }
    public boolean load(String fileName){
        try(FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            table = (DBTable) ois.readObject();
            return true;
        }catch (FileNotFoundException e){
            return false;
        }catch (IOException e){
            return false;
        }catch (ClassNotFoundException e){
            return false;
        }
    }
    public boolean save(String fileName){
        try (FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(table);
            return true;
        }catch (FileNotFoundException e){
            System.out.println("file not found!");
            return false;
        }catch (IOException e){
            System.out.println("something wrong with stream");
            return false;
        }
    }
    public boolean save(){
        return save(this.fileName);
    }

    public boolean createBackup(String fileName){
        boolean result = save(fileName);
        if (result){
            System.out.println("backup is done");
        }else {
            System.out.println("backup is not done");
        }
        return result;
    }
    public boolean loadBackup(String fileName){
        boolean isLoaded = load(fileName);
        return save();
    }
}
