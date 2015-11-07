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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


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
        ArrayList<Integer> sameShoreThanBoatMan = new ArrayList<>();

        sameShoreThanBoatMan.addAll(IntStream.range(1, items.size())
                .filter(i -> items.get(i).equals(items.get(0))).boxed().map(i -> i - 1)
                .collect(Collectors.toList()));

        //boatman traveling alone.
        if (canMove(new ArrayList<>(), sameShoreThanBoatMan) && !isWayBack(new ArrayList<Integer>() {{
            add(-1);
        }})) {
            ArrayList<Integer> toMove = new ArrayList<>();
            toMove.add(0);
            moves.add(new Moves(toMove));
        }

        final Stream<List<Integer>> combs = Combinations.getCombinationsStream(sameShoreThanBoatMan, problem.getBoatCapacity());

        //boatman traveling with others
        combs.forEach(c -> {
            ArrayList<Integer> group = new ArrayList<>();
            group.addAll(c);
            ArrayList<Integer> group2 = new ArrayList<>();
            group2.addAll(c.stream().map(x -> x + 1).collect(Collectors.toList()));
            if (canStayTogether(group) && canMove(group, sameShoreThanBoatMan) && !isWayBack(group2)) {
                group2.add(0, 0);
                moves.add(new Moves(group2));
            }
        });

        return moves;
    }

    private boolean isWayBack(ArrayList<Integer> group) {
        return !path.isEmpty() && path.get(path.size() - 1).getItems().equals(group);
    }

    public void applyMoves(Moves moves) {
        AtomicBoolean b = new AtomicBoolean(true);
        IntStream.range(0, moves.getSteps().size()).forEach(x -> {
            int pos = moves.getSteps().get(x);
            b.set(!items.get(pos));
            items.remove(pos);
            items.add(pos, b.get());
        });

        if (isAnEmptyTrip(moves)) {                                 //saving an empty trip.
            path.add(new Trip(problem, new ArrayList<Integer>() {{
                add(-1);
            }}, !b.get()));
        } else {                                                    //saving the trip(of course the boatman is in there).
            ArrayList<Integer> group = new ArrayList<>();
            group.addAll(moves.getSteps().stream().filter(x -> !x.equals(0)).collect(Collectors.toList()));
            path.add(new Trip(problem, group, !b.get()));
        }

        this.f = fitness();
    }

    private boolean isAnEmptyTrip(Moves moves) {
        return moves.getSteps().size() == 1 && moves.getSteps().get(0) == 0;
    }

    /**
     * We need to verify that when the boatman travel with some fellows, don't leave the shore in conflict.
     */
    private boolean canMove(ArrayList<Integer> toMove, ArrayList<Integer> state) {
        ArrayList<Integer> remaining = new ArrayList<>();
        remaining.addAll(state.stream().filter(x -> toMove.stream().allMatch(y -> !y.equals(x))).collect(Collectors.toList()));

        return canStayTogether(remaining);
    }

    private boolean canStayTogether(ArrayList<Integer> group) {
        for (int i = 0; i < group.size() - 1; i++) {
            for (int j = i + 1; j < group.size(); j++) {
                if (problem.affinity(group.get(i), group.get(j))) return false;
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
