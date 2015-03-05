package feature.interfaces;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class Interfaces {

    public interface MyInterface {
        
        void doSomething();
        
        default void doSomethingByDefault() {
            System.out.println("doing stuff by default!");
        }

        static void noNeedToImplement() {
            System.out.println("no need to implement me!");
        }
    }
    
    public class MyImpl implements MyInterface {
        @Override
        public void doSomething() {
            System.out.println("Interfaces.MyImpl.doSomething()");
        }
    }
    
    public void callStuff() {
        MyImpl impl = new MyImpl();
        impl.doSomething();
        impl.doSomethingByDefault();
        /**
         * static interface methods are not accessible
         * via an object instance 
         **/
        //impl.noNeedToImplement();
        
        MyInterface.noNeedToImplement();
    }
    
    public Stream<String> reverseStrings(Collection<String> strings) {
        return strings.stream()
            .sorted(Comparator.reverseOrder());
    }
    
    public static void main(String[] args) {
        new Interfaces().callStuff();
    }
}
