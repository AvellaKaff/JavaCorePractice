package main.java.lesson9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApp {
    public static void main(String[] args) {
        Course course1 = new Course("Разработка");
        Course course2 = new Course("Тестирование");
        Course course3 = new Course("Дизайн");
        Course course4 = new Course("Менеджмент");
        Course course5 = new Course("HR");
        Course course6 = new Course("SQL");
        Course course7 = new Course("Аналитика");
        Course course8 = new Course("Маркетинг");
        Course course9 = new Course("Английский язык");
        List<Course> courseList1 = new ArrayList<>();
        List<Course> courseList2 = new ArrayList<>();
        List<Course> courseList3 = new ArrayList<>();
        List<Course> courseList4 = new ArrayList<>();
        List<Course> courseList5 = new ArrayList<>();
        courseList1.add(course1);
        courseList1.add(course2);
        courseList1.add(course3);
        courseList2.add(course2);
        courseList2.add(course6);
        courseList3.add(course7);
        courseList4.add(course9);
        courseList4.add(course7);
        courseList4.add(course6);
        courseList4.add(course4);
        courseList4.add(course5);
        courseList5.add(course8);
        courseList5.add(course1);
        courseList5.add(course7);
        courseList5.add(course9);
        courseList5.add(course6);
        courseList5.add(course2);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Bob",courseList1));
        studentList.add(new Student("Jane",courseList2));
        studentList.add(new Student("Rick",courseList3));
        studentList.add(new Student("Oliver",courseList4));
        studentList.add(new Student("Sai",courseList5));
        studentList.add(new Student("Nick",courseList5));

//  1 Написать функцию, принимающую список Student и возвращающую список уникальных курсов, на которые подписаны студенты.
        System.out.println("1:");
        System.out.println(studentList.stream().flatMap(student -> student.getCourseList().stream()).collect(Collectors.toSet()));

//  2  Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных (любознательность определяется количеством курсов).
        System.out.println("2:");
      System.out.println(studentList.stream().sorted((student1, student2) -> student2.getCourseList().size() - student1.getCourseList().size()).limit(3).collect(Collectors.toList()));

//  3  Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов, которые посещают этот курс.
        System.out.println("3:");
        System.out.println(studentList.stream().filter(student -> student.getCourseList().contains(course2)).collect(Collectors.toList()));
    }
}
