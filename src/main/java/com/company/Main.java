package com.company;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {

    public static void main(String[] args) {
// driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Postgresql JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
// driver

// connection
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://192.168.31.59:5432/postgres",
                    "postgres",
                    "postgres"
            );

// file reader & header,row in TABLE
            File FILE_NAME_ONE = new File(new File("src\\main\\resources\\_dataTableOne.csv").getAbsolutePath());
            File FILE_NAME_TWO = new File(new File("src\\main\\resources\\_dataTableTwo.csv").getAbsolutePath());
            File FILE_NAME_THREE = new File(new File("src\\main\\resources\\_dataTableThree.csv").getAbsolutePath());

            BufferedReader csvReaderOne = new BufferedReader(new FileReader(FILE_NAME_ONE));
            BufferedReader csvReaderTwo = new BufferedReader(new FileReader(FILE_NAME_TWO));
            BufferedReader csvReaderThree = new BufferedReader(new FileReader(FILE_NAME_THREE));

            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String creation_date = date.format(format);

// file reader & header,row in TABLE
            try {
                String sqlTableOne = "INSERT INTO info_user (first_name,last_name,email) VALUES (?, ?, ?)";
                String sqlTableTwo = "INSERT INTO info_pass (first_name,last_name,pass_num,phone) VALUES (?, ?, ?, ?)";
                String sqlTableThree = "INSERT INTO info_acc (login,password,email,creation_date) VALUES (?, ?, ?, ?::date)";

                PreparedStatement stmtTableOne = conn.prepareStatement(sqlTableOne);
                PreparedStatement stmtTableTwo = conn.prepareStatement(sqlTableTwo);
                PreparedStatement stmtTableThree = conn.prepareStatement(sqlTableThree);

                boolean firstLineOne = true;
                boolean firstLineTwo = true;
                boolean firstLineThree = true;
                String row;

// TABLE 1
                while ((row = csvReaderOne.readLine()) != null) {
                    if (firstLineOne) {
                        firstLineOne = false;
                        continue;
                    }
                    String[] data = row.split(",");
                    stmtTableOne.setString(1, data[0]);
                    stmtTableOne.setString(2, data[1]);
                    stmtTableOne.setString(3, data[2]);
                    System.out.println("—œ»—Œ  ÕŒÃ≈– 1 = " + row);

                    stmtTableOne.executeUpdate();
                }
// TABLE 1

// TABLE 2
                while ((row = csvReaderTwo.readLine()) != null) {
                    if (firstLineTwo) {
                        firstLineTwo = false;
                        continue;
                    }
                    String[] data = row.split(",");
                    stmtTableTwo.setString(1, data[0]);
                    stmtTableTwo.setString(2, data[1]);
                    stmtTableTwo.setString(3, data[2]);
                    stmtTableTwo.setString(4, data[3]);
                    System.out.println("—œ»—Œ  ÕŒÃ≈– 2 = " + row);

                    stmtTableTwo.executeUpdate();
                }
// TABLE 2

// TABLE 3
                while ((row = csvReaderThree.readLine()) != null) {
                    if (firstLineThree) {
                        firstLineThree = false;
                        continue;
                    }
                    String[] data = row.split(",");
                    stmtTableThree.setString(1, data[0]);
                    stmtTableThree.setString(2, data[1]);
                    stmtTableThree.setString(3, data[2]);
                    stmtTableThree.setString(4, creation_date);
                    System.out.println("—œ»—Œ  ÕŒÃ≈– 3 = " + row);

                    stmtTableThree.executeUpdate();
                }
// TABLE 3
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
// connection
    }
}
