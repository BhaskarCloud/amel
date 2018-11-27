package FrameworkLibrary;
import java.lang.*;

public class ThreadDemoLLL {

   public static void main(String[] args) {
      function();
   }

   public static void function() {
      new ThreadDemoLLL().function2();
   }

   public void function2() {
      System.out.println(Thread.currentThread().getStackTrace()[3].getClassName());
   }
}