package com.example.app;

public class App
{

    private final String message = "Hello TAPD!";

    public App() {}

    public static void main(String[] args) {
        System.out.println(new App().getMessage());
    }

    private final String getMessage() {
        return message;
    }

}
