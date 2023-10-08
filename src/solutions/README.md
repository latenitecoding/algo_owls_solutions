# Go Solutions

Solutions should be organized with their own respective directories.

Each individual solution can be included into the Go project by editing the `solutions.go` file located at `src/solutions/solutions.go`.

e.g., to add a solution to the ICPC problem **Fading Wind** located at `src/solutions/icpc/na_reg_22/fading_wind.go`:

```go
import (
	"fmt"
	"github.com/latenitecoding/algo_owls/src/solutions/icpc/na_reg_22"
)

func CallSolution(solution string) {
	switch solution {
	case "fading_wind":
		na_reg_22.FadingWind()
	default:
		fmt.Printf("No solution for %s\n", solution)
	}
}
```

Note: The solution to `fading_wind.go` must be a member of `package na_reg_22` and include the `FadingWind()` exported function. That function should be an alias for the `main()` function, which would allow the solution to be submitted online by replacing the package declaration with `package main` upon submission.
