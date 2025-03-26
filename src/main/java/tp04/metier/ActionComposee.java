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

public class ActionComposee extends Action {

	/**
	 * Associate to an action its percentage. For exemple, a value of 0.5 means 50%.
	 */
	HashMap<ActionSimple, Double> shares;

	public ActionComposee(String libelle) {
		super(libelle);
		this.shares = new HashMap<>();
	}

	public void addAction(ActionSimple a, double percentage) {
		if (this.shares.containsKey(a)) {
			this.shares.put(a, percentage + this.shares.get(a));
		} else {
			this.shares.put(a, percentage);
		}
	}

	@Override
	public double getValue(int jour, int year) throws Exception {
		double value = 0.0;
		for (ActionSimple a : this.shares.keySet()) {
			value = value + a.getValue(jour, year) * (this.shares.get(a)/100.0);
		}
		return value;
	}

}
