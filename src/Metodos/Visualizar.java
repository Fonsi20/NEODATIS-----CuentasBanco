package Metodos;

import Clases.Cliente;
import Clases.Cuenta;
import Clases.CuentaCorriente;
import Clases.CuentaPlazo;
import Clases.Movimiento;
import static banco_neodatis.EntradaTeclado.read;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author a16alfonsofa
 */
public class Visualizar {

    static void VerCuentasBancarias() {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        Values val = odb.getValues(new ValuesCriteriaQuery(CuentaCorriente.class)
                .field("Numero")
                .field("Sucursal")
                .field("SaldoActual"));

        while (val.hasNext()) {
            ObjectValues ov = (ObjectValues) val.next();

            System.out.println("\n\n------------------------------\n"
                    + "\nNumero: " + ov.getByAlias("Numero")
                    + "\n\tSucursal: \t" + ov.getByAlias("Sucursal")
                    + "\n\tSaldoActual: \t" + ov.getByAlias("SaldoActual"));
        }
        odb.close();

    }

    static void VerCuentasBancariasPlazos() {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        Values val = odb.getValues(new ValuesCriteriaQuery(CuentaPlazo.class)
                .field("Numero")
                .field("Sucursal")
                .field("SaldoActual")
                .field("intereses"));

        while (val.hasNext()) {
            ObjectValues ov = (ObjectValues) val.next();

            System.out.println("\n\n------------------------------\n"
                    + "\nNumero: " + ov.getByAlias("Numero")
                    + "\n\tSucursal: " + ov.getByAlias("Sucursal")
                    + "\n\tSaldoActual: " + ov.getByAlias("SaldoActual")
                    + "\n\tIntereses: " + ov.getByAlias("intereses"));
        }
        odb.close();

    }

    static void VerClientes() {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        Values val = odb.getValues(new ValuesCriteriaQuery(Cliente.class)
                .field("dni")
                .field("nombre")
                .field("direccion"));

        while (val.hasNext()) {
            ObjectValues ov = (ObjectValues) val.next();

            System.out.println("\n\n------------------------------\n"
                    + "\nNumero: " + ov.getByAlias("dni")
                    + "\n\tDNI: " + ov.getByAlias("nombre")
                    + "\n\tNombre: " + ov.getByAlias("direccion"));
        }
        odb.close();

    }

    static void ClientePlazos() {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        IQuery query = new CriteriaQuery(CuentaPlazo.class);
        Objects<CuentaPlazo> objects = odb.getObjects(query);

        if (!objects.isEmpty()) {
            for (CuentaPlazo CP : objects) {
                if (CP.getCliente().isEmpty()) {
                    System.out.println("'ERROR': Una cuenta sin Clientes.");
                }
                for (Cliente cli : CP.getCliente()) {
                    System.out.println("- " + cli.getDni() + " // " + cli.getNombre() + " // " + CP.getNumero());
                }
            }
        } else {
            System.err.println("\n'ERROR': No hay Clientes\n");
        }

        odb.close();

    }

    static void ClientePlazos(String dni) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        IQuery query = new CriteriaQuery(Cliente.class, Where.equal("dni", dni));;
        Objects<Cliente> objects = odb.getObjects(query);

        if (!objects.isEmpty()) {
            Cliente clie = objects.getFirst();
            if (clie.getCuenta().isEmpty()) {
                System.out.println("'ERROR': Este cliente no tiene cuentas.");
            }
            for (Cuenta l : clie.getCuenta()) {
                System.out.println("- " + clie.getDni() + " // " + clie.getNombre() + " // " + l.getNumero());
            }

        } else {
            System.err.println("\n'ERROR': No hay Clientes\n");
        }

        odb.close();

    }

    public static void clientesC() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        ICriterion crit = Where.like("nombre", "C%|c%");
        CriteriaQuery query = new CriteriaQuery(Cliente.class, crit);

        Objects<Cliente> clie = odb.getObjects(query);

        while (clie.hasNext()) {
            Cliente cli = clie.next();
            System.out.println("- " + cli.getDni() + " // " + cli.getNombre() + " // " + cli.getDireccion());
        }

        odb.close();

    }

    public static void clientesRicos() {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        IQuery query = new CriteriaQuery(Cuenta.class, Where.gt("SaldoActual", 200000d)).setPolymorphic(true);;
        Objects<Cuenta> objects = odb.getObjects(query);

        if (!objects.isEmpty()) {
            while (objects.hasNext()) {
                Cuenta c = objects.next();
                for (Cliente cli : c.getCliente()) {
                    System.out.println("- " + cli.getDni() + " // " + cli.getNombre() + " // " + c.getNumero());
                }
            }
        } else {
            System.err.println("\n'ERROR': No hay cuentas con más de 200000€\n");
        }

        odb.close();

    }

    public static void clientesPobres() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        IQuery query = new CriteriaQuery(Cuenta.class, Where.lt("SaldoActual", 0d)).setPolymorphic(true);;
        Objects<Cuenta> objects = odb.getObjects(query);

        if (!objects.isEmpty()) {
            while (objects.hasNext()) {
                Cuenta c = objects.next();
                for (Cliente cli : c.getCliente()) {
                    System.out.println("- " + cli.getDni() + " // " + cli.getNombre() + " // " + c.getNumero());
                }
            }
        } else {
            System.err.println("\n'ERROR': No hay cuentas con menos de 0€\n");
        }

        odb.close();

    }

    public static void saldoMedioClientes() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        Values val = odb.getValues(new ValuesCriteriaQuery(CuentaPlazo.class).avg("SaldoActual"));
        ObjectValues ov = val.nextValues();
        BigDecimal value = (BigDecimal) ov.getByAlias("SaldoActual");

        System.out.print("El saldo medio de todas las cuentas a plazos es:\n> " + value.longValue());

        odb.close();
    }

    public static void movimientosDeUnaCuenta() throws IOException, ParseException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        VerCuentasBancarias();
        System.out.print("\n\nNumero de cuenta Bancara sobre la que realizar la consulta:"
                + "\n> ");
        String nCuenta = read.readLine();

        IQuery query = new CriteriaQuery(CuentaCorriente.class, Where.equal("Numero", nCuenta));
        Objects<CuentaCorriente> objects = odb.getObjects(query);

        if (!objects.isEmpty()) {
            CuentaCorriente aux = objects.getFirst();
            System.err.println(aux);
            System.err.println(aux.getMovimientos());
            if (!aux.getMovimientos().isEmpty()) {

                System.out.println("Fecha de inicio: (dd/MM/yyyy)");
                String fechaI = read.readLine();
                LocalDate dateI = LocalDate.parse(fechaI, dateFormat);

                System.out.println("Fecha de fin: (dd/MM/yyyy)");
                String fechaF = read.readLine();
                LocalDate dateF = LocalDate.parse(fechaF, dateFormat);

                System.out.println("\n"
                        + "                                       Movimientos                                          \n"
                        + "--------------------------------------------------------------------------------------------");
                for (Movimiento m : aux.getMovimientos()) {
                    if (m.getFechaOperacion().isAfter(dateI) && m.getFechaOperacion().isBefore(dateF)) {
                        System.out.println("Numero de Cuenta:" + m.getNumeroCta() + "\t > Cantidad: " + m.getCantidad() + " // Fecha Operacion: " + m.getFechaOperacion() + " // Hora: " + m.getHora());
                    }
                }
                System.out.println("--------------------------------------------------------------------------------------------\n");
            } else {
                System.err.println("La Cuenta Corriente seleccionada no tiene movimientos.");
            }
        } else {
            System.err.println("\nERROR: Numero de cuenta corriente no encontrado.\n");
        }
        odb.close();

    }

}
