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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class Combinations {
    public static <T> Stream<List<T>> getCombinationsStream(List<T> list, int n) {
        return LongStream.range(1, 1 << list.size())
                .mapToObj(l -> bitMapToList(l, list))
                .filter(l -> l.size() <= n)
                .sorted((l1, l2) -> l2.size() - l1.size());
    }

    private static <T> List<T> bitMapToList(long bitmap, List<T> list) {
        return IntStream.range(0, list.size())
                .filter(i -> 0 != ((1 << i) & bitmap))
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }
}
