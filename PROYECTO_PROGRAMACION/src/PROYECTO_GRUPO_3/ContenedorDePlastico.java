package PROYECTO_GRUPO_3;

import java.io.Serializable;

public class ContenedorDePlastico extends Contenedor implements Serializable{
    // CONSTRUCTOR
    public ContenedorDePlastico(int capacidadMaxima) {
        // DEFINIMOS EL TIPO DE CONTENEDOR
        super("envase de plastico", capacidadMaxima);
    }
}