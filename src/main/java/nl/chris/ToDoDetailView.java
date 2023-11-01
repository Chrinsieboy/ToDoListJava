package nl.chris;

import javax.swing.*;

public class ToDoDetailView extends JFrame {
    private ToDoController toDoController;

    public ToDoDetailView(ToDoController toDoController) {
        this.toDoController = toDoController;
    }

    /**
     * Show the detail of a ToDoItem
     * @param toDoItem - The ToDoItem object
     */
    public void ShowDetail(ToDoItem toDoItem) {
        System.out.println("-------====[ ToDo Detail ]====-------");
        JFrame detailFrame = new JFrame("ToDoDetailView");

        // if toDoItem is null, show empty form
        if (toDoItem == null) {
            // Create a new JFrame
            detailFrame.setSize(300, 150);

            // Create a new JPanel
            JPanel panel = new JPanel();

            // Create a new JLabel
            JLabel nameLabel = new JLabel("Name");
            nameLabel.setSize(300, 100);
            panel.add(nameLabel);

            // Create a new JTextField
            JTextField nameTextField = new JTextField(20);
            nameTextField.setSize(300, 100);
            panel.add(nameTextField);

            // Create a new JLabel
            JLabel isDoneLabel = new JLabel("Is done");
            panel.add(isDoneLabel);

            // Create a new JCheckBox
            JCheckBox isDoneCheckBox = new JCheckBox();
            panel.add(isDoneCheckBox);

            // Create a new JPanel
            JPanel buttonsPanel = new JPanel();

            // Create a new JButton
            JButton addButton = new JButton("Add");
            addButton.addActionListener(e -> {
                if (nameTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name is required");
                    return;
                }
                addItem(nameTextField.getText(), isDoneCheckBox.isSelected());
                detailFrame.dispose();
                System.out.println("ToDoDetailView addButton is clicked");
            });
            buttonsPanel.add(addButton);

            // Create a new JButton
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> {
                detailFrame.dispose();
                System.out.println("ToDoDetailView cancelButton is clicked");
            });
            buttonsPanel.add(cancelButton);

            // Add the panel to the frame
            detailFrame.add(panel);
            detailFrame.add(buttonsPanel);
        } else {
            // Create a new JFrame
            detailFrame.setSize(300, 150);

            // Create a new JPanel
            JPanel panel = new JPanel();

            // Create a new JLabel
            JLabel nameLabel = new JLabel("Name");
            panel.add(nameLabel);

            // Create a new JTextField
            JTextField nameTextField = new JTextField(toDoItem.getName(), 20);
            panel.add(nameTextField);

            // Create a new JLabel
            JLabel isDoneLabel = new JLabel("Is done");
            panel.add(isDoneLabel);

            // Create a new JCheckBox
            JCheckBox isDoneCheckBox = new JCheckBox();
            isDoneCheckBox.setSelected(toDoItem.getIsDone());
            panel.add(isDoneCheckBox);

            // Create a new JPanel
            JPanel buttonsPanel = new JPanel();

            // Create a new JButton
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> {
                editItem(toDoItem, nameTextField.getText(), isDoneCheckBox.isSelected());
                detailFrame.dispose();
                System.out.println("ToDoDetailView saveButton is clicked");
            });
            buttonsPanel.add(saveButton);

            // Create a new JButton
            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                deleteItem(toDoItem);
                detailFrame.dispose();
                System.out.println("ToDoDetailView deleteButton is clicked");
            });
            buttonsPanel.add(deleteButton);

            // Create a new JButton
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> {
                detailFrame.dispose();
                System.out.println("ToDoDetailView cancelButton is clicked");
            });
            buttonsPanel.add(cancelButton);

            // Add the panel to the frame
            detailFrame.add(panel);
            detailFrame.add(buttonsPanel);
        }
        detailFrame.setLayout(new BoxLayout(detailFrame.getContentPane(), BoxLayout.Y_AXIS));
        detailFrame.setLocationRelativeTo(null);
        detailFrame.setVisible(true);
        return;
    }

    /**
     * Add a ToDoItem
     * @param name - The name of the ToDoItem
     * @param isDone - The status of the ToDoItem
     * @return - The ToDoItem object
     */
    public ToDoItem addItem(String name, boolean isDone) {
        ToDoItem item = toDoController.addToDoItem(name, isDone,true);
        System.out.println(item.getName() + " has been added");
        return item;
    }

    /**
     * Edit a ToDoItem
     * @param toDoItem - The ToDoItem object to edit
     * @param name - The new name of the ToDoItem
     * @param isDone - The new status of the ToDoItem
     */
    public ToDoItem editItem(ToDoItem toDoItem, String name, boolean isDone) {
        ToDoItem item = toDoController.editToDoItem(toDoItem, name, isDone, true);

        System.out.println(item + " has been edited");
        return item;
    }

    /**
     * Delete a ToDoItem
     * @param toDoItem - The ToDoItem object to delete
     */
    public void deleteItem(ToDoItem toDoItem) {
        toDoController.removeToDoItem(toDoItem);
        System.out.println(toDoItem + " has been deleted");
    }
}