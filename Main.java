package main.java.lesson4;

/* 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
*/

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] array = {"один", "два", "один", "три", "четыре", "один", "пять", "два", "шесть", "семь"};

        Map<String, Integer> countWorlds = new HashMap<>();
        for (String a: array) {
            if (countWorlds.containsKey(a))
                countWorlds.put(a, countWorlds.get(a) + 1);
            else countWorlds.put(a, 1);
        }
        System.out.println(countWorlds);
    }
}

