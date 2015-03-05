package feature.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class MethodReferences {

    private final List<String> myStrings = new ArrayList<>();
    
    public static class StringLengthComparator  {
        public int caseInsensitiveCompare(String str1, String str2) {
            return str1.compareToIgnoreCase(str2);
        }
        public static int compareByLength(String str1, String str2) {
            return Integer.compare(str1.length(), str2.length());
        }
    }
    
    /**
     * Static Method Reference
     */
    public void staticMethodReference() {
        Collections.sort(myStrings, StringLengthComparator::compareByLength);
    }

    /**
     * Object Instance Method Reference
     */
    public void objectInstanceMethodReference() {
        Collections.sort(myStrings, new StringLengthComparator()::caseInsensitiveCompare);
    }

    /**
     * Arbitrary Type Instance Method Reference
     */
    public void arbitraryTypeInstanceMethodReference() {
        myStrings.add("a");
        myStrings.add("b");
        myStrings.add("c");

        //These are not inutitive at all because the signature doesn't match.
        //essentialy, the compiler is going to going to use the 'compareTo' method on 
        //the one of the two parameters to the 
        Collections.sort(myStrings, String::compareTo);
    }

    private <T> T factory(Supplier<T> producer) {
        T t = producer.get();
        //...
        return t;
    }
    
    /**
     * Constructor Method Reference
     */
    public void constructorMethodReference() {
        factory(String::new);
        factory(Object::new);
        factory(Object::new);
    }
    
    public static void main(String[] args) {
        new MethodReferences().arbitraryTypeInstanceMethodReference();
    }
    
}
