/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROYECTO_GRUPO_3;

import java.io.Serializable;

public class Producto_Tienda implements Serializable{
    // ATRIBUTOS
    private String nombre;
    private int cantidad;
    private double valor;
    
    public Producto_Tienda(String nombre,int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
        establecerValor();
    }
    // METODOS GETTERS
    public String getNombre(){
        return nombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public double getValor() {
        return valor;
    }
    
    // METODOS SETTERS
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    // METODO PARA ESTABLECER EL VALOR DE CADA PRODUCTO
    public void establecerValor(){
        switch(nombre){
            case "BolsaReutilizable": valor = 10; break;
            case "VasoTermico": valor = 20; break;
            case "EcoLibreta": valor = 15; break;
            case "LamparaSolar": valor = 40; break;
            case "MacetaBiodegradable": valor = 8; break;
            case "CargadorSolar": valor = 25; break;
            default: valor = 0; break;
        }
    }
    
    public boolean disminuirCantidad(int n) {
        if(cantidad - n >= 0) {
            cantidad -= n;
            return true;
        }
        return false;
    }
    
    // METODO PARA CALCULAR EL VALOR TOTAL DE LOS PRODUCTOS
    public double calcularValor(){
        return cantidad * valor;
    }
}