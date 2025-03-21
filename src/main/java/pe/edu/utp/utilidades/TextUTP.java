package utp.edu.pe.utilidades;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextUTP {

    // Enumeración para los sistemas operativos soportados
    public enum OS {WINDOWS, LINUX};

    // Método para leer todo el contenido de un archivo como una cadena
    public static String read(String filename) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))) {
            String data = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            return data;
        } catch (IOException e) {
            throw e;
        }
    }

    // Método para leer todas las líneas de un archivo como una lista de cadenas
    public static List<String> readlines(String filename, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        String data = read(filename);
        List<String> res = new LinkedList<>();
        if (data.length() > 0) {
            res = Arrays.asList(data.split(delim));
        }
        return res;
    }

    // Sobrecarga del método readlines para usar OS.LINUX por defecto
    public static List<String> readlines(String filename) throws IOException {
        return readlines(filename, OS.LINUX);
    }

    // Método para leer todas las líneas de un archivo como un arreglo de cadenas
    public static String[] readlinesAsArray(String filename, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        String data = read(filename);
        String[] res = new String[]{};
        if (data.length() > 0) {
            res = data.split(delim);
        }
        return res;
    }

    // Sobrecarga del método readlinesAsArray para usar OS.LINUX por defecto
    public static String[] readlinesAsArray(String filename) throws IOException {
        return readlinesAsArray(filename, OS.LINUX);
    }

    // Método privado para escribir datos de tipo byte en un archivo
    private static void writeText(byte[] data, String filename) throws IOException {
        try (BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(filename, true))) {
            out.write(data);
        } catch (IOException e) {
            throw e;
        }
    }

    // Método para añadir una cadena al final de un archivo
    public static void append(String data, String filename) throws IOException {
        writeText(data.getBytes(), filename);
    }

    // Método para añadir un arreglo de cadenas al final de un archivo
    public static void append(String[] data, String filename, boolean withNewLine, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            if (withNewLine) {
                sb.append(item).append(delim);
            } else {
                sb.append(item);
            }
        }
        writeText(sb.toString().getBytes(), filename);
    }

    // Sobrecarga del método append para usar OS.LINUX por defecto
    public static void append(String[] data, String filename, boolean withNewLine) throws IOException {
        append(data, filename, withNewLine, OS.LINUX);
    }

    // Sobrecarga del método append para añadir una nueva línea por defecto
    public static void append(String[] data, String filename) throws IOException {
        append(data, filename, true);
    }

    // Método para añadir una lista de cadenas al final de un archivo
    public static void append(List<String> data, String filename, boolean withNewLine, OS os) throws IOException {
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            if (withNewLine) {
                sb.append(item).append(delim);
            } else {
                sb.append(item);
            }
        }
        writeText(sb.toString().getBytes(), filename);
    }

    // Sobrecarga del método append para usar OS.LINUX por defecto
    public static void append(List<String> data, String filename, boolean withNewLine) throws IOException {
        append(data, filename, withNewLine, OS.LINUX);
    }

    // Sobrecarga del método append para añadir una nueva línea por defecto
    public static void append(List<String> data, String filename) throws IOException {
        append(data, filename, true);
    }

}
