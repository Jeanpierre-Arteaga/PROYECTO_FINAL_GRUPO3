package PROYECTO_GRUPO_3;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class SistemaDeReciclaje {
    // ATRIBUTOS
    private static ArrayList <Usuario> usuarios=new ArrayList<Usuario>();
    private static ArrayList <GestorDeUsuarios> gestores=new ArrayList<GestorDeUsuarios>();
    private static ArrayList <Producto_Tienda> productos = new ArrayList<Producto_Tienda>();
    private static Contenedores Contenedores_1;
    private static Contenedores Contenedores_2;
    private static Contenedores Contenedores_3;
    private static Contenedores Contenedores_4;
    private static int llenados;
    private static final String archivoUsuarios = "archivoUsuarios.dat";
    private static final String archivoGestores = "archivoGestores.dat";
    private static final String archivoTablasUsuarios = "usuarios_tablas.txt";
    private static final String archivoTablasGestores = "gestores_tablas.txt";
    private static final String archivoCredencialesUsuarios = "usuarios_credenciales.txt";
    private static final String archivoCredencialesGestores = "gestores_credenciales.txt";
    private static final String archivoContenedores_1 = "contenedores_1.dat";
    private static final String archivoContenedores_2 = "contenedores_2.dat";
    private static final String archivoContenedores_3 = "contenedores_3.dat";
    private static final String archivoContenedores_4 = "contenedores_4.dat";
    private static final String archivoTablasContenedores_1 = "contenedores_1_tablas.txt";
    private static final String archivoTablasContenedores_2 = "contenedores_2_tablas.txt";
    private static final String archivoTablasContenedores_3 = "contenedores_3_tablas.txt";
    private static final String archivoTablasContenedores_4 = "contenedores_4_tablas.txt";
    private static final String archivoLlenados = "llenados.dat";
    private static final String archivoProductos = "productos.dat";
    
    // CONSTRUCTOR
    public SistemaDeReciclaje(){
        cargarTodosLosContenedores();
        cargarProductosDesdeArchivo();
        EncargadoDeVaciado encargado1 = new EncargadoDeVaciado("Juan", "Vilca Lopez", 
                                                               "Santa Anita","Diurno");
        EncargadoDeVaciado encargado2 = new EncargadoDeVaciado("Andre", "Falcon Reyes", 
                                                               "Surco","Diurno");
        EncargadoDeVaciado encargado3 = new EncargadoDeVaciado("Manuel", "Tavara Suarez", 
                                                               "Miraflores","Diurno");
        EncargadoDeVaciado encargado4 = new EncargadoDeVaciado("Frank", "Gutierrez Huaman", 
                                                               "San Miguel","Diurno");
        if (Contenedores_1 == null) {
            Contenedores_1 = new Contenedores("Contenedor 1", "Santa Anita", encargado1);
        }
        if (Contenedores_2 == null) {
            Contenedores_2 = new Contenedores("Contenedor 2", "Surco", encargado2);
        }
        if (Contenedores_3 == null) {
            Contenedores_3 = new Contenedores("Contenedor 3", "Miraflores", encargado3);
        }
        if (Contenedores_4 == null) {
            Contenedores_4 = new Contenedores("Contenedor 4", "San Miguel", encargado4);
        }
        if (productos.isEmpty()) {
            productos.add(new Producto_Tienda("BolsaReutilizable", 100)); 
            productos.add(new Producto_Tienda("VasoTermico", 50));      
            productos.add(new Producto_Tienda("EcoLibreta", 75));        
            productos.add(new Producto_Tienda("LamparaSolar", 30));     
            productos.add(new Producto_Tienda("MacetaBiodegradable", 120)); 
            productos.add(new Producto_Tienda("CargadorSolar", 40));
        }
    }

    
    // METODO PARA CALCULAR LA EDAD A PARTIR DE LA FECHA DE NACIMIENTO
    public static int calcularEdadDesdeFecha(Date fecha) {
        if (fecha == null) {
            return 0; 
        }
        LocalDate nacimiento = fecha.toInstant()
                               .atZone(java.time.ZoneId.systemDefault())
                               .toLocalDate();
        return Period.between(nacimiento, LocalDate.now()).getYears();
    }

    // METODO PARA AGREGAR (REGISTRAR) UN USUARIO AL ARRAY DE USUARIOS
    public static void registrarUsuario(Usuario x){
        if (x != null) {
            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
            usuarios.add(x);
            guardarUsuariosEnArchivo();
            guardarTablaDeUsuarios(x);
            guardarCredencialesDeUsuarios(x);
        } else {
            System.out.println("El usuario es nulo. No se registró.");
        }
    }
    
    // METODO PARA GUARDAR USUARIOS EN UN ARCHIVO
    private static void guardarUsuariosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoUsuarios))) {
            oos.writeObject(usuarios);
            System.out.println("Usuarios guardados en el archivo.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    
    // METODO PARA CARGAR EL ARCHIVO EN EL ARRAY DE USUARIOS
    public static void cargarUsuariosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoUsuarios))) {
            usuarios = (ArrayList<Usuario>) ois.readObject(); 
            System.out.println("Usuarios cargados desde el archivo.");
        } 
        catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Aún no hay usuarios guardados.");
        } 
        catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Error al convertir los datos del archivo: " + e.getMessage());
        }
    }
    
    // METODO PARA GUARDAR USUARIOS EN SU FORMATO TABLA EN UN ARCHIVO
    private static void guardarTablaDeUsuarios(Usuario usuario) {
        try (FileWriter fw = new FileWriter(archivoTablasUsuarios, true); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println("USUARIO NUMERO "+usuarios.size()+" EN EL SISTEMA DE RECICLAJE:");
            pw.println(usuario.datosPersonales());
            pw.println(); 
            System.out.println("Información del usuario guardada en formato de tabla.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar la tabla del usuario: " + e.getMessage());
        }
    }
    
    // METODO PARA GUARDAR LAS CREDENCIALES DEL USUARIO EN SU FORMATO TABLA EN UN ARCHIVO
    private static void guardarCredencialesDeUsuarios(Usuario usuario) {
        try (FileWriter fw = new FileWriter(archivoCredencialesUsuarios, true); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println("CREDENCIALES DEL USUARIO NUMERO "+usuarios.size()+" "+usuario.getApellidos().toUpperCase()+" "+
                    usuario.getNombres().toUpperCase()+" EN EL SISTEMA DE RECICLAJE:");
            pw.println(usuario.mostrarCredenciales());
            pw.println(); 
            System.out.println("Credenciales del usuario guardada en formato de tabla.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar las credenciales del usuario: " + e.getMessage());
        }
    }
    
    // METODO PARA SOBRESCRIBIR EL ARCHIVO CON LOS USUARIOS RESTANTES
    private static void actualizarTablaDeUsuarios() {
        try (FileWriter fw = new FileWriter(archivoTablasUsuarios, false); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < usuarios.size(); i++) {
                pw.println("USUARIO NUMERO " + (i + 1) + " EN EL SISTEMA DE RECICLAJE:");
                pw.println(usuarios.get(i).datosPersonales());
                pw.println();
            }
            System.out.println("Archivo de usuarios en formato de tabla actualizado.");
        } 
        catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }
    
    // METODO PARA SOBRESCRIBIR EL ARCHIVO CON LAS CREDENCIALES DE LOS USUARIOS RESTANTES
    private static void actualizarCredencialesDeUsuarios() {
        try (FileWriter fw = new FileWriter(archivoCredencialesUsuarios, false); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < usuarios.size(); i++) {
                pw.println("CREDENCIALES DEL USUARIO NUMERO "+(i+1)+" "+usuarios.get(i).getApellidos().toUpperCase()+" "+
                    usuarios.get(i).getNombres().toUpperCase()+" EN EL SISTEMA DE RECICLAJE:");
                pw.println(usuarios.get(i).mostrarCredenciales());
                pw.println();
            }
            System.out.println("Archivo de credenciales en formato de tabla actualizado.");
        } 
        catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }
    private static void guardarLlenadosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoLlenados))) {
            oos.writeInt(llenados);
            System.out.println("Valor de 'llenados' guardado en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar 'llenados': " + e.getMessage());
        }
    }
    
    public static void cargarLlenadosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoLlenados))) {
            llenados = ois.readInt();
            System.out.println("Valor de 'llenados' cargado desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar 'llenados'. Usando valor predeterminado de 0.");
            llenados = 0;
        }
    }

    // METODO PARA AGREGAR (REGISTRAR) UN GESTOR AL ARRAY DE GESTORES 
    public void registrarGestor(GestorDeUsuarios x){
        if (x != null) {
            gestores.add(x);
            guardarGestoresEnArchivo();
            guardarTablaDeGestores(x);
            guardarCredencialesDeGestores(x);
        } else {
            System.out.println("El gestor es nulo. No se registró.");
        }
    }
    
    // METODO PARA GUARDAR GESTORES EN UN ARCHIVO
    private static void guardarGestoresEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoGestores))) {
            oos.writeObject(gestores);
            System.out.println("Gestores guardados en el archivo.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar gestores: " + e.getMessage());
        }
    }
    
    // METODO PARA CARGAR EL ARCHIVO EN EL ARRAY DE GESTORES
    public static void cargarGestoresDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoGestores))) {
            gestores = (ArrayList<GestorDeUsuarios>) ois.readObject(); 
            System.out.println("Gestores cargados desde el archivo.");
        } 
        catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Aún no hay gestores guardados.");
        } 
        catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Error al convertir los datos del archivo: " + e.getMessage());
        }
    }
    
    // METODO PARA GUARDAR GESTORES EN SU FORMATO TABLA EN UN ARCHIVO
    private static void guardarTablaDeGestores(GestorDeUsuarios gestor) {
        try (FileWriter fw = new FileWriter(archivoTablasGestores, true); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println("GESTOR NUMERO "+gestores.size()+" DEL SISTEMA DE RECICLAJE:");
            pw.println(gestor.datosDelGestor());
            pw.println(); 
            System.out.println("Información del gestor guardada en formato de tabla.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar la tabla del gestor: " + e.getMessage());
        }
    }
    
    // METODO PARA GUARDAR LAS CREDENCIALES DEL GESTOR EN SU FORMATO TABLA EN UN ARCHIVO
    private static void guardarCredencialesDeGestores(GestorDeUsuarios gestor) {
        try (FileWriter fw = new FileWriter(archivoCredencialesGestores, true); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println("CREDENCIALES DEL GESTOR NUMERO "+gestores.size()+" "+gestor.getApellidos().toUpperCase()+" "+
                    gestor.getNombres().toUpperCase()+" EN EL SISTEMA DE RECICLAJE:");
            pw.println(gestor.mostrarCredenciales());
            pw.println(); 
            System.out.println("Credenciales del gestor guardada en formato de tabla.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar las credenciales del gestor: " + e.getMessage());
        }
    }
    
    // METODO PARA ACTUALIZAR LA TABLA DEL ARCHIVO QUE CONFORMAN LOS GESTORES
    private static void actualizarTablaDeGestores() {
        try (FileWriter fw = new FileWriter(archivoTablasGestores, false); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < gestores.size(); i++) {
                pw.println("GESTOR NUMERO " + (i + 1) + " DEL SISTEMA DE RECICLAJE:");
                pw.println(gestores.get(i).datosDelGestor());
                pw.println();
            }
            System.out.println("Archivo de gestores en formato de tabla actualizado.");
        } 
        catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    } 
    
    // METODO PARA EL INICIO DE SESION DE UN USUARIO O UN GESTOR
    public boolean iniciar(String a, String b, String c){
        if(c.equals("usuarios")){
            for(int i=0;i<usuarios.size();i++){
                if(usuarios.get(i).getID().equals(a) && usuarios.get(i).getContraseña().equals(b)){
                    return true;
                }
            }
            return false;
        }
        else if(c.equals("gestores")){
            for(int i=0;i<gestores.size();i++){
                if(gestores.get(i).getID().equals(a) && gestores.get(i).getContraseña().equals(b)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    // METODO PARA IDENTIFICAR AL USUARIO QUE INICIO SESION
    public Usuario identificarUsuario(String a, String b){
        Usuario x = new Usuario();
        for(int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getID().equals(a) && usuarios.get(i).getContraseña().equals(b)){
                x = usuarios.get(i);
                break;
            }
        }
        return x;
    }

    // METODO PARA IDENTIFICAR AL GESTOR QUE INICIO SESION
    private GestorDeUsuarios identificarGestor(String a, String b){
        GestorDeUsuarios x = new GestorDeUsuarios();
        for(int i=0;i<gestores.size();i++){
            if(gestores.get(i).getID().equals(a) && gestores.get(i).getContraseña().equals(b)){
                x = gestores.get(i);
                break;
            }
        }
        return x;
    }

    // METODO PARA DAR EL MENSAJE DE BIENVENIDA A UN USUARIO O UN GESTOR
    public String bienvenida(String a, String b, String c){
        if(c.equals("usuarios")){
            Usuario x = identificarUsuario(a,b);
            return "BIENVENIDO SEÑOR "+primerApellido(x.getApellidos()).toUpperCase();
        }
        GestorDeUsuarios x = identificarGestor(a,b);
        return "BIENVENIDO SEÑOR "+primerApellido(x.getApellidos()).toUpperCase();
    }

    // METODO PARA OBTENER EL PRIMER APELLIDO
    private String primerApellido(String apellido){
        String b="";
        for(int i=0;i<apellido.length();i++){
            if(apellido.charAt(i)==' '){
                break;
            }
            else{
                b=b+apellido.charAt(i);
            }
        }
        return b;
    }

    // METODO PARA MOSTRAR LOS DATOS PERSONALES DEL USUARIO QUE INICIO SESION
    public Object[] datosUsuario(String a, String b) {
        Usuario x = identificarUsuario(a, b);
        Object[] datos = new Object[5];
        datos[0] = x.getNombres();
        datos[1] = x.getApellidos();
        datos[2] = x.getEdad(); 
        datos[3] = x.getEmail();
        datos[4] = x.getDistrito();
        return datos;
    }

    // METODO PARA MOSTRAR LOS DATOS PERSONALES DEL GESTOR QUE INICIO SESION
    public Object[] datosGestor(String a, String b){
        GestorDeUsuarios x = identificarGestor(a,b);
        Object[] datos = new Object[6];
        datos[0] = x.getNombres();
        datos[1] = x.getApellidos();
        datos[2] = x.getEdad();
        datos[3] = x.getTelefono();
        datos[4] = x.getEmail();
        datos[5] = x.getDistrito();
        return datos;
    }

    // METODO PARA MOSTRAR SALDO ACTUAL DEL USUARIO 
    public double mostrarSaldo(String a,String b){
        Usuario x = identificarUsuario(a,b);
        return x.getSaldo();
    }

    // METODO PARA MOSTRAR SALARIO ACTUAL DEL GESTOR 
    public double[] mostrarSalarios(String a,String b){
        GestorDeUsuarios x = identificarGestor(a,b);
        double[] salarios = new double[3];
        salarios[0] = x.getSalario();
        salarios[1] = x.getBonificacion();
        salarios[2] = x.getSalarioActual();
        return salarios;
    }

    // METODO PARA BUSCAR SI UN USUARIO FUE ATENDIDO POR UN GESTOR
    public String buscarUsuario(String a,String b,String c,String d){
        GestorDeUsuarios x = identificarGestor(a,b);
        return x.buscarUsuario(c,d);
    }
    
    // METODO PARA GUARDAR LOS CONTENEDORES EN UN ARCHIVO
    private void guardarContenedoresEnArchivo(String archivo,Contenedores x, int y) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(x);
            System.out.println("Contenedor "+y+" guardado en el archivo.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar el contenedor "+y+": " + e.getMessage());
        }
    }
    
    private void guardarContenedores_1_EnArchivo(){
        guardarContenedoresEnArchivo(archivoContenedores_1,Contenedores_1,1);
    }
    
    private void guardarContenedores_2_EnArchivo(){
        guardarContenedoresEnArchivo(archivoContenedores_2,Contenedores_2,2);
    }
    
    private void guardarContenedores_3_EnArchivo(){
        guardarContenedoresEnArchivo(archivoContenedores_3,Contenedores_3,3);
    }
    
    private void guardarContenedores_4_EnArchivo(){
        guardarContenedoresEnArchivo(archivoContenedores_4,Contenedores_4,4);
    }
    
    // METODO PARA CARGAR EL ARCHIVO EN CADA UNO DE LOS CONTENEDORES
    private static void cargarContenedoresDesdeArchivo(String archivo, int numeroContenedor) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Contenedores contenedorCargado = (Contenedores) ois.readObject();
            switch (numeroContenedor) {
                case 1 -> Contenedores_1 = contenedorCargado;
                case 2 -> Contenedores_2 = contenedorCargado;
                case 3 -> Contenedores_3 = contenedorCargado;
                case 4 -> Contenedores_4 = contenedorCargado;
            }
            System.out.println("Contenedor " + numeroContenedor + " cargado desde el archivo.");
        } 
        catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Inicializando un nuevo contenedor " + numeroContenedor + ".");
        } 
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el contenedor " + numeroContenedor + ": " + e.getMessage());
        }
    }
    
    public static void cargarTodosLosContenedores(){
        cargarContenedoresDesdeArchivo(archivoContenedores_1, 1);
        cargarContenedoresDesdeArchivo(archivoContenedores_2, 2);
        cargarContenedoresDesdeArchivo(archivoContenedores_3, 3);
        cargarContenedoresDesdeArchivo(archivoContenedores_4, 4);
    }
    
    // METODO PARA GUARDAR LOS CONTENEDORES EN UN ARCHIVO EN FORMATO TABLA
    private void guardarContenedoresEnTabla(String archivo, Contenedores x, int y) {
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println("INFORMACION DEL CONTENEDOR LUEGO DEL USO "+llenados+" DE ALGUNO DE ESTOS:");
            pw.println(x.mostrarContenedores());
        } 
        catch (IOException e) {
            System.out.println("Error al guardar la tabla de contenedor " + y + ": " + e.getMessage());
        }
    }
    
    private void guardarContenedores_1_EnTabla(){
        guardarContenedoresEnTabla(archivoTablasContenedores_1,Contenedores_1,1);
    }
    
    private void guardarContenedores_2_EnTabla(){
        guardarContenedoresEnTabla(archivoTablasContenedores_2,Contenedores_2,2);
    }
    
    private void guardarContenedores_3_EnTabla(){
        guardarContenedoresEnTabla(archivoTablasContenedores_3,Contenedores_3,3);
    }
    
    private void guardarContenedores_4_EnTabla(){
        guardarContenedoresEnTabla(archivoTablasContenedores_4,Contenedores_4,4);
    }
    
    // METODO PARA GUARDAR LOS PRODUCTOS EN UN ARCHIVO
    private static void guardarProductosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoProductos))) {
            oos.writeObject(productos);
            System.out.println("Productos guardados en el archivo.");
        } 
        catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }
    
    // METODO PARA CARGAR LOS PRODUCTOS DE UN ARCHIVO
    public static void cargarProductosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoProductos))) {
            productos = (ArrayList<Producto_Tienda>) ois.readObject();
            System.out.println("Productos cargados desde el archivo.");
        } 
        catch (FileNotFoundException e) {
            System.out.println("El archivo de productos no existe. Aún no hay productos guardados.");
        } 
        catch (IOException e) {
            System.out.println("Error al leer el archivo de productos: " + e.getMessage());
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Error al convertir los datos del archivo de productos: " + e.getMessage());
        }
    }
    
    // METODO PARA AGREGAR PRODUCTOS A LOS CONTENEDORES
    public String[] agregar_1(String a,String b,int opc1,int opc2,int n,int m,int s){
        Usuario x = identificarUsuario(a,b);
        String[] c = new String[3];
        switch(opc1){
            case 1: c = agregar_2(Contenedores_1,x,"Santa Anita",opc2,n,m,s); break;
            case 2: c = agregar_2(Contenedores_2,x,"Surco",opc2,n,m,s); break;
            case 3: c = agregar_2(Contenedores_3,x,"Miraflores",opc2,n,m,s); break;
            case 4: c = agregar_2(Contenedores_4,x,"San Miguel",opc2,n,m,s); break;
        }
        return c;
    }    
    
    // METODO PARA OBTENER TODOS LOS GESTORES QUE HAY EN UN DISTRITO
    public String[][] gestorDistrito(String a) {
        int b = 0;
        for (int i = 0; i < gestores.size(); i++) {
            if (gestores.get(i).getDistrito().equals(a)) {
                b++;
            }
        }
        String[][] gestoresArray = new String[b][4];
        int n = 0;
        for (int i = 0; i < gestores.size(); i++) {
            if (gestores.get(i).getDistrito().equals(a)) {
                gestoresArray[n][0] = String.valueOf(n + 1); 
                gestoresArray[n][1] = gestores.get(i).getNombres().toUpperCase(); 
                gestoresArray[n][2] = gestores.get(i).getApellidos().toUpperCase(); 
                gestoresArray[n][3] = "Seleccionar";
                n++;
            }
        }

        return gestoresArray;
    }
    
    // METODO PARA OBTENER TODOS LOS USUARIOS QUE HA ATENDIDO UN GESTOR
    public String[][] usuariosAtendidos(String a, String b){
        GestorDeUsuarios x = identificarGestor(a,b);
        return x.usuariosAtendidosGestor();
    }
    
    // METODO PARA OBTENER TODOS LOS USUARIOS ATENDIDOS POR UN GESTOR ORDENADOS A PARTIR DE UN CRITERIO
    public String[][] usuariosOrdenados(String a, String b, String c){
        GestorDeUsuarios x = identificarGestor(a,b);
        return x.usuariosOrdenadosGestor(c);
    }
    
    // METODO PARA IDENTIFICAR LA POSICION DE LOS GESTORES DISPONIBLES EN CADA DISTRITO EN EL ARRAY
    public int[] posicionesGestorDistrito(String a){
        int n=0;
        int[] x = new int[0];
        for(int i=0;i<gestores.size();i++){
            if(gestores.get(i).getDistrito().equals(a)){
                int[] y = new int[x.length+1];
                for(int j=0;j<x.length;j++){
                    y[j]=x[j];
                }
                x=y;
                x[n] = i;
                n++;
            }
        }
        return x;
    }

    // METODO PARA FACILITAR EL AGREGADO DE PRODUCTOS RECICLABLES A CADA UNO DE LOS CONTENEDORES
    public String[] agregar_2(Contenedores y, Usuario x, String b, int a,int n,int m,int s){ 
        int p =posicionesGestorDistrito(y.getUbicacion())[a-1];
        Producto_Reciclable r;
        Producto_Reciclable t;
        Producto_Reciclable q;
        r = new EnvaseDeVidrio(n);
        t = new EnvaseDePlastico(m);
        q = new EnvaseDeLata(s);
        
        String c="",d="",e="";
        c = y.agregarProducto(0,r);
        x.setSaldo(x.getSaldo()+r.calcularValor());

        
        d = y.agregarProducto(1, t);
        x.setSaldo(x.getSaldo()+t.calcularValor());

        q = new EnvaseDeLata(s);
        e = y.agregarProducto(2, q);
        x.setSaldo(x.getSaldo()+q.calcularValor());

        guardarUsuariosEnArchivo();
        llenados++;
        guardarLlenadosEnArchivo();
        guardarContenedores_1_EnArchivo();
        guardarContenedores_2_EnArchivo();
        guardarContenedores_3_EnArchivo();
        guardarContenedores_4_EnArchivo();
        guardarContenedores_1_EnTabla();
        guardarContenedores_2_EnTabla();
        guardarContenedores_3_EnTabla();
        guardarContenedores_4_EnTabla();
        gestores.get(p).agregarUsuarioAtendido(x);
        gestores.get(p).setBonificacion(gestores.get(p).getBonificacion()+20);
        gestores.get(p).setSalarioActual(gestores.get(p).getSalarioActual()+gestores.get(p).getBonificacion());
        guardarGestoresEnArchivo();
        
        Producto_Reciclable[] detellasEnvases = new Producto_Reciclable[]{r,t,q};
        GestorDeUsuarios gestor = gestores.get(p);
        GenerarBoleta boleta = new GenerarBoleta();
        boleta.crearBoleta(y, x, gestor, llenados, detellasEnvases);
        return new String[]{c, d, e};
    }

    // METODO PARA MOSTRAR EL ESTADO DE LOS CONTENEDORES
    public Object[][] estadoContenedores(String distrito){
        Contenedores contenedores = identificarContenedores(distrito);
        Contenedor c1 = contenedores.getContenedor(0);
        Contenedor c2 = contenedores.getContenedor(1);
        Contenedor c3 = contenedores.getContenedor(2);
        EnvaseDeVidrio e1 = new EnvaseDeVidrio(0);
        EnvaseDePlastico e2 = new EnvaseDePlastico(0);
        EnvaseDeLata e3 = new EnvaseDeLata(0);
        double precioUnitario_1 = e1.establecerValor();
        double precioUnitario_2 = e2.establecerValor();
        double precioUnitario_3 = e3.establecerValor();
        int capacidadActual_1 = c1.getCapacidadActual();
        int capacidadActual_2 = c2.getCapacidadActual();
        int capacidadActual_3 = c3.getCapacidadActual();
        int espDisponible_1 = c1.getCapacidadMaxima() - c1.getCapacidadActual();
        int espDisponible_2 = c2.getCapacidadMaxima() - c2.getCapacidadActual();
        int espDisponible_3 = c3.getCapacidadMaxima() - c3.getCapacidadActual();
        
        Object[][] estado = new Object[3][3];

        estado[0][0] = precioUnitario_1;    
        estado[0][1] = precioUnitario_2;
        estado[0][2] = precioUnitario_3;

        estado[1][0] = capacidadActual_1;  
        estado[1][1] = capacidadActual_2;
        estado[1][2] = capacidadActual_3;

        estado[2][0] = espDisponible_1;    
        estado[2][1] = espDisponible_2;
        estado[2][2] = espDisponible_3;

        return estado;
    }
    
    // METODO PARA IDENTIFICAR EL CONTENEDOR DEL DISTRITO
    public Contenedores identificarContenedores(String distrito){
        if (Contenedores_1.getUbicacion().equals(distrito)) {
            return Contenedores_1;
        } else if (Contenedores_2.getUbicacion().equals(distrito)) {
            return Contenedores_2;
        } else if (Contenedores_3.getUbicacion().equals(distrito)) {
            return Contenedores_3;
        } else if (Contenedores_4.getUbicacion().equals(distrito)) {
            return Contenedores_4;
        }
        return null;
    }
    
    // METODO PARA ACTUALIZAR DATOS PERSONALES DEL USUARIO
    public void actualizar_1(String p,String q,int opc,String a){
        Usuario Usuario = identificarUsuario(p,q);
        switch(opc){
            case 1: Usuario.setNombres(a);
                    break;
            case 2: Usuario.setApellidos(a);
                    break;
            case 3: Usuario.setEmail(a);
                    break;
            case 4: Usuario.setDistrito(a);
                    break;
            case 5: System.out.println("Saliendo de la actualización de datos..."); break;
            default: System.out.println("Opción no valida.");
        }
        guardarUsuariosEnArchivo();
        actualizarTablaDeUsuarios();
    }

    // METODO PARA ACTUALIZAR EL ID O CONTRASEÑA DEL USUARIO
    public void actualizar_2(String p,String q,int opc,String a){
        Usuario Usuario = identificarUsuario(p,q);
        switch(opc){
            case 1: Usuario.setID(a);
                    break;
            case 2: Usuario.setContraseña(a);
                    break;
            default: System.out.println("Opción no valida.");
        }
        guardarUsuariosEnArchivo();
        actualizarCredencialesDeUsuarios();
    }
    
    // METODO PARA ACTUALIZAR EL DISTRITO DEL GESTOR
    public void actualizar_3(String p,String q,String a){
        GestorDeUsuarios x = identificarGestor(p,q);
        x.setDistrito(a);
        guardarGestoresEnArchivo();
        actualizarTablaDeGestores();
    }

    // MÉTODO PARA ELIMINAR LA CUENTA DEL USUARIO
    public void eliminar(String a, String b) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getID().equals(a) && usuarios.get(i).getContraseña().equals(b)) {
                usuarios.remove(i); 
                break;
            }
        }
        guardarUsuariosEnArchivo();
        actualizarTablaDeUsuarios();
        actualizarCredencialesDeUsuarios();
    }
    
    // METODO PARA COMPRAR PRODUCTOS EN LA TIENDA
    public void comprarProductos(String a, String b, int bolsa, int vaso, int libreta, int lampara, int maceta, int cargador) {
        // Encontrar los productos en la tienda
        Producto_Tienda bolsaProducto = encontrarProducto("BolsaReutilizable");
        Producto_Tienda vasoProducto = encontrarProducto("VasoTermico");
        Producto_Tienda libretaProducto = encontrarProducto("EcoLibreta");
        Producto_Tienda lamparaProducto = encontrarProducto("LamparaSolar");
        Producto_Tienda macetaProducto = encontrarProducto("MacetaBiodegradable");
        Producto_Tienda cargadorProducto = encontrarProducto("CargadorSolar");

        // Verificar disponibilidad antes de proceder con la compra
        if (!bolsaProducto.disminuirCantidad(bolsa)) {
            JOptionPane.showMessageDialog(null, "No hay suficientes Bolsas Reutilizables en stock.\nREABASTECIENDO STOCK");
            bolsaProducto.setCantidad(10);
            return;
        }

        if (!vasoProducto.disminuirCantidad(vaso)) {
            JOptionPane.showMessageDialog(null, "No hay suficientes Vasos Térmicos en stock.\nREABASTECIENDO STOCK");
            vasoProducto.setCantidad(50);
            return;
        }

        if (!libretaProducto.disminuirCantidad(libreta)) {
            JOptionPane.showMessageDialog(null, "No hay suficientes Eco Libretas en stock.\nREABASTECIENDO STOCK");
            libretaProducto.setCantidad(75);
            return;
        }

        if (!lamparaProducto.disminuirCantidad(lampara)) {
            JOptionPane.showMessageDialog(null, "No hay suficientes Lámparas Solares en stock.\nREABASTECIENDO STOCK");
            lamparaProducto.setCantidad(30);
            return;
        }

        if (!macetaProducto.disminuirCantidad(maceta)) {
            JOptionPane.showMessageDialog(null, "No hay suficientes Macetas Biodegradables en stock.\nREABASTECIENDO STOCK");
            macetaProducto.setCantidad(120);
            return;
        }

        if (!cargadorProducto.disminuirCantidad(cargador)) {
            JOptionPane.showMessageDialog(null, "No hay suficientes Cargadores Solares en stock.\nREABASTECIENDO STOCK");
            cargadorProducto.setCantidad(40);
            return;
        }

        // Si todo está disponible, proceder con el cálculo del total de la compra
        double total = (bolsa * bolsaProducto.getValor()) + (vaso * vasoProducto.getValor()) + 
                        (libreta * libretaProducto.getValor()) + (lampara * lamparaProducto.getValor()) + 
                        (maceta * macetaProducto.getValor()) + (cargador * cargadorProducto.getValor());

        // Verificar si el saldo del usuario es suficiente
        Usuario usuario = identificarUsuario(a, b);  // Asumimos que tienes este método para identificar al usuario
        if (usuario.getSaldo() >= total) {
            // Reducir el saldo del usuario
            usuario.setSaldo(usuario.getSaldo() - total);

            JOptionPane.showMessageDialog(null, "Compra realizada con éxito. Total: " + total);
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para completar la compra.");
        }
        guardarProductosEnArchivo();
        guardarUsuariosEnArchivo();
        actualizarTablaDeUsuarios();
    }
    
    public Producto_Tienda encontrarProducto(String nombre) {
        for (Producto_Tienda producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }
}