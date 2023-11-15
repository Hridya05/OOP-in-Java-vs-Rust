trait Board {
    fn new() -> Self;
    fn print_board(&self);
    fn is_board_full(&self) -> bool;
    fn is_game_over(&self) -> bool;
    fn has_won(&self, player: char) -> bool;
    fn make_move(&mut self, row: usize, col: usize, player: char);
    fn is_valid_move(&self, row: usize, col: usize) -> bool;
}

struct TicTacToeBoard {
    board: [[char; 3]; 3],
}

impl Board for TicTacToeBoard {
    fn new() -> Self {
        TicTacToeBoard { board: [[' '; 3]; 3] }
    }

    fn print_board(&self) {
        println!("-------------");
        for row in &self.board {
            print!("| ");
            for &cell in row.iter() {
                print!("{} | ", cell);
            }
            println!("\n-------------");
        }
    }

    fn is_board_full(&self) -> bool {
        for row in &self.board {
            for &cell in row.iter() {
                if cell == ' ' {
                    return false;
                }
            }
        }
        true
    }

    fn is_game_over(&self) -> bool {
        self.has_won('X') || self.has_won('O') || self.is_board_full()
    }

    fn has_won(&self, player: char) -> bool {
        // Check rows, columns, and diagonals for a win
        for i in 0..3 {
            if (self.board[i][0] == player && self.board[i][1] == player && self.board[i][2] == player)
                || (self.board[0][i] == player && self.board[1][i] == player && self.board[2][i] == player)
            {
                return true; // Row or column win
            }
        }

        if (self.board[0][0] == player && self.board[1][1] == player && self.board[2][2] == player)
            || (self.board[0][2] == player && self.board[1][1] == player && self.board[2][0] == player)
        {
            return true; // Diagonal win
        }

        false
    }

    fn make_move(&mut self, row: usize, col: usize, player: char) {
        if self.is_valid_move(row, col) {
            self.board[row][col] = player;
        }
    }

    fn is_valid_move(&self, row: usize, col: usize) -> bool {
        row < 3 && col < 3 && self.board[row][col] == ' '
    }
}

fn main() {
    let mut board = TicTacToeBoard::new();
    let mut current_player = 'X';

    while !board.is_game_over() {
        board.print_board();
        println!("Player {}, enter row and column (e.g., 1 2):", current_player);

        let mut input = String::new();
        std::io::stdin()
            .read_line(&mut input)
            .expect("Failed to read line");

        let input: Vec<usize> = input
            .split_whitespace()
            .filter_map(|s| s.parse().ok())
            .collect();

        if input.len() == 2 {
            let (row, col) = (input[0] - 1, input[1] - 1);

            if board.is_valid_move(row, col) {
                board.make_move(row, col, current_player);

                if board.has_won(current_player) {
                    board.print_board();
                    println!("Player {} wins!", current_player);
                    break;
                } else if board.is_board_full() {
                    board.print_board();
                    println!("It's a draw!");
                    break;
                }

                current_player = if current_player == 'X' { 'O' } else { 'X' };
            } else {
                println!("Invalid move. Try again.");
            }
        } else {
            println!("Invalid input. Enter both row and column (e.g., 1 2).");
        }
    }
}
