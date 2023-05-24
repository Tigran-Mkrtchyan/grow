import { TAuthConverter } from '../types/Covertor.types';
import {
  TSignInAmplifyDataToDb,
  IAmplifySignUpUserData,
  TForgotPasswordAmplifyWithPhoneNumberProp,
  TForgotPasswordUserToDb,
  TForgotPasswordSubmitAmplifyDataUI,
  TForgotPasswordSubmitAmplifyToDb,
  TSignInWithPhoneNumberAmplifyDataUI,
  TAmplifyWithPhoneNumberUser,
  TAmplifySignInWithPhoneNumberUserData,
  TSignUpWithPhoneNumberAmplifyDataUI,
  TSignUpWithPhoneNumberAmplifyDataToDb,
  TSignUpWithPhoneNumberAmplifyDataUI2,
} from '../types/Amplify.types';

export const signUpWithPhoneNumberAmplifyConvertor: TAuthConverter<
  TSignUpWithPhoneNumberAmplifyDataUI,
  TSignUpWithPhoneNumberAmplifyDataToDb,
  TSignUpWithPhoneNumberAmplifyDataUI2,
  IAmplifySignUpUserData
> = {
  toDb: data => ({
    phoneNumber: `+${data.phoneNumber}`,
    password: data.password,
  }),
  fromDb: data => ({
    username: data?.user.username,
    password: data.password,
    userConfirmed: !!data?.userConfirmed,
    codeSend: true,
  }),
};

export const signInWithPhoneNumberAmplifyConvertor: TAuthConverter<
  TSignInWithPhoneNumberAmplifyDataUI,
  TSignInAmplifyDataToDb,
  TAmplifyWithPhoneNumberUser,
  TAmplifySignInWithPhoneNumberUserData
> = {
  fromDb: data => ({
    username: data?.username,
    name: data.attributes.name,
    phoneNumber: data.attributes.phone_number || '',
    phoneNumberVerified: data.attributes.phone_number_verified,
  }),
  toDb: data => ({
    username:
      data.phoneNumber[0] === '+' ? data.phoneNumber : `+${data.phoneNumber}`,
    password: data.password,
  }),
};

export const forgotPasswordWithPhoneNumberAmplifyConvertor: TAuthConverter<
  TForgotPasswordAmplifyWithPhoneNumberProp,
  TForgotPasswordUserToDb,
  void,
  void
> = {
  fromDb: () => {
    /* do nothing */
  },
  toDb: data => ({
    username: `+${data.phoneNumber}`,
  }),
};

export const forgotPasswordSubmitAmplifyConvertor: TAuthConverter<
  TForgotPasswordSubmitAmplifyDataUI,
  TForgotPasswordSubmitAmplifyToDb,
  void,
  void
> = {
  fromDb: () => {
    /* do nothing */
  },
  toDb: data => ({
    username: data.username,
    verificationCode: data.verificationCode,
    newPassword: data.newPassword,
  }),
};
