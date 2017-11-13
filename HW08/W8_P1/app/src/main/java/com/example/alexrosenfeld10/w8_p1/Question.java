package com.example.alexrosenfeld10.w8_p1;


import java.util.HashMap;
import java.util.Map;

public class Question {

    private String GUID;
    private int problemNumber;
    private int operand1;
    private int operand2;
    private String operation;
    private boolean answeredCorrectly;
    private String test;

    public Question(String GUID, int problemNumber, int operand1, int operand2, String operation, boolean answeredCorrectly, String test) {
        this.GUID = GUID;
        this.problemNumber = problemNumber;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.answeredCorrectly = answeredCorrectly;
        this.test = test;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("GUID", GUID);
        result.put("problemNumber", problemNumber);
        result.put("operand1", operand1);
        result.put("operand2", operand2);
        result.put("operation", operation);
        result.put("answeredCorrectly", answeredCorrectly);
        result.put("test", test);

        return result;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public void setProblemNumber(int problemNumber) {
        this.problemNumber = problemNumber;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getGUID() {
        return GUID;
    }

    public int getProblemNumber() {
        return problemNumber;
    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public String getTest() {
        return test;
    }
}
