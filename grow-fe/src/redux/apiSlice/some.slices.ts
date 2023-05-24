import { authSplitApi } from '../helpers/slice.helpers';

const URL = 'some';
// this is example
export const SomeApi = authSplitApi('some', [
  'Everything',
  'Something',
]).injectEndpoints({
  endpoints: build => ({
    getEverything: build.query<
      { id: string } /* return type */,
      { id: string } /* params type */
    >({
      query(params) {
        return {
          url: `${URL}`,
          method: 'GET',
          params,
        };
      },
      providesTags: ['Everything'],
      transformResponse: (data: { id: string } /* data type from api */) => {
        // use convertor
        return data;
      },
    }),
    getSomething: build.query<{ id: string }, { someId: string }>({
      query({ someId, ...params }) {
        return {
          url: `${URL}/${someId}`,
          method: 'GET',
          params,
        };
      },
      providesTags: ['Something'],
      transformResponse: (data: { id: string }) => {
        // use convertor
        return data;
      },
    }),
    postSomething: build.mutation<void, void>({
      query(body) {
        return {
          url: `${URL}`,
          method: 'POST',
          body,
        };
      },
      invalidatesTags: ['Everything'],
    }),
  }),
  overrideExisting: false,
});

export const {
  useGetEverythingQuery,
  useGetSomethingQuery,
  usePostSomethingMutation,
} = SomeApi;
