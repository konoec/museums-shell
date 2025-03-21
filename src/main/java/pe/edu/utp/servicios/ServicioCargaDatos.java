package pe.edu.utp.servicios;

import pe.edu.utp.objetos.MuseoVisita;
import pe.edu.utp.objetos.Usuario;
import pe.edu.utp.seguridad.ErrorLog;
import pe.edu.utp.seguridad.Validadores;
import pe.edu.utp.utilidades.TextUTP;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static pe.edu.utp.seguridad.ErrorLog.Level.ERROR;

public class ServicioCargaDatos {
    static String lugar = "ServicioCargaDatos";

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

    // Método para cargar visitas a museos desde un archivo CSV
    public static List<MuseoVisita> cargarVisitasMuseos(ErrorLog errorLog) {
        String csvFile = "src/main/resources/dataset/Base mua 2024_0.csv";
        Charset charset = Charset.forName("Windows-1252"); // Especificar la codificación ANSI

        try {
            List<String> lines = TextUTP.readlines(csvFile, charset);

            // Lista para almacenar las visitas a museos
            List<MuseoVisita> visitas = new ArrayList<>();

            // Iterar sobre cada línea del archivo CSV
            for (int i = 1; i < lines.size(); i++) { // Empezamos desde 1 para saltar la primera línea de encabezados
                String line = lines.get(i);
                String[] parts = line.split(";");

                String fechaCorte = parts[0];
                int anio = Integer.parseInt(parts[1]);
                String idMes = parts[2];
                String mes = parts[3];
                String ubigeo = parts[4];
                String departamento = parts[5];
                String provincia = parts[6];
                String distrito = parts[7];
                String idMuseo = parts[8].replaceAll("\"", ""); // Eliminar comillas dobles en el nombre del museo
                String nombreMuseo = parts[9].replaceAll("\"", ""); // Eliminar comillas dobles en el nombre del museo
                String tipo = parts[10];
                int visitantesAdulto = Integer.parseInt(parts[11]);
                int visitantesEstudiante = Integer.parseInt(parts[12]);
                int visitantesNino = Integer.parseInt(parts[13]);
                int visitantesAdultoMayor = Integer.parseInt(parts[14]);
                int visitantesMilitares = Integer.parseInt(parts[15]);
                int visitantesDiscapacitado = Integer.parseInt(parts[16]);
                int totalVisitantes = Integer.parseInt(parts[17]);

                // Validar los datos antes de crear el objeto MuseoVisita
                if (Validadores.validarFechaCorte(fechaCorte) &&
                        Validadores.validarAnio(anio) &&
                        Validadores.validarTipo(tipo) &&
                        Validadores.validarVisitantes(visitantesAdulto) &&
                        Validadores.validarVisitantes(visitantesEstudiante) &&
                        Validadores.validarVisitantes(visitantesNino) &&
                        Validadores.validarVisitantes(visitantesAdultoMayor) &&
                        Validadores.validarVisitantes(visitantesMilitares) &&
                        Validadores.validarVisitantes(visitantesDiscapacitado) &&
                        Validadores.validarVisitantes(totalVisitantes)) {

                    // Crear objeto MuseoVisita y agregarlo a la lista
                    MuseoVisita visita = new MuseoVisita(fechaCorte, anio, idMes, mes, ubigeo, departamento, provincia,
                            distrito, idMuseo, nombreMuseo, tipo, visitantesAdulto, visitantesEstudiante, visitantesNino,
                            visitantesAdultoMayor, visitantesMilitares, visitantesDiscapacitado, totalVisitantes);
                    visitas.add(visita);
                } else {
                    // Registro de error de validación si alguno de los datos no es válido
                    errorLog.log("Datos de visita a museos inválidos en la línea " + (i + 1), ERROR, lugar);
                }
            }

            return visitas;

        } catch (IOException | NumberFormatException e) {
            errorLog.log(e.getMessage(), ERROR, lugar); // Registrar el error utilizando el objeto ErrorLog
        }

        return null;
    }
}
