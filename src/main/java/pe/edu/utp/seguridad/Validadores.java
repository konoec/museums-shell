package utp.edu.pe.seguridad;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validadores {
    // Validador para el ingreso de año válido (1960 - 2023)
    public static boolean esAnioValido(int year) throws Exception {
        if (year < 1960 || year > 2023) {
            String msg = "Error: El año debe estar entre 1960 y 2023.";
            throw new Exception(msg);
        }
        return true;
    }

    // Validador para el ingreso de magnitudes válidas (positivas y rango coherente)
    public static boolean sonMagnitudesValidas(double minMagnitude, double maxMagnitude) throws Exception {
        if (minMagnitude < 0 || maxMagnitude < 0 || minMagnitude > maxMagnitude) {
            String msg = "Error: Las magnitudes deben ser positivas y la magnitud máxima debe ser mayor o igual a la mínima.";
            throw new Exception(msg);
        }
        return true;
    }

    // Validador para el ingreso de coordenadas válidas (latitud y longitud)
    public static boolean sonCoordenadasValidas(double latitud, double longitud) throws Exception {
        if (latitud < -90 || latitud > 90) {
            String msg = "Error: La latitud debe estar entre -90 y 90 grados.";
            throw new Exception(msg);
        }
        if (longitud < -180 || longitud > 180) {
            String msg = "Error: La longitud debe estar entre -180 y 180 grados.";
            throw new Exception(msg);
        }
        return true;
    }

    // Validador para el ingreso de fechas válidas
    public static boolean esFechaValida(String fecha) throws Exception {
        try {
            LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (DateTimeParseException e) {
            String msg = "Error: La fecha debe estar en el formato yyyyMMdd.";
            throw new Exception(msg);
        }
        return true;
    }

    // Validador para el ingreso de horas válidas en formato HHmmss
    public static boolean esHoraValida(String hora) throws Exception {
        try {
            LocalTime.parse(hora, DateTimeFormatter.ofPattern("HHmmss"));
        } catch (DateTimeParseException e) {
            String msg = "Error: La hora debe estar en el formato HHmmss.";
            throw new Exception(msg);
        }
        return true;
    }

    // Validador para el ingreso de profundidades válidas
    public static boolean esProfundidadValida(int profundidad) throws Exception {
        if (profundidad < 0) {
            String msg = "Error: La profundidad no puede ser negativa.";
            throw new Exception(msg);
        }
        return true;
    }
}
