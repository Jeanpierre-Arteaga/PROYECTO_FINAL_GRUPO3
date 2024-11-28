package PROYECTO_GRUPO_3;
import java.io.Serializable;
public abstract class Persona implements Serializable{
    // ATRIBUTOS
    private static final long serialVersionUID = 1L;
    protected String nombres;
    protected String apellidos;
    protected String email;
    protected int edad;
    protected String distrito;

    // CONSTRUCTORES
    public Persona() {
    }

    public Persona(String nombres, String apellidos, String email, int edad, String distrito) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.edad = edad;
        this.distrito = distrito;
    }

    public Persona(String nombres, String apellidos, String distrito) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.distrito = distrito;
    }

    // MÉTODOS SETTERS
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    // METODOS GETTERS
    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }

    public String getDistrito() {
        return distrito;
    }

    // MÉTODO PARA MOSTRAR DATOS EN FORMATO DE TABLA
    public String mostrarDatos() {
        StringBuilder tabla = new StringBuilder();

        tabla.append("+-----------------+------------------------------------------+\n");
        tabla.append("|     Dato        |               Información                |\n");
        tabla.append("+-----------------+------------------------------------------+\n");

        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Apellidos", 15), 
                FormatoDelTexto.centrarTexto(apellidos, 40)));
        tabla.append("+-----------------+------------------------------------------+\n");

        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Nombres", 15), 
                FormatoDelTexto.centrarTexto(nombres, 40)));
        tabla.append("+-----------------+------------------------------------------+\n");

        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Edad", 15), 
                FormatoDelTexto.centrarTexto(String.valueOf(edad), 40)));
        tabla.append("+-----------------+------------------------------------------+\n");

        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Email", 15), 
                FormatoDelTexto.centrarTexto(email, 40)));
        tabla.append("+-----------------+------------------------------------------+\n");

        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Distrito", 15), 
                FormatoDelTexto.centrarTexto(distrito, 40)));
        tabla.append("+-----------------+------------------------------------------+\n");

        return tabla.toString();
    }
}