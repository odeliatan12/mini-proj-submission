package nus.project.server.exception;

public class RegisteredUserException extends RuntimeException{

    public RegisteredUserException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
