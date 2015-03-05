package feature.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionalInterfaces {

    /**
     * A Functional Interface can only contain 1 abstract method.
     */
    public interface Peeker<T> {
        void peek(String t);
//        boolean shouldReject(Object t);
//        boolean equals(Object t);
//        int hashCode();
        default void andThenDoSomethingInteresting() {
            System.out.println(".");
        }
    }

    /**
     * Functional Interface with variable argument list.
     */
    public interface VarArgs {
        void accept(String... t);
    }

    /**
     * Functional Interface with more than 2 arguments
     */
    public interface MoreThanTwoArgs {
        void accept(String s1, String s2, String s3);
    }


    private final List<String> myStrings = new ArrayList<>();
 
    /**
     * Example of retro-fitting a functional interface.
     */
    public void peekAtMyStrings(Peeker<String> peeker) {
        for (String string : myStrings) {
            peeker.peek(string);
        }
        
        //won't compile because peeker doesn't match the Consumer interface
//        myStrings
//            .stream()
//            .peek(peeker);
        
        //This will work
        myStrings
          .stream()
          .peek(s -> peeker.peek(s));
    }
    
    public void lookAtABunchOfStringsAtOnce(VarArgs consumer) {
         consumer.accept("a", "b", "c");
    }

    public void lookAtABunchOfStringsAtOnce(MoreThanTwoArgs consumer) {
        consumer.accept("a", "b", "c");
    }

    public void peekAtMyStringsClient() {
        peekAtMyStrings(s -> System.out.println(s));
        lookAtABunchOfStringsAtOnce(s -> System.out.println(Arrays.toString(s)));
        lookAtABunchOfStringsAtOnce((a, b, c) -> System.out.println(a + b + c));
    }
}
