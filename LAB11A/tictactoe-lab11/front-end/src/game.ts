interface GameState {
  cells: Cell[];
  instructions: string;
  canUndo: boolean;
}

interface Cell {
  text: string;
  playable: boolean;
  x: number;
  y: number;
}

export type { GameState, Cell }