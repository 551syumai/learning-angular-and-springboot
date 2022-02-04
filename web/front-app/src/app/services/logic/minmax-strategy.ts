import { Injectable, Inject } from '@angular/core';
import { AbstractAlgorithm } from '../logic/logic-strategy';

export class MinmaxStrategy extends AbstractAlgorithm {
    doAlgorithm(board: any, depth=0, minimizer=false) {
      let winner = this.checkScore[this.checkWinner(board)];
      if (winner) return winner;
  
      if (minimizer) {
        let bestScore = -1000;
        for (let i = 0; i < 3; i++) {
          for (let j = 0; j < 3; j++) {
            if (board[i][j] === null) {
              board[i][j] = 'o';
              let score = this.doAlgorithm(board, depth + 1, !minimizer);
              bestScore = Math.max(bestScore, score);
              board[i][j] = null;
            }
          }
        }
        return bestScore;
      } else {
        let bestScore = 1000;
        for (let i = 0; i < 3; i++) {
          for (let j = 0; j < 3; j++) {
            if (board[i][j] === null) {
              board[i][j] = 'x';
              let score = this.doAlgorithm(board, depth + 1, !minimizer);
              bestScore = Math.min(bestScore, score);
              board[i][j] = null;
            }
          }
        }
        return bestScore;
      }
    }
  }