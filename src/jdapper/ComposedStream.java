package jdapper;

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

public abstract class ComposedStream<T> implements Stream<T> {

   protected Stream<T> base;

   public ComposedStream(Stream<T> base) {
      this.base = base;
   }

   @Override
   public Iterator<T> iterator() {
      return applyOptions().iterator();
   }
   
   protected abstract Stream<T> applyOptions();

   @Override
   public Spliterator<T> spliterator() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isParallel() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Stream<T> sequential() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> parallel() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> unordered() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> onClose(Runnable closeHandler) {
      return applyOptions().onClose(closeHandler);
   }

   @Override
   public void close() {
      applyOptions().close();
   }

   @Override
   public Stream<T> filter(Predicate<? super T> predicate) {
      return applyOptions().filter(predicate);
   }

   @Override
   public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
      return new MappedStream<>(this, mapper);
   }

   @Override
   public IntStream mapToInt(ToIntFunction<? super T> mapper) {
      return applyOptions().mapToInt(mapper);
   }

   @Override
   public LongStream mapToLong(ToLongFunction<? super T> mapper) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
      return applyOptions().flatMap(mapper);
   }

   @Override
   public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> distinct() {
      return applyOptions().distinct();
   }

   @Override
   public Stream<T> sorted() {
      return applyOptions().sorted();
   }

   @Override
   public Stream<T> sorted(Comparator<? super T> comparator) {
      return applyOptions().sorted(comparator);
   }

   @Override
   public Stream<T> peek(Consumer<? super T> action) {
      return applyOptions().peek(action);
   }

   @Override
   public Stream<T> limit(long maxSize) {
      return applyOptions().limit(maxSize);
   }

   @Override
   public Stream<T> skip(long n) {
      return applyOptions().skip(n);
   }

   @Override
   public void forEach(Consumer<? super T> action) {
      applyOptions().forEach(action);
   }

   @Override
   public void forEachOrdered(Consumer<? super T> action) {
      applyOptions().forEachOrdered(action);
   }

   @Override
   public Object[] toArray() {
      return applyOptions().toArray();
   }

   @Override
   public <A> A[] toArray(IntFunction<A[]> generator) {
      return applyOptions().toArray(generator);
   }

   @Override
   public T reduce(T identity, BinaryOperator<T> accumulator) {
      return applyOptions().reduce(identity, accumulator);
   }

   @Override
   public Optional<T> reduce(BinaryOperator<T> accumulator) {
      return applyOptions().reduce(accumulator);
   }

   @Override
   public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
      return applyOptions().reduce(identity, accumulator, combiner);
   }

   @Override
   public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
      return applyOptions().collect(supplier, accumulator, combiner);
   }

   @Override
   public <R, A> R collect(Collector<? super T, A, R> collector) {
      return applyOptions().collect(collector);
   }

   @Override
   public Optional<T> min(Comparator<? super T> comparator) {
      return applyOptions().min(comparator);
   }

   @Override
   public Optional<T> max(Comparator<? super T> comparator) {
      return applyOptions().max(comparator);
   }

   @Override
   public long count() {
      return applyOptions().count();
   }

   @Override
   public boolean anyMatch(Predicate<? super T> predicate) {
      return applyOptions().anyMatch(predicate);
   }

   @Override
   public boolean allMatch(Predicate<? super T> predicate) {
      return applyOptions().allMatch(predicate);
   }

   @Override
   public boolean noneMatch(Predicate<? super T> predicate) {
      return applyOptions().noneMatch(predicate);
   }

   @Override
   public Optional<T> findFirst() {
      return applyOptions().findFirst();
   }

   @Override
   public Optional<T> findAny() {
      return applyOptions().findAny();
   }
}
