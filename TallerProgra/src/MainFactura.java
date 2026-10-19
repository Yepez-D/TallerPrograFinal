import javax.swing.JOptionPane;

public class MainFactura {
    private static final int CANTIDAD_MAXIMA = 100000;
    private static final double PRECIO_MAXIMO = 1000000.0;

    public static void main() {
        String numeroPieza = leerCodigo("Ingrese el número de pieza (ej. A-101):");
        if (numeroPieza == null) {
            return;
        }
        String descripcion = leerTexto("Ingrese la descripción de la pieza:");
        if (descripcion == null) {
            return;
        }
        Integer cantidad = leerEntero("Ingrese la cantidad de artículos:", 1, CANTIDAD_MAXIMA);
        if (cantidad == null) {
            return;
        }
        Double precio = leerDecimal("Ingrese el precio por artículo:", 0.01, PRECIO_MAXIMO);
        if (precio == null) {
            return;
        }

        Factura factura = new Factura(numeroPieza, descripcion, cantidad, precio);
        mostrarFactura("Factura ingresada", factura);

        Integer nuevaCantidad = leerEntero("Ingrese una nueva cantidad:", 1, CANTIDAD_MAXIMA);
        if (nuevaCantidad == null) {
            return;
        }
        Double nuevoPrecio = leerDecimal("Ingrese un nuevo precio:", 0.01, PRECIO_MAXIMO);
        if (nuevoPrecio == null) {
            return;
        }

        factura.setCantidad(nuevaCantidad);
        factura.setPrecio(nuevoPrecio);
        mostrarFactura("Factura actualizada", factura);
    }

    private static void mostrarFactura(String titulo, Factura factura) {
        String mensaje = "Número de pieza: " + factura.getNumeroPieza()
                + "\nDescripción: " + factura.getDescripcion()
                + "\nCantidad: " + factura.getCantidad()
                + "\nPrecio por artículo: $" + String.format("%.2f", factura.getPrecio())
                + "\nMonto de la factura: $" + String.format("%.2f", factura.obtenerMontoFactura());
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Dato inválido", JOptionPane.ERROR_MESSAGE);
    }

    private static String leerCodigo(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje, "Factura", JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            texto = texto.trim();
            if (texto.isEmpty()) {
                mostrarError("El campo no puede estar vacío.");
            } else if (texto.length() > 20) {
                mostrarError("No puede tener más de 20 caracteres.");
            } else if (!texto.matches("[A-Za-z0-9]+([\\-_][A-Za-z0-9]+)*")) {
                mostrarError("Use solo letras, números y guiones (ej. A-101).");
            } else {
                return texto;
            }
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje, "Factura", JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            texto = texto.trim();
            if (texto.isEmpty()) {
                mostrarError("El campo no puede estar vacío.");
            } else if (texto.length() < 3 || texto.length() > 60) {
                mostrarError("La descripción debe tener entre 3 y 60 caracteres.");
            } else if (texto.matches("[\\d\\s\\p{Punct}]+")) {
                mostrarError("La descripción debe contener letras, no solo números o símbolos.");
            } else {
                return texto;
            }
        }
    }

    private static Integer leerEntero(String mensaje, int min, int max) {
        String indicacion = mensaje + "\n(entre " + min + " y " + max + ")";
        while (true) {
            String texto = JOptionPane.showInputDialog(null, indicacion, "Factura", JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            texto = texto.trim();
            if (texto.isEmpty()) {
                mostrarError("El campo no puede estar vacío.");
            } else if (!texto.matches("-?\\d{1,9}")) {
                mostrarError("Ingrese solo un número entero (sin letras, espacios ni decimales).");
            } else {
                int valor = Integer.parseInt(texto);
                if (valor < min || valor > max) {
                    mostrarError("El valor " + valor + " está fuera de rango. Debe estar entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }
            }
        }
    }

    private static Double leerDecimal(String mensaje, double min, double max) {
        String indicacion = mensaje + "\n(entre " + min + " y " + max + ")";
        while (true) {
            String texto = JOptionPane.showInputDialog(null, indicacion, "Factura", JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            texto = texto.trim();
            if (texto.isEmpty()) {
                mostrarError("El campo no puede estar vacío.");
            } else if (!texto.matches("-?\\d{1,9}([.,]\\d{1,4})?")) {
                mostrarError("Ingrese solo un número (ej. 12.50), sin letras ni símbolos.");
            } else {
                double valor = Double.parseDouble(texto.replace(',', '.'));
                if (valor < min || valor > max) {
                    mostrarError("El valor " + valor + " está fuera de rango. Debe estar entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }
            }
        }
    }
}
