# Java Multithreaded Sudoku Solver

I create this solve to entertain me, if you have suggestions issues and push request are welcome.

## How to use it ?

**I know that there is currently a bug when there is more than one thread (\o/). I will adress it when i find the time.**

Just call the static method solve in Runner.java with your grid in argument. 
The boolean oneSol is to indicate if the solver must stop after he found one solution or if he need to find all the solutions.
The integer passed is the number of thread working on the problem.

There this is an example in src/Runner.java 
```java
        byte [][] grid = new byte[][]{
                {1,0,5, 3,9,6, 4,0,2},
                {0,0,0, 0,0,5, 0,1,0},
                {6,9,4, 0,8,2, 0,0,3},

                {7,0,2, 0,0,0, 3,0,8},
                {8,0,9, 0,0,0, 1,0,7},
                {4,3,1, 0,0,0, 0,6,0},

                {2,4,0, 0,1,9, 8,0,5},
                {5,0,3, 0,0,0, 6,0,4},
                {0,0,6, 0,0,3, 7,2,0},
        };

        LinkedList<Sudoku> sols = solve(grid, false, 1);
        sols.forEach(System.out::println);
```
