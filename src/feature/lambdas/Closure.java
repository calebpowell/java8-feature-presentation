package feature.lambdas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class Closure {

    private String instanceVar = "Mutable Instance Variable";
    
    /**
     * Lambda references a Class instance variable on the heap... no problemo (even
     * if it isn't final or effectively final).
     */
    public void lambdaClosesOverInstanceVar(Collection<String> values) {
        this.instanceVar = "something else";
        values
            .stream()
            .map(str -> str + instanceVar); 
    }

    /**
     * Lambda references a local method variable or parameter. The variable must be 
     * 'effectively' final (i.e. variable 
     * cannot be re-assigned after intialization)
     */
    public void lambdaClosesOverLocalVar(Collection<String> values) {
        String localVar = "Local Variable";
        //localVar = "something else";
        values
            .stream()
            .map(str -> str + localVar) 
            .sequential();
        //localVar = "something else";
    }

    /**
     * Lambda references an element inside a local method array.
     * The Array resides on the heap so you can mutate it...
     */
    public void lambdaClosesOverLocalArrayVar(Collection<String> values) {
        String[] localVar = {"Local Variable"};
        localVar[0] = "something else";
        values
            .parallelStream()
            .map(str -> {
                localVar[0] = String.valueOf(Thread.currentThread().getId());
                return str + localVar[0];
             }) 
            .forEach(s -> System.out.println(s));
        localVar[0] = "something entirely different";
    }
    
    public void anonInnerClassClosesOverInstanceVar(Collection<String> values) {
        this.instanceVar = "something else";
        values
            .stream()
            .map(new Function<String, String>() {
                @Override
                public String apply(String str) {
                    return str + instanceVar;
                }
                
            });
    }

    public void anonInnerClassClosesOverLocalVar(Collection<String> values) {
        String localVar = "Local Variable";
//        localVar = "asdfadf";
        values
            .stream()
            .map(new Function<String, String>() {
                @Override
                public String apply(String str) {
                    return str + localVar;
                }
            }); 
//        localVar = "asdfadf";
    }
    
    public static void main(String[] args) {
        int[] nums = new int[10000];
        List<String> allNums = new ArrayList<>();
        for (int num : nums) {
           allNums.add(String.valueOf(num));
        }
        new Closure().lambdaClosesOverLocalArrayVar(allNums);
    }
}
