
import java.util.*;

class ForwardChaining {
    ArrayList<int[]> clauses;  // Arraylist holding where elements are integer arrays containing a literaral and its negation

    public ForwardChaining() {
        clauses=new ArrayList<>(); // constructor initializing empty arraylist
    }

    public boolean forwardChaining(int n) {
        // Simplified version of forward chaining algorithm: does not follow the 
        // textbook. This implementation does not run in linear time because it 
        // scans all clauses multiple times. 

        boolean[] model=new boolean[n+1];    // All symbols set to false initially.

        //  Sanity check clauses
        for(int i=0; i<clauses.size(); i++) {
            int posLits=0;
            int[] clause=clauses.get(i);
            for(int j=0; j<clause.length; j++) {
                assert clause[i]<=n && clause[i]>=-n : "Found reference to variable larger than n.";
                if(clause[j]>0) {
                    posLits++;
                }
            }

            assert posLits<=1 : "At most one positive literal is allowed in each clause.";
        }

        //  Iterate through set of clauses applying modus ponens until we reach a 
        //  fixpoint.
        boolean fixPoint=false;

        while(!fixPoint) {  // while facts are being made the fixpoint gets set to false at the end
            fixPoint=true;

            for(int i=0; i<clauses.size(); i++) {  // iterates over each clause in the list
                int[] clause=clauses.get(i);  // gets the clause
                //  Check all symbols that appear negated in this clause.
                //  If all true, then apply modus ponens. 

                boolean allTrue=true;  // here you check all the negative literals for the modus ponens

                for(int j=0; j<clause.length; j++) {  // looping over every clause 'j' for the 'i' selected
                    if(clause[j]<0 && ! model[-clause[j]]) {
                        allTrue=false;
                        break;
                    }
                }

                if(allTrue) {
                    boolean goalClause=true;
                    for(int j=0; j<clause.length; j++) {
                        if(clause[j]>0) {
                            goalClause=false;
                            if(! model[clause[j]]) {
                                model[clause[j]]=true;
                                fixPoint=false;
                                System.out.println("Inferred "+clause[j]+" with clause "+Arrays.toString(clause));
                            }

                            break;
                        }
                    }
                    if(goalClause) {
                        // This is a goal clause
                        System.out.println("No models satisfy all clauses simultaneously. False goal clause: "+Arrays.toString(clause));
                        return false;
                    }
                }
            }
        }

        System.out.println("Model: ");
        for(int i=1; i<model.length; i++) {
            System.out.println("Variable "+i+" = "+model[i]);
        }
        return true;
    }

    public void addClause(int[] c) {  // this function is called to add logic clauses seprated by disjunctions 'or/,' with negatives rep negation
        clauses.add(c);
    }

    public void resetClauses() {
        clauses.clear();
    }

    public static void example() {

        // The following is the CNF of Figure 7.16 in AIMA
        // Symbols A, B, L, M, P, Q are numbered 1..6.
        ForwardChaining fc=new ForwardChaining();

        fc.addClause(new int[]{-5, 6});
        fc.addClause(new int[]{-3, -4, 5});
        fc.addClause(new int[]{-2,-3,4});
        fc.addClause(new int[]{-1, -5, 3});
        fc.addClause(new int[]{-1,-2,3});
        fc.addClause(new int[]{1});
        fc.addClause(new int[]{2});

        //  Call forward chaining. 6 is the number of proposition symbols, which must be
        //  numbered 1..6. 

        boolean modelExists = fc.forwardChaining(6);
        System.out.println("Model exists: "+modelExists);
    }
    public static void multiClueWumpus() {

        ForwardChaining fc=new ForwardChaining();

        fc.addClause(new int[]{-22, -13, 31});
        fc.addClause(new int[]{-22, -16, 31});
        fc.addClause(new int[]{-21, -14, 32});
        fc.addClause(new int[]{-21, -15, 32});
        fc.addClause(new int[]{-21, -12, 33});
        fc.addClause(new int[]{-25, -16, 33});
        fc.addClause(new int[]{-22, -11, 34});
        fc.addClause(new int[]{-26, -15, 34});
        fc.addClause(new int[]{-26, -14, 35});
        fc.addClause(new int[]{-23, -12, 35});
        fc.addClause(new int[]{-24, -13, 36});
        fc.addClause(new int[]{-23, -11, 36});
        fc.addClause(new int[]{21});
        fc.addClause(new int[]{14});


        boolean modelExists = fc.forwardChaining(36);
        System.out.println("Model exists: "+modelExists);
    }
    public static void main(String [] args) {

        multiClueWumpus();

    }

}
Inferred 21 with clause [21]
        Inferred 14 with clause [14]
        Inferred 32 with clause [-21, -14, 32]
        Model: 
        Variable 1 = false
        Variable 2 = false
        Variable 3 = false
        Variable 4 = false
        Variable 5 = false
        Variable 6 = false
        Variable 7 = false
        Variable 8 = false
        Variable 9 = false
        Variable 10 = false
        Variable 11 = false
        Variable 12 = false
        Variable 13 = false
        Variable 14 = true
        Variable 15 = false
        Variable 16 = false
        Variable 17 = false
        Variable 18 = false
        Variable 19 = false
        Variable 20 = false
        Variable 21 = true
        Variable 22 = false
        Variable 23 = false
        Variable 24 = false
        Variable 25 = false
        Variable 26 = false
        Variable 27 = false
        Variable 28 = false
        Variable 29 = false
        Variable 30 = false
        Variable 31 = false
        Variable 32 = true
        Variable 33 = false
        Variable 34 = false
        Variable 35 = false
        Variable 36 = false
        Model exists: true

        Process finished with exit code 0