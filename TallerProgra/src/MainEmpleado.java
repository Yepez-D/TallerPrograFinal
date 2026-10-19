import java.util.Scanner;

public class MainEmpleado {
    private static final double SALARIO_MAXIMO = 1000000.0;

    public static void main() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("--- DATOS DEL EMPLEADO 1 ---");
        Empleado empleado1 = leerEmpleado(entrada);
        System.out.println("\n--- DATOS DEL EMPLEADO 2 ---");
        Empleado empleado2 = leerEmpleado(entrada);

        System.out.println("\n--- SALARIOS ANUALES INICIALES ---");
        mostrarSalarioAnual(empleado1);
        mostrarSalarioAnual(empleado2);

        empleado1.setSalario(empleado1.getSalario() * 1.10);
        empleado2.setSalario(empleado2.getSalario() * 1.10);

        System.out.println("\n--- SALARIOS ANUALES CON AUMENTO DEL 10% ---");
        mostrarSalarioAnual(empleado1);
        mostrarSalarioAnual(empleado2);

        entrada.close();
    }

    private static Empleado leerEmpleado(Scanner entrada) {
        String nombre = leerNombre(entrada, "Primer nombre: ");
        String apellido = leerNombre(entrada, "Apellido paterno: ");
        double salario = leerDecimal(entrada, "Salario mensual", 0.01, SALARIO_MAXIMO);
        return new Empleado(nombre, apellido, salario);
    }

    private static void mostrarSalarioAnual(Empleado empleado) {
        System.out.printf("%s %s: salario mensual $%.2f, salario anual $%.2f%n",
                empleado.getNombre(), empleado.getApellido(),
                empleado.getSalario(), empleado.obtenerSalarioAnual());
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

    private static double leerDecimal(Scanner entrada, String mensaje, double min, double max) {
        while (true) {
            System.out.print(mensaje + " [" + min + " a " + max + "]: ");
            String texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("  Error: El campo no puede estar vacío.");
            } else if (!texto.matches("-?\\d{1,9}([.,]\\d{1,4})?")) {
                System.out.println("  Error: Ingrese solo un número (ej. 1200.50), sin letras ni símbolos.");
            } else {
                double valor = Double.parseDouble(texto.replace(',', '.'));
                if (valor < min || valor > max) {
                    System.out.println("  Error: El valor " + valor + " está fuera de rango. Debe estar entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }
            }
        }
    }
}
