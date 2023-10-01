# Java Solutions

Solutions should be organized with their own respective directories.

Each individual solution can be included into the project by editing the `Solutions.java` file located at `src/solutions/Solutions.java`

e.g., to add a solution to the ICPC problem **Fading Wind** located at
`src/solutions/icpc/naReg22/FadingWind.java`
```java
public static void callSolution(String solution) {
    switch (solution) {
        case "FadingWind":
            FadingWind.main(String::new[]);
            break;
        default:
            System.out.printf("No solution for %s\n", solution);
    }
}
```
