package main.java.lesson4;

import java.util.*;

public class Directory {
    private Map<String, List<String>> mapBook = new HashMap<>();
    private List<String> phoneNumber;

    void add(String lastName,String phone) {
        if (mapBook.containsKey(lastName)) {
            phoneNumber = mapBook.get(lastName);
            phoneNumber.add(phone);
            mapBook.put(lastName, phoneNumber);
        } else {
            phoneNumber = new ArrayList<>();
            phoneNumber.add(phone);
            mapBook.put(lastName, phoneNumber);
        }
    }

    public void get(String lastname) {
      System.out.println(lastname +": " + mapBook.get(lastname));
    }
}
