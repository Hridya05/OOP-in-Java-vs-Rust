# Comparing aspects of Object oriented programming in Java and Rust
Problem Statement - Comparative Analysis of Object-Oriented Programming Paradigms in Java and Rust while exploring workarounds for missing OOP pillars in rust. This project aims to conduct a comprehensive comparative analysis of the object-oriented programming aspects in Java and Rust to provide developers and decision-makers with insights into the strengths and weaknesses of each language in the context of OOP.
This project aims at drawing a parallel between Java and Rust and discovering the capabilities Rust for Object oriented programming.

A parallel has been drawn between java and rust through a program for a collection of games. The codebase is provided in both java and rust.


Project submitted in partial fulfillment of the course CS F301 by group number -- constituting of the following members:
Hridya Arora (2021A7PS2538G)
Pathik Shah (2021A7PS2085G)
Krishna Sethiya (2021A7PS1440G)

# Comparison of Tic-Tac-Toe Game in Java and Rust"


We will compare the implementation of a simple
Tic-Tac-Toe game in two different programming languages: Java and Rust.
While the game\'s logic and functionality remain the same, the two
languages exhibit differences in syntax, structure, language constructs
and programming paradigms.

# Java:

Java is a widely-used, object-oriented, and statically-typed language.
It uses class-based inheritance and provides high-level abstractions.

To compile the given code:

``` javac Main.java ./*.java ```
```    java Main.class
```

# Rust:

Rust is a systems programming language known for safety, performance,
and memory control. It promotes composition and trait-based polymorphism
instead of class-based inheritance.


|**JAVA**|**RUST**|
| :-: | :-: |
|<p>Java uses classes with inheritance. </p><p></p>|<p>Rust uses structs for encapsulation.</p><p></p><p></p>|
|Both languages use arrays to represent the game board.||
|<p>**In Java, methods are declared in classes.**</p><p></p><p></p><p></p><p></p><p></p>|<p>In Rust, functions are defined within impl blocks for structs.</p><p></p><p></p><p></p>|
|Java uses classes and methods|<p>Rust uses structs and associated functions.</p><p></p><p>The use of mutable references is a Rust-specific feature for modifying the struct's internal state.</p>|
|<p>**Java uses the Scanner class to handle input.**</p><p></p>|<p>Rust uses the standard io library for input and explicit parsing.</p><p></p>|
|<p>**Java uses parentheses for conditionals.**</p><p></p><p></p>|<p>Rust uses a more C-like syntax for control flow.</p><p></p>|
|<p>**The Java version uses class-based inheritance to create a base Board class and a derived TicTacToeGame class.**</p><p></p><p></p><p></p><p></p>|<p>The Rust version avoids class-based inheritance, relying on composition and traits for shared behavior.</p><p>Rust's idiomatic approach promotes composition and trait implementation</p><p></p><p></p>|


