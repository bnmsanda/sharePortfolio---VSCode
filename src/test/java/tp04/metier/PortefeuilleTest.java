package tp04.metier;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PortefeuilleTest {

    @Test
    void testAfficherActionsSousFormeDeCartes() {
        Portefeuille portefeuille = new Portefeuille();
        Action action1 = new ActionSimple("Action A");
        Action action2 = new ActionSimple("Action B");

        portefeuille.buyExistingAction(action1);
        portefeuille.buyExistingAction(action2);

        // Appeler la méthode pour vérifier qu'elle affiche correctement les cartes
        portefeuille.afficherActionsSousFormeDeCartes();
    }
}