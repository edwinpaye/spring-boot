package TestEjercicios;

import Ejercicios.Ejer3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEjer3 {

    @Test
    public void testRepresentacionBinaria127(){
        assertEquals("01111111", new Ejer3(127).representacionBinaria());
    }

    @Test
    public void testRepresentacionBinaria0(){
        assertEquals("00000000", new Ejer3(0).representacionBinaria());
    }

    @Test
    public void testRepresentacionBinarianegativo(){
        assertEquals("", new Ejer3().representacionBinaria(-127));
    }

    @Test
    public void testRepresentacionBinaria12(){
        assertEquals("00001100", new Ejer3().representacionBinaria(12));
    }
}
