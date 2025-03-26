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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class PortefeuilleTest {
    
    private Portefeuille portefeuille;
    private ActionSimple action1;
    private ActionSimple action2;
    private ActionSimple action3;
    private ActionSimple action4;
    private ActionSimple action5;
    private ActionComposee actionComposee;

    // 每次测试前都会执行这个方法，创建新的投资组合和股票
    @BeforeEach
    public void setUp() throws Exception{
        portefeuille = new Portefeuille();
        
        action1 = new ActionSimple("France 2");
        action2 = new ActionSimple("Tisseo");
        action3 = new ActionSimple("Total");
        action4 = new ActionSimple("France 3");
        action5 = new ActionSimple("France 5");

        action1.addDailyValue(2025, 1, 100.0);
        action2.addDailyValue(2025, 1, 50.0);
        action3.addDailyValue(2025, 1, 30.0);
        action4.addDailyValue(2025, 1, 60.0);
        action5.addDailyValue(2025, 1, 80.0);

        actionComposee = new ActionComposee("France télévision");
        actionComposee.addAction(action1, 35.0); 
        actionComposee.addAction(action4, 50.0);
        actionComposee.addAction(action5, 15.0); 

        portefeuille.buyAction(action1, 10);
        portefeuille.buyAction(action2, 4);
        portefeuille.buyAction(action3, 13);
        portefeuille.buyAction(actionComposee, 1);
    }

    @Test
    public void testAddDailyValue_plus() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            action1.addDailyValue(2025, 389, 100.0);
        });
        assertEquals("Numéro du jour invalide pour cette année.", exception.getMessage());
    }

    @Test
    public void testAddDailyValue_moin() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            action1.addDailyValue(2025, 0, 100.0);
        });
        assertEquals("Numéro du jour invalide pour cette année.", exception.getMessage());
    }

    @Test
    public void testBuyAction_nouveau() {
        assertEquals(10, portefeuille.getMyActions().get(action1));
    }

    @Test
    public void testBuyAction_existe() {
        portefeuille.buyAction(action2, 3);
        assertEquals(7, portefeuille.getMyActions().get(action2));
    }


    @Test
    public void testSellAction_partie() throws Exception {
        double value = portefeuille.sellAction(action1, 2, 1, 2025);
        assertEquals(200.0, value); 
        assertEquals(8, portefeuille.getMyActions().get(action1));
    }

    @Test
    public void testSellAction_tous() throws Exception {
        double value = portefeuille.sellAction(action1, 10, 1, 2025);
        assertEquals(1000.0, value); 
        assertFalse(portefeuille.getMyActions().containsKey(action1));
    }

    @Test
    public void testSellAction_insufficientQuantity() {
        Exception exception = assertThrows(Exception.class, () -> {
            portefeuille.sellAction(action1, 12, 1, 2025);
        });
        assertEquals("Quantité à vendre supérieure à celle détenue.", exception.getMessage());
    }

    @Test
    public void testSellAction_ActionExistePas() {
        Exception exception = assertThrows(Exception.class, () -> {
            portefeuille.sellAction(action5, 5, 1, 2025);
        });
        assertEquals("Action n'existe pas en portefeuille.", exception.getMessage());
    }

    @Test
    public void testGetValueTotal() throws Exception {
        double totalValue = portefeuille.getValueTotal(1, 2025);
        assertEquals(1667.0, totalValue);
    }

    @Test
    public void testGetValue_noValueAnnee() {
        Exception exception = assertThrows(Exception.class, () -> {
            action1.getValue(1, 2024); 
        });
        assertEquals("Aucune valeur trouvée pour l'année.", exception.getMessage()); 
    }

    @Test
    public void testActionComposee_valueCalculation() throws Exception {
        double calculatedValue = actionComposee.getValue(1, 2025);
        double expectedValue = (100.0 * 0.35) + (60.0 * 0.50) + (80.0 * 0.15); 
        assertEquals(expectedValue, calculatedValue); 
    }

    @Test
    public void testActionValue() throws Exception{
        assertEquals(100.0, action1.getValue(1, 2025));
    }
}