package Ejercicios;

import static java.lang.Math.pow;

public class Ejer3 {

    private long valor;

    public Ejer3(){}

    public Ejer3(long valor){
        this.valor = valor;
    }

    public String representacionBinaria(){
        if (valor%1!=0 || valor<0)
            return "";
        long numero = 0;
        int i=0;
        while (valor>1){
            numero = valor%2*(long)pow(10,i) + numero;
            valor = valor/2;
            i++;
        }
        numero = (long)pow(10,i)*valor + numero;
        return String.format("%08d",numero);
    }


    public String representacionBinaria(long valor){
        this.valor = valor;
        return representacionBinaria();
    }
}
