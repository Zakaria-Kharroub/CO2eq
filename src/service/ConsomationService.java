package service;

import domain.Consomation;
import repository.ConsomationRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class ConsomationService {

    private ConsomationRepository consomationRepository;

    public ConsomationService(Connection connection) {
        this.consomationRepository = new ConsomationRepository(connection);
    }

    public void addConsomation(Consomation consomation, int userId) throws SQLException {
        consomationRepository.addConsomation(consomation, userId);
    }




//    public List<UserEntity> detectInactiveUsers(List<UserEntity> users, LocalDate start, LocalDate endDate) {
//        List<LocalDate> dates = DateUtils.dateListRange(start, endDate);
//
//        return users.stream()
//                .filter(user -> user.getConsumptions().stream()
//                        .noneMatch(consumption -> !DateUtils.isDateAvailable(consumption.getStartDate(), consumption.getEndDate(), dates)))
//                .collect(Collectors.toList());
//    }
}