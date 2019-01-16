/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Clases.CuentaPlazo;
import static banco_neodatis.EntradaTeclado.read;
import java.io.IOException;
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
public class Bajas {

    public static void cerrarCuentaPlazo() throws IOException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");
        int opc = 0, opc2 = 0;

        do {
            System.out.println("\nBorrar una cuenta a plazos.\nEscoge a un cliente, escribe su dni:");
            Visualizar.ClientePlazos();
            System.out.print("> ");
            String dni = read.readLine();

            System.out.println("Escoge una cuenta, escribe el numero de cuenta:");
            Visualizar.ClientePlazos(dni);
            System.out.print("> ");
            String nCuenta = read.readLine();

            IQuery query = new CriteriaQuery(CuentaPlazo.class, Where.equal("Numero", nCuenta));
            Objects<CuentaPlazo> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = 1;
                System.err.println("'ERROR' - Cliente con ese numero de cuenta.");

            } else {
                CuentaPlazo CP = (CuentaPlazo) odb.getObjects(query).getFirst();
                odb.delete(CP);
            }
        } while (opc != 0);

        odb.close();

    }

}
