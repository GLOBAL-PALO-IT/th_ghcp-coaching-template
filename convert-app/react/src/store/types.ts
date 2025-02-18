export const ADD_ITEM = 'ADD_ITEM';
export const REMOVE_ITEM = 'REMOVE_ITEM';
export const UPDATE_ITEM = 'UPDATE_ITEM';

export interface Item {
  id: number;
  name: string;
}

export interface AppState {
  items: Item[];
}
