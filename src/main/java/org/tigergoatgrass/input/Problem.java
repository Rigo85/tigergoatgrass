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

package org.tigergoatgrass.input;


import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;


public class Problem {
    private final int boatCapacity;
    private final ArrayList<String> items;
    private final ArrayList<ArrayList<Boolean>> affinityMatrix;

    public Problem(int boatCapacity, @NotNull ArrayList<String> items, @NotNull ArrayList<ArrayList<Boolean>> affinityMatrix) {
        this.boatCapacity = boatCapacity;
        this.items = items;
        this.affinityMatrix = affinityMatrix;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public int getBoatCapacity() {
        return boatCapacity;
    }

    public boolean affinity(int row, int col) {
        return (row < affinityMatrix.size() && col < affinityMatrix.size() && affinityMatrix.get(row).get(col));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("----------- Boat Capacity -----------\n");
        builder.append(boatCapacity);
        builder.append("\n");
        builder.append("----------- Items -----------\n");
        this.items.stream().forEach(x -> builder.append(String.format("%s ", x)));
        builder.append("\n");
        builder.append("----------- Matrix -----------\n");
        affinityMatrix.stream().forEach(x -> {
            x.stream().forEach(y -> builder.append(String.format("%s ", y ? "x" : "0")));
            builder.append("\n");
        });

        return builder.toString();
    }
}
