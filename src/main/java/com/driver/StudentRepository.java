package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    Map<String, Student> map_student;
    Map<String, Teacher> map_teacher;
    Map<String, List<String>> map_student_teacher;

    public StudentRepository() {
        map_student = new HashMap<>();
        map_teacher = new HashMap<>();
        map_student_teacher = new HashMap<>();
    }

    public void addStudent(Student student) {
        map_student.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        map_teacher.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (!map_student_teacher.containsKey(teacher)) {

            map_student_teacher.put(teacher, new ArrayList<>());
        }

        map_student_teacher.get(teacher).add(student);

    }

    public Student getStudentByName(String name) {
        return map_student.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return map_teacher.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return map_student_teacher.get(teacher);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(map_student.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        for (String name : map_student_teacher.get(teacher)) {
            map_student.remove(name);
        }
        map_teacher.remove(teacher);
        map_student_teacher.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (String name : map_student_teacher.keySet()) {
            for (String student : map_student_teacher.get(name)) {
                map_student.remove(student);
            }
        }
        map_student_teacher.clear();
        map_teacher.clear();
    }

}
