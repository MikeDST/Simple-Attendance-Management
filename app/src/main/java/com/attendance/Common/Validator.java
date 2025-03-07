package com.attendance.Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import static java.lang.Boolean.parseBoolean;

public class Validator {
    public boolean isValidUUID(String id){
        Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        return UUID_REGEX.matcher(id).matches();
    }

    public boolean isValidDateTime(String date){
        try {
            LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
            // kiểm tra ngày tương lai
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isValidBoolean(String boo){
        return parseBoolean(boo);
    }

    public boolean isValidNumber(String number){
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (number == null) {
            return false;
        }
        return pattern.matcher(number).matches();
    }


}
