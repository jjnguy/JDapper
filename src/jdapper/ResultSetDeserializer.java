package jdapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Iterator;

public class ResultSetDeserializer<T> implements Iterable<T> {

   private ResultSet data;
   private Class<T> type;

   private Field[] fields;

   public ResultSetDeserializer(ResultSet data, Class<T> type) {
      this.data = data;
      this.type = type;

      fields = type.getDeclaredFields();
      for (Field field : fields) {
         field.setAccessible(true);
      }
   }

   private T deserializeCurrentRow() {
      try {
         if (!data.next())
            return null;

         T obj = type.getConstructor().newInstance();

         for (Field field : fields) {
            Object col = data.getObject(field.getName());
            field.set(obj, col);
         }

         return obj;
      } catch (Exception e) {
         throw new JDapperException(e);
      } 
   }

   @Override
   public Iterator<T> iterator() {
      return new Iterator<T>() {

         private T next;
         private boolean nextWasCalled;

         @Override
         public boolean hasNext() {
            if (!nextWasCalled)
               return next != null;
            nextWasCalled = false;
            return (next = deserializeCurrentRow()) != null;
         }

         @Override
         public T next() {
            nextWasCalled = true;
            if (hasNext())
               return next;
            return null;
         }
      };
   }

}
