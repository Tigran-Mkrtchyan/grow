import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import { Auth } from 'aws-amplify';

const baseUrl =
  !process.env.NODE_ENV || process.env.NODE_ENV === 'development'
    ? 'http://localhost:3000/api/v1'
    : `${process.env.REACT_APP_HOSTNAME}/api/v1`;

export const authSplitApi = (reducerPath: string, tagTypes?: string[]) =>
  createApi({
    reducerPath,
    baseQuery: fetchBaseQuery({
      baseUrl,
      prepareHeaders: async headers => {
        const session = await Auth.currentSession();
        if (session) {
          headers.set(
            'authorization',
            `Bearer ${session.getIdToken().getJwtToken()}`,
          );
        }
        return headers;
      },
    }),
    tagTypes: tagTypes || [],
    endpoints: () => ({}),
  });

export const emptySplitApi = (reducerPath: string) =>
  createApi({
    reducerPath,
    baseQuery: fetchBaseQuery({
      baseUrl,
    }),
    endpoints: () => ({}),
  });
