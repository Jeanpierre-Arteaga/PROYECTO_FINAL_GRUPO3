package PROYECTO_GRUPO_3;

import java.io.Serializable;

public abstract class Encargado extends Persona implements Serializable{
    // ATRIBUTO
    private static final long serialVersionUID = 1L;
    protected double salarioBase;

    // CONSTRUCTORES
    public Encargado(){
    }

    public Encargado(String nombres, String apellidos, String email, int edad,
                    String distrito,double salarioBase){
        super(nombres,apellidos,email,edad,distrito);
        this.salarioBase = salarioBase;
    }

    public Encargado(String nombres, String apellidos, double salarioBase, String distrito){
        super(nombres,apellidos,distrito);
        this.salarioBase = salarioBase;
    }

    // METODOS SETTERS Y GETTERS
    public void setSalario(double salarioBase){
        this.salarioBase = salarioBase;
    }
    
    public double getSalario(){
        return salarioBase;
    }
}