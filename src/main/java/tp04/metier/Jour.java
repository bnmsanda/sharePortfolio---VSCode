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

/**
 *
 * @author perussel
 */
public class Jour {

    private int annee;
    private int noJour;

    /**
     * Get the value of annee
     *
     * @return the value of annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Get the value of noJour
     *
     * @return the value of noJour
     */
    public int getNoJour() {
        return noJour;
    }

    public Jour(int annee, int noJour) {
        this.annee = annee;
        this.noJour = noJour;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.annee;
        hash = 61 * hash + this.noJour;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jour other = (Jour) obj;
        if (this.annee != other.annee) {
            return false;
        }
        if (this.noJour != other.noJour) {
            return false;
        }
        return true;
    }

}
