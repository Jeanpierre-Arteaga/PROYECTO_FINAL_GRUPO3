package PROYECTO_GRUPO_3;

import java.io.Serializable;

public class ContenedorDeLata extends Contenedor implements Serializable{
    // CONSTRUCTOR
    public ContenedorDeLata(int capacidadMaxima) {
        // DEFINIMOS EL TIPO DE CONTENEDOR
        super("envase de lata", capacidadMaxima);
    }
}