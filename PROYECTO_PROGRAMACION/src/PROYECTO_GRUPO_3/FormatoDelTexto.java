package PROYECTO_GRUPO_3;

public class FormatoDelTexto {
    // METODO PARA CENTRAR EL TEXTO Y QUE AYUDARA A MEJORAR
    // LA PRESENTACION Y FORMATO DE LAS TABLAS
    public static String centrarTexto(String texto, int ancho) {
        if (texto.length() >= ancho) {
            return texto.substring(0, ancho);
        }
        int espaciosTotal = ancho - texto.length();
        int izquierda = espaciosTotal / 2;
        int derecha = espaciosTotal - izquierda;
        return " ".repeat(izquierda) + texto + " ".repeat(derecha);
    }
}