package Ejercicios;

public class Ejer2 {

    private long valor;

    public Ejer2(){}

    public Ejer2(long valor){
        this.valor = valor;
    }

    public long invertir(){
        if (valor%1!=0 || valor<0)
            return valor;
        long numero = 0;
        while (valor>9){
            numero = (numero*10) + valor%10;
            valor = (valor - valor%10)/10;
        }
        numero = (numero*10) + valor;
        return numero;
    }

    public long invertir(long valor){
        this.valor = valor;
        return invertir();
    }
}
