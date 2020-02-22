package TestEjercicios;

import Ejercicios.Ejer2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEjer2 {

    @Test
    public void testInvertir4321(){
        assertEquals(1234, new Ejer2(4321).invertir());
    }

    @Test
    public void testInvertir9876(){
        assertEquals(6789, new Ejer2(9876).invertir());
    }

    @Test
    public void testInvertir1(){
        assertEquals(1, new Ejer2().invertir(1));
    }

    @Test
    public void testInvertirNegativo(){
        assertEquals(-12, new Ejer2().invertir(-12));
    }
}
