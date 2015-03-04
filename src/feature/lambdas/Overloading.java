package feature.lambdas;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Overloading {

    public interface StringConsumer extends Consumer<String> {
    }
    
    public void overloadedConsumerMethod(Consumer<String> consumer) {
        System.out.println("Consumer");
        //...
    }

    public void overloadedConsumerMethod(StringConsumer consumer) {
        System.out.println("StringConsumer");
        //...
    }

    public interface OddPredicate {
        public boolean isOdd(int value);
    }
    
    public void overloadedPredicateMethod(Predicate<Integer> predicate) {
        System.out.println("Predicate");
        //...
    }

    public void overloadedPredicateMethod(OddPredicate predicate) {
        System.out.println("OddPredicate");
        //...
    }
    
    public static void main(String[] args) {
        Overloading overloading = new Overloading();
        
        //In this case, the java compiler picks the most specific functional 
        //interface (StringConsumer which extends Consumer)
        overloading.overloadedConsumerMethod(s -> System.out.println(s));
        
        //In this case, the compiler throws an error because the lambda type 
        //is ambiguous (because OddPredicate does not extend Predicate). 
        //overloading.overloadedPredicateMethod(n -> n % 2 == 0);
        
        //The scenario above can be handled by casting the lambda expression to the 
        //desired type.
        overloading.overloadedPredicateMethod((OddPredicate) n -> n % 2 == 0);
        overloading.overloadedPredicateMethod((Predicate<Integer>) n -> n % 2 == 0);
    }

}
