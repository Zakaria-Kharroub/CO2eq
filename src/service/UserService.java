package service;

import Config.DbConnection;
import domain.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserService {
    private Connection connection = DbConnection.getInstance().getConnection();
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository(connection);
    }

    public void addUser(String name, int age)throws SQLException {
        User nouvUser = new User(name, age);
        userRepository.addUser(nouvUser);

    }

    public List<User> afficherUsers() throws SQLException{
        return userRepository.afficherUsers();
    }

    public boolean updateUser(int id, String name, int age) throws SQLException {
        User user = new User(id, name, age);
        return userRepository.updateUser(user);

    }


    public boolean deleteUser(int id)throws SQLException{
       return userRepository.deleteUser(id);
    }

    public void findById(int id){
        try{
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()){
                System.out.println("id : " +user.get().getId() + "name : "+ user.get().getName() + "age : " +user.get().getAge());

            }else {
                System.out.println("not found : "+id);
            }

        }catch (SQLException e){
            System.out.println("error de affiche user by id "+e.getMessage());
        }
    }

}