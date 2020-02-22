package TestEjercicios;

import Ejercicios.Ejer1;
import Ejercicios.Numero;
import org.junit.jupiter.api.Test;

//import org.junit.Test;
//import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEjer1 {

    @Test
    public void testEjer1234(){
        ArrayList<Numero> numeros = new ArrayList<Numero>();
        numeros.add(new Numero(4, 0));
        numeros.add(new Numero(3, 1));
        numeros.add(new Numero(2, 2));
        numeros.add(new Numero(1, 3));
        assertEquals(numeros, new Ejer1(1234).descomponer());
    }

    @Test
    public void testEjer9987(){
        ArrayList<Numero> numeros = new ArrayList<Numero>();
        numeros.add(new Numero(7, 0));
        numeros.add(new Numero(8, 1));
        numeros.add(new Numero(9, 2));
        numeros.add(new Numero(9, 3));
        assertEquals(numeros, new Ejer1(9987).descomponer());
    }

    @Test
    public void testEjer0(){
        ArrayList<Numero> numeros = new ArrayList<Numero>();
        numeros.add(new Numero(0, 0));
        assertEquals(numeros, new Ejer1().descomponer(0));
    }

    @Test
    public void testEjerNegativo(){
        ArrayList<Numero> numeros = new ArrayList<Numero>();
        assertEquals(numeros, new Ejer1().descomponer(-7));
    }
}
