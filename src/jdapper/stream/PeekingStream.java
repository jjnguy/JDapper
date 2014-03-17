package jdapper.stream;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class PeekingStream<T> extends ComposedStream<T> {

   private Consumer<? super T> peeker;
   
   public PeekingStream(Stream<T> base, Consumer<? super T> peeker) {
      super(base);
      this.peeker = peeker;
   }

   @Override
   protected Stream<T> applyOptions() {
      return base.peek(peeker);
   }
}
