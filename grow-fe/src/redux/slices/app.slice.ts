import { createSlice, PayloadAction } from '@reduxjs/toolkit';

type TAppSliceState = {
  isFilterOpen: boolean;
  hasSelectedFilterItem: boolean;
};

const initialState: TAppSliceState = {
  isFilterOpen: false,
  hasSelectedFilterItem: false,
};

const appSlice = createSlice({
  name: 'appSlice',
  initialState,
  reducers: {
    toggleFilterModal(state) {
      state.isFilterOpen = !state.isFilterOpen;
    },
    toggleHasSelectedFilterItem(
      state,
      action: PayloadAction<{ hasSelectedFilter: boolean }>,
    ) {
      state.hasSelectedFilterItem = action.payload.hasSelectedFilter;
    },
  },
});

export default appSlice;
