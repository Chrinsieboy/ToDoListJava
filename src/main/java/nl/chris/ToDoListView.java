package nl.chris;

import com.mysql.cj.protocol.a.MysqlTextValueDecoder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ToDoListView extends JFrame {
    private ToDoController toDoController;
    private JPanel todoListPanel;
    public ToDoListView(ToDoController toDoController) {
        this.toDoController = toDoController;
    }
    private ArrayList<ToDoItem> selectedItems;
    /**
     * Show list with all ToDoItems
     * @param toDoItems - The ToDoItem array
     */
    public void ShowList(ArrayList<ToDoItem> toDoItems) {
        JFrame frame = this;
        frame.setTitle("ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

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
             sortItems();
        });
        buttonsPanel.add(sortButton);

        // remove button
        JButton removeButton = new JButton("Remove");
        selectedItems = new ArrayList<>();
        removeButton.addActionListener(e -> {
            selectedItems.clear();
            // Get all selected items
            for (int i = 0; i < todoListPanel.getComponentCount(); i++) {
                JPanel itemPanel = (JPanel) todoListPanel.getComponent(i);
                JCheckBox checkBox = (JCheckBox) itemPanel.getComponent(0);
                if (checkBox.isSelected()) {
                    System.out.println("Selected: " + checkBox.getText());
                    selectedItems.add(toDoItems.get(i));
                }
            }

            removeToDoItems(selectedItems);
        });
        buttonsPanel.add(removeButton);

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
        JCheckBox checkBox;
        if (toDoItem.getIsDone()) {
            textLabel = new JLabel(" ✔ " + toDoItem.getName() + "   ");
        } else {
            textLabel = new JLabel(" ❌ " + toDoItem.getName() + "   ");
        }

        checkBox = new JCheckBox(textLabel.getText());
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            addOrEditButton(toDoItem);
        });

        itemPanel.add(checkBox);
        itemPanel.add(editButton);
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        itemPanel.setVisible(true);
        return itemPanel;
    }
    /**
     * Sort the ToDoItems
     */
    public void sortItems() {
        // get the items
        ArrayList<ToDoItem> items = toDoController.sortItems();

        // sort the items by name, Case insensitive
        items.sort((ToDoItem o1, ToDoItem o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        // repaint the list
        repaintList(items);
    }

    /**
     * Add a ToDoItem
     * @param toDoItem - The ToDoItem object
     * @return - The ToDoItem object
     */
    public void addOrEditButton(ToDoItem toDoItem) {
        toDoController.ShowToDoItem(toDoItem);
    }

    /**
     * Delete multiple ToDoItems
     * @param toDoItems - The ToDoItem array
     */
    public void removeToDoItems(ArrayList<ToDoItem> toDoItems) {
        System.out.println("Items to delete: " + toDoItems.size());
        System.out.println("Items that will be deleted: ");
        for (ToDoItem item : toDoItems) {
            System.out.println(item.getName());
        }

        if (toDoItems.size() == 0) {
            System.out.println("No items selected");
            return;
        } else if (toDoItems.size() == 1) {
            System.out.println("One item selected");
            System.out.println("--------------------");
            toDoController.removeToDoItem(toDoItems.get(0), true);
        } else {
            System.out.println("Multiple items selected");
            System.out.println("--------------------");
            toDoController.removeToDoItems(toDoItems, true);
        }
        this.repaintList(toDoController.getToDoItems());
    }

    public void repaintList(ArrayList<ToDoItem> toDoItems) {
        todoListPanel.removeAll();
        for (ToDoItem toDoItem : toDoItems) {
            todoListPanel.add(addTodoToPanel(toDoItem));
        }
        this.revalidate();
    }
}
