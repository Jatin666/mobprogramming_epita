package fr.epita.mob.services.exceptions;

public class unableToLoadContactsException extends Exception{
    public  unableToLoadContactsException(Exception TechnicalExceptions){

        initCause(TechnicalExceptions);



    }
}
