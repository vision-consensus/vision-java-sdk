package org.vision.visionjsdk.client.exceptions;

public class IllegalException extends  Exception {
    public IllegalException(){
        super("Query failed. Please check the parameters.");
    }

    public IllegalException(String message){
        super(message);
    }
}
