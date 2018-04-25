package com.gmail.grind3x;

import java.io.Serializable;
import java.util.*;

public class Group implements Commissar, Serializable {
    private String groupName;
    private List<Student> stList = new ArrayList<>();
    private int stCount = 0;

    public Group() {
        groupName = "defaultName";
    }

    public Group(String name) {
        this.groupName = name;
    }

    @Override
    public List<Student> eighteenAgeOld() {
        List<Student> stListTwo = new ArrayList<>();

        for (Student student : stList) {
            if (student.isSex() && student.getAge() >= 18) {
                stListTwo.add(student);
            }
        }

        return stListTwo;
    }

    public boolean addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (!isStudentInGroup(student)) {
            if (stCount < 10) {
                stList.add(student);
                stCount++;
                return true;
            } else {
                try {
                    throw new OutOfStudentIndexException();
                } catch (OutOfStudentIndexException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean delStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (isStudentInGroup(student)) {
            stList.removeIf(x -> x.equals(student));
            stCount--;
            return true;
        }
        return false;
    }

    public Student findStudentbySecondName(String secondName) {
        Student tmp = null;
        for (Student st : stList) {
                if (secondName.equals(st.getSecondName())) {
                    tmp = st;
                    break;
            }
        }
        return tmp;
    }

    public Student findStudentByZachNumber(Long zachNumber) {
        Student tmp = null;
        for (Student st : stList) {
                if (zachNumber.equals(st.getZach())) {
                    tmp = st;
                    break;
            }
        }
        return tmp;
    }

    public boolean isStudentInGroup(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        return stList.indexOf(student) >= 0;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void sort(int compareMode) {
        Collections.sort(stList, Comparator.nullsFirst(new StudentComparator(compareMode)));
    }

    public List<Student> getStList() {
        return stList;
    }

    public void setStList(List<Student> stList) {
        this.stList = stList;
    }

    @Override
    public String toString() {
        String retVal = "Группа " + groupName + ':' + '\n';
        for (Student st : stList) {
            retVal += (st + "\n");
        }
        return retVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) &&
                Objects.equals(stList, group.stList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, stList);
    }
}