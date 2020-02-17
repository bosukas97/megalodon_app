package com.testproject.testproject.helloworld;

public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String hello_world) {
        this.message = hello_world;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
