package PROYECTO_GRUPO_3;

import java.io.Serializable;

public class EnvaseDeLata extends Producto_Reciclable implements Serializable{
    private static final long serialVersionUID = 1L;
    // CONSTRUCTOR
    public EnvaseDeLata(int cantidad) {
        // DEFINIMOS EL TIPO DE ENVASE
        super("envase de lata", cantidad);
    }

    // METODO PARA ESTABLECER EL VALOR DE UN ENVASE DE ESTE TIPO
    public double establecerValor() {
        return 0.10;
    }

    // METODO PARA CALCULAR EL VALOR TOTAL POR TODAS LAS CANTIDADES
    public double calcularValor(){
        return establecerValor()*getCantidad();
    }
}