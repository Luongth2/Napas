package java8;
/*
https://mkyong.com/
 */
import java.util.*;
import java.util.function.Function;
import org.apache.commons.codec.digest.DigestUtils;

public class Java8Function {
    public static void main(String[] args) {
        // This example takes a <T> String and returns the length of the string as <R> Integer.
        Function<String, Integer> func = x -> x.length();

        Integer cnt = func.apply("mkyong");   // 6

        System.out.println(cnt);

        Function<Integer, Integer> func2 = x -> x * 2;

        Integer rs = func.andThen(func2).apply("mkyong");   // 12

        System.out.println(rs);

        //This example accepts Function as an argument, convert a List into a Map.
        Java8Function obj = new Java8Function();

        List<String> list = Arrays.asList("node", "c++", "java", "javascript");

        // lambda
        Map<String, Integer> map = obj.convertListToMap(list, x -> x.length());

        System.out.println(map);    // {node=4, c++=3, java=4, javascript=10}

        // method reference
        Map<String, Integer> map2 = obj.convertListToMap(list, obj::getLength);

        System.out.println(map2);

        // This example accepts Function as an argument, convert a List of String into another List of String, which was hashed with SHA256.
        // lambda
        //List<String> result3 = obj.map(list, x -> obj.sha256(x));

        // method reference
        List<String> result2 = obj.map(list, obj::sha256);

        result2.forEach(System.out::println);
    }

    public <T, R> Map<T, R> convertListToMap(List<T> list, Function<T, R> func) {

        Map<T, R> result = new HashMap<>();
        for (T t : list) {
            result.put(t, func.apply(t));
        }
        return result;

    }

    public Integer getLength(String str) {
        return str.length();
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> func) {

        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(func.apply(t));
        }
        return result;

    }

    // sha256 a string
    public String sha256(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
