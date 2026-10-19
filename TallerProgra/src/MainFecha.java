import javax.swing.JOptionPane;

public class MainFecha {

    public static void main() {
        int[] datos = leerFecha("");
        if (datos == null) {
            return;
        }

        Fecha fecha = new Fecha(datos[0], datos[1], datos[2]);
        System.out.print("La fecha ingresada es: ");
        fecha.mostrarFecha();
        JOptionPane.showMessageDialog(null, "La fecha ingresada es: " + fecha.obtenerFechaTexto(),
                "Fecha", JOptionPane.INFORMATION_MESSAGE);

        int[] nuevos = leerFecha("nueva ");
        if (nuevos == null) {
            return;
        }

        fecha.setDia(1);
        fecha.setAnio(nuevos[2]);
        fecha.setMes(nuevos[0]);
        fecha.setDia(nuevos[1]);

        System.out.print("La fecha actualizada es: ");
        fecha.mostrarFecha();
        JOptionPane.showMessageDialog(null,
                "Mes: " + fecha.getMes() + "\nDía: " + fecha.getDia() + "\nAño: " + fecha.getAnio()
                        + "\n\nLa fecha actualizada es: " + fecha.obtenerFechaTexto(),
                "Fecha actualizada", JOptionPane.INFORMATION_MESSAGE);
    }

    private static int[] leerFecha(String adjetivo) {
        Integer mes = leerEntero("Ingrese el " + adjetivo + "mes:", 1, 12);
        if (mes == null) {
            return null;
        }
        Integer dia = leerEntero("Ingrese el " + adjetivo + "día:", 1, Fecha.diasDelMes(mes, 2000));
        if (dia == null) {
            return null;
        }
        Integer anio = leerAnio("Ingrese el " + adjetivo + "año:", mes, dia);
        if (anio == null) {
            return null;
        }
        return new int[]{mes, dia, anio};
    }

    private static Integer leerEntero(String mensaje, int min, int max) {
        String indicacion = mensaje + "\n(entre " + min + " y " + max + ")";
        while (true) {
            String texto = JOptionPane.showInputDialog(null, indicacion, "Fecha", JOptionPane.QUESTION_MESSAGE);
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

    private static Integer leerAnio(String mensaje, int mes, int dia) {
        String indicacion = mensaje + "\n(entre 1 y 9999)";
        while (true) {
            String texto = JOptionPane.showInputDialog(null, indicacion, "Fecha", JOptionPane.QUESTION_MESSAGE);
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
                String error = Fecha.errorFecha(mes, dia, valor);
                if (error != null) {
                    mostrarError(error);
                } else {
                    return valor;
                }
            }
        }
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Dato inválido", JOptionPane.ERROR_MESSAGE);
    }
}
