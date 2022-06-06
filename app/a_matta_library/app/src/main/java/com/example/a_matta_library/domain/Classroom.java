package com.example.a_matta_library.domain;

public class Classroom {
    private String name;
    private String[] teacherNames;

    public Classroom(String name, String[] teacherNames) {
        this.name = name;
        this.teacherNames = teacherNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String[] teacherNames) {
        this.teacherNames = teacherNames;
    }
}
