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

import java.util.*;

public class Portefeuille {
    private Map<Action, Integer> actionsPossedees;
    private Map<Action, List<String>> historiqueTransactions;

    public Portefeuille() {
        this.actionsPossedees = new HashMap<>();
        this.historiqueTransactions = new HashMap<>();
    }

    public void acheter(Action action, int quantite) {
        acheterAction(action, quantite, 0.0, 0, 0);
    }

    public void acheter(ActionSimple action, int quantite, double prix, int jour, int year) {
        acheterAction(action, quantite, prix, jour, year);
    }

    public void acheter(ActionComposee action, int quantite) {
        acheterAction(action, quantite, 0.0, 0, 0);
    }

    private void acheterAction(Action action, int quantite, double prix, int jour, int year) {
        actionsPossedees.merge(action, quantite, Integer::sum);
        if (action instanceof ActionSimple && prix > 0) {
            try {
                ((ActionSimple) action).ajouterValeur(year, jour, prix);
            } catch (Exception e) {
                System.err.println("Erreur d'enregistrement de prix : " + e.getMessage());
            }
        }
        historiqueTransactions.computeIfAbsent(action, k -> new ArrayList<>())
            .add("Jour " + jour + ", Année " + year + ": ACHAT de " + quantite + " à " + prix + "€");
    }

    public double vendre(Action action, int quantite, int jour, int year) {
        return vendreAction(action, quantite, jour, year);
    }

    private double vendreAction(Action action, int quantite, int jour, int year) {
        if (!actionsPossedees.containsKey(action) || actionsPossedees.get(action) < quantite) {
            throw new IllegalArgumentException("Pas assez d'actions pour vendre !");
        }

        double prixVente = action.getValue(jour, year);
        actionsPossedees.put(action, actionsPossedees.get(action) - quantite);
        if (actionsPossedees.get(action) == 0) {
            actionsPossedees.remove(action);
        }

        historiqueTransactions.computeIfAbsent(action, k -> new ArrayList<>())
            .add("Jour " + jour + ", Année " + year + ": VENTE de " + quantite + " à " + prixVente + "€");

        return prixVente * quantite;
    }

    public List<String> getHistoriqueTransactions(Action action) {
        return historiqueTransactions.getOrDefault(action, Collections.emptyList());
    }

    public void afficherPortefeuille() {
        System.out.println("Portefeuille actuel:");
        if (actionsPossedees.isEmpty()) {
            System.out.println("Aucune action détenue.");
        } else {
            for (Map.Entry<Action, Integer> entry : actionsPossedees.entrySet()) {
                System.out.println("- " + entry.getKey().getLibelle() + ": " + entry.getValue() + " actions");
            }
        }
    }
}
