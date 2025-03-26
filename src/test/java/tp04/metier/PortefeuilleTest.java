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
package tp04.metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PortefeuilleTest {

    private Portefeuille portefeuille;  
    private ActionSimple actionTesla;
    
    @BeforeEach
    void setUp() {
        portefeuille = new Portefeuille();
        actionTesla = new ActionSimple("Tesla"); // Initialisation de l'attribut
        portefeuille.myPortefeuille.put(actionTesla, 10); // Ajout de 10 actions Tesla
    }
        
    
        @Test
        public void testVenteActionReussie() throws Exception {
    
             
             portefeuille.vendreAction(actionTesla, 5);
            Assertions.assertEquals(5, portefeuille.myPortefeuille.get(actionTesla));
        }
    
    @Test
    public void testVenteActionToutVendre() throws Exception {
        
        portefeuille.vendreAction(actionTesla, 10);
        Assertions.assertFalse(portefeuille.myPortefeuille.containsKey(actionTesla));
    }

    @Test
    public void testVenteQuantiteInsuffisante() {
        
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            portefeuille.vendreAction(actionTesla, 15);
        });
        Assertions.assertEquals("Quantité insuffisante pour la vente", exception.getMessage());
    }

    @Test
    public void testVenteActionInexistante() {
        
        ActionSimple actionApple = new ActionSimple("Apple");
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            portefeuille.vendreAction(actionApple, 5);
        });
        Assertions.assertEquals("L'action n'est pas présente dans le portefeuille", exception.getMessage());
    }
    
    @Test
    public void testVenteQuantiteNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            portefeuille.vendreAction(actionTesla, null);
        });
    }
    
}