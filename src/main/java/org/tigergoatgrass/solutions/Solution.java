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

import org.antlr.v4.runtime.misc.NotNull;
import org.tigergoatgrass.input.Problem;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Solution {
    private final ArrayList<Boolean> items;
    private final ArrayList<Trip> path;
    private int f;
    private final Problem problem;

    public Solution(@NotNull Problem problem, @NotNull ArrayList<Boolean> items, @NotNull ArrayList<Trip> path) {
        this.problem = problem;
        this.items = items;
        this.path = path;
        this.f = fitness();
    }

    private int fitness() {
        int shoreACount = 0;
        int shoreBCount = 0;

        for (int i = 1; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).equals(items.get(j))) {
                    if (items.get(i)) {
                        shoreACount += problem.affinity(i - 1, j - 1) ? 1 : 0;
                    } else {
                        shoreBCount += problem.affinity(i - 1, j - 1) ? 1 : 0;
                    }
                }
            }
        }

        int howMuchInShoreA = (int) IntStream.range(1, items.size()).filter(items::get).count();

        return howMuchInShoreA + shoreACount + shoreBCount;
    }

    public ArrayList<Boolean> getItems() {
        return items;
    }

    public ArrayList<Trip> getPath() {
        return path;
    }

    public int getEvaluation() {
        return this.f;
    }

    public Solution copy() {
        ArrayList<Boolean> cItems = new ArrayList<>(items.size());
        ArrayList<Trip> cPaths = new ArrayList<>(path.size());
        cItems.addAll(items.stream().collect(Collectors.toList()));
        cPaths.addAll(path.stream().collect(Collectors.toList()));

        return new Solution(problem, cItems, cPaths);
    }

    public ArrayList<Moves> validMoves() {
        ArrayList<Moves> moves = new ArrayList<>();

        //boatman traveling alone.
        if (canMove(0, items) && (path.isEmpty() || path.get(path.size() - 1).getItem() != -1)) {
            moves.add(new Moves(new ArrayList<Integer>() {{
                add(0);
            }}));
        }

        //boatman traveling with others
        IntStream.range(1, items.size()).filter(i -> items.get(i).equals(items.get(0))).forEach(i -> {
            //create valid movement if can move and isn't the way back.
            if (canMove(i, items) && (path.isEmpty() || path.get(path.size() - 1).getItem() != i - 1)) {
                moves.add(new Moves(new ArrayList<Integer>() {{
                    add(0);
                    add(i);
                }}));
            }
        });

        return moves;
    }

    public void applyMoves(Moves moves) {
        IntStream.range(0, moves.getSteps().size()).forEach(x -> {
            int pos = moves.getSteps().get(x);
            boolean b = !items.get(pos);
            items.remove(pos);
            items.add(pos, b);

            if (moves.getSteps().size() == 1 && moves.getSteps().get(0) == 0) //saving an empty trip.
                path.add(new Trip(problem, -1, !b));
            else if (moves.getSteps().get(x) != 0)                            //saving the trip(of course the boatman is in there).
                path.add(new Trip(problem, pos - 1, !b));
        });

        this.f = fitness();
    }

    /**
     * We need to verify that when the boatman travel with some fellows, don't leave the shore in conflict.
     */
    private boolean canMove(int position, ArrayList<Boolean> state) {
        for (int i = 0; i < problem.getAffinityMatrix().size() - 1; i++) {
            if (i != position - 1 && state.get(i + 1).equals(state.get(position))) {
                for (int j = i + 1; j < problem.getAffinityMatrix().size(); j++) {
                    if (j != position - 1 && state.get(j + 1).equals(state.get(position))) {
                        if (problem.affinity(i, j)) return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        return f == solution.f && !(getItems() != null ? !getItems().equals(solution.getItems()) : solution.getItems() != null)
                && !(getPath() != null ? !getPath().equals(solution.getPath()) : solution.getPath() != null);
    }

    @Override
    public int hashCode() {
        int result = getItems() != null ? getItems().hashCode() : 0;
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        result = 31 * result + f;
        return result;
    }
}
