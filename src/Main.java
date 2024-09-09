import Config.DbConnection;
import domain.User;
import service.UserService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner inp=new Scanner(System.in);

        System.out.println("Hello and welcome!");



        DbConnection dbConnection = DbConnection.getInstance();
        dbConnection.getConnection();

        UserService userService = new UserService();


        System.out.println("bienvenu dans notre application");
        int choix;

        do {
            System.out.println("+------------------------------------------+");
            System.out.println("|                   Menu                   |");
            System.out.println("+------------------------------------------+");
            System.out.println("| 1 - ajouter utilisateur                  |");
            System.out.println("| 2 - afficher tous les utilisateurs       |");
            System.out.println("| 3   update utilisateur                   |");
            System.out.println("| 4   delete utilisateur                   |");
            System.out.println("| 5   find utilisateur by id               |");
            System.out.println("| 6 - exit                                 |");
            System.out.println("+------------------------------------------+");

            choix = inp.nextInt();
            switch (choix){
                case 1:
                    inp.nextLine();

                    System.out.println("entrez name user");
                    String name= inp.nextLine();

                    System.out.println("entrez age");
                    int age = inp.nextInt();
                    inp.nextLine();
                    try{
                        userService.addUser(name,age);
                        System.out.println("user ajouter avec success");
                    }catch (SQLException e){
                        System.out.println("error de ajouter utilisateur" + e.getMessage());
                    }

                    break;

                case 2:
                    System.out.println("voici la liste des utilisateurs");
                    try{
                        List<User> users = userService.afficherUsers();
                        if (users.isEmpty()){
                            System.out.println("aucun utilisaeur");
                        }else {
                            users.stream()
                                    .sorted(Comparator.comparing(User::getId))
                                    .forEachOrdered(user -> System.out.println("id: " + user.getId() + " name: " + user.getName() + " age: " + user.getAge()));
                        }
                    }catch (SQLException e){
                        System.out.println("error de afficher users " +e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("update utilisateur");
                    System.out.println("enter id de user to update");
                    int idUpdate = inp.nextInt();
                    inp.nextLine();

                    System.out.println("enter nouveau name");
                    String nouvName= inp.nextLine();

                    System.out.println("enter nouveau age");
                    int nouvAge = inp.nextInt();
                    inp.nextLine();

                   try {
                       boolean isUpdate=userService.updateUser(idUpdate,nouvName,nouvAge);
                       if (isUpdate) {
                           System.out.println("utilisateur update avec succes");
                       } else {
                           System.out.println("utilisateur not found");
                       }
                   }catch (SQLException e){
                       System.out.println("erreur update " +e.getMessage());
                   }
                    break;


                case 4:
                    System.out.println("delete utilisateur");
                    System.out.println("enter id de user to delete");

                    int idDelete = inp.nextInt();
                    inp.nextLine();

                    boolean isDelete = userService.deleteUser(idDelete);

                    if (isDelete) {
                        System.out.println("utilisateur deleted avec succes");
                    } else {
                        System.out.println("utilisateur not found");
                    }
                    break;
                case 5:
                    System.out.println("afficher utilisateur by id");
                    System.out.println("enter id de utilisateur");
                    int idUser = inp.nextInt();
                    inp.nextLine();


                    userService.findById(idUser);

                    break;

                case 6:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("choix invalid");
                    break;
            }


        }while (choix != 6);






    }
}