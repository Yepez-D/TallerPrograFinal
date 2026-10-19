public class Fecha {
    private int mes;
    private int dia;
    private int anio;

    public Fecha(int mes, int dia, int anio) {
        String error = errorFecha(mes, dia, anio);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        this.mes = mes;
        this.dia = dia;
        this.anio = anio;
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

    public static String errorFecha(int mes, int dia, int anio) {
        if (mes < 1 || mes > 12) {
            return "El mes debe estar entre 1 y 12.";
        }
        if (anio < 1 || anio > 9999) {
            return "El año debe estar entre 1 y 9999.";
        }
        int maximo = diasDelMes(mes, anio);
        if (dia < 1 || dia > maximo) {
            return "El mes " + mes + " del año " + anio + " solo tiene " + maximo + " días; el día " + dia + " no existe.";
        }
        return null;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        String error = errorFecha(mes, this.dia, this.anio);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        String error = errorFecha(this.mes, dia, this.anio);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        this.dia = dia;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        String error = errorFecha(this.mes, this.dia, anio);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        this.anio = anio;
    }

    public void mostrarFecha() {
        System.out.println(mes + "/" + dia + "/" + anio);
    }

    public String obtenerFechaTexto() {
        return mes + "/" + dia + "/" + anio;
    }
}
