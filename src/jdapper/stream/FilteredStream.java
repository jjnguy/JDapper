package jdapper.stream;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilteredStream<T> extends ComposedStream<T> {

   private Predicate<? super T> filter;
   
   public FilteredStream(Stream<T> base, Predicate<? super T> filter) {
      super(base);
      this.filter = filter;
   }

   @Override
   protected Stream<T> applyOptions() {
      return base.filter(filter);
   }
}
