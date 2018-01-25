import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by pierre.
 */
public class SyncStack {

    final static Logger logger = Logger.getLogger(SyncStack.class);

    private Stack<Sudoku> st = new Stack<>();
    public volatile LinkedList<Sudoku> solutions = new LinkedList<>();
    private boolean oneSolution = true;

    public SyncStack(){}

    public SyncStack(boolean oneSolution){
        this.oneSolution = oneSolution;
    }

    public synchronized void push(Sudoku s){
        st.push(s);
    }

    public synchronized Sudoku pop(){
        Sudoku s = st.pop();
        return s;
    }

    public synchronized int size(){
        return st.size();
    }

    public synchronized boolean isFinished(){
        boolean one = oneSolution && solutions.size() > 0;
        boolean multi = !oneSolution && st.size() == 0;
        return one || multi;
    }
}
