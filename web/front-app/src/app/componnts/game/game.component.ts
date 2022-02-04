import { Component, OnInit } from '@angular/core';
import { AlphabetaStrategy } from 'src/app/services/logic/alphabeta-strategy';
import { AiAlgorithmService } from '../../services/ai-algorithm.service';
import { MinmaxStrategy } from '../../services/logic/minmax-strategy';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit {

  board:any = [
    [null, null, null],
    [null, null, null],
    [null, null, null],
  ];

  firstTurn: boolean = true;
  gameStarted: boolean = false;
  winner: boolean = false;
  draw: boolean = false;
  winnerStr!: string;
  algorithmStr:string = "AlphaBeta";

  private aiAlgorithm: AiAlgorithmService;
  constructor() {
    // if (this.algorithmStr === "MinMax") {}
    // this.aiAlgorithm = new AiAlgorithmService(new AlphabetaStrategy());
    this.aiAlgorithm = new AiAlgorithmService(new MinmaxStrategy());
  }

  ngOnInit(): void {}

  moveAi() {
    let nextPosition:any = this.aiAlgorithm.evaluation(this.board);
    let x = nextPosition.i ?? null;
    let y = nextPosition.j ?? null;
    if (typeof x === 'number' && typeof y === 'number') this.move(x,y);
  }

  move(x:number,y:number):void {
    if (!this.winner && !this.board[x][y]) {
      if (this.firstTurn) this.board[x][y] = 'x';
      else this.board[x][y] = 'o';
      this.firstTurn = !this.firstTurn;
      this.checkGame();
    }
  }

  checkGame() {
    this.gameStarted = true;
    let symbol = this.aiAlgorithm.checkWinner(this.board);
    if (symbol) this.declareWinner(symbol);
    if (!this.firstTurn) this.moveAi();
  }

  declareWinner(symbol: string) {
    if (symbol != 'draw') {
      this.winner = true;
      if (symbol === "x") {
        this.winnerStr = "あなたの勝ちです";
      } else {
        this.winnerStr = "AIの勝ちです";
      }
    } else {
      this.winnerStr = '引き分け';
      this.draw = true;
    }
  }

  resetGame() {
    this.gameStarted = false;
    this.winner = false;
    this.draw = false;
    this.firstTurn = true;

    this.winnerStr = '';
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        this.board[i][j] = null;
      }
    }
  }
}
