package utp.edu.pe.servicios;

import utp.edu.pe.objetos.Usuario;
import utp.edu.pe.seguridad.ErrorLog;

import java.util.List;

public class ServicioLogin {
    public static boolean validateCredentials(String username, String password, ErrorLog errorLog) {

        // Cargar la lista de usuarios desde el archivo usando el método de carga de ServicioCargaDatos
        List<Usuario> usuarios = ServicioCargaDatos.cargarUsuarios(errorLog);

        // Validar las credenciales contra la lista de usuarios cargados
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(username) && usuario.getContrasena().equals(password)) {
                return true; // Las credenciales son válidas
            }
        }
        return false; // Las credenciales no son válidas o la lista de usuarios está vacía
    }
}
