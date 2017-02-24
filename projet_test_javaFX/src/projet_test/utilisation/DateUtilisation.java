package projet_test.utilisation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtilisation {

	private static final String DATE_PATTERN = "dd.MM.yyyy";

	private static final DateTimeFormatter DATE_FORMATTER =
	            DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
//        return date.format(DATE_FORMATTER);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN}
     * to a {@link LocalDate} object.
     *
     * Returns null if the String could not be converted.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
	public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
//            return LocalDate.parse(dateString,DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

	public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtilisation.parse(dateString) != null;
    }
}
