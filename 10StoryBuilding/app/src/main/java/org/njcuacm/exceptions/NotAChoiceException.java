package org.njcuacm.exceptions;

/**
 * Created by Saurabh on 11/28/2015.
 */
public class NotAChoiceException extends Exception {
    public NotAChoiceException()
    {
        System.err.println("The INTEGER given does not exist as a choice of answer!");
    }
}
