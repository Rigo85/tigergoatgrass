# Tiger Goat Grass
Solution for an old but fun riddle concerning a boatman who has to transport a tiger(may be a wolf), goat and a bale of grass across a river.  In this puzzle, one can represent the affinity of the three main items to be transported as follows:

<table border cellspacing=0 cellpadding=5>
<tr>
<td></td><td>Tiger</td><td>Goat</td><td>Grass</td>
</tr>
<tr>
<td>Tiger</td><td>0</td><td>X</td><td>0</td>
</tr>
<tr>
<td>Goat</td><td>X</td><td>0</td><td>X</td>
</tr>
<tr>
<td>Grass</td><td>0</td><td>X</td><td>0</td>
</tr>
</table

The "x" represents the item pair that have strong affinity, and hence shouldn't be left alone together.

## Solution
The solution was made processing the `input file` using `ANTLR` and seeking the shortest path by `A* algorithm`.

In the branch `master` is the solution for 3 elements and 1 boat capacity.

In the brach `MoreThanOneItemInTheBoat` is the solution for `N` elements and boat capacity equal to `M`
