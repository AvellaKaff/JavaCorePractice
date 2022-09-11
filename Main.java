package main.java.lesson2;

public class Main {
    public static void main(String args[]) {
        String[][] arr = {
                {"1", "1", "1", "1"},
                {"2", "2", "2", "2"},
                {"3", "3", "3", "3"},
                {"4", "4", "4", "4"}
        };
        String[][] sizeErrorArr = {
                {"1", "1", "1", "1"},
                {"2", "2", "2", "2"},
                {"3", "3", "3", "3"},
        };
        String[][] dataErrorArr = {
                {"1", "1", "э", "1"},
                {"2", "2", "2", "2"},
                {"3", "3", "3", "3"},
                {"4", "4", "4", "4"}
        };
        try {
            array(arr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            array(sizeErrorArr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            array(dataErrorArr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    public static void array(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) {
            throw new MyArraySizeException("Не правильный размер массива");
        } else {
            int sum = 0;
            int l = arr.length;
            int[][] arr2 = new int[l][l];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    try {
                        arr2[i][j] = Integer.parseInt(arr[i][j]);

                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(i +1 , j + 1);
                    }
                    sum += arr2[i][j];
                }
            }
            System.out.println("Сумма массива: "+ sum);
        }
    }

    private static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    private static class MyArrayDataException extends Exception {
        public MyArrayDataException(String message) {
            super(message);
        }

        public MyArrayDataException(int row, int col) {
            super(String.format("Неудалось преобразование в %d строке, %d столбце", row, col));
        }
    }
}
