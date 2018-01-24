import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Created by pierre.
 */
public class Runner extends Thread{


    public static void main(String ... args){
        /* -> original, to copy, paste and verify
        byte [][] grid = new byte[][]{
                {1,7,5, 3,9,6, 4,8,2},
                {3,2,8, 4,7,5, 9,1,6},
                {6,9,4, 1,8,2, 5,7,3},

                {7,5,2, 9,6,1, 3,4,8},
                {8,6,9, 2,3,4, 1,5,7},
                {4,3,1, 8,5,7, 2,6,9},

                {2,4,7, 6,1,9, 8,3,5},
                {5,1,3, 7,2,8, 6,9,4},
                {9,8,6, 5,4,3, 7,2,1},
        };
        */

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
        sols.forEach((Sudoku s) -> {
            System.out.println(s.toString());
        });
    }

    private SyncStack st;

    public Runner(SyncStack st){
        this.st = st;
    }

    public void run(){
        Sudoku s = null;
        while(!st.isFinished()){
            try {
                st.nbrChomeurs --;
                s = st.pop();
            }
            catch(EmptyStackException ignore){
                st.nbrChomeurs ++;
                s = null;
            }
            if(s != null) {
                s.solve();
                st.nbrChomeurs ++;
            }
        }
    }


    public static LinkedList<Sudoku> solve(byte [][] grid, boolean oneSol, int threads){
        SyncStack st = new SyncStack(oneSol, threads);
        Sudoku sudoku = new Sudoku(grid, (byte)0,(byte)0, st);
        st.push(sudoku);

        Runner runners [] = new Runner[threads];
        for(int i =0; i < threads; i++)
            runners[i] = new Runner(st);

        for(Runner r : runners)
            r.start();

        for(Runner r : runners)
            try {
                r.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return st.solutions;
    }
}
