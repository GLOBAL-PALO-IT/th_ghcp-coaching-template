import { ADD_ITEM, REMOVE_ITEM, UPDATE_ITEM, Item } from './types';

export const addItem = (item: Item) => ({
  type: ADD_ITEM,
  payload: item,
});

export const removeItem = (id: number) => ({
  type: REMOVE_ITEM,
  payload: id,
});

export const updateItem = (item: Item) => ({
  type: UPDATE_ITEM,
  payload: item,
});
