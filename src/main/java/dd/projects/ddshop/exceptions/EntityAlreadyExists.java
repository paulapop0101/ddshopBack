package dd.projects.ddshop.exceptions;

public class EntityAlreadyExists extends  RuntimeException{
    public EntityAlreadyExists (String message) {
        super(message);
    }
}
