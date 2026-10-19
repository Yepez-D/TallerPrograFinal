import javax.swing.JOptionPane;
import java.time.LocalDate;

public class MainPerfilMedico {

    public static void main() {
        String nombre = leerNombre("Ingrese el primer nombre:");
        if (nombre == null) {
            return;
        }
        String apellido = leerNombre("Ingrese el apellido:");
        if (apellido == null) {
            return;
        }
        String sexo = leerSexo();
        if (sexo == null) {
            return;
        }
        Integer mes = leerEntero("Ingrese el mes de nacimiento:", 1, 12);
        if (mes == null) {
            return;
        }
        Integer dia = leerEntero("Ingrese el día de nacimiento:", 1, PerfilMedico.diasDelMes(mes, 2000));
        if (dia == null) {
            return;
        }
        Integer anio = leerAnioNacimiento(mes, dia);
        if (anio == null) {
            return;
        }
        Double altura = leerDecimal("Ingrese la altura en centímetros:", 30.0, 275.0);
        if (altura == null) {
            return;
        }
        Double peso = leerDecimal("Ingrese el peso en kilogramos:", 2.0, 500.0);
        if (peso == null) {
            return;
        }

        PerfilMedico perfil = new PerfilMedico(nombre, apellido, sexo, dia, mes, anio, altura, peso);

        String datos = "Primer nombre: " + perfil.getNombre()
                + "\nApellido: " + perfil.getApellido()
                + "\nSexo: " + perfil.getSexo()
                + "\nFecha de nacimiento (día/mes/año): " + perfil.getDiaNacimiento() + "/"
                + perfil.getMesNacimiento() + "/" + perfil.getAnioNacimiento()
                + "\nAltura: " + String.format("%.1f", perfil.getAltura()) + " cm"
                + "\nPeso: " + String.format("%.1f", perfil.getPeso()) + " kg";
        JOptionPane.showMessageDialog(null, datos, "Datos del perfil médico", JOptionPane.INFORMATION_MESSAGE);

        String resultados = "Edad: " + perfil.obtenerEdad() + " años"
                + "\nBMI: " + String.format("%.2f", perfil.calcularBMI()) + " (" + perfil.obtenerCategoriaBMI() + ")"
                + "\nFrecuencia cardíaca máxima: " + perfil.obtenerFrecuenciaMaxima() + " bpm"
                + "\nRango de frecuencia cardíaca esperada: " + perfil.obtenerFrecuenciaEsperada()
                + "\n\nVALORES DE BMI"
                + "\nBajo peso: menos de 18.5"
                + "\nNormal: entre 18.5 y 24.9"
                + "\nSobrepeso: entre 25 y 29.9"
                + "\nObeso: 30 o más"
                + "\n\nConsulte a un médico antes de iniciar o modificar un programa de ejercicios.";
        JOptionPane.showMessageDialog(null, resultados, "Resultados de salud", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Dato inválido", JOptionPane.ERROR_MESSAGE);
    }

    private static String leerNombre(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje, "Perfil médico", JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            texto = texto.trim();
            if (texto.isEmpty()) {
                mostrarError("El campo no puede estar vacío.");
            } else if (texto.length() < 2 || texto.length() > 40) {
                mostrarError("Debe tener entre 2 y 40 caracteres.");
            } else if (!texto.matches("\\p{L}+([ '\\-]\\p{L}+)*")) {
                mostrarError("Solo se permiten letras (sin números ni símbolos).");
            } else {
                return texto;
            }
        }
    }

    private static String leerSexo() {
        Object[] opciones = {"Masculino", "Femenino"};
        int eleccion = JOptionPane.showOptionDialog(null, "Seleccione el sexo:", "Perfil médico",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (eleccion < 0) {
            return null;
        }
        return (String) opciones[eleccion];
    }

    private static Integer leerEntero(String mensaje, int min, int max) {
        String indicacion = mensaje + "\n(entre " + min + " y " + max + ")";
        while (true) {
            String texto = JOptionPane.showInputDialog(null, indicacion, "Perfil médico", JOptionPane.QUESTION_MESSAGE);
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

    private static Integer leerAnioNacimiento(int mes, int dia) {
        String indicacion = "Ingrese el año de nacimiento:\n(entre " + PerfilMedico.ANIO_MINIMO_NACIMIENTO
                + " y " + LocalDate.now().getYear() + ")";
        while (true) {
            String texto = JOptionPane.showInputDialog(null, indicacion, "Perfil médico", JOptionPane.QUESTION_MESSAGE);
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
                String error = PerfilMedico.errorFechaNacimiento(mes, dia, valor);
                if (error != null) {
                    mostrarError(error);
                } else {
                    return valor;
                }
            }
        }
    }

    private static Double leerDecimal(String mensaje, double min, double max) {
        String indicacion = mensaje + "\n(entre " + min + " y " + max + ")";
        while (true) {
            String texto = JOptionPane.showInputDialog(null, indicacion, "Perfil médico", JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return null;
            }
            texto = texto.trim();
            if (texto.isEmpty()) {
                mostrarError("El campo no puede estar vacío.");
            } else if (!texto.matches("-?\\d{1,9}([.,]\\d{1,4})?")) {
                mostrarError("Ingrese solo un número (ej. 65.5), sin letras ni símbolos.");
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
