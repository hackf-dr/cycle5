Here's a series of Java programs that address each of the points you've mentioned. Each program demonstrates key concepts such as garbage collection, file handling, reading/writing, exception handling, and multithreading.

### 1. Demonstrate Garbage Collection:
Garbage collection in Java is handled automatically by the JVM, but we can forcefully call the `System.gc()` method to suggest garbage collection. This program demonstrates the concept:

```java
public class GarbageCollectionDemo {
    public void finalize() {
        System.out.println("Garbage collection is happening...");
    }

    public static void main(String[] args) {
        GarbageCollectionDemo gcDemo1 = new GarbageCollectionDemo();
        GarbageCollectionDemo gcDemo2 = new GarbageCollectionDemo();

        gcDemo1 = null;
        gcDemo2 = null;

        // Requesting JVM to call garbage collector
        System.gc();
        
        System.out.println("End of program.");
    }
}
```

### 2. Program to Read from a File and Write to Another File:
This program reads the contents of one file and writes them to another, handling file-related exceptions.

```java
import java.io.*;

public class FileReadWrite {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copy completed successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
```

### 3. Program Using Reader and Writer:
This program demonstrates the usage of `FileReader` and `FileWriter` classes to handle file input/output.

```java
import java.io.*;

public class ReaderWriterDemo {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("input.txt");
             FileWriter writer = new FileWriter("output.txt")) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
            System.out.println("File reading and writing done.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
```

### 4. Read a Line of Integers and Display Each Integer and Sum:
This program reads a line of integers, splits them, displays each integer, and sums them up.

```java
import java.util.Scanner;

public class ReadIntegersAndSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a line of integers separated by space:");
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");

        int sum = 0;
        System.out.println("Individual integers:");
        for (String num : numbers) {
            int number = Integer.parseInt(num);
            System.out.println(number);
            sum += number;
        }

        System.out.println("Sum of integers: " + sum);
    }
}
```

### 5. Program Using Try, Catch, and Finally:
This program demonstrates the use of `try`, `catch`, and `finally` blocks for exception handling.

```java
public class TryCatchFinallyDemo {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);  // This will throw an exception

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e.getMessage());

        } finally {
            System.out.println("Finally block always executes.");
        }
    }
}
```

### 6. Print Odd and Even Numbers Using Two Threads:
This program creates two threads, one for printing even numbers and one for printing odd numbers from 1 to 100.

```java
public class OddEvenThreads {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX = 100;

    public static void main(String[] args) {
        Thread evenThread = new Thread(() -> {
            while (number <= MAX) {
                synchronized (lock) {
                    if (number % 2 == 0) {
                        System.out.println("Even: " + number);
                        number++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread oddThread = new Thread(() -> {
            while (number <= MAX) {
                synchronized (lock) {
                    if (number % 2 != 0) {
                        System.out.println("Odd: " + number);
                        number++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        evenThread.start();
        oddThread.start();
    }
}
```

These programs cover key concepts such as file handling, exception handling, garbage collection, and multithreading, providing a good foundation from beginner to intermediate Java programming levels.
