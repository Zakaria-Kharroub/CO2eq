package repository;

import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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



    public List<ArrayList> getUserConsomations(User user) throws SQLException {
        List<ArrayList> consomations = new ArrayList<>();
        ArrayList<Transport> transports = new ArrayList<>();

        String transportQuery = "SELECT * FROM transports WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(transportQuery)) {
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Transport transport = new Transport(rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate(), rs.getDouble("quantite"), TypeConsommation.TRANSPORT,user, rs.getDouble("distance_parcourue"), rs.getString("type_vehicule"));
                    transport.setId(rs.getInt("id"));
                    transports.add(transport);
                }

            }
        }
        consomations.add(transports);

        ArrayList<Logement> logements = new ArrayList<>();

        String logementQuery = "SELECT * FROM logements WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(logementQuery)) {
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()) {


                while (rs.next()) {
                    Logement logement = new Logement(rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate(), rs.getDouble("quantite"), TypeConsommation.LOGEMENT,user, rs.getDouble("consommation_energie"), rs.getString("type_energie"));
                    logement.setId(rs.getInt("id"));
                    logements.add(logement);
                }

            }
        }
        consomations.add(logements);


        ArrayList<Alimentation> alimentations = new ArrayList<>();
        String alimentationQuery = "SELECT * FROM alimentations WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(alimentationQuery)) {
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()) {


                while (rs.next()) {
                    Alimentation alimentation = new Alimentation(rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate(), rs.getDouble("quantite"), TypeConsommation.ALIMENTATION,user, rs.getDouble("poids"), rs.getString("type_aliment"));
                    alimentation.setId(rs.getInt("id"));
                    alimentations.add(alimentation);
                }

            }
        }
        consomations.add(alimentations);

        return consomations;
    }


































    }
