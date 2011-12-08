/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import java.util.Random;
import risk.Agent;
import risk.Territory;
import sim.util.Bag;

/**
 *
 * @author Elly
 */
public class WhenIamKing extends Agent {

    public WhenIamKing(int id, int type) {
        super(id, type);  // calls constructor from Agent 
        empireName = "Sea State";

    }
    private static final int NATRES = 1;
    private static final int PEASANTS = 2;
    private static final int PIRATES = 3;
    private boolean revolution = false;
    private int TurnsSinceCapture = 0;

    private enum statusTypes {

        FINE, POOR, EXTINCT, PEACEFUL, B1TCH
    }
    private statusTypes state = statusTypes.FINE;
    /*
     * Should update the trade array. The positions if the array should contain information in the following order:
     * 0: ID of the potential trading partner
     * 1: Type of good demanded (1 for natural resources, 2 for peasants, and 3 for soldiers)
     * 2: Amount of the demanded good
     * 3: Type of good offered in exchange
     * 4: Amount of the offered good
     */

    @Override
    protected void trade() {
        double natres = myTerritory.getNatRes();
        double peasants = myTerritory.getPeasants();
        double pirates = myTerritory.getSoldiers();
        double alpha = myTerritory.getAlpha();
        double diff = pirates - natres;
        if (diff>0){
        trade[0] = (new Random()).nextInt(myTerritory.getNeighbors().numObjs);
        // should choose a random trading partner
        trade[1] = NATRES;
        trade[2] = diff;
        trade[3] = PEASANTS;
        if (peasants >= diff){
            trade[4] = diff;           
        } else{
            trade [4] = peasants;
        }
        
        }
    }

    /*
     * Should update acceptTrade.
     */
    @Override
    protected void acceptTrade(Territory offerer, double demand, int typeDemand,
            double offer, int typeOffer) {
        double natres = myTerritory.getNatRes();
        double peasants = myTerritory.getPeasants();
        double pirates = myTerritory.getSoldiers();
        /* stores the resources
         * 0 - # of nat res
         * 1 - # of peasants
         * 2 - # of soldiers
         */
        double[] resources = {natres, peasants, pirates};
        getState();


        if (myTerritory.getSuperior() == null || offerer != myTerritory.getSuperior()) {
            if (demand <= 2 * offer) {
                if (state == statusTypes.FINE && demand != NATRES) {
                    if (typeOffer == NATRES && resources[typeDemand - 1] >= demand) {
                        acceptTrade = true;
                    }
                    if (typeOffer == PEASANTS && resources[typeDemand - 1] >= demand && demand <= offer / 2) {
                        acceptTrade = true;
                    }
                    if (typeOffer == PIRATES && resources[typeDemand - 1] >= demand && demand <= offer/2) {
                        acceptTrade = true;
                    } else {
                        acceptTrade = false;
                    }

                } else {
                    acceptTrade = false;
                }

            } else if (state != statusTypes.FINE) {
                acceptTrade = false; // replace this with actual stuff later
            }

        } else {
            acceptTrade = false;
        }

    }

    /*
     * Should update tax.
     */
    @Override
    protected void chooseTax() {
        tax = .5;
    }

    /*
     * Should update the beneficiaries bag and the retributions array.
     */
//    @Override
//    protected void setRetributionsAndBeneficiaries() {
//    }

    /*
     * Should update attackedTerritoryID and the attackingSoldiers
     */
    @Override
    protected void attack() {// add more pirates when not enough if possible
        getState();
        Bag targets = myTerritory.getNeighbors();
        int target = -1;
        int weakestLink = 0;
        double wlSoldiers = 0;
        double newPirates = 0;
        boolean win = false;

        if (state == statusTypes.B1TCH) {
            TurnsSinceCapture = TurnsSinceCapture + 1;
        } else {
            TurnsSinceCapture = 0;
        }

        if (TurnsSinceCapture <= 3) {
            // attack weakest neighbor if >= 50% chance of winning otherwise attack with 0 soldiers
            for (int j = 0; j < targets.size(); j++) {
                Territory t = (Territory) targets.get(j);
                Territory w = (Territory) targets.get(weakestLink);
                if (t.getSoldiers() < w.getSoldiers() && t.getType() != myTerritory.getType()) {
                    weakestLink = j;
                    wlSoldiers = w.getSoldiers();
                    win = canWin (.4, t);
                }
            }
            attackedTerritoryID = weakestLink;
            if (win){
            newPirates = needPiratesA(.4, wlSoldiers);
            if (newPirates > 0) {
                makePirates(newPirates);
            }
            attackingSoldiers = myTerritory.getSoldiers();
            } else attackingSoldiers = 0;
        }else {
            // attack my superior with all soldiers available
            growPirates(myTerritory.getNatRes(), myTerritory.getPeasants());
            attackingSoldiers = myTerritory.getSoldiers();
            int supID = myTerritory.getSuperior().getId();
            for (int k = 0; k < targets.size(); k++) {
                Territory t = (Territory) targets.get(k);
                int id = t.getId();
                if (id == supID) {
                    target = k;
                    break;
                }
            }
            attackedTerritoryID = target;
            TurnsSinceCapture=0;
        }
    }

    /*
     * Should update the defendingSoldiers
     */
    @Override
    protected void defend(Territory attacker, double soldiersAttacking) {
        revolution = false;
        Bag vassles = myTerritory.getSubordinates();
        // determines is my subordinates are revolting
        if (vassles != null) {
            for (int l=0; l < vassles.size(); l++){
                Territory t = (Territory) vassles.get(l); 
                if (t.getId() == attacker.getId()) {
                    revolution = true;
                }
            }
        }
        //if revolting defend with everything otherwise defend if >40% chance of winning
        if (revolution == true) {
            growPirates(myTerritory.getNatRes(), myTerritory.getPeasants());
            defendingSoldiers = myTerritory.getSoldiers();
        } else {
            boolean win = canWin(.3, attacker);
            if (win){
            double defendersNeeded = needPirates(.6, soldiersAttacking);
            if (defendersNeeded > 0) {
                makePirates(defendersNeeded);
            }
            defendingSoldiers = myTerritory.getSoldiers();
            } else defendingSoldiers = 0;
        }
    }

    protected void getState() {
        double natres = myTerritory.getNatRes();
        double peasants = myTerritory.getPeasants();
        double pirates = myTerritory.getSoldiers();
        if (myTerritory.getSuperior() == null) {
            if (natres < 2 * pirates) {
                state = statusTypes.POOR;
            } else if (peasants < 20 || peasants < pirates) {
                state = statusTypes.EXTINCT;
            } else if (pirates < 10) {
                state = statusTypes.PEACEFUL;
            } else {
                state = statusTypes.FINE;
            }
        } else {
            state = statusTypes.B1TCH;
        }
        System.out.println("GBS");
        System.out.println("peasants: " + peasants + ";   Natural Resources: " + natres
                + ";   Soldiers: " + pirates);


    }

    // checks if more soldiers are needed for defending and determines how many        
    private double needPirates(double percent, double marauders){
        double pirates = myTerritory.getSoldiers();
        double morePiratesNeeded = 0;
        if (pirates < (1-percent)/percent* marauders) {
            double piratesNeeded =(1-percent)/percent* marauders;
            morePiratesNeeded = piratesNeeded - pirates;
        }
        return morePiratesNeeded;
    }
    
    //adjusts inputs to account for difference in equation of attackers and defenders
    private double needPiratesA (double percent, double marauders){
        return needPirates(1-percent, marauders);
    }

    // determines best use of resources to make soldiers and makes them
    private void makePirates(double piratesWanted) {
        double alpha = myTerritory.getAlpha();
        double natRes = myTerritory.getNatRes();
        double peasants = myTerritory.getPeasants();
        double pirates = myTerritory.getSoldiers();
        double totalPirates = pirates + piratesWanted;
        double pNeed = 0;
        double nNeed = 0;
        // where it may be possible to feed the new soldiers next turn
        if (natRes >= totalPirates) {
            // if peasant growth is more limited
            if (myTerritory.getNatRes()>= myTerritory.getPeasants()) {
//            if (myTerritory.getFoodGrowth() >= myTerritory.getFarmGrowth()) {
                // if using the max natural resources
                pNeed = Math.pow((piratesWanted / Math.pow(natRes - totalPirates, alpha)), (1 / (1 - alpha)));
                growPirates(natRes - totalPirates, pNeed); 
            } else {
                // if nat res growth is more limited make using max peasants
                nNeed = Math.pow((piratesWanted / Math.pow(peasants, (1 - alpha))), (1 / alpha));
                    growPirates(nNeed, peasants);
            }
        } else {
            if (myTerritory.getFoodGrowth() >= myTerritory.getFarmGrowth()) {
                pNeed = Math.pow((piratesWanted / Math.pow(natRes, alpha)), (1 / (1 - alpha)));
                    growPirates(natRes, pNeed);
            } else {
                nNeed = Math.pow((piratesWanted / Math.pow(peasants, (1 - alpha))), (1 / alpha));
                    growPirates(nNeed, peasants);
            }
        }
    }
    
    private boolean canWin(double percent, Territory attacker){
        double potentialPirates = myTerritory.getSoldiers()+Math.pow(myTerritory.getNatRes(),myTerritory.getAlpha())*
                Math.pow(myTerritory.getPeasants(),1-myTerritory.getAlpha());
        double potentialAttackers = attacker.getSoldiers() +Math.pow(attacker.getNatRes(),attacker.getAlpha())*
                Math.pow(attacker.getPeasants(),1-attacker.getAlpha());
        if (potentialPirates/(potentialPirates+potentialAttackers) >= percent){
            return true;
        } else return false;
        
    }
    
    private void growPirates(double NatRes, double peasants){
        if (myTerritory.getNatRes() >0 && myTerritory.getPeasants() >0){
            myTerritory.produceSoldiers(NatRes, peasants);
        } else return;
    }
}
