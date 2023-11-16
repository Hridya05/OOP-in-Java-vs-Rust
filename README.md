---
title: "Title: Comparison of Tic-Tac-Toe Game in Java and Rust"
---

In this presentation, we will compare the implementation of a simple
Tic-Tac-Toe game in two different programming languages: Java and Rust.
While the game\'s logic and functionality remain the same, the two
languages exhibit differences in syntax, structure, language constructs
and programming paradigms.

# Java:

Java is a widely-used, object-oriented, and statically-typed language.
It uses class-based inheritance and provides high-level abstractions.

# Rust:

Rust is a systems programming language known for safety, performance,
and memory control. It promotes composition and trait-based polymorphism
instead of class-based inheritance.

+----------------------------------+-----------------------------------+
| JAVA                             | RUST                              |
+==================================+===================================+
| Java uses classes with           | Rust uses structs for             |
| inheritance.                     | encapsulation.                    |
|                                  |                                   |
| ![](vertopal_5687e2fb976b4ad     | ![](vertopal_5687e2fb976b4        |
| 5bded0bd22a52206c/media/image1.p | ad5bded0bd22a52206c/media/image2. |
| ng){width="2.5085509623797027in" | png){width="2.1668547681539807in" |
| height="0.8500732720909886in"}   | height="0.6583902012248469in"}    |
+----------------------------------+-----------------------------------+
| Both languages use arrays to     |                                   |
| represent the game board.        |                                   |
+----------------------------------+-----------------------------------+
| In Java, methods are declared in | In Rust, functions are defined    |
| classes.                         | within impl blocks for structs.   |
|                                  |                                   |
| ![](vertopal_5687e2fb976b4       | ![](vertopal_5687e2fb976b         |
| ad5bded0bd22a52206c/media/image3 | 4ad5bded0bd22a52206c/media/image4 |
| .png){width="2.90751312335958in" | .png){width="3.187108486439195in" |
| height="2.0474464129483816in"}   | height="1.5579429133858267in"}    |
+----------------------------------+-----------------------------------+
| Java uses classes and methods    | Rust uses structs and associated  |
|                                  | functions.                        |
|                                  |                                   |
|                                  | The use of mutable references is  |
|                                  | a Rust-specific feature for       |
|                                  | modifying the struct\'s internal  |
|                                  | state.                            |
+----------------------------------+-----------------------------------+
| Java uses the Scanner class to   | Rust uses the standard io library |
| handle input.                    | for input and explicit parsing.   |
+----------------------------------+-----------------------------------+
| Java uses parentheses for        | Rust uses a more C-like syntax    |
| conditionals.                    | for control flow.                 |
|                                  |                                   |
| ![](vertopal_5687e2fb976b4a      | ![](vertopal_5687e2fb976b         |
| d5bded0bd22a52206c/media/image5. | 4ad5bded0bd22a52206c/media/image6 |
| png){width="2.350203412073491in" | .png){width="2.725236220472441in" |
| height="0.29169181977252845in"}  | height="0.2500218722659667in"}    |
+----------------------------------+-----------------------------------+
| The Java version uses            | The Rust version avoids           |
| class-based inheritance to       | class-based inheritance, relying  |
| create a base Board class and a  | on composition and traits for     |
| derived TicTacToeGame class.     | shared behavior.                  |
|                                  |                                   |
| ![](vertopal_5687e2fb976b4ad     | Rust\'s idiomatic approach        |
| 5bded0bd22a52206c/media/image7.p | promotes composition and trait    |
| ng){width="2.9585892388451445in" | implementation                    |
| height="0.6667246281714786in"}   |                                   |
|                                  | ![](vertopal_5687e2fb976b4        |
|                                  | ad5bded0bd22a52206c/media/image8. |
|                                  | png){width="2.7335706474190724in" |
|                                  | height="0.4583727034120735in"}    |
+----------------------------------+-----------------------------------+
