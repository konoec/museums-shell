package pe.edu.utp;

import pe.edu.utp.modulos.Modulos;
import pe.edu.utp.seguridad.ErrorLog;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static String msg;
    static String lugar = "App";
    static ErrorLog errorLog;

    // Bloque estático para inicializar el objeto ErrorLog
    static {
        try {
            errorLog = new ErrorLog();
        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar ErrorLog", e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner para la entrada del usuario

        try {
            // Llamar al método loginMenu de la clase Modulos para autenticar al usuario
            String username = Modulos.loginMenu(scanner, errorLog);

            // Verificar si las credenciales son incorrectas
            if (username == null) {
                msg = "Credenciales incorrectas. Fin del programa.";
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar el mensaje en el archivo de log
                return; // Salir del método main
            }

            errorLog.setUsuario(username); // Establecer el nombre de usuario en el objeto ErrorLog

            int option = 0; // Variable para almacenar la opción seleccionada por el usuario

            // Ciclo do-while para mostrar el menú principal y procesar las opciones del usuario
            do {
                Modulos.printMenuPrincipal(); // Mostrar el menú principal

                try {
                    option = scanner.nextInt(); // Leer la opción ingresada por el usuario
                } catch (InputMismatchException e) {
                    scanner.next(); // Limpiar el buffer de entrada
                    msg = "Error: Ingrese un número válido.";
                    errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                    System.out.println("| "+ msg); // Imprimir mensaje en consola
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                    continue; // Saltar a la siguiente iteración del ciclo
                }

                switch (option) {
                    case 1:
                        Modulos.reporteVisitasPorAnio(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 2:
                        Modulos.reporteVisitasPorMes(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 3:
                        Modulos.reporteVisitasPorDepartamento(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 4:
                        Modulos.reporteVisitasPorProvincia(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 5:
                        Modulos.reporteVisitasPorTipoMuseo(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 6:
                        Modulos.reporteVisitasPorTipoVisitante(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 7:
                        Modulos.reporteVisitasPorMuseo(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 8:
                        Modulos.tendenciasDeVisitas(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 9:
                        Modulos.promedioDeVisitas(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 10:
                        Modulos.topMuseosMasVisitados(scanner, errorLog); // Llamar al método correspondiente en Modulos
                        break;
                    case 0:
                        System.out.println("Fin del programa."); // Mensaje de salida del programa
                        break;
                    default:
                        msg = "Opción no válida. Por favor, ingrese una opción entre 0 y 12.";
                        errorLog.log(msg, ErrorLog.Level.WARN, lugar); // Registrar mensaje de advertencia en el archivo de log
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                        System.out.println("| "+ msg); // Imprimir mensaje en consola
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                }

                System.out.println(); // Imprimir línea en blanco para separar visualmente las iteraciones del menú
            } while (option != 0); // Continuar el ciclo mientras la opción no sea 0 (salir)

        } catch (Exception e) {
            msg = "Error inesperado: " + e.getMessage();
            errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
            System.out.println("| "+ msg); // Imprimir mensaje en consola
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
        } finally {
            scanner.close(); // Cerrar el objeto Scanner al salir del ciclo
        }
    }
}
