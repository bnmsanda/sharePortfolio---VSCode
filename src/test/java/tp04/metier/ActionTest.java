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
 * limitations under the License.
 */
package tp04.metier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
class ActionTest {

    @Test
    void testActionSimpleGetValue() throws Exception {
        // Préparation des données
        Map<Integer, Double> cours = new HashMap<>();
        cours.put(1, 100.0);
        cours.put(2, 105.0);
        cours.put(3, 110.0);

        ActionSimple actionSimple = new ActionSimple("Action Simple 1", cours);

        // Tests
        assertEquals(100.0, actionSimple.getValue(1, 2025));
        assertEquals(105.0, actionSimple.getValue(2, 2025));
        assertEquals(110.0, actionSimple.getValue(3, 2025));

        // Test pour un jour inexistant
        Exception exception = assertThrows(Exception.class, () -> actionSimple.getValue(4, 2025));
        assertEquals("Cours non disponible pour le jour 4 de l'année 2025", exception.getMessage());
    }

    @Test
    void testActionComposeeGetValue() throws Exception {
        // Préparation des données pour les actions simples
        Map<Integer, Double> cours1 = new HashMap<>();
        cours1.put(1, 100.0);
        cours1.put(2, 105.0);

        Map<Integer, Double> cours2 = new HashMap<>();
        cours2.put(1, 200.0);
        cours2.put(2, 210.0);

        ActionSimple actionSimple1 = new ActionSimple("Action Simple 1", cours1);
        ActionSimple actionSimple2 = new ActionSimple("Action Simple 2", cours2);

        // Préparation de l'action composée
        Map<ActionSimple, Double> composition = new HashMap<>();
        composition.put(actionSimple1, 0.6); // 60% de l'action 1
        composition.put(actionSimple2, 0.4); // 40% de l'action 2

        ActionComposee actionComposee = new ActionComposee("Action Composée 1", composition);

        // Tests
        assertEquals(140.0, actionComposee.getValue(1, 2025)); // (100 * 0.6) + (200 * 0.4)
        assertEquals(147.0, actionComposee.getValue(2, 2025)); // (105 * 0.6) + (210 * 0.4)

        // Test pour un jour inexistant
        Exception exception = assertThrows(Exception.class, () -> actionComposee.getValue(3, 2025));
        assertEquals("Cours non disponible pour le jour 3 de l'année 2025", exception.getMessage());
    }

    @Test
    void testAfficherValeur() {
        // Préparation des données
        Map<Integer, Double> cours = new HashMap<>();
        cours.put(1, 100.0);

        ActionSimple actionSimple = new ActionSimple("Action Simple 1", cours);

        // Redirection de la sortie standard pour capturer l'affichage
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Appel de la méthode afficherValeur
        actionSimple.afficherValeur(1, 2025);

        // Vérification de l'affichage
        assertTrue(outContent.toString().contains("Valeur de l'action Action Simple 1 au jour 1 de l'année 2025 : 100.0"));

        // Réinitialisation de la sortie standard
        System.setOut(System.out);
    }

    @Test
    void testEquals() {
        // Création de deux actions avec le même libellé
        Action action1 = new ActionSimple("Action 1", null);
        Action action2 = new ActionSimple("Action 1", null);

        // Création d'une action avec un libellé différent
        Action action3 = new ActionSimple("Action 2", null);

        // Vérification de l'égalité
        assertTrue(action1.equals(action2)); // Même libellé, doivent être égales
        assertFalse(action1.equals(action3)); // Libellé différent, ne doivent pas être égales

        // Vérification avec null
        assertFalse(action1.equals(null)); // Ne doit pas être égal à null

        // Vérification avec un objet d'une autre classe
        assertFalse(action1.equals("Une chaîne de caractères")); // Ne doit pas être égal à un objet d'un autre type
    }

    public class ActionImpl extends Action {

        public ActionImpl() {
            super("");
        }
        

        @Override
        public double getValue(int jour, int year) throws Exception {
            return 0.0F;
          }
    }

}
