package com.babinska.plannerfortutor.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Component
public class DateProvider {
    public LocalDate now(){
        return LocalDate.now();
    }
}
