import Config.DbConnection;

import domain.*;
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
                    System.out.println("Ajouter Consommation");
                    System.out.println("---------------------");

                    System.out.println("enter id de utilisateur:");
                    int idUserCons = inp.nextInt();
                    inp.nextLine();

                    System.out.println("entrez la quantite de consomation:");
                    double quantite = inp.nextDouble();
                    inp.nextLine();

                    System.out.println("enter date debut (YYYY-MM-DD):");
                    LocalDate dateDebut = LocalDate.parse(inp.nextLine());

                    System.out.println("enter date fin (YYYY-MM-DD):");
                    LocalDate dateFin = LocalDate.parse(inp.nextLine());

                    // menu consomation
                    System.out.println("choisir le type de consomation:");
                    System.out.println(" => 1: Transport");
                    System.out.println(" => 2: Logement");
                    System.out.println(" => 3: Alimentation");
                    int typeConsomation = inp.nextInt();
                    inp.nextLine();

                    try {
                        switch (typeConsomation) {
                            case 1:
                                System.out.println("------Transport-----");

                                System.out.println("enter le type de vehicule:");
                                System.out.println(" => 1: Voiture");
                                System.out.println(" => 2: Train");

                                int choixTypeVehicule = inp.nextInt();
                                inp.nextLine();

                                String typeVehicule = (choixTypeVehicule == 1) ? "Voiture" : "Train";

                                System.out.println("enter la distance parcourue:");
                                double distanceParcourue = inp.nextDouble();
                                inp.nextLine();

                                Transport transport = new Transport(dateDebut, dateFin, quantite, TypeConsommation.TRANSPORT, distanceParcourue, typeVehicule);
                                consomationService.addConsomation(transport, idUserCons);
                                break;

                            case 2:
                                System.out.println("------Logement-----");

                                System.out.println("enter type d'energie:");
                                System.out.println(" => 1: Electricite");
                                System.out.println(" => 2: Gaz");

                                int choixTypeEnergie = inp.nextInt();
                                inp.nextLine();

                                String typeEnergie = (choixTypeEnergie == 1) ? "Electricite" : "Gaz";

                                System.out.println("enter la consomation d'energie:");
                                double consommationEnergie = inp.nextDouble();
                                inp.nextLine();

                                Logement logement = new Logement(dateDebut, dateFin, quantite, TypeConsommation.LOGEMENT, consommationEnergie, typeEnergie);
                                consomationService.addConsomation(logement, idUserCons);
                                break;

                            case 3:
                                System.out.println("------Alimentation-----");

                                System.out.println("enter type d'aliment:");
                                System.out.println(" => 1: Viande");
                                System.out.println(" => 2: Légume");

                                int choixTypeAliment = inp.nextInt();
                                inp.nextLine();

                                String typeAliment = (choixTypeAliment == 1) ? "Viande" : "Légume";

                                System.out.println("enter le poids:");
                                double poids = inp.nextDouble();
                                inp.nextLine();

                                Alimentation alimentation = new Alimentation(dateDebut, dateFin, quantite, TypeConsommation.ALIMENTATION, poids, typeAliment);
                                consomationService.addConsomation(alimentation, idUserCons);
                                break;

                            default:
                                System.out.println("type de consomation pas reconu.");
                                break;
                        }
                    } catch (Exception e) {
                        System.err.println("error: " + e.getMessage());
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