// Generated from Input.g4 by ANTLR 4.5
package org.tigergoatgrass.input;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InputParser}.
 */
public interface InputListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InputParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull InputParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull InputParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#items}.
	 * @param ctx the parse tree
	 */
	void enterItems(@NotNull InputParser.ItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#items}.
	 * @param ctx the parse tree
	 */
	void exitItems(@NotNull InputParser.ItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#matrix}.
	 * @param ctx the parse tree
	 */
	void enterMatrix(@NotNull InputParser.MatrixContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#matrix}.
	 * @param ctx the parse tree
	 */
	void exitMatrix(@NotNull InputParser.MatrixContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(@NotNull InputParser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(@NotNull InputParser.RowContext ctx);
}