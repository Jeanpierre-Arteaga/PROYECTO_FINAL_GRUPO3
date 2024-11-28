package PROYECTO_GRUPO_3;

import java.io.Serializable;

public abstract class Producto_Reciclable implements ValorDelProducto,Serializable {
    // ATRIBUTOS
    protected String tipo;
    protected int cantidad;

    // CONSTRUCTOR
    public Producto_Reciclable(String tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    // METODOS SETTERS Y GETTERS
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    // METODO PARA MOSTRAR UN PRODUCTO RECICLABLE
    public void mostrarProducto(){
        System.out.println("+----------------------+---------------+----------------+---------------+");
        System.out.println("|         Tipo         |     Valor     |    Cantidad    |     Total     |");
        System.out.println("+----------------------+---------------+----------------+---------------+");
        System.out.printf("| %s | %s | %s | %s |\n",
        // Utilizamos el siguiente metodo para centrar cada informacion
        FormatoDelTexto.centrarTexto(tipo,20),
        FormatoDelTexto.centrarTexto(String.format("%.2f",establecerValor()),13),
        FormatoDelTexto.centrarTexto(String.valueOf(cantidad),14),
        FormatoDelTexto.centrarTexto(String.format("%.2f",calcularValor()),13));
        System.out.println("+----------------------+---------------+----------------+---------------+");
    }
}