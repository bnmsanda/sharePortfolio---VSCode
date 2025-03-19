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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PortefeuilleTest {
    private Portefeuille portefeuille;
    private ActionSimple action1;
    private ActionSimple action2;

    @BeforeEach
    public void setUp() {
        portefeuille = new Portefeuille();
        action1 = new ActionSimple("Apple");
        action2 = new ActionSimple("Tesla");

        // 预先设置价格，确保 getValue() 方法能正确运行
        action1.ajouterValeur(2025, 1, 150.0);
        action2.ajouterValeur(2025, 1, 700.0);
    }

    @Test
    public void testAchatEtVente() {
        // 购买股票
        portefeuille.acheter(action1, 10, 150.0, 1, 2025);
        portefeuille.acheter(action2, 5, 700.0, 1, 2025);

        // 确保 action2 在交易日期有价格数据
        action2.ajouterValeur(2025, 1, 700.0);

        // 测试卖出股票
        try {
            portefeuille.vendre(action2, 5, 1, 2025); // 修正传递的日期
        } catch (Exception e) {
            fail("Vente échouée: " + e.getMessage()); // 显示具体错误信息
        }

        // 检查交易历史是否正确
        List<String> historiqueApple = portefeuille.getHistoriqueTransactions(action1);
        List<String> historiqueTesla = portefeuille.getHistoriqueTransactions(action2);

        // 验证 Apple 交易历史
        assertEquals(1, historiqueApple.size());
        assertEquals("Jour 1, Année 2025: ACHAT de 10 à 150.0€", historiqueApple.get(0));

        // 验证 Tesla 交易历史
        assertEquals(2, historiqueTesla.size());
        assertEquals("Jour 1, Année 2025: ACHAT de 5 à 700.0€", historiqueTesla.get(0));
        assertEquals("Jour 1, Année 2025: VENTE de 5 à 700.0€", historiqueTesla.get(1));
    }

    @Test
    public void testVenteSansAssezDActions() {
        portefeuille.acheter(action1, 5, 150.0, 1, 2025);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            portefeuille.vendre(action1, 10, 1, 2025); // 试图卖出比持有更多的股票
        });

        assertEquals("Pas assez d'actions pour vendre !", exception.getMessage());
    }
}
