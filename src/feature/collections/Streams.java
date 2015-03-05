package feature.collections;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    public Optional<String> findTheSmallestStringGreaterThan5ThatStartsWithA(Collection<String> values) {
        return values
            .stream() //lazy
            .filter(s -> s.length() > 5) //lazy
            .filter(s -> s.toLowerCase().startsWith("a")) //lazy
            .distinct() //lazy
            .min(String::compareTo);//eager
    }

    public Optional<String> parallelStreams(Collection<String> values) {
        return values
                .parallelStream() //lazy
                .filter(s -> s.length() > 5) //lazy
                .filter(s -> s.toLowerCase().startsWith("a")) //lazy
                .distinct() //lazy
                .min(String::compareTo);//eager
    }
    
    public Stream<String> createStreamWithDistinctStringsGreaterThan5Chats(Collection<String> values) {
        return values
                .parallelStream() //lazy
                .filter(s -> s.length() > 5) //lazy
                .distinct();//llazy
    }

    public List<String> createNumericStringRange(int quantity) {
       return IntStream
                .range(0, quantity)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }
}
