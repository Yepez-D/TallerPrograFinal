import java.time.LocalDate;
import java.time.Period;

public class FrecuenciasCardiacas {
    public static final int ANIO_MINIMO_NACIMIENTO = 1900;

    private String nombre;
    private String apellido;
    private int mesNacimiento;
    private int diaNacimiento;
    private int anioNacimiento;

    public FrecuenciasCardiacas(String nombre, String apellido, int mesNacimiento, int diaNacimiento, int anioNacimiento) {
        setNombre(nombre);
        setApellido(apellido);
        if (errorFechaNacimiento(mesNacimiento, diaNacimiento, anioNacimiento) == null) {
            this.mesNacimiento = mesNacimiento;
            this.diaNacimiento = diaNacimiento;
            this.anioNacimiento = anioNacimiento;
        } else {
            this.mesNacimiento = 1;
            this.diaNacimiento = 1;
            this.anioNacimiento = 2000;
        }
    }

    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
    }

    public static int diasDelMes(int mes, int anio) {
        switch (mes) {
            case 2:
                return esBisiesto(anio) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    public static String errorFechaNacimiento(int mes, int dia, int anio) {
        if (mes < 1 || mes > 12) {
            return "El mes debe estar entre 1 y 12.";
        }
        if (anio < ANIO_MINIMO_NACIMIENTO) {
            return "El año de nacimiento no puede ser menor que " + ANIO_MINIMO_NACIMIENTO + ".";
        }
        if (anio > LocalDate.now().getYear()) {
            return "El año de nacimiento no puede ser posterior al año actual.";
        }
        int maximo = diasDelMes(mes, anio);
        if (dia < 1 || dia > maximo) {
            return "El mes " + mes + " del año " + anio + " solo tiene " + maximo + " días; el día " + dia + " no existe.";
        }
        if (LocalDate.of(anio, mes, dia).isAfter(LocalDate.now())) {
            return "La fecha de nacimiento no puede ser posterior a hoy.";
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            this.nombre = "SIN NOMBRE";
        } else {
            this.nombre = nombre.trim();
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            this.apellido = "SIN APELLIDO";
        } else {
            this.apellido = apellido.trim();
        }
    }

    public int getMesNacimiento() {
        return mesNacimiento;
    }

    public void setMesNacimiento(int mesNacimiento) {
        if (errorFechaNacimiento(mesNacimiento, this.diaNacimiento, this.anioNacimiento) == null) {
            this.mesNacimiento = mesNacimiento;
        }
    }

    public int getDiaNacimiento() {
        return diaNacimiento;
    }

    public void setDiaNacimiento(int diaNacimiento) {
        if (errorFechaNacimiento(this.mesNacimiento, diaNacimiento, this.anioNacimiento) == null) {
            this.diaNacimiento = diaNacimiento;
        }
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        if (errorFechaNacimiento(this.mesNacimiento, this.diaNacimiento, anioNacimiento) == null) {
            this.anioNacimiento = anioNacimiento;
        }
    }

    public int obtenerEdad() {
        LocalDate fechaNacimiento = LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento);
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public int obtenerFrecuenciaMaxima() {
        return 220 - obtenerEdad();
    }

    public double obtenerFrecuenciaEsperadaMinima() {
        return obtenerFrecuenciaMaxima() * 0.50;
    }

    public double obtenerFrecuenciaEsperadaMaxima() {
        return obtenerFrecuenciaMaxima() * 0.85;
    }

    public String obtenerFrecuenciaEsperada() {
        return String.format("%.0f - %.0f bpm", obtenerFrecuenciaEsperadaMinima(), obtenerFrecuenciaEsperadaMaxima());
    }
}
