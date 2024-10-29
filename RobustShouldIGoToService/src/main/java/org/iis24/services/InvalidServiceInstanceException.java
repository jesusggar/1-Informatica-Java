package org.iis24.services;

import java.lang.RuntimeException;
public class InvalidServiceInstanceException extends RuntimeException {
    public InvalidServiceInstanceException(String msg){
        super(msg);
    }
}
