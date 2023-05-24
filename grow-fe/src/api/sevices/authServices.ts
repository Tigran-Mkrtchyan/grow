import { Auth } from 'aws-amplify';
import {
  TAmplifySignInUserData,
  TAmplifySignUpUserData,
  TConfirmSignUpAmplifyProp,
  TSignInAmplifyDataToDb,
  TResendSignUpAmplifyProps,
  TForgotPasswordAmplifyWithEmailProp,
  TForgotPasswordAmplifyWithUsernameProp,
  TForgotPasswordAmplifyWithPhoneNumberProp,
  TForgotPasswordSubmitAmplifyDataUI,
  TAmplifySignInWithEmailUserData,
  TSignUpWithEmailAmplifyProp,
  TSignUpWithPhoneNumberAmplifyProp,
  TAmplifySignInWithPhoneNumberUserData,
} from 'data/types/Amplify.types';
import {
  confirmSignUpAmplifyConvertor,
  forgotPasswordSubmitAmplifyConvertor,
} from 'data/convertors/Amplify.convertors';
import {
  signUpWithEmailAmplifyConvertor,
  forgotPasswordWithEmailAmplifyConvertor,
} from 'data/convertors/EmailAmplify.convertors';
import {
  signUpWithPhoneNumberAmplifyConvertor,
  forgotPasswordWithPhoneNumberAmplifyConvertor,
} from 'data/convertors/PhoneNumberAmplify.convertors';

export const currentAmplifyAuthUser =
  async (): Promise<TAmplifySignInUserData> => {
    return Auth.currentAuthenticatedUser();
  };

export const resendSignUpAmplifyService = async (
  props: TResendSignUpAmplifyProps,
): Promise<void> => {
  await Auth.resendSignUp(props.username);
};

export const confirmSignUpAmplifyService = async (
  props: TConfirmSignUpAmplifyProp,
): Promise<void> => {
  const { username, verificationCode } =
    confirmSignUpAmplifyConvertor.toDb(props);

  return Auth.confirmSignUp(username, verificationCode.trim());
};

export const signOutAmplifyService = async (): Promise<null> => {
  return Auth.signOut();
};

export const forgotPasswordWithUsernameAmplifyService = async (
  props: TForgotPasswordAmplifyWithUsernameProp,
): Promise<null> => {
  const { username } = props;
  return Auth.forgotPassword(username);
};

export const forgotPasswordSubmitAmplifyService = async (
  props: TForgotPasswordSubmitAmplifyDataUI,
): Promise<string> => {
  const { username, verificationCode, newPassword } =
    forgotPasswordSubmitAmplifyConvertor.toDb(props);
  return Auth.forgotPasswordSubmit(username, verificationCode, newPassword);
};

// EMAIL
export const signUpWithEmailAmplifyService = async (
  props: TSignUpWithEmailAmplifyProp,
): Promise<TAmplifySignUpUserData> => {
  const { email, password } = signUpWithEmailAmplifyConvertor.toDb(props);

  return Auth.signUp({
    username: email,
    password,
    attributes: { email },
    autoSignIn: { enabled: true },
  });
};

export const signInWithEmailAmplifyService = async (
  props: TSignInAmplifyDataToDb,
): Promise<TAmplifySignInWithEmailUserData> => {
  const { username, password } = props;

  return Auth.signIn(username, password);
};

export const forgotPasswordWithEmailAmplifyService = async (
  props: TForgotPasswordAmplifyWithEmailProp,
): Promise<null> => {
  const { username } = forgotPasswordWithEmailAmplifyConvertor.toDb(props);
  return Auth.forgotPassword(username);
};

// PHONE_NUMBER
export const signUpWithPhoneNumberAmplifyService = async (
  props: TSignUpWithPhoneNumberAmplifyProp,
): Promise<TAmplifySignUpUserData> => {
  const { phoneNumber, password } =
    signUpWithPhoneNumberAmplifyConvertor.toDb(props);

  return Auth.signUp({
    username: phoneNumber,
    password,
    attributes: { phone_number: phoneNumber },
    autoSignIn: { enabled: true },
  });
};

export const signInWithPhoneNumberAmplifyService = async (
  props: TSignInAmplifyDataToDb,
): Promise<TAmplifySignInWithPhoneNumberUserData> => {
  const { username, password } = props;

  return Auth.signIn(username, password);
};

export const forgotPasswordWithPhoneNumberAmplifyService = async (
  props: TForgotPasswordAmplifyWithPhoneNumberProp,
): Promise<null> => {
  const { username } =
    forgotPasswordWithPhoneNumberAmplifyConvertor.toDb(props);
  return Auth.forgotPassword(username);
};
