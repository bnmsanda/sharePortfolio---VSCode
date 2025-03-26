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
import java.util.NoSuchElementException;

public class ActionSimple extends Action {
    private Map<Integer, double[]> historiquePrix;

    public ActionSimple(String libelle) {
        super(libelle);
        this.historiquePrix = new HashMap<>();
    }

    @Override
    public void ajouterValeur(int year, int jour, double valeur) {
        if (jour < 0 || jour >= 366) {
            throw new IllegalArgumentException("Jour invalide : " + jour);
        }
        historiquePrix.computeIfAbsent(year, k -> new double[366])[jour] = valeur;
    }

    @Override
    public double getValue(int jour, int year) {
        if (!historiquePrix.containsKey(year)) {
            throw new NoSuchElementException("Aucune donnée pour l'année " + year);
        }
        if (jour < 0 || jour >= 366) {
            throw new IllegalArgumentException("Jour invalide : " + jour);
        }
        double valeur = historiquePrix.get(year)[jour];
        if (valeur == 0) {
            throw new NoSuchElementException("Aucune valeur disponible pour " + libelle + " au jour " + jour);
        }
        return valeur;
    }

    public void enrgCours(int jour, int year, double prix) {
        ajouterValeur(year, jour, prix);
    }

    public double valeur(int jour, int year) {
        return getValue(jour, year);
    }
}
