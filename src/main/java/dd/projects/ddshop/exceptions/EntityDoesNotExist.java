package dd.projects.ddshop.exceptions;

public class EntityDoesNotExist extends RuntimeException{

    public EntityDoesNotExist (String message) {
        super(message);
    }

}
