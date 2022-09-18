package main.java.lesson4;

/*2. Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
        Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного справочника.*/

public class TelephoneDirectory {
    public static void main(String[] args) {
        Directory directory = new Directory();
        directory.add("Johnson", "+7846551185");
        directory.add("Smith", "+7454862158");
        directory.add("Williams", "+7568311695");
        directory.add("Brown", "+7118732691");
        directory.add("Brown", "+71542185632");
        directory.add("Jones", "+71518551685");
        directory.add("Jones", "+7215632169");
        directory.add("Williams", "+7215932196");
        directory.add("Johnson", "+7999246215");
        directory.add("Jones", "+7144853185");

        directory.get("Johnson");
        directory.get("Williams");
        directory.get("Jones");
    }
}
