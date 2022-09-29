package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final CookiesClass cookiesClass = new CookiesClass(List.of(2, 7, 3, 6, 4, 6), 9);
        System.out.println("start: " + cookiesClass.toList());
        cookiesClass.merge();
        System.out.println("end: " + cookiesClass.toList());
    }
}