public class Factura {
    private String numeroPieza;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Factura(String numeroPieza, String descripcion, int cantidad, double precio) {
        setNumeroPieza(numeroPieza);
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setPrecio(precio);
    }

    public String getNumeroPieza() {
        return numeroPieza;
    }

    public void setNumeroPieza(String numeroPieza) {
        if (numeroPieza == null || numeroPieza.isBlank()) {
            this.numeroPieza = "SIN NUMERO";
        } else {
            this.numeroPieza = numeroPieza.trim();
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            this.descripcion = "SIN DESCRIPCION";
        } else {
            this.descripcion = descripcion.trim();
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad = cantidad;
        } else {
            this.cantidad = 0;
        }
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0.0) {
            this.precio = precio;
        } else {
            this.precio = 0.0;
        }
    }

    public double obtenerMontoFactura() {
        return cantidad * precio;
    }
}
