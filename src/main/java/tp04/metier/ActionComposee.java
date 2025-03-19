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

import java.util.HashMap;
import java.util.Map;

public class ActionComposee extends Action {
    private Map<Action, Integer> composants;

    public ActionComposee(String libelle) {
        super(libelle);
        this.composants = new HashMap<>();
    }

    public void ajouterComposition(ActionSimple action, float pourcentage) {
        if (pourcentage <= 0 || pourcentage > 100) {
            throw new IllegalArgumentException("Le pourcentage doit être compris entre 0 et 100.");
        }
        composants.merge(action, (int) pourcentage, Integer::sum);
    }

    @Override
    public void ajouterValeur(int year, int jour, double valeur) {
        throw new UnsupportedOperationException("Les valeurs sont dérivées des actions composées.");
    }

    @Override
    public double getValue(int jour, int year) throws Exception {
        double valeurTotale = 0;
        for (Map.Entry<Action, Integer> entry : composants.entrySet()) {
            valeurTotale += (entry.getKey().getValue(jour, year) * entry.getValue()) / 100.0;
        }
        return valeurTotale;
    }

    public double valeur(int jour, int year) throws Exception {
        return getValue(jour, year);
    }
}
