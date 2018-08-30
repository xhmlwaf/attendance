package com.yunhuakeji.attendance.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.swagger.models.auth.In;

public class StreamTest {

  public static void main(String[] args) {

    //创建流
    //1.Collection 系列集合提供的方法 stream() 或者 parallelStream()
    List<String> list = new ArrayList();
    list.stream();
    //2.由数组创建流  Arrays中的静态方法 stream() 创建数据源 。
    //3.由值创建流
    //可以使用静态方法 Stream.of(), 通过显示值 创建一个流。它可以接收任意数量的参数。
    //public static< T> Stream< T> of(T… values) : 返回一个流
    //4.由函数创建流，创建无限流。
    //可以使用静态方法 Stream.iterate() 和 Stream.generate(), 创建无限流。

//    1. 迭代：public static< T> Stream< T> iterate(final T seed, final UnaryOperator< T> f)
//    2. 生成：public static< T> Stream< T> generate(Supplier< T> s)


    //中间操作
//    filter(Predicate p)	接收 Lambda ， 从流中排除某些元素。
//    distinct()	筛选，通过流所生成元素的 hashCode() 和 equals() 去 除重复元素
//    limit(long maxSize)	截断流，使其元素不超过给定数量
//    skip(long n)	跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素 不足 n 个，则返回一个空流。与 limit(n) 互补

//    映射
//    方法	描述
//    map(Function f)	接收一个函数作为参数，该函数会被应用到每个元 素上，并将其映射成一个新的元素。
//    mapToDouble(ToDoubleFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 DoubleStream。
//    mapToInt(ToIntFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 IntStream。
//    mapToLong(ToLongFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 LongStream。
//    flatMap(Function f)	接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流

//    排序
//    方法	描述
//    sorted()	产生一个新流，其中按自然顺序排序
//    sorted(Comparator comp)	产生一个新流，其中按比较器顺序排序
    String[] ss = new String[]{"c", "f", "a", "a"};

//    Arrays.stream(ss).sorted().forEach(System.out::print);
//    Arrays.stream(ss).distinct().sorted().forEach(System.out::print);
//    Arrays.stream(ss).limit(2).sorted().forEach(System.out::print);
//    Arrays.stream(ss).skip(1).sorted().forEach(System.out::print);
//
//    Arrays.stream(ss).map(x -> x).collect(Collectors.toList());
    //mapToInt 生成后转类型
    List<String> l1 = new ArrayList<>();
    l1.add("hello");
    l1.add("world");

    l1.stream().map(x -> x.split("")).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);

    Function<String, String> function = p -> "hello," + p;
    l1.stream().map(function).forEach(System.out::println);

    Predicate<String> p = x -> "a".equals(x);
    System.out.println(Arrays.stream(ss).allMatch(p));
    System.out.println(Arrays.stream(ss).anyMatch(p));
    System.out.println(Arrays.stream(ss).noneMatch(p));

//    Stream的终止操作
//    终止操作会从流的流水线生成结果。其结果可以是任何不是流的值，例如:List、Integer，甚至是 void 。
//
//    5.1 查找与匹配
//    方法	描述
//    allMatch(Predicate p)	检查是否匹配所有元素
//    anyMatch(Predicate p)	检查是否至少匹配一个元素
//    noneMatch(Predicate p)	检查是否没有匹配所有元素
//    findFirst()	返回第一个元素
//    findAny()	返回当前流中的任意元素
//    count()	返回流中元素总数
//    max(Comparator c)	返回流中最大值
//    min(Comparator c)	返回流中最小值
//    forEach(Consumer c)	内部迭代(使用 Collection 接口需要用户去做迭 代，称为外部迭代。相反，Stream API 使用内部 迭代——它帮你把迭代做了)

//    归约
//    备注:map 和 reduce 的连接通常称为 map-reduce 模式，因 Google 用它 来进行网络搜索而出名。
//
//    方法	描述
//    reduce(T iden, BinaryOperator b)	可以将流中元素反复结合起来，得到一个值。 返回 T
//    reduce(BinaryOperator b)	可以将流中元素反复结合起来，得到一个值。 返回 Optional< T>

//    收集
//    方法	描述
//    collect(Collector c)	将流转换为其他形式。接收一个 Collector接口的 实现，用于给Stream中元素做汇总的方法

//    Collector 接口中方法的实现决定了如何对流执行收集操作(如收 集到 List、Set、Map)。但是 Collectors 实用类 供了很多静态 方法，可以方便地创建常见收集器实例，具体方法与实例如下表:
//
//    方法	返回类型	作用
//    toList	List<T>	把流中元素收集到List
//    List<Employee> emps= list.stream().collect(Collectors.toList());
//    toSet	Set<T>	把流中元素收集到Set
//    Set<Employee> emps= list.stream().collect(Collectors.toSet());
//    toCollection	Collection<T>	把流中元素收集到创建的集合
//    Collection<Employee>emps=list.stream().collect(Collectors.toCollection(ArrayList::new));
//    counting	Long	计算流中元素的个数
//    long count = list.stream().collect(Collectors.counting());
//    summingInt	Integer	对流中元素的整数属性求和
//        inttotal=list.stream().collect(Collectors.summingInt(Employee::getSalary));
//    averagingInt	Double	计算流中元素Integer属性的平均 值
//    doubleavg= list.stream().collect(Collectors.averagingInt(Employee::getSalary));
//    summarizingInt	IntSummaryStatistics	收集流中Integer属性的统计值。 如:平均值
//        IntSummaryStatisticsiss= list.stream().collect(Collectors.summarizingInt(Employee::getSalary));
//    joining	String	连接流中每个字符串
//    String str= list.stream().map(Employee::getName).collect(Collectors.joining());
//    maxBy	Optional<T>	根据比较器选择最大值
//    Optional<Emp>max= list.stream().collect(Collectors.maxBy(comparingInt(Employee::getSalary)));
//    minBy	Optional<T>	根据比较器选择最小值
//    Optional<Emp> min = list.stream().collect(Collectors.minBy(comparingInt(Employee::getSalary)));
//    reducing	归约产生的类型	从一个作为累加器的初始值 开始，利用BinaryOperator与 流中元素逐个结合，从而归 约成单个值
//    inttotal=list.stream().collect(Collectors.reducing(0, Employee::getSalar, Integer::sum));
//    collectingAndThen	转换函数返回的类型	包裹另一个收集器，对其结 果转换函数
//    inthow= list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
//    groupingBy	Map<K, List<T>>	根据某属性值对流分组，属 性为K，结果为V
//    Map<Emp.Status, List<Emp>> map= list.stream() .collect(Collectors.groupingBy(Employee::getStatus));
//    partitioningBy	Map<Boolean, List<T>>	根据true或false进行分区
//    Map<Boolean,List<Emp>>vd= list.stream().collect(Collectors.partitioningBy(Employee::getManage));

    //Predicate 断言
//    Consumer	Consumer< T >	接收T对象，不返回值
//    Predicate	Predicate< T >	接收T对象并返回boolean
//    Function	Function< T, R >	接收T对象，返回R对象
//    Supplier	Supplier< T >	提供T对象（例如工厂），不接收值
//    UnaryOperator	UnaryOperator	接收T对象，返回T对象
//    BinaryOperator	BinaryOperator	接收两个T对象，返回T对象

  }
}
