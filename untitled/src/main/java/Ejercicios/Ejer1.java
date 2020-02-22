package Ejercicios;
import java.util.ArrayList;

public class Ejer1 {

    private long valor;

    public Ejer1(){}

    public Ejer1(long valor){
        this.valor = valor;
    }

    public ArrayList<Numero> descomponer(){
        ArrayList<Numero> numeros = new ArrayList<Numero>();
        if (valor%1!=0 || valor<0)
            return numeros;
        int posicion = 0;
        while (valor>9){
            numeros.add(new Numero(valor%10, posicion));
            valor = (valor-numeros.get(posicion).getNumero())/10;
            posicion++;
        }
        numeros.add(new Numero(valor, posicion));
        return numeros;
    }

    public ArrayList<Numero> descomponer(long valor){
        this.valor = valor;
        return descomponer();
    }
}
