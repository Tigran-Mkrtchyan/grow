import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { Amplify } from 'aws-amplify';
import ErrorBoundary from './ErrorBoundary';
import ThemeWrapper from './theme/ThemeWrapper';
import { store } from './redux/store';
import RoutesPage from './routes';
import amplifyConfig from './aws-exports';

Amplify.configure(amplifyConfig);

function App() {
  return (
    <ErrorBoundary>
      <BrowserRouter>
        <Provider store={store}>
          <ThemeWrapper>
            <RoutesPage />
          </ThemeWrapper>
        </Provider>
      </BrowserRouter>
    </ErrorBoundary>
  );
}

export default App;
