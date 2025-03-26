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

	private final Map<ActionSimple, Double> composition; // ActionSimple et son pourcentage

	public ActionComposee(String libelle) {
		super(libelle);
		this.composition = new HashMap<>();
	}

	/**
	 * Ajoute une action simple à la composition avec un pourcentage donné.
	 * 
	 * @param actionSimple L'action simple à ajouter.
	 * @param pourcentage  Le pourcentage de cette action dans l'action composée.
	 * @throws IllegalArgumentException si le pourcentage est invalide.
	 */
	public void ajouterActionSimple(ActionSimple actionSimple, double pourcentage) {
		if (pourcentage <= 0 || pourcentage > 100) {
			throw new IllegalArgumentException("Le pourcentage doit être compris entre 0 et 100.");
		}
		this.composition.put(actionSimple, pourcentage);
	}

	/**
	 * Calcule la valeur de l'action composée en fonction des valeurs des actions
	 * simples.
	 */
	@Override
	public double getValue(int jour, int year) throws Exception {
		double valeurTotale = 0.0;
		for (Map.Entry<ActionSimple, Double> entry : composition.entrySet()) {
			ActionSimple actionSimple = entry.getKey();
			double pourcentage = entry.getValue();
			valeurTotale += (actionSimple.getValue(jour, year) * pourcentage / 100);
		}
		return valeurTotale;
	}
}
