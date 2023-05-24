import React from 'react';
import {
  TLinkProps,
  TParagraphProps,
  TTextProps,
  TTitleProps,
} from './Typography.types';
import {
  SLink,
  SParagraph,
  SSecondaryText,
  SText,
  STitle,
} from './Typography.styles';

export const Title = ({ fontLevel = 4, ...props }: TTitleProps) => {
  return <STitle $fontLevel={fontLevel} {...props} />;
};

export const Text = ({ fontLevel = 4, ...props }: TTextProps) => {
  return <SText $fontLevel={fontLevel} {...props} />;
};

export const SecondaryText = ({ fontLevel = 4, ...props }: TTextProps) => {
  return <SSecondaryText $fontLevel={fontLevel} {...props} />;
};

export const Link = ({ fontLevel = 4, ...props }: TLinkProps) => {
  return <SLink $fontLevel={fontLevel} {...props} />;
};

export const Paragraph = ({ fontLevel = 4, ...props }: TParagraphProps) => {
  return <SParagraph $fontLevel={fontLevel} {...props} />;
};
