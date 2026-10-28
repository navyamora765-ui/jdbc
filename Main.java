package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {

    static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    static final String USER = "root";
    static final String PASSWORD = "navya@765";

    // Database connection
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE: Insert student
    public static void insertStudent(
            String name, int age, String gender, String addhar) throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        String sql = "INSERT INTO student(name, age, gender, addhar) VALUES('"
                + name + "', " + age + ", '" + gender + "', '" + addhar + "')";

        stmt.executeUpdate(sql);

        System.out.println("Student inserted successfully!");

        stmt.close();
        cn.close();
    }

    // READ: Display all students
    public static void displayStudents() throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

        while (rs.next()) {
            System.out.println(
                    "ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Age: " + rs.getInt("age") +
                            ", Gender: " + rs.getString("gender") +
                            ", Aadhaar: " + rs.getString("addhar")
            );
        }

        rs.close();
        stmt.close();
        cn.close();
    }

    // UPDATE: Update student age
    public static void updateStudent(int id, int age) throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        String sql = "UPDATE student SET age = " + age + " WHERE id = " + id;

        int rows = stmt.executeUpdate(sql);

        System.out.println(rows + " student record(s) updated.");

        stmt.close();
        cn.close();
    }

    // DELETE: Delete student by ID
    public static void deleteStudent(int id) throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        String sql = "DELETE FROM student WHERE id = " + id;

        int rows = stmt.executeUpdate(sql);

        System.out.println(rows + " student record(s) deleted.");

        stmt.close();
        cn.close();
    }

    public static void main(String[] args) {

        try {
            // 1. Insert
            insertStudent("navya", 21, "Female", "123019");

            // 2. Read
            System.out.println("\nStudent Details:");
            displayStudents();

            // 3. Update age for student ID 1
            updateStudent(1, 22);

            // 4. Delete student ID 1
            deleteStudent(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}