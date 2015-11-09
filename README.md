# Tiger Goat Grass
Solution for an old but fun riddle concerning a boatman who has to transport a tiger(may be a wolf), goat and a bale of grass across a river.  In this puzzle, one can represent the affinity of the three main items to be transported as follows:

h1 | h2		
-- | --
| Tiger | Goat | Grass
Tiger | 0 | X | 0
Goat | X | 0 | X
Grass | 0 | X | 0

             | 
------------ | -------------
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column

The "x" represents the item pair that have strong affinity, and hence shouldn't be left alone together.

## Solution
The solution was made processing the `input file` using `ANTLR` and seeking the shortest path by `A* algorithm`.

In the branch `master` is the solution for 3 elements and 1 boat capacity.

In the brach `MoreThanOneItemInTheBoat` is the solution for `N` elements and boat capacity equal to `M`
