package za.co.binarylabs.taskapp.todo.domain;

public class UnknownTaskException extends RuntimeException{

  public UnknownTaskException(TaskId id){
    super("Task " + id.get() + " is unknown");
  }

}
