package feature.collections;

import java.util.Collection;
import java.util.Optional;

public class Streams {

    public Optional<String> findTheSmallestStringGreaterThan5ThatStartsWithA(Collection<String> values) {
        return values
            .stream() //lazy
            .filter(s -> s.length() > 5) //lazy
            .filter(s -> s.toLowerCase().startsWith("a")) //lazy
            .distinct() //lazy
            .min(String::compareTo);//eager
    }
}
