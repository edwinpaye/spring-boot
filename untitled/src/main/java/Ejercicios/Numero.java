package Ejercicios;

public class Numero {

    private long numero;
    private int posicion;

    public Numero(){}

    public Numero(long numero, int posicion){
        this.numero = numero;
        this.posicion = posicion;
    }

    public long getNumero() {
        return numero;
    }

    public long setNumero(long numero) {
        this.numero = numero;
        return numero;
    }

    public int getPosicion() {
        return posicion;
    }

    public long setPosicion(int posicion) {
        this.posicion = posicion;
        return posicion;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numero numero1 = (Numero) o;
        return numero == numero1.numero &&
                posicion == numero1.posicion;
    }

}
