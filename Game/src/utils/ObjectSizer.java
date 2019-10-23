package utils;

public class ObjectSizer {
	
	/**
	  * First and only argument is the package-qualified name of a class
	  * which has a no-argument constructor.
	  */
	  public static void main(String... args){
	    Class<?> theClass = null;
	    try {
	      theClass = Class.forName(args[0]);
	    }
	    catch (Exception ex) {
	      log("Cannot build a Class object: " + args[0]);
	      log("Use a package-qualified name, and check classpath.");
	    }
	    ObjectSizer sizer = new ObjectSizer();
	    long size = sizer.getObjectSize(theClass);
	    log("Approximate size of " + theClass + " objects :" + size);
	  }

	  /**
	  * Return the approximate size in bytes, and return zero if the class
	  * has no default constructor.
	  *
	  * @param aClass refers to a class which has a no-argument constructor.
	  */
	  public long getObjectSize(Class<?> aClass){
	    long result = 0;

	    //if the class does not have a no-argument constructor, then
	    //inform the user and return 0.
	    try {
	      aClass.getConstructor(new Class[]{});
	    }
	    catch (NoSuchMethodException ex) {
	      log(aClass + " does not have a no-argument constructor.");
	      return result;
	    }

	    //this array will simply hold a bunch of references, such that
	    //the objects cannot be garbage-collected
	    Object[] objects = new Object[SAMPLE_SIZE];

	    //build a bunch of identical objects
	    try {
	      Object throwAway = aClass.getDeclaredConstructor().newInstance();

	      long startMemoryUse = getMemoryUse();
	      for (int idx=0; idx < objects.length ; ++idx) {
	        objects[idx] = aClass.getDeclaredConstructor().newInstance();
	      }
	      long endMemoryUse = getMemoryUse();

	      float approximateSize = (endMemoryUse - startMemoryUse)/100f;
	      result = Math.round(approximateSize);
	    }
	    catch (Exception ex) {
	      log("Cannot create object using " + aClass);
	    }
	    return result;
	  }

	  // PRIVATE
	  private static int SAMPLE_SIZE = 100;
	  private static long SLEEP_INTERVAL = 100;

	  private static void log(String message){  
	    System.out.println(message);  
	  }
	  
	  private long getMemoryUse(){
	    putOutTheGarbage();
	    long totalMemory = Runtime.getRuntime().totalMemory();
	    putOutTheGarbage();
	    long freeMemory = Runtime.getRuntime().freeMemory();
	    return (totalMemory - freeMemory);
	  }

	  private void putOutTheGarbage() {
	    collectGarbage();
	    collectGarbage();
	  }

	  private void collectGarbage() {
	    try {
	      System.gc();
	      Thread.currentThread().sleep(SLEEP_INTERVAL);
	      System.runFinalization();
	      Thread.currentThread().sleep(SLEEP_INTERVAL);
	    }
	    catch (InterruptedException ex){
	      ex.printStackTrace();
	    }
	  }

}
