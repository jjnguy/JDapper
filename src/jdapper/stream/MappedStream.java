package jdapper.stream;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MappedStream<From, To> implements Stream<To> {

   private Stream<From> base;
   private Function<? super From, ? extends To> mapper;
   
   public MappedStream(Stream<From> base, Function<? super From, ? extends To> mapper){
      this.base = base;
      this.mapper = mapper;
   }

   @Override
   public Iterator<To> iterator() {
      return (Iterator<To>) base.map(mapper).iterator();
   }

   @Override
   public Spliterator<To> spliterator() {
      return (Spliterator<To>) base.map(mapper).spliterator();
   }

   @Override
   public boolean isParallel() {
      return base.isParallel();
   }

   @Override
   public Stream<To> sequential() {
      return base.sequential().map(mapper);
   }

   @Override
   public Stream<To> parallel() {
      return (Stream<To>) base.map(mapper).parallel();
   }

   @Override
   public Stream<To> unordered() {
      return (Stream<To>) base.map(mapper).unordered();
   }

   @Override
   public Stream<To> onClose(Runnable closeHandler) {
      return (Stream<To>) base.map(mapper).onClose(closeHandler);
   }

   @Override
   public void close() {
      base.map(mapper).close();
   }

   @Override
   public Stream<To> filter(Predicate<? super To> predicate) {
      return (Stream<To>) base.map(mapper).filter(predicate);
   }

   @Override
   public <R> Stream<R> map(Function<? super To, ? extends R> mapper) {
      return base.map(this.mapper).map(mapper);
   }

   @Override
   public IntStream mapToInt(ToIntFunction<? super To> mapper) {
      return base.map(this.mapper).mapToInt(mapper);
   }

   @Override
   public LongStream mapToLong(ToLongFunction<? super To> mapper) {
      return base.map(this.mapper).mapToLong(mapper);
   }

   @Override
   public DoubleStream mapToDouble(ToDoubleFunction<? super To> mapper) {
      return base.map(this.mapper).mapToDouble(mapper);
   }

   @Override
   public <R> Stream<R> flatMap(Function<? super To, ? extends Stream<? extends R>> mapper) {
      return base.map(this.mapper).flatMap(mapper);
   }

   @Override
   public IntStream flatMapToInt(Function<? super To, ? extends IntStream> mapper) {
      return base.map(this.mapper).flatMapToInt(mapper);
   }

   @Override
   public LongStream flatMapToLong(Function<? super To, ? extends LongStream> mapper) {
      return base.map(this.mapper).flatMapToLong(mapper);
   }

   @Override
   public DoubleStream flatMapToDouble(Function<? super To, ? extends DoubleStream> mapper) {
      return base.map(this.mapper).flatMapToDouble(mapper);
   }

   @Override
   public Stream<To> distinct() {
      return (Stream<To>) base.map(this.mapper).distinct();
   }

   @Override
   public Stream<To> sorted() {
      return (Stream<To>) base.map(this.mapper).sorted();
   }

   @Override
   public Stream<To> sorted(Comparator<? super To> comparator) {
      return (Stream<To>) base.map(this.mapper).sorted(comparator);
   }

   @Override
   public Stream<To> peek(Consumer<? super To> action) {
      return (Stream<To>) base.map(this.mapper).peek(action);
   }

   @Override
   public Stream<To> limit(long maxSize) {
      return (Stream<To>) base.map(this.mapper).limit(maxSize);
   }

   @Override
   public Stream<To> skip(long n) {
      return (Stream<To>) base.map(this.mapper).skip(n);
   }

   @Override
   public void forEach(Consumer<? super To> action) {
      base.map(this.mapper).forEach(action);
   }

   @Override
   public void forEachOrdered(Consumer<? super To> action) {
      base.map(this.mapper).forEachOrdered(action);
   }

   @Override
   public Object[] toArray() {
      return base.map(this.mapper).toArray();
   }

   @Override
   public <A> A[] toArray(IntFunction<A[]> generator) {
      return base.map(this.mapper).toArray(generator);
   }

   @Override
   public To reduce(To identity, BinaryOperator<To> accumulator) {
      return base.map(item->(To)mapper.apply(item)).reduce(identity, accumulator);
   }

   @Override
   public Optional<To> reduce(BinaryOperator<To> accumulator) {
      return base.map(item->(To)mapper.apply(item)).reduce(accumulator);
   }

   @Override
   public <U> U reduce(U identity, BiFunction<U, ? super To, U> accumulator, BinaryOperator<U> combiner) {
      return base.map(this.mapper).reduce(identity, accumulator, combiner);
   }

   @Override
   public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super To> accumulator, BiConsumer<R, R> combiner) {
      return base.map(this.mapper).collect(supplier, accumulator, combiner);
   }

   @Override
   public <R, A> R collect(Collector<? super To, A, R> collector) {
      return base.map(this.mapper).collect(collector);
   }

   @Override
   public Optional<To> min(Comparator<? super To> comparator) {
      return (Optional<To>) base.map(this.mapper).min(comparator);
   }

   @Override
   public Optional<To> max(Comparator<? super To> comparator) {
      return (Optional<To>) base.map(this.mapper).max(comparator);
   }

   @Override
   public long count() {
      return base.map(this.mapper).count();
   }

   @Override
   public boolean anyMatch(Predicate<? super To> predicate) {
      return base.map(this.mapper).anyMatch(predicate);
   }

   @Override
   public boolean allMatch(Predicate<? super To> predicate) {
      return base.map(this.mapper).allMatch(predicate);
   }

   @Override
   public boolean noneMatch(Predicate<? super To> predicate) {
      return base.map(this.mapper).noneMatch(predicate);
   }

   @Override
   public Optional<To> findFirst() {
      return (Optional<To>) base.map(this.mapper).findFirst();
   }

   @Override
   public Optional<To> findAny() {
      return (Optional<To>) base.map(this.mapper).findAny();
   }
}
