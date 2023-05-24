import { authSplitApi } from 'redux/helpers/slice.helpers';
import {
  confirmSignUpAmplifyService,
  currentAmplifyAuthUser,
  forgotPasswordSubmitAmplifyService,
  forgotPasswordWithUsernameAmplifyService,
  resendSignUpAmplifyService,
  signOutAmplifyService,
} from 'api/sevices/authServices';
import {
  TConfirmSignUpAmplifyProp,
  TResendSignUpAmplifyProps,
  TForgotPasswordAmplifyWithUsernameProp,
  TForgotPasswordSubmitAmplifyDataUI,
  TAmplifyError,
} from 'data/types/Amplify.types';

export const AuthAmplify = authSplitApi('amplify').injectEndpoints({
  endpoints: build => ({
    currentAuthUserAmplify: build.mutation<null, null>({
      // @ts-ignore
      async queryFn() {
        try {
          const user = await currentAmplifyAuthUser();
          return { data: user.attributes };
        } catch (e) {
          /*
          * todo research build. the query doesn't work with return err
            currentAuthUserAmplify.matchRejected didn't triggering
            that's why I use mutation instead
          * */
          const error = e as TAmplifyError;

          return {
            error: {
              data: {
                name: error.name,
                code: error.code,
                message: error.message,
              },
              status: error.code,
            },
          };
        }
      },
    }),
    resendSignUpAmplify: build.mutation<null, TResendSignUpAmplifyProps>({
      // @ts-ignore
      async queryFn(props) {
        try {
          await resendSignUpAmplifyService(props);
          return { data: null };
        } catch (e) {
          return { error: e };
        }
      },
    }),
    confirmSignUpAmplify: build.mutation<null, TConfirmSignUpAmplifyProp>({
      // @ts-ignore
      async queryFn(props) {
        try {
          await confirmSignUpAmplifyService(props);
          return { data: null };
        } catch (e) {
          const error = e as TAmplifyError;
          return {
            error: {
              data: {
                name: error.name,
                code: error.code,
                message: error.message,
              },
              status: error.code,
            },
          };
        }
      },
    }),
    signOutAmplify: build.mutation<null, void>({
      // @ts-ignore
      async queryFn() {
        try {
          await signOutAmplifyService();
          return { data: null };
        } catch (e) {
          return { error: e };
        }
      },
    }),
    forgotPasswordWithUsernameAmplify: build.mutation<
      null,
      TForgotPasswordAmplifyWithUsernameProp
    >({
      // @ts-ignore
      async queryFn(data) {
        try {
          await forgotPasswordWithUsernameAmplifyService(data);
          return { data: null };
        } catch (e) {
          return { error: e };
        }
      },
    }),
    forgotPasswordSubmitAmplify: build.mutation<
      null,
      TForgotPasswordSubmitAmplifyDataUI
    >({
      // @ts-ignore
      async queryFn(data) {
        try {
          await forgotPasswordSubmitAmplifyService(data);
          return { data: null };
        } catch (e) {
          return { error: e };
        }
      },
    }),
  }),
  overrideExisting: false,
});

export const {
  useCurrentAuthUserAmplifyMutation,
  useResendSignUpAmplifyMutation,
  useConfirmSignUpAmplifyMutation,
  useSignOutAmplifyMutation,
  useForgotPasswordWithUsernameAmplifyMutation,
  useForgotPasswordSubmitAmplifyMutation,
} = AuthAmplify;
