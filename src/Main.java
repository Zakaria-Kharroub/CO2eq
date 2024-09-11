import Config.DbConnection;
import domain.Alimentation;
import domain.Logement;
import domain.Transport;
import domain.User;
import service.ConsomationService;
import service.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner inp=new Scanner(System.in);

        System.out.println("Hello and welcome!");



        DbConnection dbConnection = DbConnection.getInstance();
        dbConnection.getConnection();

        UserService userService = new UserService();
        ConsomationService consomationService = new ConsomationService(dbConnection.getConnection());





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
            System.out.println("| 6 - ajouter consomation                  |");
            System.out.println("| 7 - exit                                 |");
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

                    try {
                        Optional<User> foundUser = userService.findById(idUser);
                        if (foundUser.isPresent()) {
                            User user = foundUser.get();
                            System.out.println("id: " + user.getId() + ", name: " + user.getName() + ", age: " + user.getAge());
                        } else {
                            System.out.println("utilisateur not found.");
                        }
                    } catch (SQLException e) {
                        System.out.println("error de affiche user by id "+e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("ajouter Consomation");
                    System.out.println("---------------------");

                    System.out.println("enter id de user vous voulez ajouter consomation");
                    int idUserCons = inp.nextInt();
                    inp.nextLine();

                    System.out.println("enter quantite de consomation ");
                    int quantite = inp.nextInt();
                    inp.nextLine();

                    System.out.println("enter date debut (YYYY-MM-DD)");
                    LocalDate dateDebut = LocalDate.parse(inp.nextLine());

                    System.out.println("enter date fin (YYYY-MM-DD)");
                    LocalDate dateFin = LocalDate.parse(inp.nextLine());
//                    menu consomation
                    System.out.println("enter type de consomation");
                    System.out.println(" => 1: Transport");
                    System.out.println(" => 2: Logement");
                    System.out.println(" => 3: Alimentation");
                    int typeConsomation = inp.nextInt();
                    inp.nextLine();

                    try {
                        switch (typeConsomation) {
                            case 1:
                                System.out.println("------Transport-----");

                                System.out.println("enter type de vehicule:");
                                System.out.println(" => 1: voiture");
                                System.out.println(" => 2: train");

                                int choixTypeVehicule = inp.nextInt();
                                inp.nextLine();

                                String typeVehicule = (choixTypeVehicule == 1) ? "Voiture" : "Train";

                                System.out.println("enter distance parcourue ");
                                double distanceParcourue = inp.nextDouble();
                                inp.nextLine();


                                Transport transport = new Transport(quantite, dateDebut, dateFin, distanceParcourue, typeVehicule);
                                consomationService.addConsomation(transport, idUserCons);

                                System.out.println("consomation ajouter avec success");
                                break;

                            case 2:
                                System.out.println("------Logement-----");

                                System.out.println("enter type Energie");
                                System.out.println(" => 1: electricite");
                                System.out.println(" => 2: gaz");

                                int choixTypeEnergie = inp.nextInt();
                                inp.nextLine();

                                String typeEnergie = (choixTypeEnergie ==1)? "Electricite" : "Gaz";

                                System.out.println("enter consommation energie");
                                double consommationEnergie = inp.nextDouble();
                                inp.nextLine();








                                break;

                            case 3:
                                System.out.println("------Alimentation-----");
                                break;

                            default:
                                System.out.println("Choix invalide");
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("error ajouter consomation: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("choix invalid");
                    break;
            }


        }while (choix != 7);






    }
}