package Exceptions;

/**
 * @author Alexandre Debus
 * @version  1
 *
 */
public class MonException extends Throwable
{

    public MonException(String message)
    {
        System.out.println(message);
    }
}
