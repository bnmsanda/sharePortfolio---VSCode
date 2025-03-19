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

public class ActionSimple extends Action {

	HashMap<Integer, double[]> yearMap;

	public ActionSimple(String libelle) {
		super(libelle);
		this.yearMap = new HashMap<>();
	}

	public void addDailyValue(int annee, int jour, double value) {
		if (this.yearMap.containsKey(annee)) {
			this.yearMap.get(annee)[jour-1] = value;
		} else {
			double[] values = new double[365];
			values[jour-1] = value;
			this.yearMap.put(annee, values);
		}
	}

	@Override
	public double getValue(int jour, int year) throws Exception {
		if (this.yearMap.containsKey(year)) {
			return this.yearMap.get(year)[jour-1];
		} else {
			throw new Exception("No value for this year");
		}
	}

}
