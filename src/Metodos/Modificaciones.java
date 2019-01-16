package Metodos;

import Clases.CuentaPlazo;
import static banco_neodatis.EntradaTeclado.read;
import java.io.IOException;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author a16alfonsofa
 */
public class Modificaciones {

    public static void cambiarIntereses() throws IOException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Cuentas");

        CuentaPlazo CP = null;
        String numeroCta, dni;
        int opc2 = 0, opc3 = 0;
        float intereses;
        CriteriaQuery query;

        do {
            do {
                Visualizar.ClientePlazos();
                System.out.print("DNI del cliente:\n> ");
                dni = read.readLine();
                opc3 = Comprobaciones.validarDNI(dni);
            } while (opc3 != 0);

            do {
                Visualizar.ClientePlazos(dni);
                System.out.print("Numero Cuenta Plazos:\n> ");
                numeroCta = read.readLine();
                opc3 = Comprobaciones.comprobarNC(numeroCta);
            } while (opc3 != 0);

            ICriterion crit = new And().add(Where.equal("Numero", numeroCta));
            query = new CriteriaQuery(CuentaPlazo.class, crit);

            CP = (CuentaPlazo) odb.getObjects(query).getFirst();

            System.out.print("Los anteriores intereses de esta cuenta son: " + CP.getIntereses()
                    + "\nIntroduce los nuevos intereses de la cuenta:\n> ");
            intereses = Float.parseFloat(read.readLine());

            if (CP == null) {
                opc2 = 1;
                System.err.println("'ERROR' No existe esa cuenta en la BBDD");

            } else {
                opc2 = 0;
                CP.setIntereses(intereses);
                odb.store(CP);
            }
        } while (opc2 != 0);

        odb.close();

    }

}
