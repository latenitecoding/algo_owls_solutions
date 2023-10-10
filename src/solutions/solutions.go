package solutions

import (
	"fmt"
	"github.com/latenitecoding/algo_owls/src/solutions/usaco/dec2022"
)

func CallSolution(solution string) {
	switch solution {
	case "cow_college":
		dec2022.CowCollege()
	default:
		fmt.Printf("No solution for %s\n", solution)
	}
}
