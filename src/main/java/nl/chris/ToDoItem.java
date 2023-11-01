package nl.chris;

public class ToDoItem {
    private String name;
    private boolean isDone;

    /**
     * set the name of the ToDoItem
     * @param name - The name of the ToDoItem
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set the status of the ToDoItem
     * @param isDone - The status of the ToDoItem
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
    * get the name of the ToDoItem@
    * @return String - The name of the ToDoItem
    */
    public String getName() {
        return this.name;
    }

    /**
     * get the status of the ToDoItem
     * @return String - The status of the ToDoItem
     */
    public boolean getIsDone() {
        return this.isDone;
    }
}
