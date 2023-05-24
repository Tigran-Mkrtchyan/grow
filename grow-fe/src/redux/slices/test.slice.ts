import { createSlice, PayloadAction } from '@reduxjs/toolkit';

type TAuthSliceState = {
  test: unknown;
};

const initialState: TAuthSliceState = {
  test: null,
};

const testSlice = createSlice({
  name: 'testSlice',
  initialState,
  reducers: {
    setTest(state, action: PayloadAction<unknown>) {
      state.test = action.payload;
    },
  },
});

export default testSlice;
