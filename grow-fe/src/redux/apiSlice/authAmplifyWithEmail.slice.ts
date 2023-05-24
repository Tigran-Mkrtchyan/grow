import { authSplitApi } from 'redux/helpers/slice.helpers';
import {
  forgotPasswordWithEmailAmplifyService,
  signInWithEmailAmplifyService,
  signUpWithEmailAmplifyService,
} from 'api/sevices/authServices';
import {
  TAmplifyError,
  TSignUpAmplifyDataUI2,
  IAmplifySignUpUserData,
  TForgotPasswordAmplifyWithEmailProp,
  TForgotPasswordUser,
  TSignInWithEmailAmplifyProp,
  TSignUpWithEmailAmplifyProp,
  TAmplifyWithEmailUser,
} from 'data/types/Amplify.types';
import {
  forgotPasswordWithEmailAmplifyConvertor,
  signInWithEmailAmplifyConvertor,
  signUpWithEmailAmplifyConvertor,
} from 'data/convertors/EmailAmplify.convertors';

export const AuthAmplifyWithEmail = authSplitApi(
  'amplifyEmail',
).injectEndpoints({
  endpoints: build => ({
    signUpWithEmailAmplify: build.mutation<
      TSignUpAmplifyDataUI2,
      TSignUpWithEmailAmplifyProp
    >({
      // @ts-ignore
      async queryFn(props) {
        try {
          const amplifyUser = await signUpWithEmailAmplifyService(props);
          return {
            data: signUpWithEmailAmplifyConvertor.fromDb({
              ...(amplifyUser as IAmplifySignUpUserData),
              password: props.password,
            }),
          };
        } catch (e) {
          return { error: e };
        }
      },
    }),
    signInWithEmailAmplify: build.mutation<
      TAmplifyWithEmailUser,
      TSignInWithEmailAmplifyProp
    >({
      // @ts-ignore
      async queryFn(props) {
        const userData = signInWithEmailAmplifyConvertor.toDb(props);
        try {
          const amplifyUser = await signInWithEmailAmplifyService(userData);
          return { data: signInWithEmailAmplifyConvertor.fromDb(amplifyUser) };
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
    forgotPasswordWithEmailAmplify: build.mutation<
      TForgotPasswordUser,
      TForgotPasswordAmplifyWithEmailProp
    >({
      // @ts-ignore
      async queryFn(data) {
        try {
          await forgotPasswordWithEmailAmplifyService(data);
          return { data: forgotPasswordWithEmailAmplifyConvertor.toDb(data) };
        } catch (e) {
          const error = e as TAmplifyError;
          return {
            error: {
              data: {
                name: error.name,
                code: error.code,
                message: error.message,
                username: data.email,
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
  useSignInWithEmailAmplifyMutation,
  useForgotPasswordWithEmailAmplifyMutation,
  useSignUpWithEmailAmplifyMutation,
} = AuthAmplifyWithEmail;
