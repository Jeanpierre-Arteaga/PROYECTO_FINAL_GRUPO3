package PROYECTO_GRUPO_3;

import java.io.Serializable;

public class ContenedorDeVidrio extends Contenedor implements Serializable{
    // CONSTRUCTOR
    public ContenedorDeVidrio(int capacidadMaxima) {
        // DEFINIMOS EL TIPO DE CONTENEDOR
        super("envase de vidrio", capacidadMaxima);
    }
}