package pe.edu.utp.modulos;

import pe.edu.utp.objetos.MuseoVisita;
import pe.edu.utp.seguridad.ErrorLog;
import pe.edu.utp.servicios.ServicioCargaDatos;
import pe.edu.utp.servicios.ServicioLogin;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Modulos {
    static String lugar = "Modulos";
    static String msg;

    // Método para el menú de inicio de sesión
    public static String loginMenu(Scanner scanner, ErrorLog errorLog) {
        String bienvenida = """
            -----------------------------------------------------------------------------------------------------------------------------------------
            | Bienvenido. Por favor, inicie sesión.                                                                                                 |
            -----------------------------------------------------------------------------------------------------------------------------------------
            """;

        System.out.printf(bienvenida);

        System.out.print("| Ingrese su nombre de usuario: ");
        String username = scanner.nextLine().trim(); // Leer y limpiar espacios en blanco

        System.out.print("| Ingrese su contraseña: ");
        String password = scanner.nextLine().trim(); // Leer y limpiar espacios en blanco

        try {
            if (ServicioLogin.validateCredentials(username, password, errorLog)) {
                return username;
            }
        } catch (Exception e) {
            errorLog.log("Error al validar credenciales: " + e.getMessage(), ErrorLog.Level.WARN,lugar);
        }

        return null;
    }

    // Método para imprimir el menú principal en formato ASCII
    public static void printMenuPrincipal() {
        String menu = """
                -----------------------------------------------------------------------------------------------------------------------------------------
                | Menú Principal: Generador de Estadísticas de Visitas a Museos en Perú (Ley N° 30599)                                                  |
                -----------------------------------------------------------------------------------------------------------------------------------------
                1. Reporte de Visitas por Año
                2. Reporte de Visitas por Mes en un Intervalo de Años
                3. Reporte de Visitas por Departamento en un Intervalo de Años
                4. Reporte de Visitas por Provincia en un Intervalo de Años
                5. Reporte de Visitas por Tipo de Museo en un Intervalo de Años
                6. Reporte de Visitas por Tipo de Visitante en un Intervalo de Años
                7. Reporte de Visitas por Museo en un Intervalo de Años
                8. Tendencias de Visitas
                9. Promedio de Visitas
                10. Top Museos Más Visitados
                0. Salir
                -----------------------------------------------------------------------------------------------------------------------------------------
                | Seleccione una opción:\s""";

        System.out.print(menu);
    }

    // Método para el reporte de visitas por año
    public static void reporteVisitasPorAnio(Scanner scanner, ErrorLog errorLog) {

        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {

            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Visitas por Año
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    Reportes.imprimirReporteVisitasPorAnio(data);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    String filename = scanner.next();
                    Reportes.escribirReporteVisitasPorAnio(data, filename);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }

    // Método para el reporte de visitas por mes
    public static void reporteVisitasPorMes(Scanner scanner, ErrorLog errorLog) {

        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Visitas por Mes
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String anioInicioInput = scanner.nextLine();
                    Integer anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    String anioFinInput = scanner.nextLine();
                    Integer anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.imprimirReporteVisitasPorMes(data, anioInicio, anioFin);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String filename = scanner.nextLine();

                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    anioInicioInput = scanner.nextLine();
                    anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    anioFinInput = scanner.nextLine();
                    anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.escribirReporteVisitasPorMes(data, filename, anioInicio, anioFin);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }


    // Método para el reporte de visitas por departamento
    public static void reporteVisitasPorDepartamento(Scanner scanner, ErrorLog errorLog) {

        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Visitas por Departamento
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String anioInicioInput = scanner.nextLine();
                    Integer anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    String anioFinInput = scanner.nextLine();
                    Integer anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.imprimirReporteVisitasPorDepartamento(data, anioInicio, anioFin);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String filename = scanner.nextLine();

                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    anioInicioInput = scanner.nextLine();
                    anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    anioFinInput = scanner.nextLine();
                    anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.escribirReporteVisitasPorDepartamento(data, filename, anioInicio, anioFin);
                    break;

                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }

    // Método para el reporte de visitas por provincia
    public static void reporteVisitasPorProvincia(Scanner scanner, ErrorLog errorLog) {

        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Visitas por Provincia
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String anioInicioInput = scanner.nextLine();
                    Integer anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    String anioFinInput = scanner.nextLine();
                    Integer anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.imprimirReporteVisitasPorProvincia(data, anioInicio, anioFin);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String filename = scanner.nextLine();

                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    anioInicioInput = scanner.nextLine();
                    anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    anioFinInput = scanner.nextLine();
                    anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.escribirReporteVisitasPorProvincia(data, filename, anioInicio, anioFin);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }


    // Método para el reporte de visitas por tipo de museo
    public static void reporteVisitasPorTipoMuseo(Scanner scanner, ErrorLog errorLog) {
        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Visitas por Tipo de Museo
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String anioInicioInput = scanner.nextLine();
                    Integer anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    String anioFinInput = scanner.nextLine();
                    Integer anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.imprimirReporteVisitasPorTipoMuseo(data, anioInicio, anioFin);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String filename = scanner.nextLine();

                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    anioInicioInput = scanner.nextLine();
                    anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    anioFinInput = scanner.nextLine();
                    anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.escribirReporteVisitasPorTipoMuseo(data, filename, anioInicio, anioFin);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }


    // Método para el reporte de visitas por tipo de visitante
    public static void reporteVisitasPorTipoVisitante(Scanner scanner, ErrorLog errorLog) {
        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Visitas por Tipo de Visitante
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String anioInicioInput = scanner.nextLine();
                    Integer anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    String anioFinInput = scanner.nextLine();
                    Integer anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.imprimirReporteVisitasPorTipoVisitante(data,anioInicio,anioFin);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String filename = scanner.nextLine();

                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    anioInicioInput = scanner.nextLine();
                    anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    anioFinInput = scanner.nextLine();
                    anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.escribirReporteVisitasPorTipoVisitante(data, filename,anioInicio,anioFin);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }


    // Método para el reporte de visitas por museo
    public static void reporteVisitasPorMuseo(Scanner scanner, ErrorLog errorLog) {
        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Visitas por Tipo de Museo
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String anioInicioInput = scanner.nextLine();
                    Integer anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    String anioFinInput = scanner.nextLine();
                    Integer anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.imprimirReporteVisitasPorMuseo(data,anioInicio,anioFin);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String filename = scanner.nextLine();

                    System.out.print("Ingresa el año de inicio (o presiona Enter para todos los años): ");
                    anioInicioInput = scanner.nextLine();
                    anioInicio = anioInicioInput.isEmpty() ? null : Integer.parseInt(anioInicioInput);

                    System.out.print("Ingresa el año de fin (o presiona Enter para todos los años): ");
                    anioFinInput = scanner.nextLine();
                    anioFin = anioFinInput.isEmpty() ? null : Integer.parseInt(anioFinInput);

                    Reportes.escribirReporteVisitasPorMuseo(data, filename,anioInicio,anioFin);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }


    // Método para las tendencias de visitas
    public static void tendenciasDeVisitas(Scanner scanner, ErrorLog errorLog) {
        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Tendencia de Visitas
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    Reportes.imprimirReporteTendenciasVisitas(data);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    String filename = scanner.next();
                    Reportes.escribirReporteTendenciasVisitas(data, filename);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }


    // Método para el promedio de visitas
    public static void promedioDeVisitas(Scanner scanner, ErrorLog errorLog) {
        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Reporte de Promedio de Visitas
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    Reportes.imprimirReportePromedioVisitas(data);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    String filename = scanner.next();
                    Reportes.escribirReportePromedioVisitas(data, filename);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }


    // Método para el top de museos más visitados
    public static void topMuseosMasVisitados(Scanner scanner, ErrorLog errorLog) {
        List<MuseoVisita> data = ServicioCargaDatos.cargarVisitasMuseos(errorLog);

        // Submenú para seleccionar la opción de acción
        int opcion = 0;
        do {
            String subMenu = """
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Menú Secundario: Top 10 Museos mas visitados entre 2022 y 2024
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                1. Imprimir en por pantalla
                2. Exportar a archivo plano
                3. Retroceder al menú principal
                -----------------------------------------------------------------------------------------------------------------------------------------------------
                Seleccione una opción:""";

            System.out.print(subMenu);

            try {
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar el buffer de entrada
                msg = "Error: Ingrese un número válido.";
                errorLog.log(msg, ErrorLog.Level.ERROR, lugar); // Registrar mensaje de error en el archivo de log
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                System.out.println("| "+ msg); // Imprimir mensaje en consola
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------"); // Imprimir mensaje en consola
                continue; // Saltar a la siguiente iteración del ciclo
            }

            switch (opcion) {
                case 1:
                    Reportes.imprimirReporteTopMuseosMasVisitados(data);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del archivo a exportar: ");
                    String filename = scanner.next();
                    Reportes.escribirReporteTopMuseosMasVisitados(data, filename);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    msg = "Opción no válida. Por favor, seleccione una opción válida.";
                    errorLog.log(msg, ErrorLog.Level.INFO, lugar); // Registrar mensaje de error en el archivo de log
                    System.out.println(msg);
            }

        } while (opcion != 3); // Continuar mostrando el submenú hasta que se seleccione retroceder
    }
}
