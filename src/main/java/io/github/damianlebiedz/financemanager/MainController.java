package io.github.damianlebiedz.financemanager;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    
    @FXML
    private TextField total;
    @FXML
    private TextField search;
    @FXML
    private TextField errorField;

    @FXML
    private TextField nameField;
    @FXML
    private MenuButton categoryField;
    @FXML
    private TextField priceField;
    @FXML
    private DatePicker dateField;

    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> nameColumn;
    @FXML
    private TableColumn<Data, String> categoryColumn;
    @FXML
    private TableColumn<Data, Float> priceColumn;
    @FXML
    private TableColumn<Data, Date> dateColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData();
        dateField.setValue(LocalDate.now());
    }
    @FXML
    private void addBtn() {
        try {
            if(categoryField.getText().equals("Select a category")) {
                throw new NumberFormatException();
            }
            String insert =
                    "INSERT INTO DATA VALUES (default,'" + nameField.getText() + "','" + categoryField.getText() +
                            "'," + Float.parseFloat(priceField.getText()) + ",'" + dateField.getValue().toString() +
                            "')";
            executeUpdate(insert);
            showData();
            errorField.clear();
        }
        catch(NumberFormatException e) {
            errorField.setText("INCORRECT DATA");
        }
    }
    @FXML
    private void deleteBtn() {
        Data data = table.getSelectionModel().getSelectedItem();
        int id = data.getId();
        String delete =
                "DELETE FROM DATA WHERE ID="+id+";";
        executeUpdate(delete);
        showData();
    }
    @FXML
    private void updateBtn() {
        try {
            if(categoryField.getText().equals("Select a category") || priceField.getText().startsWith("0")) {
                throw new NumberFormatException();
            }
            Data data = table.getSelectionModel().getSelectedItem();
            int id = data.getId();
            String update =
                    "UPDATE  DATA SET NAME ='" + nameField.getText() + "', CATEGORY = '" + categoryField.getText() + "'," + " PRICE = " +
                            Float.parseFloat(priceField.getText()) + ", DATE = '" + dateField.getValue().toString() + "' " +
                            "WHERE id = " + id;
            executeUpdate(update);
            showData();
            errorField.clear();
        }
        catch(NumberFormatException e) {
            errorField.setText("INCORRECT DATA");
        }
    }
    @FXML
    private void onMouseClickedTable() {
        try {
            Data data = table.getSelectionModel().getSelectedItem();
            nameField.setText(data.getName());
            categoryField.setText(data.getCategory());
            priceField.setText(String.valueOf(data.getPrice()));
            priceField.setText(data.getPrice().toString());
            dateField.setValue(data.getDate().toLocalDate());
        }
        catch (NullPointerException ignored) {
        }
    }
    @FXML
    private void category(ActionEvent event) {
        String category = ((MenuItem) event.getSource()).getText();
        switch (category) {
            case "food" -> categoryField.setText("food");
            case "cosmetics" -> categoryField.setText("cosmetics");
            case "medicines" -> categoryField.setText("medicines");
            case "bills" -> categoryField.setText("bills");
            case "other" -> categoryField.setText("other");
        }
    }
    private ObservableList<Data> getDataList() {
        ObservableList<Data> dataList = FXCollections.observableArrayList();

        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            dbConnection.initializeDatabase(connection);
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM DATA");
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    dataList.add(new Data(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("category"),
                            result.getBigDecimal("price"),
                            result.getDate("date"))
                    );
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }
    private void showData() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        searchData();
        dateColumn.setSortType(TableColumn.SortType.DESCENDING);
        table.getSortOrder().add(dateColumn);
        table.sort();
    }
    private void executeUpdate(String query) {
        try {
            DBConnection DBConnection = new DBConnection();
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void searchData() {
        FilteredList<Data> filteredData = new FilteredList<>(getDataList(), _ -> true);
        search.textProperty().addListener((_, _, mixedNewValue) -> filteredData.setPredicate(Data -> {
            String newValue = mixedNewValue.toLowerCase();
            if(newValue.isEmpty() || newValue.isBlank()) {
                return true;
            }
            if(Data.getName().toLowerCase().contains(newValue)) {
                return true;
            }
            else if(Data.getCategory().contains(newValue)) {
                return true;
            }
            else if(String.valueOf(Data.getPrice()).contains(newValue)) {
                return true;
            }
            else return String.valueOf(Data.getDate()).contains(newValue);
        }));
        SortedList<Data> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

        sortedData.addListener((ListChangeListener.Change<? extends Data> _) -> setTotal());
        setTotal();
    }
    private void setTotal() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        totalAmount = table.getItems().stream().map(
                Data::getPrice).reduce(totalAmount, BigDecimal::add);
        total.setText("Total: "+String.format("%.2f", totalAmount));
    }
}
