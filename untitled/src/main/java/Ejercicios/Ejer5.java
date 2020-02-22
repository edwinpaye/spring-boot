package Ejercicios;

public class Ejer5 {

    private int[] valores;

    public Ejer5(){}

    public Ejer5(int... valores){
        this.valores = valores;
    }

    public String decifrar(){
        String respuesta = "";
        for (int numero: valores)
            respuesta = respuesta + (char) numero;
        return respuesta;
    }

    public String decifrar(int... valores){
        this.valores = valores;
        return decifrar();
    }
}
