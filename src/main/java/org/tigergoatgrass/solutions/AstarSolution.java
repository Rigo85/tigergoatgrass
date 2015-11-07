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
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class AstarSolution implements ISearchSolution {
    @Override
    public ArrayList<Trip> compute(Problem problem) {
        ArrayList<Boolean> items = new ArrayList<>(problem.getItems().size() + 1);

        items.addAll(problem.getItems().stream().map(x -> true).collect(Collectors.toList()));
        items.add(true);

        final Solution result = search(new Solution(problem, items, new ArrayList<>()));

        return result != null ? result.getPath() : new ArrayList<>();
    }

    /**
     * Algorithm A*
     * Algorithms in a Nutshell by George T. Heiniman, Gary Pollice and Stanley Selkow. 2009. page 194.
     * */
    private Solution search(Solution initial) {
        PriorityQueue<Solution> open = new PriorityQueue<>(100, (o1, o2) -> o1.getEvaluation() - o2.getEvaluation());
        Solution copy = initial.copy();

        open.add(copy);
        Hashtable<Integer, Solution> closed = new Hashtable<>();

        while (!open.isEmpty()) {
            Solution n = open.remove();
            closed.put(n.hashCode(), n);

            if (goal(n)) return n;

            for (Moves moves : n.validMoves()) {
                Solution successor = n.copy();
                successor.applyMoves(moves);

                Solution past = closed.get(successor.hashCode());
                if (past != null) {
                    if (successor.getEvaluation() >= past.getEvaluation()) continue;
                    closed.remove(past.hashCode());
                }
                open.add(successor);
            }
        }
        return null;
    }

    private boolean goal(Solution n) {
        return n.getItems().stream().allMatch(x -> !x);
    }
}
