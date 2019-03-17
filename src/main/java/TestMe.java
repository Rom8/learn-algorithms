import java.math.BigInteger;
import java.util.Objects;
import java.util.stream.Stream;

public class TestMe {

    public static void main(String[] args) {
        BigInteger bi = new BigInteger("123");

        int i = (int) (18e5+23);
        System.out.println(Long.MAX_VALUE);

        new Professor();
    }

    static String[] bigSorting(String[] unsorted) {
        return Stream.of(unsorted)
                .map(BigInteger::new)
                .sorted()
                .map(Objects::toString)
                .toArray(String[]::new);
    }


    static class Person {

        public Person() {
            name();
        }

        protected void name(){
            System.out.println("Person");
        }
    }

    static class Professor extends Person {



        @Override
        protected void name() {
            System.out.println("Professor");
        }
    }
}
