package pe.edu.utp.seguridad;

import pe.edu.utp.objetos.MuseoVisita;

public class Validadores {
    // Método para validar el campo de fechaCorte (AAAAMMDD)
    public static boolean validarFechaCorte(String fechaCorte) {
        // Verificar que tenga exactamente 8 caracteres y que sean numéricos
        return fechaCorte.matches("\\d{8}");
    }

    // Método para validar el campo de año (AAAA)
    public static boolean validarAnio(int anio) {
        // Verificar que esté dentro de un rango válido para el proyecto
        return anio >= 1900 && anio <= 2100;
    }

    public static boolean validarTipo(String tipo) {
        // Verificar que la cadena no esté vacía y que contenga solo letras (mayúsculas y minúsculas)
        return !tipo.isEmpty() && tipo.matches("^[A-Za-z ]+$");
    }


    // Método para validar el campo de visitantes (0:9999999)
    public static boolean validarVisitantes(int cantidad) {
        // Verificar que esté dentro de un rango válido para el proyecto
        return cantidad >= 0 && cantidad <= 9999999;
    }
}
