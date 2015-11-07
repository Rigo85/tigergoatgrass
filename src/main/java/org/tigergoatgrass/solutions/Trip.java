/**
 * Author Rigoberto Leander Salgado Reyes <rlsalgado2006@gmail.com>
 * <p>
 * Copyright 2015 by Rigoberto Leander Salgado Reyes.
 * <p>
 * This program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http:www.gnu.org/licenses/agpl-3.0.txt) for more details.
 */

package org.tigergoatgrass.solutions;

import org.tigergoatgrass.input.Problem;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.tigergoatgrass.solutions.Shore.SHORE_A;
import static org.tigergoatgrass.solutions.Shore.SHORE_B;


public class Trip {
    private final ArrayList<Integer> items;
    private final boolean from;
    private final Problem problem;

    public Trip(Problem problem, ArrayList<Integer> items, boolean from) {
        this.items = items;
        this.from = from;
        this.problem = problem;
    }

    @Override
    public String toString() {
        final String passenger = items.stream().map(x -> x != -1 ? problem.getItems().get(x-1) : "Empty").collect(Collectors.joining(", "));
        return String.format("%s: from %s to %s", passenger, from ? SHORE_A : SHORE_B, !from ? SHORE_A : SHORE_B);
    }

    public ArrayList<Integer> getItems() {
        return items;
    }
}
