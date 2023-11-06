package nl.chris;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    // Initialize dotenv
    Dotenv dotenv = Dotenv.load();

    // get database url from the .env
    private String DATABASE_URL = dotenv.get("DB_URL");
    private String DATABASE_USERNAME = dotenv.get("DB_USER");
    private String DATABASE_PASSWORD = dotenv.get("DB_PASSWORD");

    /**
     * Get all ToDoItem objects from the database
     * @return - The ToDoItem array
     */
    public ArrayList<ToDoItem> getToDoItems() {
        ArrayList<ToDoItem> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM todoitems");

            while (rs.next()) {
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setName(rs.getString("name"));
                toDoItem.setIsDone(rs.getBoolean("isDone"));
                list.add(toDoItem);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     * Add a ToDoItem object to the database
     * @param toDoItem - The ToDoItem object to add
     */
    public void addToDoItem(ToDoItem toDoItem) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            String name = toDoItem.getName();
            boolean isDone = toDoItem.getIsDone();

            PreparedStatement ps = con.prepareStatement("INSERT INTO todoitems (name, isDone) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setBoolean(2, isDone);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Edit a ToDoItem object in the database
     * @param toDoItem - The ToDoItem object to edit
     * @param name - The new name of the ToDoItem
     * @param isDone - The new status of the ToDoItem
     */
    public void editToDoItem(ToDoItem toDoItem, String name, Boolean isDone) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement ps = con.prepareStatement("UPDATE todoitems SET name = ?, isDone = ? WHERE name = ?");
            ps.setString(1, name);
            ps.setBoolean(2, isDone);
            ps.setString(3, toDoItem.getName());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Remove a ToDoItem object from the database
     * @param toDoItem - The ToDoItem object to remove
     */
    public void removeToDoItem(ToDoItem toDoItem) {
        System.out.println("Database: RemoveToDoItem");
        System.out.println("Database removeToDoItem: " + toDoItem.getName());
        System.out.println("Database removeToDoItem: " + toDoItem.getIsDone());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement ps = con.prepareStatement("DELETE FROM todoitems WHERE name = ? AND isDone = ?");
            ps.setString(1, toDoItem.getName());
            ps.setBoolean(2, toDoItem.getIsDone());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Sort ToDoItem objects by name or status
     */
    public ArrayList<ToDoItem> sortToDoItems() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM todoitems ORDER BY name ASC");
            ps.executeQuery();
            return getToDoItems();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
