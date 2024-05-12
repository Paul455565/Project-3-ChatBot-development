package za.ac.cput.service;

import java.sql.*;

public class ChatbotDBex {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ChatbotDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private static Connection connection;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            createTables();
            insertData();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createTables() throws SQLException {
        createUsersTable();
        createChatSessionTable();
        createChatMessagesTable();
        createSearchHistoryTable();
        createCampusShuttleTable();
        createFAQTable();
        createInformationTable();
        createChatHistoryTable();
    }

    private static void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS USER (" +
                "userID INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50), " +
                "password VARCHAR(50), " +
                "email VARCHAR(100))";
        executeSQL(sql);
    }

    private static void createChatSessionTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS CHAT_SESSION (" +
                "sessionID INT AUTO_INCREMENT PRIMARY KEY, " +
                "userID INT, " +
                "startTime DATETIME, " +
                "endTime DATETIME, " +
                "FOREIGN KEY (userID) REFERENCES USER(userID))";
        executeSQL(sql);
    }

    private static void createChatMessagesTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS CHAT_MESSAGE (" +
                "messageID INT AUTO_INCREMENT PRIMARY KEY, " +
                "sessionID INT, " +
                "text TEXT, " +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (sessionID) REFERENCES CHAT_SESSION(sessionID))";
        executeSQL(sql);
    }

    private static void createSearchHistoryTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS SEARCH_HISTORY (" +
                "historyID INT AUTO_INCREMENT PRIMARY KEY, " +
                "userID INT, " +
                "searchTerm VARCHAR(100), " +
                "searchTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (userID) REFERENCES USER(userID))";
        executeSQL(sql);
    }

    private static void createCampusShuttleTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS CAMPUS_SHUTTLE (" +
                "shuttleID INT AUTO_INCREMENT PRIMARY KEY, " +
                "routeName VARCHAR(100), " +
                "schedule TEXT)";
        executeSQL(sql);
    }

    private static void createFAQTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS FAQ (" +
                "faqID INT AUTO_INCREMENT PRIMARY KEY, " +
                "question TEXT, " +
                "answer TEXT)";
        executeSQL(sql);
    }

    private static void createInformationTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS INFORMATION (" +
                "infoID INT AUTO_INCREMENT PRIMARY KEY, " +
                "category VARCHAR(50), " +
                "details TEXT)";
        executeSQL(sql);
    }
    private static void createChatHistoryTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS CHAT_HISTORY (" +
                "historyID INT AUTO_INCREMENT PRIMARY KEY, " +
                "userID INT, " +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "messages TEXT, " +
                "FOREIGN KEY (userID) REFERENCES USER(userID))";
        executeSQL(sql);
    }

    private static void insertData() throws SQLException {
        insertUsers();
        insertChatHistory();
        insertChatSessions();
        insertChatMessages();
        insertCampusShuttle();
        insertFAQ();
        insertInformation();
    }

    private static void insertUsers() throws SQLException {
        String sql = "INSERT INTO USER (username, password, email) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "student1");
        preparedStatement.setString(2, "password1");
        preparedStatement.setString(3, "student1@example.com");
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, "student2");
        preparedStatement.setString(2, "password2");
        preparedStatement.setString(3, "student2@example.com");
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, "admin");
        preparedStatement.setString(2, "admin123");
        preparedStatement.setString(3, "admin@example.com");
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    private static void insertChatHistory() throws SQLException {
        String sql = "INSERT INTO CHAT_HISTORY (userID, timestamp, messages) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "2024-05-01");
        preparedStatement.setString(3, "Chat history for student1");
        preparedStatement.executeUpdate();

        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "2024-05-02");
        preparedStatement.setString(3, "Chat history for student2");
        preparedStatement.executeUpdate();
    }

    private static void insertChatSessions() throws SQLException {
        String sql = "INSERT INTO CHAT_SESSION (userID, startTime, endTime) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "2024-05-01");
        preparedStatement.setString(3, "2024-05-01");
        preparedStatement.executeUpdate();

        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "2024-05-02");
        preparedStatement.setString(3, "2024-05-02");
        preparedStatement.executeUpdate();
    }

    private static void insertChatMessages() throws SQLException {
        String sql = "INSERT INTO CHAT_MESSAGE (sessionID, text, timestamp) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "Hello, how can I assist you today?");
        preparedStatement.setString(3, "2024-05-01");
        preparedStatement.executeUpdate();

        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "I need help with my course registration.");
        preparedStatement.setString(3, "2024-05-01");
        preparedStatement.executeUpdate();

        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Can you tell me about the shuttle schedule?");
        preparedStatement.setString(3, "2024-05-02");
        preparedStatement.executeUpdate();
    }

    private static void insertCampusShuttle() throws SQLException {
        String sql = "INSERT INTO CAMPUS_SHUTTLE (routeName, schedule) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "Route A");
        preparedStatement.setString(2, "Every 15 minutes from 8 AM to 5 PM");
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, "Route B");
        preparedStatement.setString(2, "Every 30 minutes from 9 AM to 6 PM");
        preparedStatement.executeUpdate();
    }

    private static void insertFAQ() throws SQLException {
        String sql = "INSERT INTO FAQ (question, answer) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "How do I reset my password?");
        preparedStatement.setString(2, "To reset your password, click on \"Forgot Password\" on the login page and follow the instructions.");
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, "What is the campus shuttle schedule?");
        preparedStatement.setString(2, "The campus shuttle schedule can be found on the Campus Shuttle page.");
        preparedStatement.executeUpdate();
    }

    private static void insertInformation() throws SQLException {
        String sql = "INSERT INTO INFORMATION (category, details) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "Courses");
        preparedStatement.setString(2, "All available courses and their details can be found on the Courses page.");
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, "Application Process");
        preparedStatement.setString(2, "The application process can be found on the Admissions page.");
        preparedStatement.executeUpdate();

        preparedStatement.setString(1, "Administration");
        preparedStatement.setString(2, "Contact details and information about the administration can be found on the Administration page.");
        preparedStatement.executeUpdate();
    }

    private static void executeSQL(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}





