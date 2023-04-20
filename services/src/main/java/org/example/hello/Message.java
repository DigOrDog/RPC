package org.example.hello;

import java.io.Serializable;

public class Message implements Serializable {
    String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
