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

import java.util.Objects;

public abstract class Action {
	String libelle;

	public void afficherValeur(int jour, int year) {
		try {
			System.out.println("Valeur de l'action " + libelle + " au jour " + jour + " de l'année " + year + " : "
					+ getValue(jour, year));
		} catch (Exception e) {
			System.out.println("Erreur: " + e.getMessage());
		}
	}

	public Action(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(libelle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Action other = (Action) obj;
		return Objects.equals(libelle, other.libelle);
	}

	public abstract double getValue(int jour, int year) throws Exception;
}
