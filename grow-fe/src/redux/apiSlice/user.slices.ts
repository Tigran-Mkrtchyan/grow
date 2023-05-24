import { TUser } from 'data/types/User';
import { authSplitApi } from '../helpers/slice.helpers';

export type TUserSliceState = {
  user: TUser | null;
};

const URL = 'user';

export const UserApi = authSplitApi('authUser', ['User']).injectEndpoints({
  endpoints: build => ({
    signInUser: build.query<TUser, null>({
      query() {
        return {
          url: `${URL}/signIn`,
          method: 'GET',
        };
      },
      providesTags: ['User'],
      keepUnusedDataFor: 60 * 60 * 24,
      transformResponse: (data: TUser) => ({ ...data, id: `${data.id}` }),
    }),
    updateProfile: build.mutation<TUser, TUser>({
      query({ id, ...body }) {
        return {
          url: `${URL}/${id}`,
          method: 'PATCH',
          body,
        };
      },
      invalidatesTags: (result, error, arg) => [
        { type: 'Members' },
        { type: 'Members', id: arg.id },
      ],
      transformResponse: (data: TUser) => ({ ...data, id: `${data.id}` }),
    }),
  }),
  overrideExisting: false,
});

export const { useSignInUserQuery, useUpdateProfileMutation } = UserApi;
