import demo.Stocks;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {

        Stocks stocks = new Stocks("coal", 85);
        Stocks stocks1= new Stocks("cdsl", 1000);
        //Predicate demo
        Predicate<Integer> predicate = a -> (a > 80);
        System.out.println(predicate.test(75));

        //Function demo

        Function<Integer, Integer> function = b -> b/ 10;
        System.out.println(function.apply(80));

        //Consumer demo
        Consumer<String> consumer= C-> System.out.println(C);
         C.accept

            }
        };


    }
}