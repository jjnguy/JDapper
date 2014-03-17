package jdapper.stream;

import java.util.stream.Stream;

public class ClosingStream<T> extends ComposedStream<T> {

   private Runnable closeHandler;
   
   public ClosingStream(Stream<T> base, Runnable closeHandler) {
      super(base);
      this.closeHandler = closeHandler;
   }

   @Override
   protected Stream<T> applyOptions() {
      return base.onClose(closeHandler);
   }
}
