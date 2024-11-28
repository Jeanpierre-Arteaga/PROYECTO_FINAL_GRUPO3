package PROYECTO_GRUPO_3;

import java.io.Serializable;
public abstract class Contenedor implements Serializable{
    // ATRIBUTOS
    private static final long serialVersionUID = 1L;
    protected String tipo;
    protected int capacidadActual;
    protected int capacidadMaxima;
    protected Producto_Reciclable[] productos;

    // CONSTRUCTOR
    public Contenedor(String tipo, int capacidadMaxima) {
        this.tipo = tipo; 
        this.capacidadActual = 0;
        this.capacidadMaxima = capacidadMaxima;
        this.productos = new Producto_Reciclable[capacidadMaxima];
    }

    // METODOS SETTERS
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setCapacidadActual(int capacidadActual){
        this.capacidadActual = capacidadActual;
    }
    public void setCapacidadMaxima(int capacidadMaxima){
        this.capacidadMaxima = capacidadMaxima;
    }

    // METODOS GETTERS
    public String getTipo(){
        return tipo;
    }
    public int getCapacidadActual(){
        return capacidadActual;
    }
    public int getCapacidadMaxima(){
        return capacidadMaxima;
    }
    
    // METODO PARA AGREGAR UN PRODUCTO AL CONTENEDOR
    public String agregar(Producto_Reciclable producto) {
        if (!producto.getTipo().equalsIgnoreCase(tipo)) {
            return "Este contenedor solo acepta productos de tipo: " + tipo;
        }
    
        int espacioDisponible = capacidadMaxima - capacidadActual;  
    
        if (espacioDisponible >= producto.getCantidad()) {
            productos[capacidadActual] = producto;
            capacidadActual += producto.getCantidad();
            return "Los envases se agregaron correctamente.";
        } else if (espacioDisponible > 0) {
            // Crear un nuevo producto con la cantidad que puede ser agregada
            Producto_Reciclable productoParcial = crearProductoParcial(producto, espacioDisponible);;
            productos[capacidadActual] = productoParcial;
            capacidadActual += espacioDisponible; // Actualizamos la capacidad ocupada
            return "Espacio insuficiente. Solo se agregaron " + espacioDisponible +
                    " productos de " + producto.getCantidad() + ".";
        } 
        return "";
    }

    // METODO PARA CREAR EL PRODUCTO PARCIAL QUE AYUDARA A AGREGAR PRODUCTOS AL CONTENEDOR
    private Producto_Reciclable crearProductoParcial(Producto_Reciclable producto, int cantidad) {
        switch (producto.getTipo()) {
            case "envase de vidrio":
                return new EnvaseDeVidrio(cantidad);
            case "envase de plastico":
                return new EnvaseDePlastico(cantidad);
            case "envase de lata":
                return new EnvaseDeLata(cantidad);
            default:
                return null; 
        }
    }

    // METODO PARA MOSTRAR LOS PRODUCTOS RECICLABLES QUE HAY EN EL CONTENEDOR
    public String mostrarContenedor(){
        // Utilizamos una instancia de la clase StringBuilder
        // Para facilitar el agregado de informacion a la tabla de salida
        StringBuilder tabla = new StringBuilder();
        tabla.append(String.format("CONTENEDOR DE %s DE CAPACIDAD %d/%d%n", tipo.toUpperCase(), capacidadActual, capacidadMaxima));
        tabla.append("+----------------------+---------------+----------------+---------------+\n");
        tabla.append("|         Tipo         |     Valor     |    Cantidad    |     Total     |\n");
        tabla.append("+----------------------+---------------+----------------+---------------+\n");
        for(int i=0;i<capacidadActual;i++){
            if (productos[i] != null) {
                // Filas de la tabla con los datos centrados
                tabla.append(String.format("| %s | %s | %s | %s |\n", 
                    FormatoDelTexto.centrarTexto(productos[i].getTipo(), 20), 
                    FormatoDelTexto.centrarTexto(String.format("%.2f",productos[i].establecerValor()), 13),
                    FormatoDelTexto.centrarTexto(String.valueOf(productos[i].getCantidad()), 14),
                    FormatoDelTexto.centrarTexto(String.format("%.2f",productos[i].calcularValor()), 13)));
                tabla.append("+----------------------+---------------+----------------+---------------+\n");
            }
        }
        return tabla.toString();
    }
}