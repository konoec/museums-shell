package pe.edu.utp.seguridad;

import pe.edu.utp.utilidades.TextUTP;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {
    // Enum para especificar el nivel de error
    public enum Level {ERROR, INFO, WARN}

    private String filename = "src/main/resources/auditoria.log"; // Nombre del archivo de log
    private String usuario; // Nombre del archivo de log

    // Constructor que valida el nombre del archivo de log
    public ErrorLog() throws IOException {

        File f = new File(filename);
        if (f.isDirectory()) {
            throw new IOException("El archivo log no puede ser un directorio.");
        }

        this.usuario = "NO_AUTOENTICADO";
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Método para registrar un mensaje de error en el archivo de log
    public void log(String msg, Level level, String lugar) {
        // Obtener fecha y hora actual
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        // Construir el mensaje de log
        String logMessage = String.format("%s;%s;%s;%s;%s%n",
                formattedDateTime, level, usuario, lugar, msg);

        // Escribir el mensaje en el archivo de log
        try {
            TextUTP.append(logMessage, filename);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}
