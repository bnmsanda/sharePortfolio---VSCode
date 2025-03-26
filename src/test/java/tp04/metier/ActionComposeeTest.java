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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
class ActionComposeeTest {

    @Test
    void testGetValue() throws Exception {
        ActionSimple action1 = new ActionSimple("Action A") {
            @Override
            public double getValue(int jour, int year) {
                return 100.0;
            }
        };

        ActionSimple action2 = new ActionSimple("Action B") {
            @Override
            public double getValue(int jour, int year) {
                return 200.0;
            }
        };

        ActionComposee actionComposee = new ActionComposee("Panier 1");
        actionComposee.ajouterActionSimple(action1, 50.0); // 50% de Action A
        actionComposee.ajouterActionSimple(action2, 50.0); // 50% de Action B

        double valeur = actionComposee.getValue(1, 2025);
        Assertions.assertEquals(150.0, valeur); // (100 * 50% + 200 * 50%)
    }

    @Test
    void testAjouterActionSimplePourcentageInvalide() {
        ActionSimple action = new ActionSimple("Action A");
        ActionComposee actionComposee = new ActionComposee("Panier 1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            actionComposee.ajouterActionSimple(action, -10.0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            actionComposee.ajouterActionSimple(action, 110.0);
        });
    }
}
