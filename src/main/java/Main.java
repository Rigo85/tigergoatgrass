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

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.tigergoatgrass.input.InputLexer;
import org.tigergoatgrass.input.InputParser;
import org.tigergoatgrass.input.InputWalker;
import org.tigergoatgrass.input.Problem;
import org.tigergoatgrass.solutions.AstarSolution;
import org.tigergoatgrass.solutions.ISearchSolution;
import org.tigergoatgrass.solutions.Trip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> input;
        if (args.length != 0) {
            input = Arrays.asList(args);
        } else {
            System.err.println("You need to enter the input file(s)!");
            System.err.println("Trying to use default input file(s)! ;-)");
            input = Arrays.asList("input1.txt", "input2.txt", "input3.txt");
        }

        input.stream().forEach(file -> {
            final Problem problem = getProblem(file);
            if (problem != null) {
                ArrayList<ISearchSolution> ISearchSolutions = new ArrayList<>();
                ISearchSolutions.add(new AstarSolution());

                ISearchSolutions.stream().forEach(x -> {
                    System.out.printf("<------------ Solution: %s ------------->\n", x.getClass().getName());
                    final ArrayList<Trip> trips = x.compute(problem);
                    if (trips != null) {
                        trips.stream().forEach(System.out::println);
                    }
                });
                System.out.println("<===============================================>");
            }
        });
    }

    private static Problem getProblem(String inputPath) {
        Problem problem = null;
        try {
            ANTLRFileStream fileStream = new ANTLRFileStream(inputPath);
            InputLexer lexer = new InputLexer(fileStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            InputParser parser = new InputParser(tokens);
            ParseTree tree = parser.start();
            ParseTreeWalker walker = new ParseTreeWalker();
            InputWalker inputWalker = new InputWalker();
            walker.walk(inputWalker, tree);

            problem = inputWalker.getProblem();

            System.out.println(problem != null ? problem : "Problem is empty!");

            if (!inputWalker.getErrorList().isEmpty()) {
                System.out.println("----------- Errors -----------");
                inputWalker.getErrorList().stream().forEach(System.err::println);
                problem = null;
            }
        } catch (IOException | RecognitionException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return problem;
    }
}
