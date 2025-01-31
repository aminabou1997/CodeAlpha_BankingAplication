package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Cloud
 */
public class Validator {

    /**
     * Método estático para validar el formato de un IBAN.
     *
     * @param iban IBAN a validar.
     * @return true si el formato es válido, false de lo contrario.
     */
    public static boolean validarIBAN(String iban) {
        return iban.matches("ES\\d{22}");
    }

    /**
     * Valida un nombre del Titular.
     *
     * @param nombre Nombre a validar.
     * @return true si el nombre es válido, false de lo contrario.
     */
    public static boolean validarNombre(String nombre) {
        // Verificar que la longitud del nombre no exceda los 40 caracteres
        if (nombre.length() > 40) {
            return false; // Nombre demasiado largo
        }
        for (int i = 0; i < nombre.length(); i++) {
            char caracter = nombre.charAt(i);
            // Verificar que el nombre no contenga dígitos
            if (Character.isDigit(caracter)) {
                return false; // El nombre no puede contener dígitos
            }
        }
        return true;
    }

    /**
     * Valida que los apellidos cumplan con los requisitos especificados.
     *
     * @param apellidos Los apellidos a validar.
     * @return true si los apellidos son válidos, false en caso contrario.
     */
    public static boolean validarApellidos(String apellidos) {
        // Verificar que la longitud de los apellidos no exceda los 60 caracteres
        if (apellidos.length() > 60) {
            return false; // Apellidos demasiado largos
        }
        char caracter;
        for (int i = 0; i < apellidos.length(); i++) {
            caracter = apellidos.charAt(i);
            // Verificar que los apellidos no contengan dígitos
            if (Character.isDigit(caracter)) {
                return false; // Los apellidos no pueden contener dígitos
            }
        }
        return true;
    }

    /**
     * Valida que el formato del DNI sea correcto.
     *
     * @param DNI El número de DNI a validar.
     * @return true si el DNI tiene el formato correcto, false en caso
     * contrario.
     */
    public static boolean validarDNI(String DNI) {
        // Definir el formato esperado del DNI utilizando una expresión regular
        String formatoDNI = "\\d{8}[A-HJ-NP-TV-Z]";
        // Compilar la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(formatoDNI);
        // Crear un objeto Matcher para el DNI proporcionado
        Matcher matcher = pattern.matcher(DNI);
        // Devolver true si el DNI coincide con el formato esperado, false en caso contrario
        return matcher.matches();
    }

}


