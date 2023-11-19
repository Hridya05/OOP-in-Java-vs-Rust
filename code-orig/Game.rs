use std::io;
use rand::seq::SliceRandom;
use rand::Rng;
 
trait Game {
    fn initialize(&mut self);
    fn print_result(&self, player: char);
    fn play(&mut self);
    fn has_won(&self, player: char) -> bool;
}
 
trait Board: Game {
    fn is_board_full(&self) -> bool;
    fn is_valid_move(&self, row: usize, col: usize) -> bool;
    fn make_move(&mut self, row: usize, col: usize, player: char);
    fn print_board(&self);
}
 
trait Card {
    fn initialize_deck(&mut self);
    fn shuffle_deck(&mut self);
    fn draw_card(&mut self) -> Option<(char, i32)>;
}
 
struct HighLow {
    deck: Vec<(char, i32)>,
    current_card: i32,
    score: i32,
}
 
impl Game for HighLow {
    fn has_won(&self, player: char) -> bool{false}
 
    fn initialize(&mut self) {
        self.deck = Vec::new();
        self.score = 0;
        self.initialize_deck();
    }
 
    fn print_result(&self, player: char) {
        println!("Congratulations! You scored: {} points.", self.score);
    }
 
    fn play(&mut self) {
        self.shuffle_deck();
        let mut rng = rand::thread_rng();
        self.current_card = match self.draw_card() {
            Some(card) => card.1,
            None => {
                println!("No cards left. Game over!");
                self.print_result('-');
                return;
            }
        };
 
        let stdin = io::stdin();
 
        while let Some(card) = self.draw_card() {
            println!("Current card is {}. Will the next card be higher (h) or lower (l)?", self.current_card);
            let mut guess = String::new();
            stdin.read_line(&mut guess).expect("Failed to read line");
            let guess = guess.trim();
 
            if guess != "h" && guess != "l" {
                println!("Invalid input. Please enter 'h' or 'l'.");
                continue;
            }
 
            let next_card = card.1;
            println!("Next card is {}", next_card);
 
            if (guess == "h" && next_card > self.current_card) || (guess == "l" && next_card < self.current_card) {
                println!("Correct guess!");
                self.score += 1;
            } else {
                println!("Wrong guess. Game over.");
                self.print_result('-');
                break;
            }
 
            self.current_card = next_card;
        }
 
        if self.deck.is_empty() {
            println!("No more cards left. Game over!");
            self.print_result('-');
        }
    }
}
 
impl Card for HighLow {
    fn initialize_deck(&mut self) {
        for suit in &['H'] {
            for rank in 1..=13 {
                self.deck.push((*suit, rank));
            }
        }
    }
 
    fn shuffle_deck(&mut self) {
        let mut rng = rand::thread_rng();
        self.deck.shuffle(&mut rng);
    }
 
    fn draw_card(&mut self) -> Option<(char, i32)> {
        match self.deck.pop() {
            Some(card) => Some(card),
            None => None,
        }
    }
}
 
struct ConnectFour {
    board: Vec<Vec<char>>,
    rows: usize,
    cols: usize,
    next_row: Vec<usize>,
    current_player: char,
}
 
impl Game for ConnectFour {
    fn initialize(&mut self) {
        self.board = vec![vec![' '; 7]; 6];
        self.current_player = 'R';
        self.next_row = vec![5; 7];
    }
 
    fn print_result(&self, player: char) {
        println!("Player {} wins!", player);
    }
 
    fn play(&mut self) {
        let mut input = String::new();
        let stdin = io::stdin();
 
        while !self.is_board_full() {
            println!("Player {}, enter column (1-7): ", self.current_player);
            input.clear();
            stdin.read_line(&mut input).expect("Failed to read line");
            let col: usize = input.trim().parse::<usize>().expect("Invalid input") - 1;
            let row = self.next_row[col];
 
            if self.is_valid_move(row, col) {
                self.make_move(row, col, self.current_player);
                self.print_board();
                self.next_row[col] -= 1;
 
                if self.has_won(self.current_player) {
                    self.print_result(self.current_player);
                    break;
                } else if self.is_board_full() {
                    println!("It's a draw!");
                    break;
                }
 
                self.current_player = if self.current_player == 'R' { 'B' } else { 'R' };
            } else {
                println!("Invalid move. Try again.");
            }
        }
    }
    
    fn has_won(&self, player: char) -> bool {
        // Check for horizontal win
        for row in 0..self.rows {
            for col in 0..=self.cols - 4 {
                if self.board[row][col] == player
                    && self.board[row][col + 1] == player
                    && self.board[row][col + 2] == player
                    && self.board[row][col + 3] == player
                {
                    return true;
                }
            }
        }
 
        // Check for vertical win
        for row in 0..=self.rows - 4 {
            for col in 0..self.cols {
                if self.board[row][col] == player
                    && self.board[row + 1][col] == player
                    && self.board[row + 2][col] == player
                    && self.board[row + 3][col] == player
                {
                    return true;
                }
            }
        }
 
        // Check for diagonal win (from bottom-left to top-right)
        for row in 3..self.rows {
            for col in 0..=self.cols - 4 {
                if self.board[row][col] == player
                    && self.board[row - 1][col + 1] == player
                    && self.board[row - 2][col + 2] == player
                    && self.board[row - 3][col + 3] == player
                {
                    return true;
                }
            }
        }
 
        // Check for diagonal win (from top-left to bottom-right)
        for row in 0..=self.rows - 4 {
            for col in 0..=self.cols - 4 {
                if self.board[row][col] == player
                    && self.board[row + 1][col + 1] == player
                    && self.board[row + 2][col + 2] == player
                    && self.board[row + 3][col + 3] == player
                {
                    return true;
                }
            }
        }
 
        false
    }
}
 
impl Board for ConnectFour {
    fn is_board_full(&self) -> bool {
        self.next_row.iter().all(|&row| row == 0)
    }
 
    fn is_valid_move(&self, row: usize, col: usize) -> bool {
        row < 6 && col < 7 && self.board[row][col] == ' '
    }
 
    fn make_move(&mut self, row: usize, col: usize, player: char) {
        if self.is_valid_move(row, col) {
            self.board[row][col] = player;
        }
    }
    
    fn print_board(&self) {
        println!("-------------");
        for row in 0..self.rows {
            print!("| ");
            for col in 0..self.cols {
                print!("{} | ", self.board[row][col]);
            }
            println!("\n-------------");
        }
    }
}
 
struct TicTacToeGame {
    board: Vec<Vec<char>>,
    current_player: char,
}
 
impl Game for TicTacToeGame {
    fn initialize(&mut self) {
        self.board = vec![vec![' '; 3]; 3];
        self.current_player = 'X';
    }
 
    fn print_result(&self, player: char) {
        println!("Player {} wins!", player);
    }
 
    fn play(&mut self) {
        let mut input = String::new();
        let stdin = io::stdin();
 
        while !self.is_board_full() {
            println!("Player {}, enter row and column (e.g., 1 2): ", self.current_player);
            input.clear();
            stdin.read_line(&mut input).expect("Failed to read line");
            let mut parts = input.trim().split_whitespace();
            let mut row: usize = parts.next().expect("Invalid input").parse().expect("Invalid input");
            let mut col: usize = parts.next().expect("Invalid input").parse().expect("Invalid input");
            row-=1;
            col-=1;
            if self.is_valid_move(row, col) {
                self.make_move(row, col, self.current_player);
                self.print_board();
 
                if self.has_won(self.current_player) {
                    println!("Player {} wins!", self.current_player);
                    break;
                } else if self.is_board_full() {
                    println!("It's a draw!");
                    break;
                }
 
                self.current_player = if self.current_player == 'X' { 'O' } else { 'X' };
            } else {
                println!("Invalid move. Try again.");
            }
        }
    }
    
    fn has_won(&self, player: char) -> bool {
        // Check rows, columns, and diagonals for a win
        for i in 0..3 {
            if self.board[i][0] == player && self.board[i][1] == player && self.board[i][2] == player {
                return true; // Row win
            }
            if self.board[0][i] == player && self.board[1][i] == player && self.board[2][i] == player {
                return true; // Column win
            }
        }
 
        if self.board[0][0] == player && self.board[1][1] == player && self.board[2][2] == player {
            return true; // Diagonal win
        }
        if self.board[0][2] == player && self.board[1][1] == player && self.board[2][0] == player {
            return true; // Diagonal win
        }
 
        false
    }
}
 
impl Board for TicTacToeGame {
    fn is_board_full(&self) -> bool {
        self.board.iter().all(|row| row.iter().all(|&cell| cell != ' '))
    }
 
    fn is_valid_move(&self, row: usize, col: usize) -> bool {
        row < 3 && col < 3 && self.board[row][col] == ' '
    }
 
    fn make_move(&mut self, row: usize, col: usize, player: char) {
        if self.is_valid_move(row, col) {
            self.board[row][col] = player;
        }
    }
    
    fn print_board(&self) {
        println!("-------------");
        for row in &self.board {
            print!("| ");
            for cell in row {
                print!("{} | ", cell);
            }
            println!("\n-------------");
        }
    }
}
 
fn main() {
    // let mut high_low_game = HighLow {
    //     deck: vec![],
    //     current_card: 0,
    //     score: 0,
    // };
    // high_low_game.initialize();
    // high_low_game.play();
 
    // let mut connect_four_game = ConnectFour {
    //     board: vec![],rows:6,cols:7,
    //     next_row: vec![],
    //     current_player: 'R',
    // };
    // connect_four_game.initialize();
    // connect_four_game.play();
 
    let mut tic_tac_toe_game = TicTacToeGame {
        board: vec![],
        current_player: 'X',
    };
    tic_tac_toe_game.initialize();
    tic_tac_toe_game.play();
}
