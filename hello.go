package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func Hello() {
	main()
}

//========================================================
// StdIn Helpers
//========================================================

func getScanner() *bufio.Scanner {
	return bufio.NewScanner(os.Stdin)
}

func next(scanner *bufio.Scanner) int {
	return parse(nextLine(scanner))
}

func nextLine(scanner *bufio.Scanner) string {
	scanner.Scan()
	return scanner.Text()
}

func nextTuple(scanner *bufio.Scanner) []int {
	var vec []int
	for _, s := range strings.Fields(nextLine(scanner)) {
		vec = append(vec, parse(s))
	}
	return vec
}

func parse(s string) int {
	i, err := strconv.ParseInt(s, 10, 32)
	if err != nil {
		panic(err)
	}
	return int(i)
}

//========================================================
// Solution
//========================================================

func main() {
	scanner := getScanner()
	line := nextLine(scanner)
	fmt.Println(line)
}
