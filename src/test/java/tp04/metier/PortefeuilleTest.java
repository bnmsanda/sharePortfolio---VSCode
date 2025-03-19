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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void setUp() {
        portefeuille = new Portefeuille();
        
        action1 = new ActionSimple("France 2");
        action2 = new ActionSimple("Tisseo");
        action3 = new ActionSimple("Total");
        action4 = new ActionSimple("France 3");
        action5 = new ActionSimple("France 5");

        actionComposee = new ActionComposee("France télévision");

        actionComposee.addAction(action1, 35); 
        actionComposee.addAction(action4, 50);
        actionComposee.addAction(action5, 15); 
    }

    // 模拟购买一些股票
    @Test
    void testBuyAction() {
        portefeuille.buyAction("France 2", 10, false);

        assertEquals(10, portefeuille.getMyActions().get(action1));
    }

    //模拟卖出股票
    @Test
    void testSellAction() throws Exception {
        portefeuille.buyAction("France 2", 10, false);
        action1.addDailyValue(2025, 1, 100.0);
        double value = portefeuille.sellAction(action1, 1, 2025);

        assertEquals(1000.0, value); 
    }

    @Test
    void testGetValueTotal() throws Exception {
        portefeuille.buyAction("France 2", 10, false);
        portefeuille.buyAction("Tisseo", 4, false);
        portefeuille.buyAction("Total", 13, false);

        action1.addDailyValue(2025, 1, 100.0);
        action2.addDailyValue(2025, 1, 50.0);
        action3.addDailyValue(2025, 1, 30.0);

        double totalValue = portefeuille.getValueTotal(1, 2025);

        assertEquals(1590.0, totalValue);
    }

    @Test
    void testGetValueTotalWithComposeAction() throws Exception {

        portefeuille.buyAction("France télévision", 1, true);

        action1.addDailyValue(2025, 1, 100.0);
        action4.addDailyValue(2025, 1, 60.0);
        action5.addDailyValue(2025, 1, 80.0);

        double value = portefeuille.getValueTotal(1, 2025);

        assertEquals(77.0, value);
    }
}
