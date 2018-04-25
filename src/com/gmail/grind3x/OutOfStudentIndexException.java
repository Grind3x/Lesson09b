package com.gmail.grind3x;

public class OutOfStudentIndexException extends Exception {

    @Override
    public String getMessage() {
        return "Out of student index!";
    }

}
