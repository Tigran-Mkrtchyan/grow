import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { TUser } from 'data/types/User';
import { UserApi } from '../apiSlice/user.slices';

type TAuthSliceState = {
  user: TUser | null;
};

const initialState: TAuthSliceState = {
  user: localStorage.getItem('user')
    ? JSON.parse(localStorage.getItem('user') as string)
    : null,
};

const authSlice = createSlice({
  name: 'authSlice',
  initialState,
  reducers: {
    setUser(state, action: PayloadAction<TUser>) {
      state.user = action.payload;
      localStorage.setItem('user', JSON.stringify(action.payload));
    },
    updateUser(
      state,
      action: PayloadAction<{ photoUrl: string; fullName: string }>,
    ) {
      if (!state.user) {
        return;
      }
      const newUser = { ...state.user, ...action.payload };
      state.user = newUser;
      localStorage.setItem('user', JSON.stringify(newUser));
    },
    removeUser(state) {
      state.user = null;
      localStorage.removeItem('user');
    },
  },
  extraReducers: builder => {
    builder
      .addMatcher(
        UserApi.endpoints.signInUser.matchFulfilled,
        (state, { payload }) => {
          state.user = payload;
          localStorage.setItem('user', JSON.stringify(payload));
        },
      )
      .addMatcher(
        UserApi.endpoints.updateProfile.matchFulfilled,
        (state, { payload }) => {
          state.user = payload;
          localStorage.setItem('user', JSON.stringify(payload));
        },
      );
  },
});

export default authSlice;
