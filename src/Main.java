import Config.DbConnection;

import domain.*;
import service.ConsomationService;
import service.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


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
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("|                           Menu                                  |");
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("| 1  - ajouter utilisateur                                        |");
            System.out.println("| 2  - afficher tous les utilisateurs                             |");
            System.out.println("| 3  - update utilisateur                                         |");
            System.out.println("| 4  - delete utilisateur                                         |");
            System.out.println("| 5  - find utilisateur by id                                     |");
            System.out.println("| 6  - ajouter consomation                                        |");
            System.out.println("| 7  - afficher detail user consomation                           |");
            System.out.println("| 8  - filtrage des utilisateurs depasse 3000 kg de CO2           |");
            System.out.println("| 9  - detecter les utlisateur inactif dans une periode           |");
            System.out.println("| 10 - calcul impactConsomation moyenne par user dans une periode |");
            System.out.println("| 11 - tri des utilisateurs par impactConsomation                 |");
            System.out.println("| 12 - exit                                                       |");
            System.out.println("+-----------------------------------------------------------------+");

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
                                Optional<User> foundUser = userService.findById(idUserCons);
                                if (foundUser.isEmpty()) {
                                    System.out.println("utilisateur non trouvé.");
                                    break;
                                }
                                Transport transport = new Transport(dateDebut, dateFin, quantite, TypeConsommation.TRANSPORT,foundUser.get(), distanceParcourue, typeVehicule);
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

                                Optional<User> foundUserLog = userService.findById(idUserCons);
                                if (foundUserLog.isEmpty()) {
                                    System.out.println("utilisateur non trouvé.");
                                    break;
                                }
                                Logement logement = new Logement(dateDebut, dateFin, quantite, TypeConsommation.LOGEMENT,foundUserLog.get(), consommationEnergie, typeEnergie);
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
                                Optional<User> foundUserAlim = userService.findById(idUserCons);
                                if (foundUserAlim.isEmpty()) {
                                    System.out.println("utilisateur non trouvé.");
                                    break;
                                }

                                Alimentation alimentation = new Alimentation(dateDebut, dateFin, quantite, TypeConsommation.ALIMENTATION,foundUserAlim.get(), poids, typeAliment);
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
                    System.out.println("afficher details comsomations de user");
                    System.out.println("enter id user :");
                    int idUserConsDetails = inp.nextInt();
                    inp.nextLine();

                    Optional<User> foundUser = userService.findById(idUserConsDetails);

                    if (foundUser.isEmpty()) {
                        System.out.println("Utilisateur not found.");
                        break;
                    }

                    try {
                        List<Consomation> consomations= consomationService.getUserConsomations(foundUser.get());

                        if (!consomations.isEmpty()) {
                            consomations.forEach(consomation -> {
                                if (consomation != null) {
                                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    System.out.println(consomation);
                                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                } else {
                                    System.out.println("Consommation est null");
                                }
                            });
                        } else {
                            System.out.println("Pas de consommation pour cet user.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("error daffichage des consommations : " + e.getMessage());
                    }
                    break;

                case 8:
                    System.out.println("filtrage des utilisateurs depasse 3000 kg de CO2");
                    consomationService.CalculConsomationImact().forEach(user -> System.out.println("id: " + user.getId() + " name: " + user.getName() + " age: " + user.getAge()));

                    break;

                case 9:

                    System.out.println("detecter les utlisateur inactif dans une periode");
                    inp.nextLine();
                    System.out.println("enter date debut (YYYY-MM-DD):");

                    LocalDate dateDebutPeriode = LocalDate.parse(inp.nextLine());

                    System.out.println("enter date fin (YYYY-MM-DD)");
                    LocalDate dateFinPeriode = LocalDate.parse(inp.nextLine());

                    try {
                        List<User> inactiveUsers = consomationService.detectInactiveUsers(dateDebutPeriode, dateFinPeriode);
                        if (inactiveUsers.isEmpty()) {
                            System.out.println("aucum user inatif dans periode");
                        } else {
                            System.out.println("les utilisateurs inactifs dans periode " + dateDebutPeriode + " -> " + dateFinPeriode + ":");
                            inactiveUsers.forEach(user -> System.out.println("id: " + user.getId() + ", name: " + user.getName() + ", age: " + user.getAge()));
                        }
                    } catch (SQLException e) {
                        System.err.println("Erreur lors de la détection des utilisateurs inactifs: " + e.getMessage());
                    }


                    break;

                case 10:
                    System.out.println("calcul impactConsomation moyenne par user dans une periode");
                    System.out.println("enter id user");
                    int idUserMoyenne = inp.nextInt();
                    inp.nextLine();

                    System.out.println("enter date debut (YYYY-MM-DD)");
                    LocalDate dateDebutPeriodeMoyenne = LocalDate.parse(inp.nextLine());

                    System.out.println("enter date fin (YYYY-MM-DD)");
                    LocalDate dateFinPeriodeMoyenne = LocalDate.parse(inp.nextLine());

                    double moyenneImpact = consomationService.moyenneConsByPeriode(idUserMoyenne,dateDebutPeriodeMoyenne,dateFinPeriodeMoyenne);

                    System.out.println("consomation moyenne dans " + dateDebutPeriodeMoyenne + " -> " + dateFinPeriodeMoyenne + " : " + moyenneImpact + " kgCo2");
                    break;

                case 11:
                    System.out.println("tri des utilisateurs par impactConsomation");
                    List<User> users = userService.afficherUsers();
                    users.stream()
//                            .sorted(Comparator.comparingDouble(user -> {
//                                return consomationService.calculerImpactTotal(user);
//                            }))

                            .sorted((u1,u2) -> Double.compare(consomationService.calculerImpactTotal(u2),consomationService.calculerImpactTotal(u1)) )


                            .forEach(user -> {
                                System.out.println("id: " + user.getId() + ", name: " + user.getName() + ", age: " + user.getAge() + ", impact: " + consomationService.calculerImpactTotal(user) + " kgCO2");
                            });

                    break;





                case 12:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("choix invalid");
                    break;
            }


        }while (choix != 12);






    }
}