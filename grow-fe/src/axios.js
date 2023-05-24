import axios, { Method } from 'axios';
import { Auth } from 'aws-amplify';

axios.defaults.withCredentials = true;

const baseURL =
  !process.env.NODE_ENV || process.env.NODE_ENV === 'development'
    ? 'http://localhost:3000/api/v1'
    : `${process.env.REACT_APP_HOSTNAME}/api/v1`;

const instance = axios.create({
  baseURL,
});

instance.interceptors.request.use(async function (config) {
  const res = { ...config };
  const session = await Auth.currentSession();
  const { jwtToken } = session.idToken;
  res.headers.Authorization = jwtToken ? `Bearer ${jwtToken}` : '';
  return res;
});

const axiosUnauth = axios.create({
  baseURL,
  headers: {
    'content-type': 'application/json',
  },
});

export { axiosUnauth, Method };

export default instance;

export const formDataCreator = data => {
  const formData = new FormData();
  Object.keys(data).forEach(key => formData.append(key, data[key]));
  return formData;
};
