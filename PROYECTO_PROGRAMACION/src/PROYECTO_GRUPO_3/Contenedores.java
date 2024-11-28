package PROYECTO_GRUPO_3;
import java.io.Serializable;
public class Contenedores implements Serializable{
    // ATRIBUTOS
    private static final long serialVersionUID = 1L;
    private Contenedor[] contenedores;
    private String idContenedor;
    private String ubicacion;
    private EncargadoDeVaciado encargado;
    
    // CONSTRUCTOR
    public Contenedores(String idContenedor,String ubicacion,EncargadoDeVaciado encargado){
        this.idContenedor = idContenedor;
        this.ubicacion = ubicacion;
        this.encargado = encargado;
        contenedores = new Contenedor[3]; 
        contenedores[0] = new ContenedorDeVidrio(500);
        contenedores[1] = new ContenedorDePlastico(500);
        contenedores[2] = new ContenedorDeLata(500);
    }

    // METODOS SETTERS Y GETTERS
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
    public String getUbicacion(){
        return ubicacion;
    }
    
    // METODO PARA OBTENER UNO DE LOS TRES CONTENEDORES
    public Contenedor getContenedor(int i) {
        if (i >= 0 && i < contenedores.length) {
            return contenedores[i];
        }
        return null; 
    }
    
    // METODO PARA AGREGAR PRODUCTOS RECICLABLES EN UN CONTENEDOR ESPECIFICO
    public String agregarProducto(int i, Producto_Reciclable producto) {
        Contenedor contenedor = getContenedor(i);
        if (contenedor == null) {
            return "Contenedor no encontrado.";
        }
        // Intentamos agregar el producto
        String a = contenedor.agregar(producto);
        String b = "";
        // Si el contenedor está lleno después de agregar el producto, el encargado lo vacía
        if (contenedor.getCapacidadActual() >= contenedor.getCapacidadMaxima()) {
            b = encargado.vaciarContenedor(contenedor);
        }
        String c = a +"\n"+b;
        return c;
    }

    // METODO PARA MOSTRAR CADA UNO DE LOS CONTENEDORES
    public String mostrarContenedores() {
        StringBuilder salida = new StringBuilder();
        salida.append("CONTENEDOR ").append(idContenedor).append(" UBICADO EN ").append(ubicacion.toUpperCase()).append("\n");
        for (int i = 0; i < 3; i++) {
            salida.append(contenedores[i].mostrarContenedor()); // Método nuevo en Contenedor
        }
        return salida.toString();
    }
}