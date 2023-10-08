# Algo Owls

The source code for `algo_owls` is defined in `main.go`. The CLI for `algo_owls` is defined as follows:

_Usage: algo_owls [[-s --solution] solution | [-h --help] | [-V --version]]_

Each solution should be included within its own directory inside of the solutions directory. See `src/solutions/README.md` for more instructions on how solutions can be included within the project.

To meet the requirements of this project, it is necessary that solution files be included into the project with little to no dependency on the project structure. This is currently not possible in Go. To permit relative imports, the current project must be formatted as a module, which requires that all files be included within packages named after their parent directory and that all packages be imported using the absolute path of the GitHub project URI to the package. This imposes the least burden possible on competitive programmers as they only need to change the package name in order to submit their solutions online.
