package jdapper.stream;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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

public class CombinedStream<T> implements Stream<T> {

   private List<Stream<T>> streams;
   
   public CombinedStream(List<Stream<T>> streams){
      this.streams = streams;
   }
   
   @Override
   public Iterator<T> iterator() {
      return new Iterator<T>() {
         
         private int currentIteratorNumber = 0;
         private Iterator<T> currentIterator = streams.get(currentIteratorNumber).iterator();
         
         @Override
         public boolean hasNext() {
            if (currentIterator.hasNext()) return true;
            if (currentIteratorNumber >= streams.size()) return false;
            currentIteratorNumber++;
            currentIterator = streams.get(currentIteratorNumber).iterator();
            return currentIterator.hasNext();
         }

         @Override
         public T next() {
            if (!hasNext()) return null;
            return currentIterator.next();
         }
      };
   }

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
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void close() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public Stream<T> filter(Predicate<? super T> predicate) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IntStream mapToInt(ToIntFunction<? super T> mapper) {
      // TODO Auto-generated method stub
      return null;
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
      // TODO Auto-generated method stub
      return null;
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
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> sorted() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> sorted(Comparator<? super T> comparator) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> peek(Consumer<? super T> action) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> limit(long maxSize) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Stream<T> skip(long n) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void forEach(Consumer<? super T> action) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void forEachOrdered(Consumer<? super T> action) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public Object[] toArray() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <A> A[] toArray(IntFunction<A[]> generator) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public T reduce(T identity, BinaryOperator<T> accumulator) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Optional<T> reduce(BinaryOperator<T> accumulator) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <R, A> R collect(Collector<? super T, A, R> collector) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Optional<T> min(Comparator<? super T> comparator) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Optional<T> max(Comparator<? super T> comparator) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public long count() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public boolean anyMatch(Predicate<? super T> predicate) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean allMatch(Predicate<? super T> predicate) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean noneMatch(Predicate<? super T> predicate) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Optional<T> findFirst() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Optional<T> findAny() {
      // TODO Auto-generated method stub
      return null;
   }

}
