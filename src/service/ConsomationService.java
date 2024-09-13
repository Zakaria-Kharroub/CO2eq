package service;

import Config.DbConnection;
import domain.*;
import repository.ConsomationRepository;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ConsomationService {
    private Connection connection = DbConnection.getInstance().getConnection();

    private ConsomationRepository consomationRepository;

    public ConsomationService(Connection connection) {
        this.consomationRepository = new ConsomationRepository(connection);
    }

    public void addConsomation(Consomation consomation, int userId) {
        try {
            consomationRepository.addConsomation(consomation, userId);
            System.out.println("consomation ajouter avec succes");
        } catch (SQLException e) {
            System.err.println("error de ajouter consomation: " + e.getMessage());
        }
    }


    public List<Consomation> getUserConsomations(User user) {
        try {
            return consomationRepository.getUserConsomations(user);
        } catch (SQLException e) {
            System.err.println("error de recupuration des consomations : " + e.getMessage());
            return Collections.emptyList();
        }
    }



    public double calculerImpactTotal(User user) {
        List<Consomation> consomations = getUserConsomations(user);
        double impactTotal = consomations.stream()
                .mapToDouble(Consomation::calculerImpact)
                .sum();
//        System.out.println("l'impact de " + user.getName() + " est de " + impactTotal + " kgCO2e");
        return impactTotal;
    }

    public List<User> CalculConsomationImact() throws SQLException {
        UserRepository userRepository = new UserRepository(connection);
        List <User> users = userRepository.afficherUsers();
        return users.stream()
                .filter(e -> calculerImpactTotal(e) > 3000)
                .collect(Collectors.toList());

    }












}
