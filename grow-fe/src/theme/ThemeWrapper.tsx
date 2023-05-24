import React, { FC } from 'react';
import { ThemeProvider } from 'styled-components';
import GlobalStyles, { DropDownGlobalStyle } from 'styles/GlobalStyles';
import { LightTheme } from './theme';
import 'styles/App.less';

const ThemeWrapper: FC = ({ children }) => {
  return (
    <ThemeProvider theme={LightTheme}>
      <GlobalStyles />
      <DropDownGlobalStyle />
      {children}
    </ThemeProvider>
  );
};

export default ThemeWrapper;
