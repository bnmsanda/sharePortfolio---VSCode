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
import java.util.NoSuchElementException;

public class ActionSimple extends Action {

	HashMap<Integer, double[]> yearMap;

	public ActionSimple(String libelle) {
		super(libelle);
		this.yearMap = new HashMap<Integer, double[]>();
	}

	public void addDailyValue(int year, int jour, double value) {
		if (this.yearMap.containsKey(year)) {
			this.yearMap.get(year)[jour] = value;
		} else {
			double[] values = new double[365];
			values[jour] = value;
			this.yearMap.put(year, values);
		}
	}

	@Override
	public double getValue(int jour, int year) throws NoSuchElementException {
		if (this.yearMap.containsKey(year)) {
			return this.yearMap.get(year)[jour];
		} else {
			throw new NoSuchElementException("No value for this year");
		}
	}

}
