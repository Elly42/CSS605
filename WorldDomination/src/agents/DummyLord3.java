package agents;
import java.util.Random;
import risk.*;

/**
 *
 * @author Omar A. Guerrero
 */

public class DummyLord3 extends Agent{

    public DummyLord3(int id, int type){
        super(id, type);
        empireName = "DummyEmpire3";
    }

    public void attack(){
        // makes as many soldiers as possible
        myTerritory.produceSoldiers(myTerritory.getNatRes(), myTerritory.getPeasants());
        attackingSoldiers = myTerritory.getSoldiers();//attacks with all soldiers
        //attacks random territory
        attackedTerritoryID = (new Random()).nextInt(myTerritory.getNeighbors().numObjs) + 1;
    }
}
