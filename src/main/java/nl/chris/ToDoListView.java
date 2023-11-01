package nl.chris;

import javax.swing.*;
import java.util.ArrayList;

public class ToDoListView extends JFrame {
    private ToDoController toDoController;
    private JPanel todoListPanel;
    public ToDoListView(ToDoController toDoController) {
        this.toDoController = toDoController;
    }

    /**
     * Show list with all ToDoItems
     * @param toDoItems - The ToDoItem array
     */
    public void ShowList(ArrayList<ToDoItem> toDoItems) {
        JFrame frame = this;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        // list panel
        todoListPanel = new JPanel();
        for (ToDoItem toDoItem : toDoItems) {
            todoListPanel.add(addTodoToPanel(toDoItem));
        }

        // buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            addOrEditButton(null);
        });
        buttonsPanel.add(addButton);

        // sort button
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> {
            // sortItems();
        });
        buttonsPanel.add(sortButton);

        // set layout
        todoListPanel.setLayout(new BoxLayout(todoListPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // add panels to frame
        frame.add(todoListPanel);
        frame.add(buttonsPanel);

        // show frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private JPanel addTodoToPanel(ToDoItem toDoItem){
        JPanel itemPanel = new JPanel();
        JLabel textLabel;
        if (toDoItem.getIsDone()) {
            textLabel = new JLabel(" ✔ " + toDoItem.getName() + "   ");
        } else {
            textLabel = new JLabel(" ❌ " + toDoItem.getName() + "   ");
        }
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            addOrEditButton(toDoItem);
        });

        itemPanel.add(textLabel);
        itemPanel.add(editButton);
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        itemPanel.setVisible(true);
        return itemPanel;
    }
    /**
     * Sort the ToDoItems
     */
    public void sortItems() {
        //
    }

    /**
     * Add a ToDoItem
     * @param toDoItem - The ToDoItem object
     * @return - The ToDoItem object
     */
    public void addOrEditButton(ToDoItem toDoItem) {
        toDoController.ShowToDoItem(toDoItem);
    }

    public void repaintList(ArrayList<ToDoItem> toDoItems) {
        todoListPanel.removeAll();
        for (ToDoItem toDoItem : toDoItems) {
            todoListPanel.add(addTodoToPanel(toDoItem));
        }
        this.revalidate();
    }
}
