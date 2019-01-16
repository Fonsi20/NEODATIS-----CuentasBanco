package banco_neodatis;

import Metodos.Altas;
import Metodos.Bajas;
import Metodos.Modificaciones;
import Metodos.Visualizar;
import static banco_neodatis.EntradaTeclado.read;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author a16alfonsofa
 */
class Menu {

    public static void MenuPrincipal() throws IOException, ParseException {

        int opc = 0;

        do {

            System.out.println("\n\nBienvenido al programa encargado de CUENTAS BANCARIAS.\n"
                    + "Propiedad de: Alfonso Fernández Álvarez\n"
                    + "----------------------------------------------------------------------------------------\n"
                    + "  1  /  Altas\n\n"
                    + "  2  /  Modificación del atributo intereses en una cuentaPlazo ya existente, recibiendo nºcta y dni del "
                    + "cliente.\n\n"
                    + "  3  / Bajas de una cuenta Plazo de un cliente determinado ,recibiendo como parámetros el nºde cuenta\n\t"
                    + "y el nombre de un cliente, ya que partimos del supuesto que el cliente tiene varias ctas.\n\n"
                    + "  4  /  Crear un método que visualice todos los clientes cuyo nombre empiece por C,utilizando la\n\t"
                    + "interfaz Icriterion.\n\n"
                    + "  5  /  Visualiza todos los Clientes cuyo saldo en la cuentaCorriente sea > de 200.000 euros.\n\n"
                    + "  6  /  Crea un método que obtenga el número de Clientes en números rojos.\n\n"
                    + "  7  /  crea un método que obtenga el saldo medio de las cuentasPlazo de todos los Clientes de la\n\t"
                    + "entidad bancaria.\n\n"
                    + "  8  /  Crea un método que obtenga un extracto, de los movimientos realizados sobre una\n\t"
                    + "cuentaCorriente entre dos fechas determinadas.\n"
                    + "\n"
                    + "  9 /   SALIR DEL PROGRAMA\n"
                    + "----------------------------------------------------------------------------------------\n");

            opc = Integer.parseInt(read.readLine());

            switch (opc) {

                case 1:
                    MenuAltas();
                    break;
                case 2:
                    Modificaciones.cambiarIntereses();
                    break;
                case 3:
                    Bajas.cerrarCuentaPlazo();
                    break;
                case 4:
                    Visualizar.clientesC();
                    break;
                case 5:
                    Visualizar.clientesRicos();
                    break;
                case 6:
                    Visualizar.clientesPobres();
                    break;
                case 7:
                    Visualizar.saldoMedioClientes();
                    break;
                case 8:
                    Visualizar.movimientosDeUnaCuenta();
                    break;
                case 9:
                    System.err.println("Adios!");
                    break;
                default:
                    System.err.println("'Error', elija una opción porfavor");
                    break;

            }

        } while (opc != 9);

    }

    private static void MenuAltas() throws IOException, ParseException {
        int opc = 0;

        do {

            System.out.println("\n\nEscoja un tipo de alta.\n"
                    + "Propiedad de: Alfonso Fernández Álvarez\n"
                    + "----------------------------------------------------------------------------------------\n"
                    + "  1  /  Alta Cuenta Corriente\n"
                    + "  2  /  Alta Cuenta Plazos\n"
                    + "  3  /  Alta Movimiento\n\n"
                    + "  4  /   Volver a menú principal.\n"
                    + "----------------------------------------------------------------------------------------\n");

            opc = Integer.parseInt(read.readLine());

            switch (opc) {

                case 1:
                    Altas.CuentaCorriente();
                    break;
                case 2:
                    Altas.CuentaPlazos();
                    break;
                case 3:
                    Altas.Movimiento();
                    break;
                case 4:
                    System.err.println("Volvemos al menú principal!");
                    break;
                default:
                    System.err.println("'Error', elija una opción porfavor");
                    break;

            }

        } while (opc != 4);

    }

}
