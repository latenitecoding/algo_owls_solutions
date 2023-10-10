package dec2022

import (
	"bufio"
	"fmt"
	"os"
	"slices"
	"strconv"
	"strings"
)

func CowCollege() {
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
	i, err := strconv.ParseInt(s, 10, 64)
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
	N := next(scanner)
	c := nextTuple(scanner)

	slices.Sort(c)

	maxPay, bestTuition := 0, 0
	for i := 0; i < N; i++ {
		if pay := c[i] * (N - i); pay > maxPay {
			maxPay = pay;
			bestTuition = c[i]
		}
	}

	fmt.Printf("%d %d\n", maxPay, bestTuition)
}
