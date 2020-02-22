package TestEjercicios;

import Ejercicios.Ejer5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEjer5 {

    @Test
    public void testDescifrarMensaje(){
        assertEquals("\"Bienvenidos a la Fundacion Jala :-)!\"", new Ejer5(
                34,66,105,101,110,118,101,110,105,100,
                111,115,32,97,32,108,97,32,70,117,110,100,97,
                99,105,111,110,32,74,97,108,97,32,
                58,45,41,33,34).decifrar());
    }

    @Test
    public void testDescifrarMensaje2(){
        assertEquals("Bienvenidos", new Ejer5(
                66,105,101,110,118,101,110,105,100,111,115).decifrar());
    }

    @Test
    public void testDescifrarMensaje3(){
        assertEquals("Fundacion Jala", new Ejer5().decifrar(
                70,117,110,100,97,99,105,111,110,32,74,97,108,97
        ));
    }

    @Test
    public void testDescifrarMensaje4(){
        assertEquals(":-)", new Ejer5().decifrar(58,45,41));
    }
}
