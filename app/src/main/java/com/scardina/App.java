/*
 * This source file was generated by the Gradle 'init' task
 */
package com.scardina;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Flags flags = new Flags(true, true, true, true, true, true, false, 10, 9, 100);
        System.out.println(flags.getValue());
        System.out.println(flags.toJSON());
    }
}