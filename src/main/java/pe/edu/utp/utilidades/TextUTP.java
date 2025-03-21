package pe.edu.utp.utilidades;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class TextUTP {

    // Enumeración para los sistemas operativos soportados
    public enum OS {WINDOWS, LINUX};

    // Método para leer todo el contenido de un archivo como una cadena con una codificación específica
    public static String read(String filename, Charset charset) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))) {
            byte[] bytes = in.readAllBytes();
            return new String(bytes, charset);
        } catch (IOException e) {
            throw e;
        }
    }

    // Método para leer todas las líneas de un archivo como una lista de cadenas con una codificación específica
    public static List<String> readlines(String filename, OS os, Charset charset) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename), charset);
        } catch (IOException e) {
            throw e;
        }
        return lines;
    }

    // Sobrecarga del método readlines para usar OS.LINUX y UTF-8 por defecto
    public static List<String> readlines(String filename) throws IOException {
        return readlines(filename, OS.LINUX, StandardCharsets.UTF_8);
    }

    // Sobrecarga del método readlines para usar OS.LINUX y una codificación específica
    public static List<String> readlines(String filename, Charset charset) throws IOException {
        return readlines(filename, OS.LINUX, charset);
    }

    // Método para leer todas las líneas de un archivo como un arreglo de cadenas con una codificación específica
    public static String[] readlinesAsArray(String filename, OS os, Charset charset) throws IOException {
        String data = read(filename, charset);
        String delim = (os == OS.WINDOWS) ? "\r\n" : "\n";
        return data.split(delim);
    }

    // Sobrecarga del método readlinesAsArray para usar OS.LINUX y UTF-8 por defecto
    public static String[] readlinesAsArray(String filename) throws IOException {
        return readlinesAsArray(filename, OS.LINUX, StandardCharsets.UTF_8);
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
