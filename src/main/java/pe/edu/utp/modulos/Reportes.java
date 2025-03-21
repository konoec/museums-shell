package pe.edu.utp.modulos;

import pe.edu.utp.objetos.MuseoVisita;
import pe.edu.utp.utilidades.TextUTP;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Reportes {

    public static void imprimirReporteVisitasPorAnio(List<MuseoVisita> visitas) {
        // Encabezado del reporte
        System.out.println("Reporte de Visitas a Museos por Año");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");


        // Encabezados de la tabla
        System.out.println("| Año  | Total Visitas | Barra de Progreso                                                                                              |");

        // Calcular el total de visitas por año y encontrar el máximo para normalizar la barra de progreso
        Map<Integer, Integer> visitasPorAnio = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anio = visita.getAnio();
            int total = visita.getTotalVisitantes();

            if (visitasPorAnio.containsKey(anio)) {
                visitasPorAnio.put(anio, visitasPorAnio.get(anio) + total);
            } else {
                visitasPorAnio.put(anio, total);
            }

            if (total > maxTotal) {
                maxTotal = total;
            }
        }

        // Mostrar la tabla de reporte por año en ASCII
        for (Map.Entry<Integer, Integer> entry : visitasPorAnio.entrySet()) {
            int anio = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total/ maxTotal);

            // Calcula el porcentaje
            double percentage = ((double) total / maxTotal);

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-4d | %-13d | %-100s | %.2f%%  |\n", anio, total, "#".repeat(Math.max(0, barLength))
                    // Formatear y mostrar cada línea del reporte en forma de tabla
                    , percentage);
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorAnio.values().stream().mapToInt(Integer::intValue).sum();

        // Mostrar la línea del total acumulado
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total de Visitas Acumulado                                                                                                 %10s |\n", totalAcumulado);
    }

    // Método para generar y guardar el reporte en un archivo de texto
    public static void escribirReporteVisitasPorAnio(List<MuseoVisita> visitas, String filename) {
        // Generar el reporte similar al método imprimirReporteVisitasPorAnio
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Visitas a Museos por Año\n");
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Año  | Total Visitas | Barra de Progreso                                                                                              |\n");

        // Calcular el total de visitas por año y encontrar el máximo para normalizar la barra de progreso
        Map<Integer, Integer> visitasPorAnio = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anio = visita.getAnio();
            int total = visita.getTotalVisitantes();

            if (visitasPorAnio.containsKey(anio)) {
                visitasPorAnio.put(anio, visitasPorAnio.get(anio) + total);
            } else {
                visitasPorAnio.put(anio, total);
            }

            if (total > maxTotal) {
                maxTotal = total;
            }
        }

        // Construir el reporte por año en ASCII
        for (Map.Entry<Integer, Integer> entry : visitasPorAnio.entrySet()) {
            int anio = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Construir la barra de progreso

            // Agregar la línea al reporte
            double percentage = ((double) total / maxTotal);
            reporte.append(String.format("| %-4d | %-13d | %-100s | %.2f%%  |\n", anio, total, "#".repeat(Math.max(0, barLength))
                    // Agregar la línea al reporte
                    , percentage));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorAnio.values().stream().mapToInt(Integer::intValue).sum();

        // Agregar la línea del total acumulado
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| Total de Visitas Acumulado                                                                                                 %10d |\n", totalAcumulado));

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename+".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename+".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReporteVisitasPorMes(List<MuseoVisita> visitas, Integer anioInicio, Integer anioFin) {
        // Encabezado del reporte
        System.out.println("Reporte de Visitas a Museos por Mes" + (anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : ""));
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Mes        | Total Visitas | Barra de Progreso                                                                                               |");

        // Calcular el total de visitas por mes y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorMes = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            // Filtrar por año si se especifica
            if ((anioInicio == null || visita.getAnio() >= anioInicio) && (anioFin == null || visita.getAnio() <= anioFin)) {
                String mes = visita.getMes();
                int total = visita.getTotalVisitantes();

                if (visitasPorMes.containsKey(mes)) {
                    visitasPorMes.put(mes, visitasPorMes.get(mes) + total);
                } else {
                    visitasPorMes.put(mes, total);
                }

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Mostrar la tabla de reporte por mes en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorMes.entrySet()) {
            String mes = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Construir la barra de progreso
            StringBuilder barra = new StringBuilder();
            for (int i = 0; i < barLength; i++) {
                barra.append("#");
            }

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-10s | %-13d | %-100s | %6.2f%%  |\n", mes, total, barra.toString(), ((double) total / maxTotal));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorMes.values().stream().mapToInt(Integer::intValue).sum();

        // Mostrar la línea del total acumulado
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total de Visitas Acumulado                                                                                                        %10d |\n", totalAcumulado);
    }

    public static void escribirReporteVisitasPorMes(List<MuseoVisita> visitas, String filename, Integer anioInicio, Integer anioFin) {
        // Generar el reporte similar al método imprimirReporteVisitasPorMes
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Visitas a Museos por Mes").append(anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : "").append("\n");
        reporte.append("------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Mes        | Total Visitas | Barra de Progreso                                                                                   |\n");

        // Calcular el total de visitas por mes y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorMes = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            // Filtrar por año si se especifica
            if ((anioInicio == null || visita.getAnio() >= anioInicio) && (anioFin == null || visita.getAnio() <= anioFin)) {
                String mes = visita.getMes();
                int total = visita.getTotalVisitantes();

                if (visitasPorMes.containsKey(mes)) {
                    visitasPorMes.put(mes, visitasPorMes.get(mes) + total);
                } else {
                    visitasPorMes.put(mes, total);
                }

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Construir el reporte por mes en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorMes.entrySet()) {
            String mes = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Agregar la línea al reporte
            reporte.append(String.format("| %-10s | %-13d | %-100s | %6.2f%%  |\n", mes, total, "#".repeat(Math.max(0, barLength))
                    // Agregar la línea al reporte
                    , ((double) total / maxTotal)));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorMes.values().stream().mapToInt(Integer::intValue).sum();

        // Agregar la línea del total acumulado
        reporte.append("------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| Total de Visitas Acumulado                                                                                                        %10d |\n", totalAcumulado));

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReporteVisitasPorDepartamento(List<MuseoVisita> visitas, Integer anioInicio, Integer anioFin) {
        // Encabezado del reporte
        System.out.println("Reporte de Visitas a Museos por Departamento " + (anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : ""));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Departamento    | Total Visitas | Barra de Progreso                                                                                               |");

        // Calcular el total de visitas por departamento y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorDepartamento = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                String departamento = visita.getDepartamento();
                int total = visita.getTotalVisitantes();

                if (visitasPorDepartamento.containsKey(departamento)) {
                    visitasPorDepartamento.put(departamento, visitasPorDepartamento.get(departamento) + total);
                } else {
                    visitasPorDepartamento.put(departamento, total);
                }

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Mostrar la tabla de reporte por departamento en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorDepartamento.entrySet()) {
            String departamento = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-15s | %-13d | %-100s | %6.2f%%  |\n", departamento, total, "#".repeat(Math.max(0, barLength))
                    // Formatear y mostrar cada línea del reporte en forma de tabla
                    , ((double) total / maxTotal));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorDepartamento.values().stream().mapToInt(Integer::intValue).sum();

        // Mostrar la línea del total acumulado
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total de Visitas Acumulado                                                                                                             %10d |\n", totalAcumulado);
    }

    public static void escribirReporteVisitasPorDepartamento(List<MuseoVisita> visitas, String filename, Integer anioInicio, Integer anioFin) {
        // Generar el reporte similar al método imprimirReporteVisitasPorDepartamento
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Visitas a Museos por Departamento ").append(anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : "").append("\n");
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Departamento    | Total Visitas | Barra de Progreso                                                                                               |\n");

        // Calcular el total de visitas por departamento y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorDepartamento = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                String departamento = visita.getDepartamento();
                int total = visita.getTotalVisitantes();

                if (visitasPorDepartamento.containsKey(departamento)) {
                    visitasPorDepartamento.put(departamento, visitasPorDepartamento.get(departamento) + total);
                } else {
                    visitasPorDepartamento.put(departamento, total);
                }

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Construir el reporte por departamento en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorDepartamento.entrySet()) {
            String departamento = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Agregar la línea al reporte
            reporte.append(String.format("| %-15s | %-13d | %-100s | %6.2f%%  |\n", departamento, total, "#".repeat(Math.max(0, barLength))
                    // Agregar la línea al reporte
                    , ((double) total / maxTotal)));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorDepartamento.values().stream().mapToInt(Integer::intValue).sum();

        // Agregar la línea del total acumulado
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| Total de Visitas Acumulado                                                                                                             %10d |\n", totalAcumulado));

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename+".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename+".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }


    public static void imprimirReporteVisitasPorProvincia(List<MuseoVisita> visitas, Integer anioInicio, Integer anioFin) {
        // Encabezado del reporte
        System.out.println("Reporte de Visitas a Museos por Provincia" + (anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : ""));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Provincia       | Total Visitas | Barra de Progreso                                                                                               |");

        // Calcular el total de visitas por provincia y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorProvincia = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                String provincia = visita.getProvincia();
                int total = visita.getTotalVisitantes();

                visitasPorProvincia.merge(provincia, total, Integer::sum);

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Mostrar la tabla de reporte por provincia en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorProvincia.entrySet()) {
            String provincia = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-15s | %-13d | %-100s | %6.2f%%  |\n", provincia, total, "#".repeat(Math.max(0, barLength))
                    // Formatear y mostrar cada línea del reporte en forma de tabla
                    , ((double) total / maxTotal));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorProvincia.values().stream().mapToInt(Integer::intValue).sum();

        // Mostrar la línea del total acumulado
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total de Visitas Acumulado                                                                                                             %10d |\n", totalAcumulado);
    }

    public static void escribirReporteVisitasPorProvincia(List<MuseoVisita> visitas, String filename, Integer anioInicio, Integer anioFin) {
        // Generar el reporte similar al método imprimirReporteVisitasPorProvincia
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Visitas a Museos por Provincia").append(anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : "").append("\n");
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Provincia       | Total Visitas | Barra de Progreso                                                                                               |\n");

        // Calcular el total de visitas por provincia y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorProvincia = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                String provincia = visita.getProvincia();
                int total = visita.getTotalVisitantes();

                visitasPorProvincia.merge(provincia, total, Integer::sum);

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Construir el reporte por provincia en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorProvincia.entrySet()) {
            String provincia = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Agregar la línea al reporte
            reporte.append(String.format("| %-15s | %-13d | %-100s | %6.2f%%  |\n", provincia, total, "#".repeat(Math.max(0, barLength))
                    // Agregar la línea al reporte
                    , ((double) total / maxTotal)));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorProvincia.values().stream().mapToInt(Integer::intValue).sum();

        // Agregar la línea del total acumulado
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| Total de Visitas Acumulado                                                                                                             %10d |\n", totalAcumulado));

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReporteVisitasPorTipoMuseo(List<MuseoVisita> visitas, Integer anioInicio, Integer anioFin) {
        // Encabezado del reporte
        System.out.println("Reporte de Visitas a Museos por Tipo de Museo" + (anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : ""));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Tipo de Museo   | Total Visitas | Barra de Progreso                                                                                               |");

        // Calcular el total de visitas por tipo de museo y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorTipo = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                String tipoMuseo = visita.getTipo();
                int total = visita.getTotalVisitantes();

                visitasPorTipo.merge(tipoMuseo, total, Integer::sum);

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Mostrar la tabla de reporte por tipo de museo en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorTipo.entrySet()) {
            String tipoMuseo = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-15s | %-13d | %-100s | %6.2f%%  |\n", tipoMuseo, total, "#".repeat(Math.max(0, barLength))
                    // Formatear y mostrar cada línea del reporte en forma de tabla
                    , ((double) total / maxTotal));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorTipo.values().stream().mapToInt(Integer::intValue).sum();

        // Mostrar la línea del total acumulado
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total de Visitas Acumulado                                                                                                             %10d |\n", totalAcumulado);
    }

    public static void escribirReporteVisitasPorTipoMuseo(List<MuseoVisita> visitas, String filename, Integer anioInicio, Integer anioFin) {
        // Generar el reporte similar al método imprimirReporteVisitasPorTipoMuseo
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Visitas a Museos por Tipo de Museo ").append(anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : "").append("\n");
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Tipo de Museo   | Total Visitas | Barra de Progreso                                                                                               |\n");

        // Calcular el total de visitas por tipo de museo y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorTipo = new HashMap<>();
        int maxTotal = 0;

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                String tipoMuseo = visita.getTipo();
                int total = visita.getTotalVisitantes();

                visitasPorTipo.merge(tipoMuseo, total, Integer::sum);

                if (total > maxTotal) {
                    maxTotal = total;
                }
            }
        }

        // Construir el reporte por tipo de museo en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorTipo.entrySet()) {
            String tipoMuseo = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal);

            // Agregar la línea al reporte
            reporte.append(String.format("| %-15s | %-13d | %-100s | %6.2f%%  |\n", tipoMuseo, total, "#".repeat(Math.max(0, barLength))
                    // Agregar la línea al reporte
                    , ((double) total / maxTotal)));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorTipo.values().stream().mapToInt(Integer::intValue).sum();

        // Agregar la línea del total acumulado
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| Total de Visitas Acumulado                                                                                                             %10d |\n", totalAcumulado));

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReporteVisitasPorTipoVisitante(List<MuseoVisita> visitas, Integer anioInicio, Integer anioFin) {
        // Encabezado del reporte
        System.out.println("Reporte de Visitas a Museos por Tipo de Visitante " + (anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : ""));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Tipo de Visitante  | Total Visitas | Barra de Progreso                                                                                               |");

        // Calcular el total de visitas por tipo de visitante y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorTipoVisitante = new HashMap<>();
        visitasPorTipoVisitante.put("Adultos", 0);
        visitasPorTipoVisitante.put("Estudiantes", 0);
        visitasPorTipoVisitante.put("Niños", 0);
        visitasPorTipoVisitante.put("Adultos Mayores", 0);
        visitasPorTipoVisitante.put("Militares", 0);
        visitasPorTipoVisitante.put("Discapacitados", 0);

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                visitasPorTipoVisitante.put("Adultos", visitasPorTipoVisitante.get("Adultos") + visita.getVisitantesAdulto());
                visitasPorTipoVisitante.put("Estudiantes", visitasPorTipoVisitante.get("Estudiantes") + visita.getVisitantesEstudiante());
                visitasPorTipoVisitante.put("Niños", visitasPorTipoVisitante.get("Niños") + visita.getVisitantesNino());
                visitasPorTipoVisitante.put("Adultos Mayores", visitasPorTipoVisitante.get("Adultos Mayores") + visita.getVisitantesAdultoMayor());
                visitasPorTipoVisitante.put("Militares", visitasPorTipoVisitante.get("Militares") + visita.getVisitantesMilitares());
                visitasPorTipoVisitante.put("Discapacitados", visitasPorTipoVisitante.get("Discapacitados") + visita.getVisitantesDiscapacitado());
            }
        }

        int maxTotal = visitasPorTipoVisitante.get("Adultos")+visitasPorTipoVisitante.get("Estudiantes") +
                visitasPorTipoVisitante.get("Niños") + visitasPorTipoVisitante.get("Adultos Mayores") +
                visitasPorTipoVisitante.get("Militares") + visitasPorTipoVisitante.get("Discapacitados");

        // Mostrar la tabla de reporte por tipo de visitante en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorTipoVisitante.entrySet()) {
            String tipoVisitante = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal * 100);

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-17s | %-13d | %-100s | %6.2f%%  |\n", tipoVisitante, total, "#".repeat(Math.max(0, barLength))
                    // Formatear y mostrar cada línea del reporte en forma de tabla
                    , ((double) total / maxTotal * 100));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorTipoVisitante.values().stream().mapToInt(Integer::intValue).sum();

        // Mostrar la línea del total acumulado
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total de Visitas Acumulado                                                                                                                 %10d |\n", totalAcumulado);
    }

    public static void escribirReporteVisitasPorTipoVisitante(List<MuseoVisita> visitas, String filename, Integer anioInicio, Integer anioFin) {
        // Generar el reporte similar al método imprimirReporteVisitasPorTipoVisitante
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Visitas a Museos por Tipo de Visitante ").append(anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : "").append("\n");
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Tipo de Visitante  | Total Visitas | Barra de Progreso                                                                                               |\n");

        // Calcular el total de visitas por tipo de visitante y encontrar el máximo para normalizar la barra de progreso
        Map<String, Integer> visitasPorTipoVisitante = new HashMap<>();
        visitasPorTipoVisitante.put("Adultos", 0);
        visitasPorTipoVisitante.put("Estudiantes", 0);
        visitasPorTipoVisitante.put("Niños", 0);
        visitasPorTipoVisitante.put("Adultos Mayores", 0);
        visitasPorTipoVisitante.put("Militares", 0);
        visitasPorTipoVisitante.put("Discapacitados", 0);

        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                visitasPorTipoVisitante.put("Adultos", visitasPorTipoVisitante.get("Adultos") + visita.getVisitantesAdulto());
                visitasPorTipoVisitante.put("Estudiantes", visitasPorTipoVisitante.get("Estudiantes") + visita.getVisitantesEstudiante());
                visitasPorTipoVisitante.put("Niños", visitasPorTipoVisitante.get("Niños") + visita.getVisitantesNino());
                visitasPorTipoVisitante.put("Adultos Mayores", visitasPorTipoVisitante.get("Adultos Mayores") + visita.getVisitantesAdultoMayor());
                visitasPorTipoVisitante.put("Militares", visitasPorTipoVisitante.get("Militares") + visita.getVisitantesMilitares());
                visitasPorTipoVisitante.put("Discapacitados", visitasPorTipoVisitante.get("Discapacitados") + visita.getVisitantesDiscapacitado());
            }
        }

        int maxTotal = visitasPorTipoVisitante.get("Adultos")+visitasPorTipoVisitante.get("Estudiantes") +
                visitasPorTipoVisitante.get("Niños") + visitasPorTipoVisitante.get("Adultos Mayores") +
                visitasPorTipoVisitante.get("Militares") + visitasPorTipoVisitante.get("Discapacitados");

        // Construir el reporte por tipo de visitante en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorTipoVisitante.entrySet()) {
            String tipoVisitante = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal * 100);

            // Agregar la línea al reporte
            reporte.append(String.format("| %-17s | %-13d | %-100s | %6.2f%%  |\n", tipoVisitante, total, "#".repeat(Math.max(0, barLength))
                    // Agregar la línea al reporte
                    , ((double) total / maxTotal * 100)));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorTipoVisitante.values().stream().mapToInt(Integer::intValue).sum();

        // Agregar la línea del total acumulado
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| Total de Visitas Acumulado                                                                                                                 %10d |\n", totalAcumulado));

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReporteVisitasPorMuseo(List<MuseoVisita> visitas,Integer anioInicio, Integer anioFin) {
        // Encabezado del reporte
        System.out.println("Reporte de Visitas a Museos" + (anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : ""));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Museo                                               | Total Visitas | Barra de Progreso                    |");

        // Calcular el total de visitas por museo
        Map<String, Integer> visitasPorMuseo = new HashMap<>();
        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                visitasPorMuseo.put(visita.getNombreMuseo(), visitasPorMuseo.getOrDefault(visita.getNombreMuseo(), 0) + visita.getTotalVisitantes());
            }
        }

        // Encontrar el máximo número de visitas por museo para normalizar la barra de progreso
        int maxTotal = visitas.stream().mapToInt(MuseoVisita::getTotalVisitantes).sum();

        // Mostrar la tabla de reporte por museo en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorMuseo.entrySet()) {
            String museo = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal * 50);  // Ajustar longitud de la barra a 50 caracteres

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-70s | %-13d | %-50s | %6.2f%%  |\n", museo, total, "#".repeat(Math.max(0, barLength))
                    // Formatear y mostrar cada línea del reporte en forma de tabla
                    , ((double) total / maxTotal * 100));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorMuseo.values().stream().mapToInt(Integer::intValue).sum();

        // Mostrar la línea del total acumulado
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| Total de Visitas Acumulado                                                                                                                 %10d |\n", totalAcumulado);
    }

    public static void escribirReporteVisitasPorMuseo(List<MuseoVisita> visitas, String filename, Integer anioInicio, Integer anioFin) {
        // Generar el reporte similar al método imprimirReporteVisitasPorMuseo
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Visitas a Museos ").append(anioInicio != null ? " del año " + anioInicio + (anioFin != null ? " al " + anioFin : "") : "").append("\n");
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Museo                                               | Total Visitas | Barra de Progreso                    |\n");

        // Calcular el total de visitas por museo
        Map<String, Integer> visitasPorMuseo = new HashMap<>();
        for (MuseoVisita visita : visitas) {
            int anioVisita = visita.getAnio();
            if ((anioInicio == null || anioVisita >= anioInicio) && (anioFin == null || anioVisita <= anioFin)) {
                visitasPorMuseo.put(visita.getNombreMuseo(), visitasPorMuseo.getOrDefault(visita.getNombreMuseo(), 0) + visita.getTotalVisitantes());
            }
        }

        // Encontrar el máximo número de visitas por museo para normalizar la barra de progreso
        int maxTotal = visitas.stream().mapToInt(MuseoVisita::getTotalVisitantes).sum();

        // Construir el reporte por museo en ASCII
        for (Map.Entry<String, Integer> entry : visitasPorMuseo.entrySet()) {
            String museo = entry.getKey();
            int total = entry.getValue();

            // Calcula la longitud de la barra de progreso normalizada
            int barLength = (int) Math.ceil((double) total / maxTotal * 50);  // Ajustar longitud de la barra a 50 caracteres

            // Agregar la línea al reporte
            reporte.append(String.format("| %-70s | %-13d | %-50s | %6.2f%%  |\n", museo, total, "#".repeat(Math.max(0, barLength))
                    // Agregar la línea al reporte
                    , ((double) total / maxTotal * 100)));
        }

        // Calcular el total acumulado de visitas
        int totalAcumulado = visitasPorMuseo.values().stream().mapToInt(Integer::intValue).sum();

        // Agregar la línea del total acumulado
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| Total de Visitas Acumulado                                                                                                                 %10d |\n", totalAcumulado));

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReporteTendenciasVisitas(List<MuseoVisita> visitas) {
        // Encabezado del reporte
        System.out.println("Reporte de Tendencias de Visitas a Museos");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Periodo        | Total Visitas | Cambio Relativo (%)          |");

        // Agrupar las visitas por período (año y mes)
        Map<String, Integer> visitasPorPeriodo = new TreeMap<>();
        for (MuseoVisita visita : visitas) {
            String periodo = visita.getAnio() + "-" + visita.getIdMes();
            visitasPorPeriodo.put(periodo, visitasPorPeriodo.getOrDefault(periodo, 0) + visita.getTotalVisitantes());
        }

        // Calcular las tendencias comparando períodos consecutivos
        String periodoAnterior = null;
        int visitasAnterior = 0;
        for (Map.Entry<String, Integer> entry : visitasPorPeriodo.entrySet()) {
            String periodo = entry.getKey();
            int total = entry.getValue();
            double cambioRelativo = 0;

            if (periodoAnterior != null) {
                cambioRelativo = ((double) (total - visitasAnterior) / visitasAnterior) * 100;
            }

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-14s | %-13d | %-28.2f |\n", periodo, total, cambioRelativo);

            // Actualizar el período y visitas anteriores para la próxima iteración
            periodoAnterior = periodo;
            visitasAnterior = total;
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    }


    public static void escribirReporteTendenciasVisitas(List<MuseoVisita> visitas, String filename) {
        // Generar el reporte similar al método imprimirReporteTendenciasVisitas
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Tendencias de Visitas a Museos\n");
        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Periodo        | Total Visitas | Cambio Relativo (%)          |\n");

        // Agrupar las visitas por período (año y mes)
        Map<String, Integer> visitasPorPeriodo = new TreeMap<>();
        for (MuseoVisita visita : visitas) {
            String periodo = visita.getAnio() + "-" + visita.getIdMes();
            visitasPorPeriodo.put(periodo, visitasPorPeriodo.getOrDefault(periodo, 0) + visita.getTotalVisitantes());
        }

        // Calcular las tendencias comparando períodos consecutivos
        String periodoAnterior = null;
        int visitasAnterior = 0;
        for (Map.Entry<String, Integer> entry : visitasPorPeriodo.entrySet()) {
            String periodo = entry.getKey();
            int total = entry.getValue();
            double cambioRelativo = 0;

            if (periodoAnterior != null) {
                cambioRelativo = ((double) (total - visitasAnterior) / visitasAnterior) * 100;
            }

            // Formatear y construir cada línea del reporte en forma de tabla
            reporte.append(String.format("| %-14s | %-13d | %-28.2f |\n", periodo, total, cambioRelativo));

            // Actualizar el período y visitas anteriores para la próxima iteración
            periodoAnterior = periodo;
            visitasAnterior = total;
        }

        reporte.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReportePromedioVisitas(List<MuseoVisita> visitas) {
        // Encabezado del reporte
        System.out.println("Reporte de Promedio Mensual de Visitas a Museos");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        // Encabezados de la tabla
        System.out.println("| Año   | Mes  | Promedio Visitas |");

        // Agrupar las visitas por año y mes
        Map<String, List<Integer>> visitasPorPeriodo = new HashMap<>();
        for (MuseoVisita visita : visitas) {
            String periodo = visita.getAnio() + "-" + visita.getIdMes();
            if (!visitasPorPeriodo.containsKey(periodo)) {
                visitasPorPeriodo.put(periodo, new ArrayList<>());
            }
            visitasPorPeriodo.get(periodo).add(visita.getTotalVisitantes());
        }

        // Calcular el promedio por año y mes
        for (Map.Entry<String, List<Integer>> entry : visitasPorPeriodo.entrySet()) {
            String periodo = entry.getKey();
            List<Integer> visitasMensuales = entry.getValue();

            int totalVisitas = visitasMensuales.stream().mapToInt(Integer::intValue).sum();
            double promedioVisitas = (double) totalVisitas / visitasMensuales.size();

            // Obtener año y mes del periodo
            String[] partes = periodo.split("-");
            int anio = Integer.parseInt(partes[0]);
            String mes = partes[1];

            // Formatear y mostrar cada línea del reporte en forma de tabla
            System.out.printf("| %-5d | %-4s | %-16.2f |\n", anio, mes, promedioVisitas);
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }


    public static void escribirReportePromedioVisitas(List<MuseoVisita> visitas, String filename) {
        // Generar el reporte similar al método imprimirReportePromedioVisitas
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Promedio Mensual de Visitas a Museos\n");
        reporte.append("------------------------------------------------------------------------------------------------------------------\n");
        reporte.append("| Año   | Mes   | Promedio Visitas |\n");

        // Agrupar las visitas por año y mes
        Map<String, List<Integer>> visitasPorPeriodo = new HashMap<>();
        for (MuseoVisita visita : visitas) {
            String periodo = visita.getAnio() + "-" + visita.getIdMes();
            if (!visitasPorPeriodo.containsKey(periodo)) {
                visitasPorPeriodo.put(periodo, new ArrayList<>());
            }
            visitasPorPeriodo.get(periodo).add(visita.getTotalVisitantes());
        }

        // Calcular el promedio por año y mes
        for (Map.Entry<String, List<Integer>> entry : visitasPorPeriodo.entrySet()) {
            String periodo = entry.getKey();
            List<Integer> visitasMensuales = entry.getValue();

            int totalVisitas = visitasMensuales.stream().mapToInt(Integer::intValue).sum();
            double promedioVisitas = (double) totalVisitas / visitasMensuales.size();

            // Obtener año y mes del periodo
            String[] partes = periodo.split("-");
            int anio = Integer.parseInt(partes[0]);
            String mes = partes[1];

            // Formatear y agregar cada línea del reporte al StringBuilder
            reporte.append(String.format("| %-5d | %-4s | %-16.2f |\n", anio, mes, promedioVisitas));
        }

        reporte.append("------------------------------------------------------------------------------------------------------------------\n");

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void imprimirReporteTopMuseosMasVisitados(List<MuseoVisita> visitas) {
        int cantidadMuseos = 10;

        // Calcular el total de visitas por museo
        Map<String, Integer> visitasPorMuseo = new HashMap<>();
        Map<String, Integer> visitasMaximasPorMuseo = new HashMap<>();
        Map<String, String> fechaMasVisitadaPorMuseo = new HashMap<>();

        for (MuseoVisita visita : visitas) {
            String nombreMuseo = visita.getNombreMuseo();
            int totalVisitas = visita.getTotalVisitantes();

            // Actualizar total de visitas por museo
            visitasPorMuseo.put(nombreMuseo, visitasPorMuseo.getOrDefault(nombreMuseo, 0) + totalVisitas);

            // Obtener la fecha real de la visita
            String fechaActual = visita.getAnio() + visita.getIdMes();

            // Actualizar la fecha más visitada y visitas máximas por museo
            if (!fechaMasVisitadaPorMuseo.containsKey(nombreMuseo) || totalVisitas > visitasMaximasPorMuseo.get(nombreMuseo)) {
                fechaMasVisitadaPorMuseo.put(nombreMuseo, fechaActual);
                visitasMaximasPorMuseo.put(nombreMuseo, totalVisitas);
            }
        }

        // Ordenar los museos por visitas
        List<Map.Entry<String, Integer>> listaMuseosOrdenados = new ArrayList<>(visitasPorMuseo.entrySet());
        listaMuseosOrdenados.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Encabezado del reporte
        System.out.println("Reporte de Top " + cantidadMuseos + " Museos Más Visitados");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-50s | %-15s | %-20s |\n", "Museo", "Total Visitas", "Fecha Más Visitada");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        // Mostrar los primeros "cantidadMuseos" museos más visitados
        int museosMostrados = 0;
        for (Map.Entry<String, Integer> entry : listaMuseosOrdenados) {
            if (museosMostrados >= cantidadMuseos) {
                break;
            }
            String museo = entry.getKey();
            int totalVisitas = entry.getValue();
            String fechaMasVisitada = fechaMasVisitadaPorMuseo.get(museo);

            // Formatear la fecha si es válida
            String fechaFormateada = "";
            DateFormat inputFormat = new SimpleDateFormat("yyyyMM");
            DateFormat outputFormat = new SimpleDateFormat("MMMM yyyy", new Locale("es", "ES"));
            try {
                Date date = inputFormat.parse(fechaMasVisitada);
                fechaFormateada = outputFormat.format(date);
            } catch (ParseException ignored) {
            }

            System.out.printf("| %-50s | %-15d | %-20s |\n", museo, totalVisitas, fechaFormateada);
            museosMostrados++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }

    public static void escribirReporteTopMuseosMasVisitados(List<MuseoVisita> visitas, String filename) {
        int cantidadMuseos = 10;

        // Calcular el total de visitas por museo y la fecha más visitada por museo
        Map<String, Integer> visitasPorMuseo = new HashMap<>();
        Map<String, Integer> visitasMaximasPorMuseo = new HashMap<>();
        Map<String, String> fechaMasVisitadaPorMuseo = new HashMap<>();

        for (MuseoVisita visita : visitas) {
            String nombreMuseo = visita.getNombreMuseo();
            int totalVisitas = visita.getTotalVisitantes();

            // Actualizar total de visitas por museo
            visitasPorMuseo.put(nombreMuseo, visitasPorMuseo.getOrDefault(nombreMuseo, 0) + totalVisitas);

            // Obtener la fecha real de la visita
            String fechaActual = visita.getAnio() + visita.getIdMes();

            // Actualizar la fecha más visitada y visitas máximas por museo
            if (!fechaMasVisitadaPorMuseo.containsKey(nombreMuseo) || totalVisitas > visitasMaximasPorMuseo.get(nombreMuseo)) {
                fechaMasVisitadaPorMuseo.put(nombreMuseo, fechaActual);
                visitasMaximasPorMuseo.put(nombreMuseo, totalVisitas);
            }
        }

        // Ordenar los museos por visitas
        List<Map.Entry<String, Integer>> listaMuseosOrdenados = new ArrayList<>(visitasPorMuseo.entrySet());
        listaMuseosOrdenados.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Generar el reporte similar al método imprimirReporteTopMuseosMasVisitados
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Top ").append(cantidadMuseos).append(" Museos Más Visitados\n");
        reporte.append("-------------------------------------------------------------------------------------------------------------------------\n");
        reporte.append(String.format("| %-70s | %-15s | %-20s |\n", "Museo", "Total Visitas", "Fecha Más Visitada"));
        reporte.append("-------------------------------------------------------------------------------------------------------------------------\n");

        // Mostrar los primeros "cantidadMuseos" museos más visitados
        int museosMostrados = 0;
        for (Map.Entry<String, Integer> entry : listaMuseosOrdenados) {
            if (museosMostrados >= cantidadMuseos) {
                break;
            }
            String museo = entry.getKey();
            int totalVisitas = entry.getValue();
            String fechaMasVisitada = fechaMasVisitadaPorMuseo.get(museo);

            // Formatear la fecha si es válida
            String fechaFormateada = "";
            DateFormat inputFormat = new SimpleDateFormat("yyyyMM");
            DateFormat outputFormat = new SimpleDateFormat("MMMM yyyy", new Locale("es", "ES"));
            try {
                Date date = inputFormat.parse(fechaMasVisitada);
                fechaFormateada = outputFormat.format(date);
            } catch (ParseException ignored) {
            }

            reporte.append(String.format("| %-70s | %-15d | %-20s |\n", museo, totalVisitas, fechaFormateada));
            museosMostrados++;
        }
        reporte.append("-------------------------------------------------------------------------------------------------------------------------\n");

        // Escribir el reporte en un archivo de texto usando TextUTP
        try {
            TextUTP.append(reporte.toString(), "src/main/resources/exportados/" + filename + ".txt");
            System.out.println("El reporte se ha guardado correctamente en src/main/resources/exportados/" + filename + ".txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
