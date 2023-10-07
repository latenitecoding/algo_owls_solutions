package main

import (
	"flag"
	"fmt"
	"os"
	"github.com/latenitecoding/algo_owls/src/solutions"
)


func main() {
	var helpFlag = flag.Bool("help", false, "Print help")
	var hFlag = flag.Bool("h", false, "Print help")
	var versionFlag = flag.Bool("version", false, "Print version")
	var vFlag = flag.Bool("V", false, "Print version")
	var solutionFlag = flag.String("solution", "hello", "Executes target solution")
	var sFlag = flag.String("s", "hello", "Executes target solution")

	flag.Parse()

	if *helpFlag || *hFlag {
		fmt.Println("Usage: algo_owls --solution <SOLUTION>")
		fmt.Println()
		fmt.Println("Options:")
		fmt.Printf("%-30s %s\n", "  -s, --solution <SOLUTION>", "Executes target <SOLUTION>")
		fmt.Printf("%-30s %s\n", "  -h, --help", "Print help")
		fmt.Printf("%-30s %s\n", "  -V, --version", "Print version")
		os.Exit(0)
	}

	if *versionFlag || *vFlag {
		fmt.Println("algo_owls 0.1.0")
		os.Exit(0)
	}

	if *solutionFlag != "hello" {
		solutions.CallSolution(*solutionFlag)
	} else {
		solutions.CallSolution(*sFlag)
	}
}

