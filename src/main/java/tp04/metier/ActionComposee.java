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

	/**
	 * Associate to an action its percentage. For exemple, a value of 0.5 means 50%.
	 */
	private Map<Action, Integer> shares;

	public ActionComposee(String libelle) {
		super(libelle);
		this.shares = new HashMap<>();
	}

	public void addAction(Action a, int percentage) {
		this.shares.put(a, this.shares.getOrDefault(a, 0) + percentage);
	}

	@Override
	public double getValue(int jour, int year) throws Exception {
        double value = 0;

        for (Map.Entry<Action, Integer> entry : this.shares.entrySet()) {
            Action a = entry.getKey();
            int percentage = entry.getValue();
            value += a.getValue(jour, year) * percentage;
        }

        return value;
    }
}


