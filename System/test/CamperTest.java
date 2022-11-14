package System.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import System.Camper;

public class CamperTest {
    public Camper camper;
    public Camper another;

    @BeforeEach
    public void setup(){
        camper = new Camper("Ben", "Gordon", LocalDate.parse("2010-05-18"));
        another = new Camper("Jose","Rod", LocalDate.parse("2008-01-01"));
    }
    @AfterEach
    public void tearDown() {
        camper = null;

    }

    @Test
    void testFirstNameValid() {
        String hasBen = camper.getFirstName();
        boolean valid = false;
        if(hasBen == "Ben")
        {
            valid = true;
        }
        assertTrue(valid);
    }
    @Test
    void testFirstNameValid2() {
        String hasJose = another.getFirstName();
        boolean valid = false;
        if(hasJose == "Jose")
        {
            valid = true;
        }
        assertTrue(valid);
    }
    @Test
    void testLastNameValid() {
        String hasGordon = camper.getLastName();
        boolean valid = false;
        if(hasGordon == "Gordon")
            valid = true;
        assertTrue(valid);
    }
    @Test
    void testLastNameValid2() {
        String hasRod = another.getLastName();
        boolean valid = false;
        if(hasRod == "Rod")
        {
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    void testBirthday(){
        LocalDate bday = camper.getBirthday();
        boolean valid = false;
        if(bday.equals(LocalDate.parse("2010-05-18")))
            valid = true;
        assertTrue(valid);
    }
    @Test
    void testBirthday2(){
        LocalDate bday = another.getBirthday();
        boolean valid = false;
        if(bday.equals(LocalDate.parse("2008-01-01")))
            valid = true;
        assertTrue(valid);
    }
}