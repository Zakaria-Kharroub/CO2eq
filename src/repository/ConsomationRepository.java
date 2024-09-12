package repository;

import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsomationRepository {

    private Connection connection;

    public ConsomationRepository(Connection connection) {
        this.connection = connection;
    }

    public void addConsomation(Consomation consomation, int userId) throws SQLException {
        if (consomation instanceof Transport) {
            addTransport((Transport) consomation, userId);
        } else if (consomation instanceof Logement) {
            addLogement((Logement) consomation, userId);
        } else if (consomation instanceof Alimentation) {
            addAlimentation((Alimentation) consomation, userId);
        }
    }

    private void addTransport(Transport transport, int userId) throws SQLException {
        String sql = "INSERT INTO transports (quantite, date_debut, date_fin, user_id, type_consomation, type_vehicule, distance_parcourue) " +
                "VALUES (?, ?, ?, ?, 'TRANSPORT', ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, transport.getQuantite());
            pstmt.setDate(2, java.sql.Date.valueOf(transport.getDateDebut()));
            pstmt.setDate(3, java.sql.Date.valueOf(transport.getDateFin()));
            pstmt.setInt(4, userId);
            pstmt.setString(5, transport.getTypeDeVehicule());
            pstmt.setDouble(6, transport.getDistanceParcourue());
            pstmt.executeUpdate();
        }
    }

    private void addLogement(Logement logement, int userId) throws SQLException {
        String sql = "INSERT INTO logements (quantite, date_debut, date_fin, user_id, type_consomation, consommation_energie, type_energie) " +
                "VALUES (?, ?, ?, ?, 'LOGEMENT', ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, logement.getQuantite());
            pstmt.setDate(2, java.sql.Date.valueOf(logement.getDateDebut()));
            pstmt.setDate(3, java.sql.Date.valueOf(logement.getDateFin()));
            pstmt.setInt(4, userId);
            pstmt.setDouble(5, logement.getConsommationEnergie());
            pstmt.setString(6, logement.getTypeEnergie());
            pstmt.executeUpdate();
        }
    }

    private void addAlimentation(Alimentation alimentation, int userId) throws SQLException {
        String sql = "INSERT INTO alimentations (quantite, date_debut, date_fin, user_id, type_consomation, poids, type_aliment) " +
                "VALUES (?, ?, ?, ?, 'ALIMENTATION', ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, alimentation.getQuantite());
            pstmt.setDate(2, java.sql.Date.valueOf(alimentation.getDateDebut()));
            pstmt.setDate(3, java.sql.Date.valueOf(alimentation.getDateFin()));
            pstmt.setInt(4, userId);
            pstmt.setDouble(5, alimentation.getPoids());
            pstmt.setString(6, alimentation.getTypeAliment());
            pstmt.executeUpdate();
        }
    }
}
