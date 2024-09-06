import Config.DbConnection;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");

//        DbConnection dbConnection = new DbConnection();
//        dbConnection.dataBaseConnection("GreenPulse", "GreenPulse", "");

        DbConnection dbConnection = DbConnection.getInstance();
        dbConnection.getConnection();

        Scanner inp=new Scanner(System.in);
        System.out.println("bienvenu dans notre application");
        int choix;

        do {
            System.out.println("+------------------------------------------+");
            System.out.println("|                   Menu                   |");
            System.out.println("+------------------------------------------+");
            System.out.println("| 1 - ajouter utilisateur                  |");
            System.out.println("| 2 - afficher tous les utilisateurs       |");
            System.out.println("| 3 - exit                                 |");
            System.out.println("+------------------------------------------+");

            choix = inp.nextInt();
            switch (choix){
                case 1:
                    System.out.println("entrez id user");
                    int id = inp.nextInt();
                    inp.nextLine();

                    System.out.println("entrez name user");
                    String name= inp.nextLine();

                    System.out.println("entrez age");
                    int age = inp.nextInt();
                    inp.nextLine();
                    break;

                case 2:
                    System.out.println("voici la listes des utilisateurs");
                    break;
                case 3:
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("choix invalid");
                    break;
            }


        }while (choix != 3);






    }
}