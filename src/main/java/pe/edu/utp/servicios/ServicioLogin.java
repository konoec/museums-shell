package pe.edu.utp.servicios;

import pe.edu.utp.objetos.Usuario;
import pe.edu.utp.seguridad.ErrorLog;

import java.io.IOException;
import java.util.List;

public class ServicioLogin {
    public static boolean validateCredentials(String username, String password, ErrorLog errorLog) {
        // Cargar la lista de usuarios desde el archivo usando el método de carga de ServicioCargaDatos
        List<Usuario> usuarios;
        usuarios = ServicioCargaDatos.cargarUsuarios(errorLog);

        // Validar las credenciales contra la lista de usuarios cargados
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(username) && usuario.getContrasena().equals(password)) {
                return true; // Las credenciales son válidas
            }
        }

        // Si llegamos aquí, las credenciales no son válidas
        errorLog.log("Credenciales incorrectas para usuario: " + username, ErrorLog.Level.INFO, "ServicioLogin");
        return false;
    }
}
