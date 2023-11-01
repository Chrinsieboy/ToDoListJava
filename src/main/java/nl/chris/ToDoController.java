package nl.chris;

import java.util.ArrayList;

public class ToDoController {
    public ToDoManager manager = new ToDoManager();
    private ToDoListView list = new ToDoListView(this);
    private ToDoDetailView detailView = new ToDoDetailView(this);

    /**
     * Constructor
     */
    public ToDoController() {
        // Create temporary mock data
        addToDoItem("Test", false);
        addToDoItem("Test5", true);
        addToDoItem("Test2", true);
        addToDoItem("Test4", true);
        addToDoItem("Test3", false);

        // Show all ToDoItems
        ShowList(manager.getToDoItems());
    }

    /**
     * Show all ToDoItems
     * @param toDoItems - The ToDoItem array
     */
    public void ShowList(ArrayList<ToDoItem> toDoItems) {
        list.ShowList(toDoItems);
    }

    /**
     * Show a specific ToDoItem
     * @param toDoItem - The ToDoItem object
     */
    public void ShowToDoItem(ToDoItem toDoItem) {
        detailView.ShowDetail(toDoItem);
    }

    public ArrayList<ToDoItem> sortItems() {
        return manager.getToDoItems();
    }

    /**
     * Add a ToDoItem
     * @param name - The name of the ToDoItem
     * @param isDone - The status of the ToDoItem
     */
    public void addToDoItem(String name, boolean isDone) {
        manager.addToDoItem(name, isDone);
    }
    public void addToDoItem(String name, boolean isDone,boolean updateListview) {
        this.addToDoItem(name,isDone);
        if(updateListview)
             list.repaintList(manager.getToDoItems());
    }
    /**
     * Edit a ToDoItem
     * @param toDoItem - The ToDoItem object to edit
     * @param name - The new name of the ToDoItem
     * @param isDone - The new status of the ToDoItem
     */
    public void editToDoItem(ToDoItem toDoItem, String name, boolean isDone) {
        manager.editToDoItem(toDoItem, name, isDone);
    }
    public void editToDoItem(ToDoItem toDoItem, String name, boolean isDone,boolean updateListview) {
        this.editToDoItem(toDoItem,name,isDone);
        if(updateListview)
            list.repaintList(manager.getToDoItems());
    }

    /**
     * Remove a ToDoItem
     * @param toDoItem - The ToDoItem object to remove
     */
    public void removeToDoItem(ToDoItem toDoItem) {
        manager.removeToDoItem(toDoItem);
    }
    public void removeToDoItem(ToDoItem toDoItem,boolean updateListview) {
        this.removeToDoItem(toDoItem);
        if(updateListview)
            list.repaintList(manager.getToDoItems());
    }
}
