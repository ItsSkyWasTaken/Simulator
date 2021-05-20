package net.sky.simulator;

import java.util.Random;
import java.util.Scanner;

public class Simulator {
    private static final Random r = new Random();
    private static final Scanner sc = new Scanner(System.in);

    private static void runTrial(int numerator, int denominator, int trials) {
        int successes = 0;
        for(int trial = 0; trial < trials; trial++) {
            if(Simulator.r.nextInt(denominator) < numerator) {
                successes += 1;
            }
        }
        System.out.println("There were " + successes + " successes out of " + trials + " trials observed.");
        System.exit(0);
    }

    private static void countSets(int numerator, int denominator, int trials, int goal) {
        int successes;
        long sets = 0;

        new Thread(() -> {
            System.out.println("Started the trials!  Type \"stop\" to end the process early.");
            while(!Simulator.sc.nextLine().equalsIgnoreCase("stop")) {
                // Do nothing.  Keep taking responses until there is a match.
            }
            System.exit(0);
        }).start();

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
        System.exit(0);
    }

    public static void main(String[] args) {
        if(args.length != 3 && args.length != 4) {
            System.err.println("Arguments: <int: numerator> <int: denominator> <int: trials>\n        or <int: numerator> <int: denominator> <int: trials> <int: goal>");
            System.exit(1);
        }

        int numerator = 0;
        int denominator = 0;
        int trials = 0;

        try {
            numerator = Integer.parseInt(args[0]);
            denominator = Integer.parseInt(args[1]);
            trials = Integer.parseInt(args[2]);
        } catch(NumberFormatException e) {
            System.err.println("One or more of your arguments are not valid integers.  Please try again.");
            System.exit(1);
        }

        int goal = 0;

        if(args.length == 4) {
            try {
                goal = Integer.parseInt(args[3]);
            } catch(NumberFormatException e) {
                System.err.println("One or more of your arguments are not valid integers.  Please try again.");
                System.exit(1);
            }
        }

        if(args.length == 3) {
            Simulator.runTrial(numerator, denominator, trials);
        } else {
            Simulator.countSets(numerator, denominator, trials, goal);
        }
    }
}
