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
        // remove all components from existing frame
        this.getContentPane().removeAll();

        JFrame detailFrame = this;
        detailFrame.setTitle("ToDo Detail");
        detailFrame.setSize(300, 150);

        // Create a new JLabel
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setSize(300, 100);

        // Create a new JTextField
        JTextField nameTextField = new JTextField(20);
        nameTextField.setSize(300, 100);

        // Create a new JLabel
        JLabel isDoneLabel = new JLabel("Is done");

        // Create a new JCheckBox
        JCheckBox isDoneCheckBox = new JCheckBox();

        // Create a new JPanel
        JPanel panel = new JPanel();

        // Create a new JPanel
        JPanel buttonsPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton saveButton = new JButton("Save");
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        if (toDoItem == null) {
            nameTextField.setText("");
            isDoneCheckBox.setSelected(false);

            panel.add(nameLabel);
            panel.add(nameTextField);
            panel.add(isDoneLabel);
            panel.add(isDoneCheckBox);

            // Create a new JButton
            addButton.addActionListener(e -> {
                if (nameTextField.getText().isEmpty() || nameTextField.getText() == null || nameTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name is required");
                    return;
                }
                addItem(nameTextField.getText(), isDoneCheckBox.isSelected());
                detailFrame.dispose();
            });
            buttonsPanel.add(addButton);

            // Create a new JButton
            cancelButton.addActionListener(e -> {
                detailFrame.dispose();
            });
            buttonsPanel.add(cancelButton);
        } else {
            nameTextField.setText(toDoItem.getName());
            isDoneCheckBox.setSelected(toDoItem.getIsDone());

            panel.add(nameLabel);
            panel.add(nameTextField);
            panel.add(isDoneLabel);
            panel.add(isDoneCheckBox);

            // Create a new JButton
            saveButton.addActionListener(e -> {
                if (nameTextField.getText().isEmpty() || nameTextField.getText() == null || nameTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name is required");
                    return;
                }
                editItem(toDoItem, nameTextField.getText(), isDoneCheckBox.isSelected());
                detailFrame.dispose();
            });
            buttonsPanel.add(saveButton);

            // Create a new JButton
            deleteButton.addActionListener(e -> {
                deleteItem(toDoItem);
                detailFrame.dispose();
            });
            buttonsPanel.add(deleteButton);

            // Create a new JButton
            cancelButton.addActionListener(e -> {
                detailFrame.dispose();
            });
            buttonsPanel.add(cancelButton);

            // Add the panel to the frame
            detailFrame.add(panel);
            detailFrame.add(buttonsPanel);
        }
        // Add the panel to the frame
        detailFrame.add(panel);
        detailFrame.add(buttonsPanel);

        detailFrame.setLayout(new BoxLayout(detailFrame.getContentPane(), BoxLayout.Y_AXIS));
        detailFrame.setLocationRelativeTo(null);
        detailFrame.setVisible(true);
    }

    /**
     * Add a ToDoItem
     * @param name - The name of the ToDoItem
     * @param isDone - The status of the ToDoItem
     * @return - The ToDoItem object
     */
    public void addItem(String name, boolean isDone) {
        toDoController.addToDoItem(name, isDone,true);
    }

    /**
     * Edit a ToDoItem
     * @param toDoItem - The ToDoItem object to edit
     * @param name - The new name of the ToDoItem
     * @param isDone - The new status of the ToDoItem
     */
    public void editItem(ToDoItem toDoItem, String name, boolean isDone) {
        toDoController.editToDoItem(toDoItem, name, isDone, true);
    }

    /**
     * Delete a ToDoItem
     * @param toDoItem - The ToDoItem object to delete
     */
    public void deleteItem(ToDoItem toDoItem) {
        toDoController.removeToDoItem(toDoItem, true);
    }
}
