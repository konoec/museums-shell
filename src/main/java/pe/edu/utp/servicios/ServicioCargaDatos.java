package utp.edu.pe.servicios;

import utp.edu.pe.objetos.RegistroSismico;
import utp.edu.pe.objetos.Usuario;
import utp.edu.pe.seguridad.ErrorLog;
import utp.edu.pe.seguridad.Validadores;
import utp.edu.pe.utilidades.TextUTP;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static utp.edu.pe.seguridad.ErrorLog.Level.ERROR;

public class ServicioCargaDatos {
    static String lugar = "ServicioCargaDatos";

    // Método para cargar registros sísmicos desde un archivo CSV
    public static List<RegistroSismico> cargarRegistrosSismicos(ErrorLog errorLog) {
        String csvFile = "src/main/resources/dataset/Catalogo1960_2023.csv";
        // Lista para almacenar los registros
        List<RegistroSismico> registros = new ArrayList<>();

        try {
            List<String> lines = TextUTP.readlines(csvFile);

            // Iterar sobre cada línea del archivo CSV
            for (int i = 1; i < lines.size(); i++) { // Empezamos desde 1 para saltar la primera línea de encabezados
                String line = lines.get(i);
                String[] parts = line.split(";");

                int id = Integer.parseInt(parts[0]);
                String fechaUTC = parts[1];
                String horaUTC = parts[2];
                double latitud = Double.parseDouble(parts[3]);
                double longitud = Double.parseDouble(parts[4]);
                int profundidad = Integer.parseInt(parts[5]);
                double magnitud = Double.parseDouble(parts[6]);

                // Validar los datos antes de crear el objeto RegistroSismico
                Validadores.esFechaValida(fechaUTC);
                Validadores.esHoraValida(horaUTC);
                Validadores.sonCoordenadasValidas(latitud, longitud);
                Validadores.esProfundidadValida(profundidad);
                Validadores.esAnioValido(LocalDate.parse(fechaUTC, DateTimeFormatter.ofPattern("yyyyMMdd")).getYear());
                Validadores.sonMagnitudesValidas(magnitud, magnitud);

                // Crear objeto RegistroSismico y agregarlo a la lista
                RegistroSismico registro = new RegistroSismico(id, fechaUTC, horaUTC, latitud, longitud, profundidad, magnitud);
                registros.add(registro);
            }

            return registros;

        } catch (Exception e) {
            errorLog.log(e.getMessage(), ERROR, lugar); // Registrar el error utilizando el objeto ErrorLog
        }

        return null;
    }

    // Método para cargar usuarios desde un archivo TXT
    public static List<Usuario> cargarUsuarios(ErrorLog errorLog) {
        String usuariosFile = "src/main/resources/credenciales/usuarios.txt";

        try {
            List<String> lines = TextUTP.readlines(usuariosFile);

            // Lista para almacenar los usuarios
            List<Usuario> usuarios = new ArrayList<>();

            // Iterar sobre cada línea del archivo de usuarios
            for (String line : lines) {
                if (line.isEmpty()) {
                    continue; // Saltar líneas vacías, si las hubiera
                }
                String[] parts = line.split(":");

                String nombreUsuario = parts[0].trim();
                String contrasena = parts[1].trim();

                // Crear objeto Usuario y agregarlo a la lista
                Usuario usuario = new Usuario(nombreUsuario, contrasena);
                usuarios.add(usuario);
            }

            return usuarios;

        } catch (IOException e) {
            errorLog.log(e.getMessage(), ERROR, lugar); // Registrar el error utilizando el objeto ErrorLog
        }

        return null;
    }
}
