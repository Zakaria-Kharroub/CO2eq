package service;

import domain.*;
import repository.ConsomationRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class ConsomationService {
    private ConsomationRepository consomationRepository;

    public ConsomationService(Connection connection) {
        this.consomationRepository = new ConsomationRepository(connection);
    }

    public void addConsomation(Consomation consomation, int userId) {
        try {
            consomationRepository.addConsomation(consomation, userId);
            System.out.println("Consommation ajoutée avec succès");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la consommation: " + e.getMessage());
        }
    }
}
