package util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    public static List<LocalDate> dateLitRange(LocalDate dateDebut, LocalDate dateFin){
        List<LocalDate> dateListRange = new ArrayList<>();
        for (LocalDate dateTest = dateDebut; !dateTest.isAfter(dateFin); dateTest = dateTest.plusDays(1)){
            dateListRange.add(dateTest);
        }
        return dateListRange;
    }
}
