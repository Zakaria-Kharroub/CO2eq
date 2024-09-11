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
//        System.out.println(consomation.getQuantite()); //just pour debuger

        String query = "INSERT INTO consomations (quantity, date_debut, date_fin, type_consomations, user_id) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, consomation.getQuantite());
            pstmt.setDate(2, java.sql.Date.valueOf(consomation.getDateDebut()));
            pstmt.setDate(3, java.sql.Date.valueOf(consomation.getDateFin()));
            pstmt.setString(4, consomation.getTypeConsommation().name());
            pstmt.setInt(5, userId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows ==0) {
                throw new SQLException("creatig consomation failed, no rows affected");
            }

            try (var rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int consomationId = rs.getInt(1);
                    if (consomation instanceof Transport) {
                        addTransport((Transport) consomation, consomationId);
                    }
                }
            }
        }
    }

    private void addTransport(Transport transport, int consomationId) throws SQLException {
        String query = "INSERT INTO transports (id, quantity, date_debut, date_fin, type_consomations, type_vehicule, distancep_parcourue) VALUES (?, ?, ?,?,?, ?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, consomationId);
            pstmt.setInt(2, transport.getQuantite());
            pstmt.setDate(3, java.sql.Date.valueOf(transport.getDateDebut()));
            pstmt.setDate(4, java.sql.Date.valueOf(transport.getDateFin()));
            pstmt.setString(5, transport.getTypeConsommation().name());
            pstmt.setString(6, transport.getTypeVehicule());
            pstmt.setDouble(7, transport.getDistanceParcourue());
            pstmt.executeUpdate();
        }
    }
















}
