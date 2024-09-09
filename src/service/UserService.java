package service;

import Config.DbConnection;
import domain.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class UserService {
    private Connection connection = DbConnection.getInstance().getConnection();
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository(connection);
    }

    public void addUser(String name, int age) {
        User nouvUser = new User(name, age);

        try {
            userRepository.addUser(nouvUser);
            System.out.println("user ajouter avec sucess");
        } catch (SQLException e) {
            System.out.println("error de ajouter user " + e.getMessage());
        }
    }

    public void afficherUsers() {
        try {
            List<User> users = userRepository.afficherUsers();
            if (users.isEmpty()) {
                System.out.println("aucun utilisateur");
            } else {
                users.stream()
                        .sorted(Comparator.comparing(User::getId))
                        .forEach(user -> System.out.println("id: " + user.getId() + " name: " + user.getName() + " age: " + user.getAge()));
            }
        } catch (SQLException e) {
            System.out.println("error de afficher users " + e.getMessage());
        }
    }

    public boolean updateUser(int id, String name, int age) {
        User user = new User(id, name, age);

        try {
            return userRepository.updateUser(user);
        } catch (SQLException e) {
            System.out.println("error update utilisateur " + e.getMessage());
            return false;
        }
    }


    public boolean deleteUser(int id) {
        try {
            return userRepository.deleteUser(id);
        } catch (SQLException e) {
            System.out.println("error de suppression " + e.getMessage());
            return false;
        }
    }

}