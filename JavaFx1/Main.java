package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

public class Main extends Application {

    public void polacz() {

        Stage stage2 = new Stage();

        stage2.initStyle(StageStyle.UNDECORATED);

        GridPane okno = new GridPane();

        Menu menu = new Menu("Menu");

        MenuItem menuItem2 = new MenuItem("Wróć");
        menuItem2.setOnAction(e -> {
            stage2.close();
        });
        menu.getItems().add(menuItem2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        okno.add(menuBar, 0, 0, 1, 1);

        Label wynik = new Label("Połączono z bazą danych");
        Label err1 = new Label("Bląd połączenia z bazą danych");
        Label err2 = new Label("Błąd sterownika");

        Connection con;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://153.19.7.13:1401; databaseName=jkislowski; user=jkislowski;password=254784;");

            okno.add(wynik,2,1,1,1);

            con.close();
        } catch (SQLException con1) {
            okno.add(err1,2,1,1,1);
        } catch (ClassNotFoundException ster1) {
            okno.add(err2,2,1,1,1);
        }

        Scene scene = new Scene(okno, 800, 1000);
        stage2.setScene(scene);
        stage2.show();
    }

    public void lista() {

        Stage stage2 = new Stage();

        stage2.initStyle(StageStyle.UNDECORATED);

        GridPane okno = new GridPane();

        Menu menu = new Menu("Menu");

        MenuItem menuItem2 = new MenuItem("Wróć");
        menuItem2.setOnAction(e -> {
            stage2.close();
        });
        menu.getItems().add(menuItem2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        okno.add(menuBar, 0, 0, 1, 1);

        Label err1 = new Label("Bląd połączenia z bazą danych");
        Label err2 = new Label("Błąd sterownika");

        Connection con;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://153.19.7.13:1401; databaseName=********; user=jkislowski;password=*******;");

            String zapytanie = "SELECT P.nazwa, K.nazwa, PK.nazwa  FROM produkt P " +
                    "INNER JOIN podkategoria PK ON PK.id_podkategoria = P.id_podkategoria " +
                    "INNER JOIN kategoria K ON K.id_kategoria = PK.id_kategoria " +
                    "WHERE P.ilosc_sztuk_magazyn > 0";

            ResultSet wynik_zapytania = con.createStatement().executeQuery(zapytanie);

            StringBuilder sb = new StringBuilder();
            sb.append("nazwa  |  kategoria  |  podkategoria\n");
            while (wynik_zapytania.next()) {
                sb.append(wynik_zapytania.getString(1));
                sb.append("  |  ");
                sb.append(wynik_zapytania.getString(2));
                sb.append("  |  ");
                sb.append(wynik_zapytania.getString(3));
                sb.append("\n");
            }

            okno.add(new Label(sb.toString()), 2, 1, 2, 1);

            con.close();
        } catch (SQLException con1) {
            okno.add(err1, 2, 1, 1, 1);
            con1.printStackTrace();
        } catch (ClassNotFoundException ster1) {
            okno.add(err2, 2, 1, 1, 1);
            ster1.printStackTrace();
        }

        Scene scene = new Scene(okno, 800, 1000);
        stage2.setScene(scene);
        stage2.show();
    }

    public void formularz() {
        Stage stage2 = new Stage();

        stage2.initStyle(StageStyle.UNDECORATED);

        GridPane okno = new GridPane();

        Menu menu = new Menu("Menu");

        MenuItem menuItem2 = new MenuItem("Close");
        menuItem2.setOnAction(e -> {
            stage2.close();
        });
        menu.getItems().add(menuItem2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        okno.add(menuBar, 0, 0, 1, 1);

        VBox lewy = new VBox();
        lewy.setMinWidth(200);
        lewy.setAlignment(Pos.TOP_RIGHT);
        okno.add(lewy, 0, 1, 1, 4);

        VBox prawy = new VBox();
        prawy.setMinWidth(400);
        prawy.setAlignment(Pos.TOP_RIGHT);
        okno.add(prawy, 1, 1, 1, 1);

        Label err1 = new Label("Bląd połączenia z bazą danych");
        Label err2 = new Label("Błąd sterownika");

        TextField nazwiskoF = new TextField();
        nazwiskoF.setMaxWidth(200);

        Label naz = new Label("Nazwisko: ");

        prawy.getChildren().clear();
        prawy.getChildren().addAll(naz, nazwiskoF);

        Button wyswietl = new Button("Wyswietl");
        prawy.getChildren().add(wyswietl);

        wyswietl.setOnAction(g-> {

            prawy.getChildren().clear();

            Label nazwawynik = new Label();
            nazwawynik.setText(nazwiskoF.getText());
            String wynikn = nazwawynik.getText();

            Connection con;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection("jdbc:sqlserver://153.19.7.13:1401; databaseName=jkislowski; user=jkislowski;password=254784;");

                String query = "SELECT TOP 1 K.nazwisko, Z.data_zamowienia, S.nazwa, P.nazwisko  FROM zamowienie Z " +
                        "INNER JOIN klient K ON K.id_klient = Z.id_klient " +
                        "INNER JOIN zamowienie_status ZS ON ZS.id_Zamowienie = Z.id_zamowienie " +
                        "INNER JOIN status S ON ZS.id_status = S.id_status " +
                        "INNER JOIN pracownik P ON P.id_pracownik = Z.id_pracownik " +
                        "WHERE K.nazwisko = \'" + wynikn + "\' " +
                        "GROUP BY K.nazwisko, Z.data_zamowienia, S.nazwa, P.nazwisko " +
                        "ORDER BY Z.data_zamowienia DESC ";

                ResultSet resultSet = con.createStatement().executeQuery(query);

                StringBuilder sb = new StringBuilder();
                sb.append("nazwisko klienta  |  data zamowienia  |  status zamowienia  |  nazwisko pracownika\n");
                while (resultSet.next()) {
                    sb.append(resultSet.getString(1));
                    sb.append("  |  ");
                    sb.append(resultSet.getString(2));
                    sb.append("  |  ");
                    sb.append(resultSet.getString(3));
                    sb.append("  |  ");
                    sb.append(resultSet.getString(4));
                    sb.append("\n");
                }

                prawy.getChildren().add(new Label(sb.toString()));

                con.close();
            } catch (SQLException cerr) {
                okno.add(err1, 2, 1, 1, 1);
                cerr.printStackTrace();
            } catch (ClassNotFoundException derr) {
                okno.add(err2, 2, 1, 1, 1);
                derr.printStackTrace();
            }
        });

        Scene scene = new Scene(okno, 800,1000);
        stage2.setScene(scene);
        stage2.show();
    }

    @Override
    public void start(Stage stage) {
        GridPane window = new GridPane();

        stage.initStyle(StageStyle.UNDECORATED);

        Menu menu = new Menu("Menu");

        MenuItem menuItem = new MenuItem("Połącz");
        menuItem.setOnAction(e -> {
            polacz();
        });
        menu.getItems().add(menuItem);

        MenuItem menuItem1 = new MenuItem("Lista");
        menuItem1.setOnAction(e -> {
            lista();
        });
        menu.getItems().add(menuItem1);

        MenuItem menuItem2 = new MenuItem("Formularz");
        menuItem2.setOnAction(e -> {
            formularz();
        });
        menu.getItems().add(menuItem2);

        MenuItem menuItem3 = new MenuItem("Zamknij");
        menuItem3.setOnAction(e -> {
            stage.close();
        });
        menu.getItems().add(menuItem3);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        window.add(menuBar, 0, 0, 1, 1);


        Scene scene = new Scene(window, 800, 1000);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}