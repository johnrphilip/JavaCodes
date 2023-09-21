# Wumpus World Solver: Forward Chaining in Java

## Overview

This project provides an implementation of a simplified forward chaining algorithm to solve the Wumpus World problem. The application makes use of propositional logic to infer the possible locations of the Wumpus based on given clues (Stench and Safe).

## Prerequisites

- Java (JDK 8 or above)

## How to Run

1. Clone this repository.
2. Compile the Java files: `javac ForwardChaining.java`
3. Run the program: `java ForwardChaining`

## Code Structure

- `ForwardChaining` class: Main class responsible for the forward chaining logic.
  - `clauses`: ArrayList holding clauses, where each element is an integer array containing literals and their negation.
  - `forwardChaining(int n)`: Executes the forward chaining algorithm.
  - `addClause(int[] c)`: Adds a new clause.
  - `resetClauses()`: Clears all clauses.

- `example()`: Provides a simple example to illustrate forward chaining.
- `multiClueWumpus()`: Executes a Wumpus World example with multiple clues.

### Literals Representation

Symbols are represented as integers:
- Symbols ending in 6 are considered as safe symbols. E.g., (1,1) is represented as 116.
- For better memory usage and scaling, symbols are tightly packed into integers.

## Understanding the Output

- `Inferred XX with clause [YY]`: A new inference has been made.
- `Model exists: true/false`: Indicates if a model exists that satisfies all clauses simultaneously.
- `Variable X = true/false`: The truth value of each variable after running forward chaining.

## Limitations

- This is a simplified version of forward chaining and does not run in linear time.
- The model may only suggest "possible" locations for the Wumpus, especially when it's in corner positions.

## Contributing

Feel free to contribute by submitting pull requests or by reporting issues.

## License

This project is open-source, under the MIT License.

## Acknowledgements

Thanks to my classmates for fruitful discussions, especially Yassin for his helpful ideas on literals representation.

