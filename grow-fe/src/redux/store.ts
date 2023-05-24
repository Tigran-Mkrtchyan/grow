import {
  configureStore,
  combineReducers,
  CombinedState,
  AnyAction,
} from '@reduxjs/toolkit';
import {
  shallowEqual,
  TypedUseSelectorHook,
  useDispatch,
  useSelector,
} from 'react-redux';
import { setupListeners } from '@reduxjs/toolkit/dist/query';
import authSlice from './slices/auth.slice';
import appSlice from './slices/app.slice';
import testSlice from './slices/test.slice';
import { UserApi } from './apiSlice/user.slices';
import { SomeApi } from './apiSlice/some.slices';
import { AuthAmplify } from './apiSlice/authAmplify.slice';
import { AuthAmplifyWithPhoneNumber } from './apiSlice/authAmplifyWithPhoneNumber.slice';
import { AuthAmplifyWithEmail } from './apiSlice/authAmplifyWithEmail.slice';

type TState =
  | CombinedState<{
      [x: string]: unknown;
    }>
  | undefined;

const appReducer = combineReducers({
  [SomeApi.reducerPath]: SomeApi.reducer,
  testSlice: testSlice.reducer,
  authSlice: authSlice.reducer,
  appSlice: appSlice.reducer,
  [AuthAmplify.reducerPath]: AuthAmplify.reducer,
  [AuthAmplifyWithPhoneNumber.reducerPath]: AuthAmplifyWithPhoneNumber.reducer,
  [AuthAmplifyWithEmail.reducerPath]: AuthAmplifyWithEmail.reducer,
});

const rootReducer = (state: TState, action: AnyAction) => {
  if (action.type === 'USER_LOGOUT') {
    return appReducer(undefined, action);
  }

  // @ts-ignore
  return appReducer(state, action);
};

export const store = configureStore({
  reducer: rootReducer,
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware().concat(
      UserApi.middleware,
      AuthAmplify.middleware,
      AuthAmplifyWithPhoneNumber.middleware,
      AuthAmplifyWithEmail.middleware,
    ),
});

export const clearAllCachedData = () => {
  store.dispatch(AuthAmplify.util.resetApiState());
  store.dispatch(AuthAmplifyWithPhoneNumber.util.resetApiState());
  store.dispatch(AuthAmplifyWithEmail.util.resetApiState());
};

export type RootState = ReturnType<typeof rootReducer>;
type AppDispatch = typeof store.dispatch;

export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<RootState> = cb =>
  useSelector(cb, shallowEqual);

setupListeners(store.dispatch);
