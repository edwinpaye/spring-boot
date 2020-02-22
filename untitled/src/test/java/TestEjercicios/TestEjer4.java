package TestEjercicios;

import Ejercicios.Ejer4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEjer4 {

    @Test
    public void test5342base6(){
        assertEquals(1214, new Ejer4(5342, 6).baseDecimal());
    }

    @Test
    public void test1234base8(){
        assertEquals(668, new Ejer4(1234, 8).baseDecimal());
    }

    @Test
    public void test1100base2(){
        assertEquals(12, new Ejer4().baseDecimal(1100, 2));
    }

    @Test
    public void test01111111base2(){
        assertEquals(127, new Ejer4().baseDecimal(1111111, 2));
    }
}
