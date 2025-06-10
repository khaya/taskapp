package za.co.binarylabs.taskapp.task.domain;

class UnknownTaskException extends RuntimeException{

  public UnknownTaskException(TaskId id){
    super("Task " + id.get() + " is unknown");
  }

}
