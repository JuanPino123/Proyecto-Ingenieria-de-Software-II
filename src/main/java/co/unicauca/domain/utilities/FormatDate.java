package co.unicauca.domain.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Valentina
 */

public class FormatDate {

    public static String getCurrentDateFormatted() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(formatter);
    }
}
