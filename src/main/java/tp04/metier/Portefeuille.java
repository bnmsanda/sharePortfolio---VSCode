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
import java.util.NoSuchElementException;
import java.util.HashMap;

public class Portefeuille {

	private Map<Action, Integer> myActions;

	public Portefeuille() {
		this.myActions = new HashMap<>();
	}

	public Map<Action, Integer> getMyActions() {
        return myActions;
    }

	public void buyAction(Action a, int quantite) {
		if (this.myActions.containsKey(a)) {
			this.myActions.put(a, this.myActions.get(a) + quantite);
		} else {
			this.myActions.put(a, quantite);
		}
	}
	

	public double sellAction(Action a, int quantite, int jour, int annee) throws Exception {
		if (!this.myActions.containsKey(a)) {
			throw new NoSuchElementException("Action n'existe pas en portefeuille.");
		}
		
		int currentQuantity = this.myActions.get(a);
		if (quantite > currentQuantity) {
			throw new NoSuchElementException("Quantité à vendre supérieure à celle détenue.");
		}
	
		double value = quantite * a.getValue(jour, annee);
		
		if (quantite == currentQuantity) {
			this.myActions.remove(a);
		} else {
			this.myActions.put(a, currentQuantity - quantite);
		}
	
		return value;
	}	

	public double getValueTotal(int jour, int annee) throws Exception {
		double total = 0;
		for (Action a : this.myActions.keySet()){
			total += this.myActions.get(a) * a.getValue(jour, annee);
		}
		return total;
	}
}

