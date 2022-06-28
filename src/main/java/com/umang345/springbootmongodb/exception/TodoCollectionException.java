package com.umang345.springbootmongodb.exception;

public class TodoCollectionException extends Exception
{
    public static final long serialVersionUID = 1l;

    public TodoCollectionException(String message)
    {
        super(message);
    }

    public static String NotFoundException(String id)
    {
         return "Todo with "+id+" not found!!";
    }

    public static String TodoAlreadyExists()
    {
         return "Todo with given name already exists!!";
    }
}
