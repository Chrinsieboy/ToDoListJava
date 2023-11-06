package nl.chris;

import java.util.ArrayList;

public class ToDoManager {
    private String name;
    protected ToDoItem[] toDoItems = new ToDoItem[0];
    private Database database = new Database();

    /**
     * Get a ToDoItem object
     * @param toDoItem - The selected ToDoItem object
     * @return - The ToDoItem object
     */
    public ToDoItem getToDoItem(ToDoItem toDoItem) {
        return toDoItem;
    }

    /**
     * Get all ToDoItem objects
     * @return - The ToDoItem array
     */
    public ArrayList<ToDoItem> getToDoItems() {
        // Get all ToDoItems from database
        ArrayList<ToDoItem> list = database.getToDoItems();

        // Set the ToDoItem array
        toDoItems = list.toArray(new ToDoItem[0]);

        // Return the ToDoItem array
        return list;
    }

    /**
     * Add a ToDoItem object to the list
     * @param name - The name of the ToDoItem
     * @param isDone - The status of the ToDoItem
     * @return - The ToDoItem object
     */
    public ToDoItem addToDoItem(String name, boolean isDone) {
        // Create a new ToDoItem object
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(name);
        toDoItem.setIsDone(isDone);

        // Add item to the database
        database.addToDoItem(toDoItem);

        // Add item to the ToDoItem array
        ToDoItem[] newToDoItems = new ToDoItem[toDoItems.length + 1];
        System.arraycopy(toDoItems, 0, newToDoItems, 0, toDoItems.length);

        // Add the new ToDoItem object to the array
        newToDoItems[toDoItems.length] = toDoItem;
        toDoItems = newToDoItems;
        return toDoItem;
    }

    /**
     * Edit a ToDoItem object
     * @param toDoItem - The ToDoItem object to edit
     * @param name - The new name of the ToDoItem
     * @param isDone - The new status of the ToDoItem
     * @return - The ToDoItem object
     */
    public ToDoItem editToDoItem(ToDoItem toDoItem, String name, boolean isDone) {
        // Edit the ToDoItem object
        toDoItem.setName(name);
        toDoItem.setIsDone(isDone);

        // Edit the ToDoItem object in the database
        database.editToDoItem(toDoItem, name, isDone);

        // Return the ToDoItem object
        return toDoItem;
    }

    /**
     * Remove a ToDoItem object
     * @param toDoItem - The ToDoItem object to remove
     */
    public void removeToDoItem(ToDoItem toDoItem, ArrayList<ToDoItem> multipleItems) {
        System.out.println("ToDoManager: RemoveToDoItem");
        if (multipleItems != null && toDoItem != null) {
            // send error message
            System.out.println("Error: multiple items and single item selected");
        } else if (multipleItems != null) {
            System.out.println("ToDoManager: Multiple items selected");
            for (ToDoItem item : multipleItems) {
                System.out.println("Item that should be deleted: " + item.getName());
                System.out.println("--------------------");
                database.removeToDoItem(item);
                System.out.println("--------------------");
                System.out.println("Item is deleted: " + item.getName());
            }
            System.out.println("--------------------");
        } else if (toDoItem != null) {
            System.out.println("ToDoManager: One item selected");
            System.out.println("Item that should be deleted: " + toDoItem.getName());
            System.out.println("--------------------");
            database.removeToDoItem(toDoItem);
            System.out.println("--------------------");
            System.out.println("Item is deleted: " + toDoItem.getName());
            System.out.println("--------------------");
        } else {
            // send error message
            System.out.println("Error: no items selected");
        }
    }

    // get the array with items
//        ToDoItem[] newToDoItems = new ToDoItem[toDoItems.length - 1];
//        int index = 0;
//
//        // Loop through the ToDoItem array
//        for (ToDoItem item : toDoItems) {
//            // If the item is not the selected item, add it to the new array
//            if (item != toDoItem) {
//                newToDoItems[index] = item;
//                index++;
//            } else {
//                // Remove the item from the database
//                System.out.println("Item is deleted: " + item.getName());
//                database.removeToDoItem(toDoItem);
//            }
//        }
//        // Set the new array
//        toDoItems = newToDoItems;
}
