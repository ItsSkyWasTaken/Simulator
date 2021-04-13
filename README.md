# Simulator
This is a single-class **Java 1.8** command line program that rapidly runs simulations based on the arguments provided in the command line.

This program can be ran in the command prompt or terminal with three or four arguments.

## Run Single Set of Trials
With three arguments (`<numerator> <denominator> <trials>`), the program will run a set of `<trials>` trials with each trial having a `<numerator>` in `<denominator>` chance of succeeding and output the result.

For example, running `java -jar Simulator.jar 1 2 100` will run 100 trials each with a 50/50 chance of success.

## Run Sets of Trials Until Threshold Reached
With four arguments (`<numerator> <denominator> <trials> <goal>`), the program will continue to run sets of trials with each trial having the specified chance until there are at least `<goal>` successes in a given set;  then, it outputs how many sets of trials it took.

For example, running `java -jar Simulator.jar 1 2 100 75` will have the program continue to run sets of 100 trials with each trial being a 50/50 chance of success until one trial has at least 75 successes.

This can take time with larger numbers, so the program outputs how many sets it has already tried every 2.5 seconds.
