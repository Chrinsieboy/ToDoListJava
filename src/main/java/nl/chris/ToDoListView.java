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

         todoListPanel = new JPanel();

        for (ToDoItem toDoItem : toDoItems) {

            todoListPanel.add(addTodoToPanel(toDoItem));
        }

        // buttons panel
        JPanel buttonsPanel = new JPanel();

        // padding between buttons
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            toDoController.ShowToDoItem(null);
            System.out.println("ToDoListView ShowToDoItem is called");
        });
        buttonsPanel.add(addButton);

        // sort button
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> {
            // sortItems();
            System.out.println("ToDoListView sortItems is called");
        });
        buttonsPanel.add(sortButton);

        todoListPanel.setLayout(new BoxLayout(todoListPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(todoListPanel);
        frame.add(buttonsPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("ToDoListView is initialized");
    }
    private JPanel addTodoToPanel(ToDoItem toDoItem){
        JPanel itemPanel = new JPanel();
        JLabel textLabel;
        if (toDoItem.getIsDone()) {
            textLabel = new JLabel(toDoItem.getName() + " (done) ");
        } else {
            textLabel = new JLabel(toDoItem.getName() + " (not done) ");

        }
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            toDoController.ShowToDoItem(toDoItem);
            System.out.println("ToDoListView ShowToDoItem is called");
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
     * @param name - The name of the ToDoItem
     * @param isDone - The status of the ToDoItem
     * @return - The ToDoItem object
     */
    public ToDoItem addItem(String name, boolean isDone) {
        ToDoItem item = toDoController.addToDoItem(name, isDone);
        System.out.println(item);
        return item;
    }

    public void repaintList(ArrayList<ToDoItem> toDoItems) {
        System.out.println("List should be repainted, or deleted and created again");
        todoListPanel.removeAll();
        for (ToDoItem toDoItem : toDoItems) {
            todoListPanel.add(addTodoToPanel(toDoItem));
        }
        this.revalidate();
    }
}
