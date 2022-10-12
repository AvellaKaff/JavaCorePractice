package main.java.lesson9;

public class Course {
    private String titleCourse;

    public Course(String titleCourse) {
        this.titleCourse = titleCourse;
    }

    public String getTitleCourse() {
        return titleCourse;
    }

    public void setTitleCourse(String titleCourse) {
        this.titleCourse = titleCourse;
    }

    @Override
    public String toString() {
        return  titleCourse;
    }
}
