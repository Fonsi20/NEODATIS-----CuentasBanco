package Metodos;

import static banco_neodatis.EntradaTeclado.read;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author a16alfonsofa
 */
public class Comprobaciones {

    static int comprobarNC(String cuenta) {
        boolean esValido = false;
        int i = 2;
        int caracterASCII = 0;
        int resto = 0;
        int dc = 0;
        String cadenaDc = "";
        BigInteger cuentaNumero = new BigInteger("0");
        BigInteger modo = new BigInteger("97");

        if (cuenta.length() == 24 && cuenta.substring(0, 1).toUpperCase().equals("E")
                && cuenta.substring(1, 2).toUpperCase().equals("S")) {

            do {
                caracterASCII = cuenta.codePointAt(i);
                esValido = (caracterASCII > 47 && caracterASCII < 58);
                i++;
            } while (i < cuenta.length() && esValido);

            if (esValido) {
                cuentaNumero = new BigInteger(cuenta.substring(4, 24) + "142800");
                resto = cuentaNumero.mod(modo).intValue();
                dc = 98 - resto;
                cadenaDc = String.valueOf(dc);
            }

            if (dc < 10) {
                cadenaDc = "0" + cadenaDc;
            }

            // Comparamos los caracteres 2 y 3 de la cuenta (dígito de control IBAN) con cadenaDc
            if (cuenta.substring(2, 4).equals(cadenaDc)) {
                esValido = true;
            } else {
                esValido = false;
            }
        }

        if (esValido == true) {
            return 0;
        } else {
            return 1;
        }
    }

    static int PreguntaSiNO() throws IOException {
        int resul = 0;
        System.out.println("Desea continuar?  (0 - SI / 1 - NO):\n");
        resul = Integer.parseInt(read.readLine());
        if (resul > 1 && resul < 0) {
            System.err.println("'Error' No se introdujo un valor válido.");
            resul = 1;
        }
        return resul;
    }

    public static int validarDNI(String dni) {

        String letraMayuscula = "";
        if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false) {
            return 1;
        }

        letraMayuscula = (dni.substring(8)).toUpperCase();
        if (soloNumeros(dni) == true && letraDNI(dni).equals(letraMayuscula)) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean soloNumeros(String dni) {

        int i, j = 0;
        String numero = "";
        String miDNI = "";
        String[] unoNueve = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (i = 0; i < dni.length() - 1; i++) {
            numero = dni.substring(i, i + 1);

            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) {
                    miDNI += unoNueve[j];
                }
            }
        }

        if (miDNI.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    public static String letraDNI(String dni) {
        int miDNI = Integer.parseInt(dni.substring(0, 8));
        int resto = 0;
        String miLetra = "";
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        resto = miDNI % 23;

        miLetra = asignacionLetra[resto];

        return miLetra;
    }

}
