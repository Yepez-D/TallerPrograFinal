import java.time.LocalDate;
import java.util.Scanner;

public class MainFrecuencias {

    public static void main() {
        Scanner entrada = new Scanner(System.in);

        String nombre = leerNombre(entrada, "Ingrese el primer nombre: ");
        String apellido = leerNombre(entrada, "Ingrese el apellido: ");
        int mes = leerEntero(entrada, "Ingrese el mes de nacimiento", 1, 12);
        int dia = leerEntero(entrada, "Ingrese el día de nacimiento", 1, FrecuenciasCardiacas.diasDelMes(mes, 2000));
        int anio = leerAnioNacimiento(entrada, mes, dia);

        FrecuenciasCardiacas persona = new FrecuenciasCardiacas(nombre, apellido, mes, dia, anio);

        System.out.println("\n========================================");
        System.out.println("   INFORMACIÓN DE LA PERSONA Y SU SALUD");
        System.out.println("========================================");
        System.out.println("Primer nombre: " + persona.getNombre());
        System.out.println("Apellido: " + persona.getApellido());
        System.out.println("Fecha de nacimiento (mes/día/año): " + persona.getMesNacimiento() + "/"
                + persona.getDiaNacimiento() + "/" + persona.getAnioNacimiento());
        System.out.println("Edad: " + persona.obtenerEdad() + " años");
        System.out.println("Frecuencia cardíaca máxima: " + persona.obtenerFrecuenciaMaxima() + " bpm");
        System.out.println("Rango de frecuencia cardíaca esperada: " + persona.obtenerFrecuenciaEsperada());
        System.out.println("========================================");
        System.out.println("Consulte a un médico antes de iniciar o modificar un programa de ejercicios.");

        entrada.close();
    }

    private static String leerNombre(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("  Error: El campo no puede estar vacío.");
            } else if (texto.length() < 2 || texto.length() > 40) {
                System.out.println("  Error: Debe tener entre 2 y 40 caracteres.");
            } else if (!texto.matches("\\p{L}+([ '\\-]\\p{L}+)*")) {
                System.out.println("  Error: Solo se permiten letras (sin números ni símbolos).");
            } else {
                return texto;
            }
        }
    }

    private static int leerEntero(Scanner entrada, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje + " [" + min + " a " + max + "]: ");
            String texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("  Error: El campo no puede estar vacío.");
            } else if (!texto.matches("-?\\d{1,9}")) {
                System.out.println("  Error: Ingrese solo un número entero (sin letras, espacios ni decimales).");
            } else {
                int valor = Integer.parseInt(texto);
                if (valor < min || valor > max) {
                    System.out.println("  Error: El valor " + valor + " está fuera de rango. Debe estar entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }
            }
        }
    }

    private static int leerAnioNacimiento(Scanner entrada, int mes, int dia) {
        int min = FrecuenciasCardiacas.ANIO_MINIMO_NACIMIENTO;
        int max = LocalDate.now().getYear();
        while (true) {
            System.out.print("Ingrese el año de nacimiento [" + min + " a " + max + "]: ");
            String texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("  Error: El campo no puede estar vacío.");
            } else if (!texto.matches("-?\\d{1,9}")) {
                System.out.println("  Error: Ingrese solo un número entero (sin letras, espacios ni decimales).");
            } else {
                int valor = Integer.parseInt(texto);
                String error = FrecuenciasCardiacas.errorFechaNacimiento(mes, dia, valor);
                if (error != null) {
                    System.out.println("  Error: " + error);
                } else {
                    return valor;
                }
            }
        }
    }
}
