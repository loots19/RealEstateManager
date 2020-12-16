package com.openclassrooms.realestatemanager.testJava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import static com.openclassrooms.realestatemanager.utils.Utils.getTodayDate;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TestJava {

    @Test
    public void getDateOfTheDay(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String date = dateFormat.format(new Date());
        assertEquals(date,getTodayDate());
    }
}
