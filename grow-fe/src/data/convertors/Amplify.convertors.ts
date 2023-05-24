import { TAuthConverter } from '../types/Covertor.types';
import {
  TConfirmSignUpAmplifyDataToDb,
  TConfirmSignUpAmplifyDataUi,
  TForgotPasswordSubmitAmplifyDataUI,
  TForgotPasswordSubmitAmplifyToDb,
} from '../types/Amplify.types';

export const confirmSignUpAmplifyConvertor: TAuthConverter<
  TConfirmSignUpAmplifyDataUi,
  TConfirmSignUpAmplifyDataToDb,
  void,
  void
> = {
  fromDb: () => {
    /* do nothing */
  },
  toDb: data => ({
    username: data.username,
    verificationCode: data.verificationCode,
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
