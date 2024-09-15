package repository;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;

    }
    Scanner inp = new Scanner(System.in);


    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, age) VALUES (?, ?)";


        try{
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setInt(2, user.getAge());

                System.out.println("vraiment vous voulez ajouter ce user?");
                System.out.println(" 1 - yes");
                System.out.println(" 2 - no ");

                int choix = inp.nextInt();

                if (choix == 1){
                    preparedStatement.executeUpdate();
                    connection.commit();
                    System.out.println("transaction confimee");
                }else {
                    connection.rollback();
                    System.out.println("ajout anulle transaction rollback");
                }


            }

        }catch (SQLException e){
            if (connection !=null){
                try{
                    connection.rollback();
                    System.out.println("error transaction rollback");
                }catch(SQLException rolbackExcep){
                    rolbackExcep.printStackTrace();
                }
            }
//            System.out.println("error "+e.getMessage());

        }finally {
            if (connection !=null){
                connection.setAutoCommit(true);
            }
        }
    }

    public List<User> afficherUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                User user = new User(id, name, age);
                userList.add(user);
            }
        }

        return userList;
    }


    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE users SET name = ?, age = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
            return true;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    public Optional<User> findById(int id)throws SQLException{
        String query = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()){
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    User user =new User(id,name,age);
                    return Optional.of(user);
                }else {
                    return Optional.empty();
                }
            }

        }


    }





}