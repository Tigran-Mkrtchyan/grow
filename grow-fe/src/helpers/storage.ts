export enum TStorageNames {
  someStorageName = 'someStorage',
}

type TStorage = {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  getItem: (key: TStorageNames) => any;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  setItem: (key: TStorageNames, value: any) => void;
  removeItem: (key: TStorageNames) => void;
  clear: () => void;
};

const appSessionStorage: TStorage = {
  getItem: key => {
    const item = sessionStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  },
  setItem: (key, value) => {
    try {
      sessionStorage.setItem(key, JSON.stringify(value));
    } catch (error) {
      console.log(error);
    }
  },
  removeItem: key => {
    try {
      sessionStorage.removeItem(key);
    } catch (error) {
      console.log(error);
    }
  },
  clear: () => {
    try {
      sessionStorage.clear();
    } catch (error) {
      console.log(error);
    }
  },
};
const appLocalStorage: TStorage = {
  getItem: key => {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  },
  setItem: (key, value) => {
    try {
      localStorage.setItem(key, JSON.stringify(value));
    } catch (error) {
      console.log(error);
    }
  },
  removeItem: key => {
    try {
      localStorage.removeItem(key);
    } catch (error) {
      console.log(error);
    }
  },
  clear: () => {
    try {
      localStorage.clear();
    } catch (error) {
      console.log(error);
    }
  },
};

export { appLocalStorage, appSessionStorage };
