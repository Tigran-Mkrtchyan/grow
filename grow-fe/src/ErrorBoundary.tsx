import React, { ReactNode } from 'react';

type Props = {
  children?: ReactNode;
};

class ErrorBoundary extends React.Component<Props> {
  public componentDidCatch(error: Error) {
    if (error?.name?.includes('ChunkLoadError')) {
      window.location.reload();
    }
  }

  public render() {
    const { children } = this.props;
    return children;
  }
}

export default ErrorBoundary;
