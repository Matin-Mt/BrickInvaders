package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String url = "jdbc:mysql://localhost:3306/brickinvaders";
    private final String user = "";
    private final String password = "";

    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Something went wrong...");
            System.out.println("Unable to connect!");
//            e.printStackTrace();
//            System.exit(1);
        }
    }

    public void createTable() {
        String query = "CREATE TABLE `brickinvaders`.`records` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `score` INT NOT NULL DEFAULT 0," +
                "  `date` DATE NOT NULL," +
                "  PRIMARY KEY (`id`));";

        Statement statement;
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(query);

            if (result > 0)
                System.out.println("table created successfully!");

        } catch (SQLException e) {
            System.out.println("something went wrong...");
            System.out.println("unable to create table!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void addRecord(int score) {
        String query = "INSERT INTO `brickinvaders`.`records` (score, date) VALUES(?,?)";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, score);
            statement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            int result = statement.executeUpdate();

            if(result > 0)
                System.out.println("score saved successfully!");

        } catch (SQLException e) {
            System.out.println("something went wrong...");
            System.out.println("unable to save score!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public ArrayList<String> getTopRecords() {
        ArrayList<String> results = new ArrayList<>();

        ArrayList<int[]> scores = new ArrayList<>();

        String query = "SELECT * FROM records";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                int id = res.getInt("id");
                int score = res.getInt("score");
                scores.add(new int[]{id, score});
            }

        } catch (SQLException e) {
            System.out.println("something went wrong...");
            System.out.println("unable to get scores!");
            e.printStackTrace();
            System.exit(1);
        }

        if (scores != null){
            for (int i = scores.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (scores.get(j)[1] < scores.get(j + 1)[1]) {
                        int[] temp = scores.get(j);
                        scores.set(j, scores.get(j + 1));
                        scores.set(j + 1, temp);
                    }
                }
            }
        }

        int[] IDs = new int[5];

        for (int i = 0; i < 5; i++) {
            if (scores != null)
                IDs[i] = scores.get(i)[0];
            else
                IDs[i] = 0;
        }

        query = "SELECT * FROM records WHERE id IN (?,?,?,?,?)";

        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < 5; i++)
                statement.setInt(i + 1, IDs[i]);

            ResultSet res = statement.executeQuery();

            while(res.next()) {
                int id = res.getInt("id");
                int score;
                java.sql.Date date;

                if (id != 0) {
                    score = res.getInt("score");
                    date = res.getDate("date");
                    results.add("Score: " + score + ", Date: " + date);
                } else {
                    results.add("");
                }
            }

        } catch (SQLException e) {
            System.out.println("something went wrong...");
            System.out.println("unable to get scores!");
            e.printStackTrace();
            System.exit(1);
        }

        return results;
    }
}
