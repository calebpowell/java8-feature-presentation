package feature.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FunctionalInterfaces {

    /**
     * A custom example of a Functional Interface can only 
     * contain 1 abstract method.
     */
    public interface Peeker<T> {
        void peek(String t);
//        void reject(String t);
//        default void andThenPrintAPeriod() {
//            System.out.println(".");
//        }
    }

    /**
     * A custom example of a Functional Interface 
     * with variable argument list.
     */
    public interface VarArgs {
        void accept(String... t);
    }

    /**
     * A custom example of a Functional Interface 
     * with more than 2 arguments
     */
    public interface MoreThanTwoArgs {
        void accept(String s1, String s2, String s3);
    }


    private final List<String> myStrings = new ArrayList<>();
 
    /**
     * Example of retro-fitting a functional interface.
     */
    public void peekAtMyStrings(Peeker<String> peeker) {
        //Example legacy implementation
        for (String string : myStrings) {
            peeker.peek(string);
        }
        
        //this won't compile because peeker doesn't match the Consumer interface
//        myStrings
//            .stream()
//            .peek(peeker);
        
        //This will work
        myStrings
          .stream()
          .peek(s -> peeker.peek(s));
        
        //Or delegate with a cast
        this.peekAtMyStrings((Consumer<String>) s -> peeker.peek(s));
    }
    
    public void peekAtMyStrings(Consumer<String> consumer) {
        myStrings
            .stream()
            .peek(consumer);
    }

    
    public void callOverloadedMethodExample() {
//        peekAtMyStrings(s -> System.out.println(s));
        peekAtMyStrings((Consumer<String>) 
                s -> System.out.println(s)
        );
        peekAtMyStrings((Peeker<String>) s -> System.out.println(s));
    }

    public void peekAtMyStrings2(Consumer<String> consumer) {
        for (String string : myStrings) {
            consumer.accept(string);
        }
    }

    public void customPeekAtMyStrings(Peeker<String> consumer) {
        for (String string : myStrings) {
            consumer.peek(string);
        }
    }
    
    public void lookAtABunchOfStringsAtOnce(VarArgs consumer) {
         consumer.accept("a", "b", "c");
    }

    public void lookAtABunchOfStringsAtOnce(MoreThanTwoArgs consumer) {
        consumer.accept("a", "b", "c");
    }

    public void peekAtMyStringsClient() {
        peekAtMyStrings2(s -> System.out.println(s));
        customPeekAtMyStrings(s -> System.out.println(s));
        lookAtABunchOfStringsAtOnce(s -> System.out.println(Arrays.toString(s)));
        lookAtABunchOfStringsAtOnce((String[] s) -> System.out.println(Arrays.toString(s)));
        lookAtABunchOfStringsAtOnce((a, b, c) -> System.out.println(a + b + c));
    }
}
