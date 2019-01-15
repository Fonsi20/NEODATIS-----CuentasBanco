package Metodos;

import Clases.CuentaCorriente;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
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

            System.out.println("Numero: " + ov.getByAlias("Numero")
                    + "\n Sucursal: " + ov.getByAlias("Sucursal")
                    + "\n SaldoActual: " + ov.getByAlias("SaldoActual"));
        }
        odb.close();

    }

}
