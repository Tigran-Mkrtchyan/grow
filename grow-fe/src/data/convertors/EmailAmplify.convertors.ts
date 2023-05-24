import { TAuthConverter } from '../types/Covertor.types';
import {
  TSignInAmplifyDataToDb,
  IAmplifySignUpUserData,
  TForgotPasswordAmplifyWithEmailProp,
  TForgotPasswordUserToDb,
  TSignInWithEmailAmplifyDataUI,
  TAmplifyWithEmailUser,
  TAmplifySignInWithEmailUserData,
  TSignUpWithEmailAmplifyDataUI,
  TSignUpWithEmailAmplifyDataToDb,
  TSignUpWithEmailAmplifyDataUI2,
} from '../types/Amplify.types';

export const signUpWithEmailAmplifyConvertor: TAuthConverter<
  TSignUpWithEmailAmplifyDataUI,
  TSignUpWithEmailAmplifyDataToDb,
  TSignUpWithEmailAmplifyDataUI2,
  IAmplifySignUpUserData
> = {
  toDb: data => ({ email: data.email.toLowerCase(), password: data.password }),
  fromDb: data => ({
    username: data?.user.username,
    password: data.password,
    userConfirmed: !!data?.userConfirmed,
    codeSend: true,
  }),
};

export const signInWithEmailAmplifyConvertor: TAuthConverter<
  TSignInWithEmailAmplifyDataUI,
  TSignInAmplifyDataToDb,
  TAmplifyWithEmailUser,
  TAmplifySignInWithEmailUserData
> = {
  fromDb: data => ({
    username: data?.username,
    name: data.attributes.name,
    email: data.attributes.email || '',
    emailVerified: data.attributes.email_verified,
  }),
  toDb: data => ({
    username: data.email,
    password: data.password,
  }),
};

export const forgotPasswordWithEmailAmplifyConvertor: TAuthConverter<
  TForgotPasswordAmplifyWithEmailProp,
  TForgotPasswordUserToDb,
  void,
  void
> = {
  fromDb: () => {
    /* do nothing */
  },
  toDb: data => ({
    username: data.email,
  }),
};
