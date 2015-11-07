/**
 * Author Rigoberto Leander Salgado Reyes <rlsalgado2006@gmail.com>
 *
 * Copyright 2015 by Rigoberto Leander Salgado Reyes.
 * <p>
 * This program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http:www.gnu.org/licenses/agpl-3.0.txt) for more details.
 */

package org.tigergoatgrass.input;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;

public class InputWalker extends InputBaseListener {

    private final ArrayList<String> items = new ArrayList<>();
    private final ArrayList<ArrayList<Boolean>> affinityMatrix = new ArrayList<>();
    private final ArrayList<String> errorList = new ArrayList<>();
    private Problem problem = null;
    private int rowCount = 0;

    @Override
    public void exitItems(@NotNull InputParser.ItemsContext ctx) {
        ctx.NAME().stream().forEach(x -> items.add(x.getText()));
    }

    @Override
    public void exitMatrix(@NotNull InputParser.MatrixContext ctx) {
        ctx.row().stream().forEach(x -> {
            ArrayList<Boolean> row = new ArrayList<>();
            x.AFFINITY().stream().forEach(y -> row.add(toBoolean(y.getText())));
            affinityMatrix.add(row);
        });
        if (affinityMatrix.size() != items.size())
            errorList.add(String.format("The number of the rows in the matrix is %d, need to be %d", affinityMatrix.size(), items.size()));
    }

    private Boolean toBoolean(String text) {
        return text.equalsIgnoreCase("x");
    }

    @Override
    public void enterRow(@NotNull InputParser.RowContext ctx) {
        rowCount++;
        super.enterRow(ctx);
    }

    @Override
    public void exitRow(@NotNull InputParser.RowContext ctx) throws RecognitionException {
        if (ctx.AFFINITY().size() != items.size())
            errorList.add(String.format("The size of the ROW %d is %d, need to be %d", rowCount, ctx.AFFINITY().size(), items.size()));
    }

    @Override
    public void exitStart(@NotNull InputParser.StartContext ctx) {
        if (errorList.isEmpty())
            problem = new Problem(items, affinityMatrix);
    }

    public Problem getProblem() {
        return problem;
    }

    public ArrayList<String> getErrorList() {
        return errorList;
    }
}
