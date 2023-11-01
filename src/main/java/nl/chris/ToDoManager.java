package nl.chris;

import java.util.ArrayList;

public class ToDoManager {
    private String name;
    protected ToDoItem[] toDoItems = new ToDoItem[0];

    /**
     * Get a ToDoItem object
     * @param toDoItem - The selected ToDoItem object
     * @return - The ToDoItem object
     */
    public ToDoItem getToDoItem(ToDoItem toDoItem) {
        // Get the ToDoItem object
        return toDoItem;
    }

    /**
     * Get all ToDoItem objects
     * @return - The ToDoItem array
     */
    public ArrayList<ToDoItem> getToDoItems() {
        ArrayList<ToDoItem> list = new ArrayList<>();

        // Loop through the ToDoItem array
        for (ToDoItem toDoItem : toDoItems) {
            System.out.println(toDoItem.getName() + ", " + toDoItem.getIsDone());
            list.add(toDoItem);
        }

        System.out.println("ToDoManager getToDoItems is called");

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
        System.out.println("addToDoItem is called: " + name + ", " + isDone);
        // Create a new ToDoItem object
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(name);
        toDoItem.setIsDone(isDone);

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

        // Return the ToDoItem object
        return toDoItem;
    }

    /**
     * Remove a ToDoItem object
     * @param toDoItem - The ToDoItem object to remove
     */
    public void removeToDoItem(ToDoItem toDoItem) {
        // get the array with items
        ToDoItem[] newToDoItems = new ToDoItem[toDoItems.length - 1];
        int index = 0;

        // Loop through the ToDoItem array
        for (ToDoItem item : toDoItems) {
            // If the item is not the selected item, add it to the new array
            if (item != toDoItem) {
                newToDoItems[index] = item;
                index++;
            } else {
                System.out.println("Item is deleted: " + item.getName());
            }
        }
        // Set the new array
        toDoItems = newToDoItems;

        System.out.println("-------====[ ToDo List ]====-------");
        for (ToDoItem item : toDoItems) {
            System.out.println(item.getName() + ", " + item.getIsDone());
        }
    }
}
