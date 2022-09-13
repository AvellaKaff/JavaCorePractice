package main.java.lesson3;

/* 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);*/

public  class Main {
    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        showArr(arr);
        System.out.println(" ");
        System.out.println("Меняем местами два элемента массива:");
        try {
            replacement(arr, 0, 2);
            showArr(arr);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        System.out.println(" ");
    }

    private static void showArr(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void replacement (String[] arr,int indexX, int indexY) throws ArrayIndexOutOfBoundsException{
        String v = null;
        String w = null;
        for (int i = 0; i < arr.length; i++) {
            if (i == indexX) {
                v = arr[i];
            }
            if (i == indexY) {
                w = arr[i];
            }
        }
        arr[indexY] = v;
        arr[indexX] = w;
    }
}
