package com.ejemplo.model;

/**
 * Representa un item dentro del carrito de compras,
 * asociando un producto con su cantidad seleccionada.
 */
public class CarritoItem {

    private Producto producto;
    private int cantidad;

    public CarritoItem(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Calcula el subtotal de este item
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}