/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp04.metier.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PortefeuilleTest {
    private ActionSimple actionSimple;
    private ActionComposee actionComposee;
    private Portefeuille portefeuille;

    @BeforeEach
    public void setUp() {
        actionSimple = new ActionSimple("Action Simple Test");
        actionComposee = new ActionComposee("Action Composee Test");
        portefeuille = new Portefeuille();
    }

    @Test
    public void testAjoutEtRecuperationValeurActionSimple() {
        actionSimple.ajouterValeur(2025, 100, 50.5);
        assertEquals(50.5, actionSimple.getValue(100, 2025));
    }

    @Test
    public void testValeurInvalideActionSimple() {
        assertThrows(IllegalArgumentException.class, () -> actionSimple.ajouterValeur(2025, -1, 30.0));
        assertThrows(IllegalArgumentException.class, () -> actionSimple.ajouterValeur(2025, 366, 30.0));
        assertThrows(IllegalArgumentException.class, () -> actionSimple.ajouterValeur(2025, 400, 30.0));
        assertThrows(NoSuchElementException.class, () -> actionSimple.getValue(100, 2026));
        actionSimple.ajouterValeur(2025, 100, 0);
        assertThrows(NoSuchElementException.class, () -> actionSimple.getValue(100, 2025));
    }

    @Test
    public void testEnrgCoursEtValeur() {
        actionSimple.enrgCours(150, 2025, 99.99);
        assertEquals(99.99, actionSimple.valeur(150, 2025));
    }

    @Test
    public void testAjoutEtValeurActionComposee() {
        actionSimple.ajouterValeur(2025, 50, 80);
        actionComposee.ajouterComposition(actionSimple, 50);
        assertEquals(40.0, actionComposee.getValue(50, 2025));
    }

    @Test
    public void testErreurValeurActionComposeeSansComposant() {
        assertThrows(IllegalStateException.class, () -> actionComposee.getValue(10, 2025));
    }

    @Test
    public void testAjouterValeurNonSupporteeDansComposee() {
        assertThrows(UnsupportedOperationException.class, () -> actionComposee.ajouterValeur(2025, 10, 100));
    }

    @Test
    public void testAjouterCompositionInvalide() {
        assertThrows(IllegalArgumentException.class, () -> actionComposee.ajouterComposition(actionSimple, 0));
        assertThrows(IllegalArgumentException.class, () -> actionComposee.ajouterComposition(actionSimple, -10));
        assertThrows(IllegalArgumentException.class, () -> actionComposee.ajouterComposition(actionSimple, 110));
    }

    @Test
    public void testAcheterVendreActionSimple() {
        portefeuille.acheter(actionSimple, 10, 75.0, 10, 2025);
        List<String> historique = portefeuille.getHistoriqueTransactions(actionSimple);
        assertEquals(1, historique.size());
        assertTrue(historique.get(0).contains("ACHAT"));

        double montantVente = portefeuille.vendre(actionSimple, 5, 10, 2025);
        assertEquals(375.0, montantVente);
    }

    @Test
    public void testVenteQuantiteExacteEtPleineSuppression() {
        portefeuille.acheter(actionSimple, 3, 60.0, 20, 2025);
        double montant = portefeuille.vendre(actionSimple, 3, 20, 2025);
        assertEquals(180.0, montant);
        assertTrue(portefeuille.getHistoriqueTransactions(actionSimple).get(1).contains("VENTE"));
    }

    @Test
    public void testVenteInvalide() {
        portefeuille.acheter(actionSimple, 3, 60.0, 20, 2025);
        assertThrows(IllegalArgumentException.class, () -> portefeuille.vendre(actionSimple, 5, 20, 2025));
    }

    @Test
    public void testAffichagePortefeuilleVideEtNonVide() {
        portefeuille.afficherPortefeuille();
        portefeuille.acheter(actionSimple, 2, 30.0, 1, 2025);
        portefeuille.afficherPortefeuille();
    }

    @Test
    public void testHistoriqueTransactionVide() {
        ActionSimple autre = new ActionSimple("Nouvelle");
        assertTrue(portefeuille.getHistoriqueTransactions(autre).isEmpty());
    }

    @Test
    public void testEqualsAndHashCode() {
        ActionSimple a1 = new ActionSimple("X");
        ActionSimple a2 = new ActionSimple("X");
        ActionSimple a3 = new ActionSimple("Y");

        assertEquals(a1, a2);
        assertNotEquals(a1, a3);
        assertEquals(a1.hashCode(), a2.hashCode());
    }
}
