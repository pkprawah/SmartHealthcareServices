package com.teembinsys.core.healthcare.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CommonUtils {

    /**
     * Truncates a LocalDateTime value to seconds precision
     * (removes milliseconds and nanoseconds).
     *
     * @param dateTime the original LocalDateTime
     * @return truncated LocalDateTime (up to seconds)
     */
    public static LocalDateTime truncateToSeconds(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.truncatedTo(ChronoUnit.SECONDS);
    }


}


