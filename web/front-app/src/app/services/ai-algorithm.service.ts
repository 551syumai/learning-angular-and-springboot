import { Injectable,Inject } from '@angular/core';
import { IStrategy,AbstractAlgorithm } from './logic/logic-strategy';

export interface IAiAlgorithm {
  evaluation(board: any): any;
  checkWinner(board: any): any;
}

@Injectable({
  providedIn: 'root',
})
export class AiAlgorithmService implements IAiAlgorithm  {
  private strategy: IStrategy;
  constructor(@Inject(AbstractAlgorithm)strategy:IStrategy) {
    this.strategy = strategy;
  }

  evaluation(board: any) {
    let bestScore = -1000;
    let bestPosition;

    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (board[i][j] === null) {
          let score = bestScore;
          score = this.strategy.doAlgorithm(board);
          board[i][j] = null;
          if (score > bestScore) {
            bestScore = score;
            bestPosition = { i, j };
          }
        }
      }
    }
    return bestPosition;
  }

  checkWinner(board: any) {
    return this.strategy.checkWinner(board);
  }
}
