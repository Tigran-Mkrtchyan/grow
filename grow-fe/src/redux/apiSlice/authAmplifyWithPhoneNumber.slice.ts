import { authSplitApi } from 'redux/helpers/slice.helpers';
import {
  forgotPasswordWithPhoneNumberAmplifyService,
  signInWithPhoneNumberAmplifyService,
  signUpWithPhoneNumberAmplifyService,
} from 'api/sevices/authServices';
import {
  TAmplifyError,
  TSignUpAmplifyDataUI2,
  IAmplifySignUpUserData,
  TAmplifyWithPhoneNumberUser,
  TForgotPasswordAmplifyWithPhoneNumberProp,
  TForgotPasswordUser,
  TSignInWithPhoneNumberAmplifyProp,
  TSignUpWithPhoneNumberAmplifyProp,
} from 'data/types/Amplify.types';
import {
  forgotPasswordWithPhoneNumberAmplifyConvertor,
  signInWithPhoneNumberAmplifyConvertor,
  signUpWithPhoneNumberAmplifyConvertor,
} from 'data/convertors/PhoneNumberAmplify.convertors';

export const AuthAmplifyWithPhoneNumber = authSplitApi(
  'amplifyPhone',
).injectEndpoints({
  endpoints: build => ({
    signUpWithPhoneNumberAmplify: build.mutation<
      TSignUpAmplifyDataUI2,
      TSignUpWithPhoneNumberAmplifyProp
    >({
      // @ts-ignore
      async queryFn(props) {
        try {
          const amplifyUser = await signUpWithPhoneNumberAmplifyService(props);
          return {
            data: signUpWithPhoneNumberAmplifyConvertor.fromDb({
              ...(amplifyUser as IAmplifySignUpUserData),
              password: props.password,
            }),
          };
        } catch (e) {
          return { error: e };
        }
      },
    }),
    signInWithPhoneNumberAmplify: build.mutation<
      TAmplifyWithPhoneNumberUser,
      TSignInWithPhoneNumberAmplifyProp
    >({
      // @ts-ignore
      async queryFn(props) {
        const userData = signInWithPhoneNumberAmplifyConvertor.toDb(props);
        try {
          const amplifyUser = await signInWithPhoneNumberAmplifyService(
            userData,
          );
          return {
            data: signInWithPhoneNumberAmplifyConvertor.fromDb(amplifyUser),
          };
        } catch (e) {
          const error = e as TAmplifyError;
          return {
            error: {
              data: {
                name: error.name,
                code: error.code,
                message: error.message,
                username: userData.username,
                password: props.password,
              },
              status: error.code,
            },
          };
        }
      },
    }),
    forgotPasswordWithPhoneNumberAmplify: build.mutation<
      TForgotPasswordUser,
      TForgotPasswordAmplifyWithPhoneNumberProp
    >({
      // @ts-ignore
      async queryFn(data) {
        try {
          await forgotPasswordWithPhoneNumberAmplifyService(data);
          return {
            data: forgotPasswordWithPhoneNumberAmplifyConvertor.toDb(data),
          };
        } catch (e) {
          const error = e as TAmplifyError;
          return {
            error: {
              data: {
                name: error.name,
                code: error.code,
                message: error.message,
                username: data.phoneNumber,
              },
              status: error.code,
            },
          };
        }
      },
    }),
  }),
  overrideExisting: false,
});

export const {
  useSignUpWithPhoneNumberAmplifyMutation,
  useSignInWithPhoneNumberAmplifyMutation,
  useForgotPasswordWithPhoneNumberAmplifyMutation,
} = AuthAmplifyWithPhoneNumber;
