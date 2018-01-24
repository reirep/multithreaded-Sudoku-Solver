import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by pierre.
 */
public class SyncStack {
    private Stack<Sudoku> st = new Stack<>();
    public volatile LinkedList<Sudoku> solutions = new LinkedList<>();
    private boolean oneSolution = true;
    private final int nbrThreads;
    public volatile int nbrChomeurs;

    public SyncStack(int nbrThreads){
        this.nbrThreads = nbrThreads;
        nbrChomeurs = nbrThreads;
    }

    public SyncStack(boolean oneSolution, int nbrThreads){
        this.oneSolution = oneSolution;
        this.nbrThreads = nbrThreads;
        nbrChomeurs = nbrThreads;
    }

    public synchronized void push(Sudoku s){
        st.push(s);
    }

    public synchronized Sudoku pop(){
        return st.pop();
    }

    public synchronized int size(){
        return st.size();
    }

    public synchronized boolean isFinished(){
        return oneSolution && solutions.size() > 0 || st.size() == 0 && !oneSolution && solutions.size() > 0;
    }
}
