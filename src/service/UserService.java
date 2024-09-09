package service;
import Config.DbConnection;
import domain.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class UserService {
    Connection connection = DbConnection.getInstance().getConnection();


    private UserRepository userRepository;
    public UserService() {
        this.userRepository = new UserRepository();
    }



    public void addUser(String name,int age){
        User nouvUser= new User(name,age);

        try{
            userRepository.addUser(nouvUser,connection);
            System.out.println("user ajouter avec sucess");
        }catch (SQLException e){
            System.out.println("error de ajouter user " + e.getMessage());
        }

    }

    public void afficherUsers() {
        try {
            List<User> users = userRepository.afficherUsers(connection);
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

    public boolean updateUser(int id,String name,int age){
        User user = new User(id,name,age);

        try{
            boolean isUpdate = userRepository.updateUser(user,connection);
            if (isUpdate){
                System.out.println("utilisateur update avec succes");

            }else {
                System.out.println("utilisateur not found");
            }
            return isUpdate;


        }catch (SQLException e){
            System.out.println("error update utilisateur " + e.getMessage());
            return false;
        }

    }


    public boolean deleteUser(int id){
        try{
            boolean isDelete =userRepository.deleteUser(id,connection);
            if (isDelete){
                System.out.println("utilisateur deleted");
            }else {
                System.out.println("utilisateur not found");
            }
            return isDelete;
        }catch (SQLException e){
            System.out.println("error de suppression " +e.getMessage());
            return false;
        }
    }



}
