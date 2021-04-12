# Simulator
This is a single-class **Java 1.8** command line program that rapidly runs simulations based on the arguments provided in the command line.

## Program Usage
This program can be ran in the command prompt or terminal with three or four arguments.

### Run Single Set of Trials
With three arguments (`<numerator> <denominator> <trials>`), the program will run a set of `<trials>` trials with each trial having a `<numerator>` in `<denominator>` chance of succeeding and output the result.

For example, running `java -jar Simulator.jar 1 2 100` will run 100 trials each with a 50/50 chance of success.

### Run Sets of Trials Until Threshold Reached
With four arguments (`<numerator> <denominator> <trials> <goal>`), the program will continue to run sets of trials with each trial having the specified chance until there are at least `<goal>` successes in a given set;  then, it outputs how many sets of trials it took.

For example, running `java -jar Simulator.jar 1 2 100 75` will have the program continue to run sets of 100 trials with each trial being a 50/50 chance of success until one trial has at least 75 successes.

This can take time with larger numbers, so the program outputs how many sets it has already tried every 2.5 seconds.

## Program Walkthrough and Documentation
After the program starts running, it will have received a number of arguments.  The program starts by seeing that the number of arguments is valid.  Afterwards, it attempts to parse the arguments to integers, outputting an error message if it fails.  Finally, it runs the appropriate task based on the number of arguments provided.

### runTrial()
```java
private static void runTrial(int numerator, int denominator, int trials) {
    int successes = 0;
    for(int trial = 0; trial < trials; trial++) {
        if(Simulator.r.nextInt(denominator) < numerator) {
            successes += 1;
        }
    }
    System.out.println("There were " + successes + " successes out of " + trials + " trials observed.");
}
```
This task starts by initialising a counter for the number of successes.  Then, it runs the specified number of trials, counting the successful ones.  A trial is tested by taking a random number from 0 to the number one less than the denominator and comparing it against the numerator.  The trial is successful if the random number is less than **(not equal to)** the numerator.  For example, a trial with a 1/2 chance of success would take a random number from 0-1 and see if it's less than 1, so a 0 is a success, and a 1 is a fail.

### countSets()
```java
private static void countSets(int numerator, int denominator, int trials, int goal) {
    int successes;
    long sets = 0;

    long lastTime = System.currentTimeMillis();
    long sinceLastTime;

    do {
        successes = 0;
        sets++;
        for(int trial = 0; trial < trials; trial++) {
            if(Simulator.r.nextInt(denominator) < numerator) {
                successes += 1;
            }
            
            sinceLastTime = System.currentTimeMillis() - lastTime;
            if(sinceLastTime >= 2500L) {
                System.out.println("Sets: " + sets);
                lastTime = System.currentTimeMillis();
            }
        }
    } while(successes < goal);
        
    System.out.println("It took " + sets + " sets of trials to get " + goal + " successes in " + trials + " trials.");
}
```
This task starts by initialising a success counter and a set counter.  It then initialises a timer to output regular updates.  Inside a do-while loop, the task resets the success counter and increments the set counter by 1 (including the first time -- it does not make sense to succeed in 0 sets).  It runs the set of trials, and after each trial, it checks to see if 2.5 seconds (2500 milliseconds) have passed and prints an update if it is time.  The do-while loop exits when the goal number of successes has been reached.
