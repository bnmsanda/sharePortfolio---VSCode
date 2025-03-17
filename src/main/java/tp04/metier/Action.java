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

/* verifier push*/
package tp04.metier;

import java.util.Objects;

public abstract class Action {
	String libelle;

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
			repackage tp04.metier;

import java.util.Objects;

/**
 * Represents an action with a label and a value that can be calculated based on a day and a year.
 */
public abstract class Action {

    private final String libelle;

    /**
     * Creates a new action with the given label.
     *
     * @param libelle the label of the action
     */
    public Action(String libelle) {
        this.libelle = Objects.requireNonNull(libelle, "Label cannot be null");
    }

    /**
     * Returns the label of the action.
     *
     * @return the label of the action
     */
    public String getLibelle() {
        return libelle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Action other = (Action) obj;
        return Objects.equals(libelle, other.libelle);
    }

    /**
     * Calculates the value of the action for the given day and year.
     *
     * @param jour  the day
     * @param year the year
     * @return the value of the action
     * @throws Exception if an error occurs during calculation
     */
    public abstract double getValue(int jour, int year) throws Exception;
}turn false;
		Action other = (Action) obj;
		return Objects.equals(libelle, other.libelle);
	}

	public abstract double getValue(int jour, int year) throws Exception;
}
