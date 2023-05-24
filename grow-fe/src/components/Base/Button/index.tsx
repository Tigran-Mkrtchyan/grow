import React from 'react';
import { TButtonProps, EButtonTypes, EButtonColors } from './Button.types';
import SButton from './Button.styles';

const Button = ({
  type = EButtonTypes.Primary,
  color = EButtonColors.Primary,
  outlined = false,
  ...props
}: TButtonProps) => {
  return <SButton type={type} color={color} $outlined={outlined} {...props} />;
};

export { EButtonColors, EButtonTypes };
export default Button;
