package nl.chris;

import javax.swing.*;

public class ToDoScreen extends JFrame{
    public ToDoScreen() {
        super("ToDoList");

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // add(new ToDoListView());
    }
}
