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

import java.util.Map;

public class ActionComposee extends Action {
    private Map<ActionSimple, Double> composition; // Map contenant les actions simples et leurs proportions

    public ActionComposee(String libelle, Map<ActionSimple, Double> composition) {
        super(libelle);
        this.composition = composition;
    }

    @Override
    public double getValue(int jour, int year) throws Exception {
        double valeurTotale = 0.0;
        for (Map.Entry<ActionSimple, Double> entry : composition.entrySet()) {
            ActionSimple actionSimple = entry.getKey();
            double proportion = entry.getValue();
            valeurTotale += actionSimple.getValue(jour, year) * proportion;
        }
        return valeurTotale;
    }
}
