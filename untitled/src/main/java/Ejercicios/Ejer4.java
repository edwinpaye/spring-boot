package Ejercicios;

import static java.lang.Math.pow;

public class Ejer4 {

    private long valor;
    private int base;

    public Ejer4() {}

    public Ejer4(long valor, int base) {
        this.valor = valor;
        this.base = base;
    }

    public long baseDecimal(){
        if (valor%1!=0 || valor<0)
            return valor;
        int i = 0;
        long numero = 0;
        while (valor>9){
            numero = (long) pow(base, i)*(valor%10)+numero;
            valor = (valor-valor%10)/10;
            i++;
        }
        numero = (long) pow(base, i)*(valor)+numero;
        return numero;
    }

    public long baseDecimal(long valor, int base){
        this.valor = valor;
        this.base = base;
        return baseDecimal();
    }
}
