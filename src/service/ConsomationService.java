package service;

import domain.*;
import repository.ConsomationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsomationService {
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


    public List<ArrayList> getUserConsomations(User user) {
        try {
            return consomationRepository.getUserConsomations(user);
        } catch (SQLException e) {
            System.err.println("error de recupurer consomation: " + e.getMessage());
            return null;
        }
    }









}
