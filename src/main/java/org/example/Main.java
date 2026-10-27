
package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "Avinash@0246";

        String query = "INSERT INTO student_details "
                + "(id, Name, Mobile_no, gender, adhaar_no) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = DriverManager.getConnection(
                        url, username, password);

                PreparedStatement ps = con.prepareStatement(query)
        ) {

            // Details of 4 students
            Object[][] students = {
                    {1, "Avinash Reddy","9959988075" , "Male", "9959-9889-7075"},
                    {2, "faizan", "999999999", "Male", "9999-9999-9999"},
                    {3, "Arjun Reddy", "984907653", "Male", "7863-3754-3643"},
                    {4, "Sneha", "9840987798", "Female","3764-7454-8645" }
            };

            // Insert all students
            for (Object[] student : students) {

                ps.setInt(1, (int) student[0]);
                ps.setString(2, (String) student[1]);
                ps.setString(3, (String) student[2]);
                ps.setString(4, (String) student[3]);
                ps.setString(5, (String) student[4]);

                ps.addBatch();
            }

            int[] results = ps.executeBatch();

            System.out.println(
                    results.length + " students inserted successfully!"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

