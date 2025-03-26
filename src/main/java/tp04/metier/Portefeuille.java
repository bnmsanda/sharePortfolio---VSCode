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

import java.util.ArrayList;

public class Portefeuille {

	ArrayList<Action> myActions;

	public Portefeuille() {
		this.myActions = new ArrayList<Action>();
	}

	public void buyNewAction(String libelle) {
		this.myActions.add(new ActionSimple(libelle));
	}

	public void buyExistingAction(Action a) {
		this.myActions.add(a);
	}

	public void afficherValeurAction(Action a, int year, int day) {
		if (this.myActions.contains(a)) {
			a.afficherValeur(day, year);
		} else {
			System.out.println("Cette action n'est pas dans le portefeuille.");
		}
	}

	public double sellAction(Action a, int year, int day) throws Exception {
		double value = this.myActions.get(this.myActions.indexOf(a)).getValue(day, year);
		this.myActions.remove(a);
		return value;
	}

	public void afficherActionsSousFormeDeCartes() {
		if (myActions.isEmpty()) {
			System.out.println("Le portefeuille est vide.");
			return;
		}

		System.out.println("=== Vue d'ensemble des actions ===");
		for (Action action : myActions) {
			System.out.println("-------------------------------");
			System.out.println("Nom de l'action : " + action.getLibelle());
			try {
				double valeur = action.getValue(1, 2025); // Exemple : valeur au jour 1 de l'année 2025
				System.out.println("Valeur actuelle : " + valeur);
			} catch (Exception e) {
				System.out.println("Erreur lors de la récupération de la valeur : " + e.getMessage());
			}
			System.out.println("-------------------------------");
		}
	}
}
