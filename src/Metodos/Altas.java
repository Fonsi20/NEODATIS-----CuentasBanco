package Metodos;

import Clases.Cliente;
import Clases.CuentaCorriente;
import Clases.CuentaPlazo;
import Clases.Movimiento;
import static banco_neodatis.EntradaTeclado.read;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author a16alfonsofa
 */
public class Altas {

    public static void CuentaCorriente() throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        CuentaCorriente CC = null;
        Double SaldoActual;
        String Numero, Sucursal;
        int opc = 0, opc2 = 0, opc3 = 0;

        IQuery query;

        do {
            System.out.print("Nombre de la Sucursal:\n> ");

            Sucursal = read.readLine();

            do {
                System.out.print("Numero Cuenta Corriente:\n> ");
                Numero = read.readLine();
                opc3 = Comprobaciones.comprobarNC(Numero);
            } while (opc3 != 0);

            System.out.print("Saldo Actual de la cuenta:\n> ");
            SaldoActual = Double.parseDouble(read.readLine());

            query = new CriteriaQuery(CuentaCorriente.class, Where.equal("Numero", Numero));
            Objects<CuentaCorriente> objects = odb.getObjects(query);

            if (objects.isEmpty()) {

                opc2 = 0;
                CC = new CuentaCorriente(Numero, Sucursal, SaldoActual);

                System.err.println("----------------------------------------------"
                        + "\nAñade un Cliente a esta cuenta con numero: " + Numero + ": \n");
                do {
                    AñadirCliente(CC);
                    opc = Comprobaciones.PreguntaSiNO();
                } while (opc != 1);

            } else {
                opc2 = 1;
                System.err.println("Ya existe esa CUENTA en la BBDD.");
            }
        } while (opc2 != 0);
        odb.store(CC);
        odb.close();

    }

    public static void CuentaPlazos() throws IOException, ParseException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        CuentaPlazo CP = null;
        Double SaldoActual;
        String Numero, Sucursal;
        float intereses = 0;
        Date fechaVencimiento;
        int opc = 0, opc2 = 0, opc3 = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        IQuery query;

        do {
            System.out.print("Nombre de la Sucursal:\n> ");
            Sucursal = read.readLine();

            do {
                System.out.print("Numero Cuenta Corriente:\n> ");
                Numero = read.readLine();
                opc3 = Comprobaciones.comprobarNC(Numero);
            } while (opc3 != 0);

            System.out.print("Saldo Actual de la cuenta:\n> ");
            SaldoActual = Double.parseDouble(read.readLine());

            System.out.print("Intereses de la cuenta:\n> ");
            intereses = Float.parseFloat(read.readLine());

            System.out.print("Fecha de vencimiento de la cuenta:\n> ");
            String fecha = read.readLine();
            fechaVencimiento = sdf.parse(fecha);

            query = new CriteriaQuery(CuentaPlazo.class, Where.equal("Numero", Numero));
            Objects<CuentaCorriente> objects = odb.getObjects(query);

            if (objects.isEmpty()) {

                opc2 = 0;
                CP = new CuentaPlazo(intereses, fechaVencimiento, Numero, Sucursal, SaldoActual);

                System.err.println("----------------------------------------------"
                        + "\nAñade un Cliente a esta cuenta con numero: " + Numero + ": \n");
                do {
                    AñadirCliente(CP);
                    opc = Comprobaciones.PreguntaSiNO();
                } while (opc != 1);

            } else {
                opc2 = 1;
                System.err.println("Ya existe esa CUENTA en la BBDD.");
            }
        } while (opc2 != 0);
        odb.store(CP);
        odb.close();
    }

    public static void Movimiento() throws IOException, ParseException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        Movimiento M = null;
        String numeroCta;
        int opc = 0, opc2 = 0, opc3 = 0;
        LocalDate fechaOperacion;
        LocalTime  hora;
        float cantidad;
        Double SaldoAnterior;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

        IQuery query;

        do {
            do {
                System.out.print("Numero Cuenta Corriente:\n> ");
                numeroCta = read.readLine();
                opc3 = Comprobaciones.comprobarNC(numeroCta);
            } while (opc3 != 0);

            System.out.print("Cantidad de dinero introducido o retirado en la operación:\n> ");
            cantidad = Float.parseFloat(read.readLine());

            System.out.print("Fecha en la cual se realizó la operación:(dd/mm/aaaa)\n> ");
            fechaOperacion = LocalDate.now();

            System.out.print("Hora en la cual se realizó la operación:(hh:mm:ss)\n> ");
            hora = LocalTime.now();

            query = new CriteriaQuery(CuentaCorriente.class, Where.equal("Numero", numeroCta));
            Objects<CuentaCorriente> objects = odb.getObjects(query);

            System.out.print("El saldo actual antes de realizar el movimiento es de:\n> " + objects.getFirst().getSaldoActual()
                    + "\nDespues de realizar el movimiento será de:\n> " + (objects.getFirst().getSaldoActual() + cantidad));

            if (objects.isEmpty()) {

                opc2 = 1;
                System.err.println("'ERROR' No existe esa cuenta en la BBDD");

            } else {
                opc2 = 0;
                M = new Movimiento(numeroCta, fechaOperacion, hora, cantidad, objects.getFirst().getSaldoActual());

            }
        } while (opc2 != 0);

        CuentaCorriente CC = (CuentaCorriente) odb.getObjects(query).getFirst();
        CC.setSaldoActual(CC.getSaldoActual() + cantidad);
        odb.store(CC);
        odb.store(M);
        odb.close();

    }

    private static void AñadirCliente(CuentaCorriente CC) throws IOException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        String dni, nombre, direccion;
        int opc = 0, opc2 = 0;

        IQuery query;

        do {

            do {
                System.out.print("DNI del cliente:\n> ");
                dni = read.readLine();
                opc2 = Comprobaciones.validarDNI(dni);
            } while (opc2 != 0);

            System.out.print("Nombre del cliente:\n> ");
            nombre = read.readLine();

            System.out.print("Direccion del cliente:\n> ");
            direccion = read.readLine();

            query
                    = new CriteriaQuery(Cliente.class,
                            Where.equal("dni", dni));
            Objects<Cliente> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = 0;
                Cliente cli = new Cliente(dni, nombre, direccion);
                CC.getCliente().add(cli);
                cli.getCuenta().add(CC);

            } else {
                opc = 1;
                System.err.println("Ya existe este cliente en la BBDD.");
            }
        } while (opc != 0);
        odb.close();

    }

    private static void AñadirCliente(CuentaPlazo CP) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        String dni, nombre, direccion;
        int opc = 0, opc2 = 0;

        IQuery query;

        do {

            do {
                System.out.print("DNI del cliente:\n> ");
                dni = read.readLine();
                opc2 = Comprobaciones.validarDNI(dni);
            } while (opc2 != 0);

            System.out.print("Nombre del cliente:\n> ");
            nombre = read.readLine();

            System.out.print("Direccion del cliente:\n> ");
            direccion = read.readLine();

            query
                    = new CriteriaQuery(Cliente.class,
                            Where.equal("dni", dni));
            Objects<Cliente> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = 0;
                Cliente cli = new Cliente(dni, nombre, direccion);
                CP.getCliente().add(cli);
                cli.getCuenta().add(CP);

            } else {
                opc = 1;
                System.err.println("Ya existe este cliente en la BBDD.");
            }
        } while (opc != 0);
        odb.close();
    }

}
