package PROYECTO_GRUPO_3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class GestorDeUsuarios extends Encargado implements Serializable{
    // ATRIBUTOS
    private static final long serialVersionUID = 1L;
    private String ID;
    private String contraseña;
    private String telefono;
    private double bonificacion;
    private double salarioActual;
    private ArrayList<Usuario> atendidos= new ArrayList<Usuario>();

    // CONSTRUCTORES
    public GestorDeUsuarios(){
    }

    public GestorDeUsuarios(String nombres, String apellidos, String email, int edad,
                            String distrito, String ID, String contraseña,
                            String telefono){
        super(nombres,apellidos,email,edad,distrito,800);
        this.ID = ID;
        this.contraseña = contraseña;
        this.telefono = telefono;
        bonificacion = 0;
        salarioActual = getSalario();
    }
    
    // METODOS SETTERS 
    public void setID(String ID){
        this.ID = ID;
    }

    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    public void setBonificacion(double bonificacion){
        this.bonificacion = bonificacion;
    }
    
    public void setSalarioActual(double salarioActual){
        this.salarioActual = salarioActual;
    }

    // METODOS GETTERS
    public String getID(){
        return ID;
    }

    public String getContraseña(){
        return contraseña;
    }

    public String getTelefono(){
        return telefono;
    }
    
    public double getBonificacion(){
        return bonificacion;
    }
    
    public double getSalarioActual(){
        return salarioActual;
    }

    // METODO PARA AGREGAR UN USUARIO ATENDIDO AL ARRAY
    public void agregarUsuarioAtendido(Usuario x){
        if (x != null) {
            atendidos.add(x);
        } 
    }
    
    // METODO PARA MOSTRAR LOS USUARIOS ATENDIDOS
    public String[][] usuariosAtendidosGestor(){
        String[][] usuariosData = new String[atendidos.size()][5];
        for (int i = 0; i < atendidos.size(); i++) {
            Usuario usuario = atendidos.get(i); 
            usuariosData[i][0] = String.valueOf(i+1); 
            usuariosData[i][1] = usuario.getNombres(); 
            usuariosData[i][2] = usuario.getApellidos(); 
            usuariosData[i][3] = String.valueOf(usuario.getSaldo()); 
            usuariosData[i][4] = String.valueOf(usuario.getEdad()); 
        }
        return usuariosData; 
    }

    // METODO PARA MOSTRAR EL SALARIO ACTUAL DEL GESTOR
    public String mostrarSalario() {
        StringBuilder tabla = new StringBuilder();
        tabla.append("+-----------------+------------------------------------------+\n");
        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Salario", 15), 
                FormatoDelTexto.centrarTexto(String.valueOf(salarioActual), 40)));
        tabla.append("+-----------------+------------------------------------------+\n");
        return tabla.toString();
    }
    
    //METODO PARA REINICIAR "entrada" DESPUÉS DE LA DESERIALIZACIÓN
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // Deserializa los atributos normales

        // Asegúrate de inicializar la lista 'atendidos' si está nula
        if (atendidos == null) {
            atendidos = new ArrayList<>();
        }
    }
    
    // METODO PARA BUSCAR UN USUARIO ATENDIDO POR EL GESTOR MEDIANTE APELLIDOS Y NOMBRES
    public String buscarUsuario(String b, String c){
        if(atendidos.size() == 0){
            return "Usted no ha atendido a ningun usuario por el momento.";
        }
        else{
            String a = b + " " + c;
            a = a.toUpperCase();
            StringBuilder posiciones = new StringBuilder(); 
            int i = 0;
            boolean encontrado = false; 
            while(i < atendidos.size()){
                if((atendidos.get(i).getNombres().toUpperCase() + " " + atendidos.get(i).getApellidos().toUpperCase()).equals(a)){
                    if (!encontrado) {
                        encontrado = true;
                        posiciones.append(a + " si ha sido atendido por usted.\n ");
                        posiciones.append("POSICION(ES) donde se encuentra: ");
                    } else {
                        posiciones.append(" - ");
                    }
                    posiciones.append((i+1));
                }
                i++;
            }
            if(encontrado){
                return posiciones.toString(); 
            } else {
                return a + " no ha sido atendido por usted o no se encuentra en el sistema.";
            }
        }
    }
    
    // METODO PARA MOSTRAR LOS USUARIOS ATENDIDOS Y ORDENAR SEGÚN UN CRITERIO
    public String[][] usuariosOrdenadosGestor(String tipo) {
        Map<String, Usuario> usuariosUnicos = new LinkedHashMap<>();
        for (Usuario usuario : atendidos) {
            String clave = usuario.getNombres() + " " + usuario.getApellidos(); 
            usuariosUnicos.put(clave, usuario);
        }

        List<Usuario> listaUsuarios = new ArrayList<>(usuariosUnicos.values());

        switch (tipo) {
            case "Mediante los nombres":
                Collections.sort(listaUsuarios, new Comparator<Usuario>() {
                    public int compare(Usuario u1, Usuario u2) {
                        return u1.getNombres().compareTo(u2.getNombres()); 
                    }
                });
                break;
            case "Mediante los apellidos":
                Collections.sort(listaUsuarios, new Comparator<Usuario>() {
                    public int compare(Usuario u1, Usuario u2) {
                        return u1.getApellidos().compareTo(u2.getApellidos());  
                    }
                });
                break;
            case "Mediante el saldo":
                Collections.sort(listaUsuarios, new Comparator<Usuario>() {
                    public int compare(Usuario u1, Usuario u2) {
                        return Double.compare(u1.getSaldo(), u2.getSaldo()); 
                    }
                });
                break;
            case "Mediante la edad":
                Collections.sort(listaUsuarios, new Comparator<Usuario>() {
                    public int compare(Usuario u1, Usuario u2) {
                        return Integer.compare(u1.getEdad(), u2.getEdad());
                    }
                });
                break;
            default:
                System.out.println("Criterio de ordenación no válido.");
                break;
        }

        String[][] usuariosData = new String[listaUsuarios.size()][5];
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuario = listaUsuarios.get(i);
            usuariosData[i][0] = String.valueOf(i + 1); 
            usuariosData[i][1] = usuario.getNombres(); 
            usuariosData[i][2] = usuario.getApellidos();
            usuariosData[i][3] = String.valueOf(usuario.getSaldo());
            usuariosData[i][4] = String.valueOf(usuario.getEdad()); 
        }
        return usuariosData;
    }
    
    // METODO PARA MOSTRAR LOS DATOS PERSONALES DEL GESTOR
    public String datosDelGestor(){
        StringBuilder tabla = new StringBuilder();
        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Telefono", 15), 
                FormatoDelTexto.centrarTexto(telefono, 40)));
        tabla.append("+-----------------+------------------------------------------+\n");
        return super.mostrarDatos()+tabla.toString();
    }
    
    // METODO PARA MOSTRAR EL ID Y CONTRASEÑA
    public String mostrarCredenciales(){
        StringBuilder tabla = new StringBuilder();
        tabla.append("+-----------------+------------------------------------------+\n");
        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("ID", 15), 
                FormatoDelTexto.centrarTexto(ID, 40)));
        tabla.append("+-----------------+------------------------------------------+\n");
        tabla.append(String.format("| %s | %s |\n", 
                FormatoDelTexto.centrarTexto("Contraseña", 15), 
                FormatoDelTexto.centrarTexto(contraseña, 40)));
        tabla.append("+-----------------+------------------------------------------+\n");
        return tabla.toString();
    }
}