package main.java.lesson3.fruits;

/* Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
        Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
        Для хранения фруктов внутри коробки можно использовать ArrayList;
        Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
        Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
        Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
        Не забываем про метод добавления фрукта в коробку. */

public class Main {
    public static void main(String[] args) {
        Apple apple = new Apple(1.0f);
        Orange orange = new Orange(1.5f);

        Box<Apple> appleBox = new Box<>();
        appleBox.add(apple);
        appleBox.add(apple);
        appleBox.add(apple);

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(orange);
        orangeBox.add(orange);

        Box<Orange> newBox = new Box<>();
        newBox.add(orange);

        System.out.println("Коробка с яблоками весит: "+appleBox.getWeight(apple));
        System.out.println("Коробка с апельсинами весит: "+orangeBox.getWeight(orange));
        System.out.println("Новая коробка весит: " +newBox.getWeight(orange));
        System.out.println("--------------------------------");
        System.out.println("Сравниваем коробки ");
        System.out.println("Апельсины и яблоки: "+ orangeBox.compare(appleBox));
        System.out.println("Апельсины и новая коробка: " + orangeBox.compare(newBox));
        System.out.println("--------------------------------");
        System.out.println("Пересыпаем апельсины в новую коробку");
        newBox.pourOver(orangeBox);
        System.out.println("В коробке с апельсинами осталось: " +orangeBox.getWeight(orange));
        System.out.println("Новая коробка теперь весит: " +newBox.getWeight(orange));
    }
}
