package PROYECTO_GRUPO_3;

import java.io.Serializable;
public class EncargadoDeVaciado extends Encargado implements Serializable{
    // ATRIBUTO
    private static final long serialVersionUID = 1L;
    private String turno;

    // CONSTRUCTOR
    public EncargadoDeVaciado(String nombres, String apellidos, String distrito, String turno) {
        // EL ENCARGADO DE VACIADO TIENE UN SALARIO FIJO DE 500 SOLES
        super(nombres,apellidos,500,distrito);
        this.turno = turno;
    }

    // METODOS SETTERS Y GETTERS
    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    // MÉTODO PARA VACIAR EL CONTENEDOR SOLO SI ES REQUERIDO
    public String vaciarContenedor(Contenedor contenedor) {
        // Verificamos si el contenedor está lleno antes de vaciarlo
        if (contenedor.getCapacidadActual() >= contenedor.getCapacidadMaxima()) {
            contenedor.setCapacidadActual(0);  // Restablecemos la capacidad actual a 0
            return "El contenedor de tipo " + contenedor.getTipo() + " estaba llenado y ha sido vaciado por " 
                    +nombres + " "+ apellidos + " que trabaja en turno " + turno;
        } 
        return "";
    }
}