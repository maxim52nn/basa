package test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by User on 19.10.2017.
 */
public class Controller {
    @FXML
    private Button editElemButton;
    @FXML
    private Button deleteElemButton;
    @FXML
    private Button addNewElemButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button loadDbButton;
    @FXML
    private Button saveDbButton;
    @FXML
    private Button loadBackupButton;
    @FXML
    private Button saveBackupButton;
    @FXML
    private Button cancelSearchButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button deleteDbButton;
    @FXML
    private TableColumn<BookObject,Long> idColumn;
    @FXML
    private TableColumn<BookObject,Integer> yearColumn;
    @FXML
    private TableColumn<BookObject,String> bookNameColumn;
    @FXML
    private TableColumn<BookObject,String> authorFirstNameColumn;
    @FXML
    private TableColumn<BookObject,String> authorLastNameColumn;
    @FXML
    private TableView<BookObject> graphicalTable;
    @FXML
    private TextField pathToDB;
    @FXML
    private TextField pathToBackup;
    private ObservableList<BookObject> data = FXCollections.observableArrayList();
    @FXML
    private RadioButton idRadio;
    @FXML
    private RadioButton authorFirstNameRadio;
    @FXML
    private RadioButton authorLastNameRadio;
    @FXML
    private RadioButton bookNameRadio;
    @FXML
    private RadioButton yearRadio;
    @FXML
    private TextField authorFirstNameTextField;
    @FXML
    private TextField authorLastNameTextField;
    @FXML
    private TextField bookNameTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private RadioButton newElemRadio;
    @FXML
    private RadioButton editRadio;
    @FXML
    private TextField idTextField;
    @FXML
    private Button editPath;
    private DBManager manager;
    private DBTable table;
    private ArrayList<BookObject> queryList = new ArrayList<>();


    // Group
    ToggleGroup group = new ToggleGroup();

    public void showTable(){
        showTable(false);
    }
    public void showTable(boolean all){
        //show table with graphicaltable
        if (all){
            queryList = new ArrayList<>();
            queryList.addAll(table.getAll());
        }
        data.clear();
        data.addAll(queryList);
        graphicalTable.setItems(data);
    }
    public long parseId(){
        try{
            return Long.parseLong(idTextField.getText());
        }catch (Exception e){
            idTextField.setText(e.getMessage());
            return 0;
        }
    }
    public void initialize(){


        idColumn.setCellValueFactory(new PropertyValueFactory<BookObject, Long>("id"));
        authorFirstNameColumn.setCellValueFactory(new PropertyValueFactory<BookObject, String>("authorFirstName"));
        authorLastNameColumn.setCellValueFactory(new PropertyValueFactory<BookObject, String>("authorLastName"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<BookObject, String>("bookName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<BookObject, Integer>("year"));



        idRadio.setToggleGroup(group);
        authorFirstNameRadio.setToggleGroup(group);
        authorLastNameRadio.setToggleGroup(group);
        bookNameRadio.setToggleGroup(group);
        yearRadio.setToggleGroup(group);
        newElemRadio.setToggleGroup(group);
        editRadio.setToggleGroup(group);


        manager = new DBManager();
        table = manager.getTable();
        loadDbButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String stringPath = pathToDB.getText();
                pathToDB.setEditable(false);
                manager.load(stringPath);
                table = manager.getTable();
                showTable(true);
            }
        });
        saveDbButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String stringPath = pathToDB.getText();
                pathToDB.setEditable(false);
                manager.save(stringPath);
            }
        });
        saveBackupButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String backupPath = pathToBackup.getText();
                manager.createBackup(backupPath);
            }
        });
        loadBackupButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String backupPath = pathToBackup.getText();
                manager.loadBackup(backupPath);
                table = manager.getTable();
                showTable(true);
            }
        });

        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showTable(true);
            }
        });
        cancelSearchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showTable(true);
            }
        });

        idRadio.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                yearTextField.setEditable(false);
                yearTextField.clear();
                authorFirstNameTextField.setEditable(false);
                authorLastNameTextField.setEditable(false);
                bookNameTextField.setEditable(false);
                idTextField.setEditable(true);
            }
        });
        authorFirstNameRadio.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                yearTextField.setEditable(false);
                yearTextField.clear();
                authorLastNameTextField.setEditable(false);
                authorLastNameTextField.clear();
                bookNameTextField.setEditable(false);
                bookNameTextField.clear();
                authorFirstNameTextField.setEditable(true);
                idTextField.setEditable(false);
            }
        });
        authorLastNameRadio.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                yearTextField.setEditable(false);
                yearTextField.clear();
                authorFirstNameTextField.setEditable(false);
                authorFirstNameTextField.clear();
                bookNameTextField.setEditable(false);
                bookNameTextField.clear();
                authorLastNameTextField.setEditable(true);
                idTextField.setEditable(false);
            }
        });
        bookNameRadio.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                yearTextField.setEditable(false);
                yearTextField.clear();
                authorLastNameTextField.setEditable(false);
                authorLastNameTextField.clear();
                authorFirstNameTextField.setEditable(false);
                authorFirstNameTextField.clear();
                bookNameTextField.setEditable(true);
                idTextField.setEditable(false);
            }
        });
        yearRadio.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                authorFirstNameTextField.setEditable(false);
                authorFirstNameTextField.clear();
                authorLastNameTextField.setEditable(false);
                authorLastNameTextField.clear();
                bookNameTextField.setEditable(false);
                bookNameTextField.clear();
                yearTextField.setEditable(true);
            }
        });
        newElemRadio.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                authorLastNameTextField.setEditable(true);
                authorFirstNameTextField.setEditable(true);
                bookNameTextField.setEditable(true);
                yearTextField.setEditable(true);
            }
        });
        editRadio.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idTextField.setEditable(true);
                authorFirstNameTextField.setEditable(true);
                authorLastNameTextField.setEditable(true);
                bookNameTextField.setEditable(true);
                yearTextField.setEditable(true);
            }
        });

        deleteElemButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (idRadio.isSelected()) {
                    long id = parseId();
                    table.delete(id);
                }else if (authorFirstNameRadio.isSelected()){
                    table.deletebyAuthorFirstName(authorFirstNameTextField.getText());

                }else if (authorLastNameRadio.isSelected()){
                    table.deletebyAuthorLastName(authorLastNameTextField.getText());
                }else if (bookNameRadio.isSelected()){
                    table.deletebyBookName(bookNameTextField.getText());
                }else if (yearRadio.isSelected()){
                    table.deletebyYear(Integer.parseInt(yearTextField.getText()));
                }
                System.out.println(table);
                showTable(true);

            }
        });
        addNewElemButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BookObject newObj = new BookObject(
                        bookNameTextField.getText(),
                        authorFirstNameTextField.getText(),
                        authorLastNameTextField.getText(),
                        Integer.parseInt(yearTextField.getText())
                );
                table.add(newObj);
                showTable(true);
            }
        });
        editElemButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                long id = parseId();
                BookObject newObj = new BookObject(
                        bookNameTextField.getText(),
                        authorFirstNameTextField.getText(),
                        authorLastNameTextField.getText(),
                        Integer.parseInt(yearTextField.getText())
                );
                BookObject oldObj = table.search(id);
                if (oldObj != null){
                    table.edit(oldObj.getId(),newObj);
                }
                showTable(true);
            }
        });
        searchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (idRadio.isSelected()){
                    queryList = new ArrayList<>();
                    queryList.add(table.search(parseId()));
                }else if (authorFirstNameRadio.isSelected()){
                    queryList = table.searchAuthorFirstNames(authorFirstNameTextField.getText());
                }else if (authorLastNameRadio.isSelected()){
                    queryList = table.searchAuthorLastNames(authorLastNameTextField.getText());
                }else if (bookNameRadio.isSelected()){
                    queryList = table.searchBookNames(bookNameTextField.getText());
                }else if (yearRadio.isSelected()){
                    queryList = table.searchYears(Integer.parseInt(yearTextField.getText()));
                }
                showTable();
            }
        });

        deleteDbButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                manager.deleteDB();
                queryList = new ArrayList<>();
                showTable();
            }
        });

        editPath.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pathToDB.setEditable(true);
            }
        });

    }
}