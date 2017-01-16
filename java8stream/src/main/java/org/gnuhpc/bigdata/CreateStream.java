package org.gnuhpc.bigdata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by gnuhpc on 2017/1/16.
 */
public class CreateStream {
    public static void main(String[] args) {
        Stream.iterate(0, n -> n+1).limit(10).forEach(System.out::println);
        IntStream.of(1,2,3).forEach(System.out::println);
        IntStream.range(1,3).forEach(System.out::println);
        IntStream.rangeClosed(1,3).forEach(System.out::println);
        Stream.of(5, 4, 3, 2, 1).skip(2).forEach(System.out::println);
        Stream.of(5, 4, 3, 2, 1).findFirst().ifPresent(System.out::println);
        Stream.of(5, 4, 3, 2, 1).findAny().ifPresent(System.out::println);

        List<String> strings = new ArrayList<>();
        strings.add("a,b,c");
        strings.add("d,e,f");

        strings.stream().flatMap(str -> Stream.of(str.split(","))).collect(Collectors.toList()).stream().forEach(System.out::println);

        Function<Integer, Integer> identity = n -> n;
        Function<Integer, Integer> square = n -> n * n;
        Stream.of(1, 2, 3).collect(Collectors.toMap(identity, square)).entrySet().stream().forEach(System.out::println);

        BiFunction<StringBuilder, Integer, StringBuilder> accumulator = (sb, val) -> sb.append(val);
        BinaryOperator<StringBuilder> combiner = (s1, s2) -> s1.append(s2);
        System.out.println(Stream.of(1, 2, 3).reduce(new StringBuilder(), accumulator, combiner));

    }


}
