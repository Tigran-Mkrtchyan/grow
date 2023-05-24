/* eslint-disable */
import { CognitoUser } from '@aws-amplify/auth';
import { ISignUpResult } from 'amazon-cognito-identity-js';

export enum EVia {
  EMAIL = 'email',
  PHONE = 'phone',
}

// SIGN_UP_WITH_EMAIL
export type TSignUpWithEmailAmplifyProp = { email: string; password: string };

export type TSignUpWithEmailAmplifyDataUI = { email: string; password: string };

export type TSignUpWithEmailAmplifyDataUI2 = {
  username: string;
  userConfirmed: boolean;
  codeSend: boolean;
};

export type TSignUpWithEmailAmplifyDataToDb = {
  email: string;
  password: string;
};

// SIGN_UP_WITH_PHONE_NUMBER
export type TSignUpWithPhoneNumberAmplifyProp = {
  phoneNumber: string;
  password: string;
};

export type TSignUpWithPhoneNumberAmplifyDataUI = {
  phoneNumber: string;
  password: string;
};

export type TSignUpWithPhoneNumberAmplifyDataUI2 = {
  username: string;
  userConfirmed: boolean;
  codeSend: boolean;
};

export type TSignUpWithPhoneNumberAmplifyDataToDb = {
  phoneNumber: string;
  password: string;
};

// SIGN_UP
export type TSignUpAmplifyProp = (
  | { email: string }
  | { phoneNumber: string }
) & { password: string };

export type TSignUpAmplifyDataUI = (
  | { email: string }
  | { phoneNumber: string }
) & { password: string };

export type TSignUpAmplifyDataUI2 = {
  username: string;
  password: string;
  userConfirmed: boolean;
  codeSend: boolean;
  via: EVia;
};

export type TSignUpAmplifyDataToDb = (
  | { email: string }
  | { phoneNumber: string }
) & { password: string };

export interface IAmplifySignUpUserData extends ISignUpResult {
  user: IUserProps & CognitoUser;
  password: string;
}

export type TAmplifySignUpUserData = ISignUpResult | null;

// RESEND
export type TResendSignUpAmplifyProps = { username: string };

// CONFIRMATION
export type TConfirmSignUpAmplifyProp = {
  username: string;
  verificationCode: string;
};

export type TConfirmSignUpAmplifyDataUi = {
  username: string;
  verificationCode: string;
};

export type TConfirmSignUpAmplifyDataToDb = {
  username: string;
  verificationCode: string;
};

// FORGOT_PASSWORD
export type TForgotPasswordUser = {
  username: string;
  verificationCode?: string;
};

export type TForgotPasswordUserToDb = { username: string };

export type TForgotPasswordSubmitAmplifyDataUI = {
  username: string;
  verificationCode: string;
  newPassword: string;
};

export type TForgotPasswordSubmitAmplifyToDb = {
  username: string;
  verificationCode: string;
  newPassword: string;
};

export type TForgotPasswordAmplifyWithUsernameProp = { username: string };

export type TForgotPasswordAmplifyWithEmailProp = { email: string };

export type TForgotPasswordAmplifyWithPhoneNumberProp = { phoneNumber: string };

// SIGN_IN_WITH_EMAIL
export type TSignInWithEmailAmplifyProp = { email: string; password: string };

export type TSignInWithEmailAmplifyDataUI = { email: string; password: string };

export interface TAmplifySignInWithEmailUserData extends CognitoUser {
  username: string;
  attributes: UserEmailAttributes;
}

// SIGN_IN_WITH_PHONE_NUMBER
export type TSignInWithPhoneNumberAmplifyProp = {
  phoneNumber: string;
  password: string;
};

export type TSignInWithPhoneNumberAmplifyDataUI = {
  phoneNumber: string;
  password: string;
};

export interface TAmplifySignInWithPhoneNumberUserData extends CognitoUser {
  username: string;
  attributes: UserPhoneNumberAttributes;
}

// SIGN_IN
export type TSignInAmplifyProp = (
  | { email: string }
  | { phoneNumber: string }
) & { password: string };

export type TSignInAmplifyDataUI = (
  | { email: string }
  | { phoneNumber: string }
) & { password: string };

export type TSignInAmplifyDataToDb = { username: string; password: string };

// export type TAmplifySignInUserData = CognitoUser | null;
export interface IUserProps {
  username: string;
}

export type TAmplifyUser = {
  username: string;
  name: string;
} & (
  | { email: string; emailVerified: boolean }
  | { phoneNumber: string; phoneNumberVerified: boolean }
);

export type TAmplifyWithPhoneNumberUser = {
  username: string;
  name: string;
} & { phoneNumber: string; phoneNumberVerified: boolean };

export type TAmplifyWithEmailUser = {
  username: string;
  name: string;
} & { email: string; emailVerified: boolean };

export interface UserEmailAttributes {
  sub: string;
  email: string;
  email_verified: boolean;
  name: string;
  updated_at: string;
}

export interface UserPhoneNumberAttributes {
  sub: string;
  phone_number: string;
  phone_number_verified: boolean;
  name: string;
  updated_at: string;
}

export interface TAmplifySignInUserData extends CognitoUser {
  username: string;
  attributes: UserEmailAttributes | UserPhoneNumberAttributes;
}

export type TAmplifyError = {
  code: string;
  name: string;
  message: string;
  username: string;
  password: string;
};

export type TAmplifyErrorPayload = { data: TAmplifyError };

export enum EErrorCode {
  USER_NOT_CONFIRMED_EXCEPTION = 'UserNotConfirmedException',
  INVALID_PARAMETER_EXCEPTION = 'InvalidParameterException',
}
