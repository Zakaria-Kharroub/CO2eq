package service;

import Config.DbConnection;
import domain.*;
import jdk.jshell.execution.Util;
import repository.ConsomationRepository;
import repository.UserRepository;
import util.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ConsomationService {
    private Connection connection = DbConnection.getInstance().getConnection();

    private ConsomationRepository consomationRepository;
    UserRepository userRepository = new UserRepository(connection);


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


    public double moyenneConsByPeriode(int userId, LocalDate dateDebut, LocalDate dateFin) throws SQLException {
        if (!dateDebut.isAfter(dateFin)) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("user de id "+userId + " existe pas."));

            List<Consomation> consomations = consomationRepository.getUserConsomations(user);

            List<LocalDate> dates = DateUtils.dateLitRange(dateDebut, dateFin);

            List<Consomation> consomationsPeriode = consomations.stream()
                    .filter(consomation -> !(consomation.getDateDebut().isAfter(dateFin) || consomation.getDateFin().isBefore(dateDebut)))
                    .collect(Collectors.toList());

            double impactTotal = consomationsPeriode.stream()
                    .mapToDouble(Consomation::calculerImpact)
                    .sum();

            return impactTotal / dates.size();
        } else {
            throw new IllegalArgumentException("error de date debut > date fin");
        }
    }



    public List<User> detectInactiveUsers(LocalDate dateDebut, LocalDate dateFin) throws SQLException {
        List<User> users = userRepository.afficherUsers();
        List<User> inactiveUsers = new ArrayList<>();

        for (User user : users) {
            List<Consomation> consomations = consomationRepository.getUserConsomations(user);
            boolean isActive = consomations.stream()
                    .anyMatch(consomation -> !(consomation.getDateDebut().isAfter(dateFin) || consomation.getDateFin().isBefore(dateDebut)));
            if (!isActive) {
                inactiveUsers.add(user);
            }
        }
        return inactiveUsers;
    }















}
