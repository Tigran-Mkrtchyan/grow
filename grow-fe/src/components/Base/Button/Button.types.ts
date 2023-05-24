import { Colors } from 'core/CssVariables';
import { ButtonProps } from 'antd/es/button';

export enum EButtonColors {
  Primary = 'primary',
  Default = 'default',
  Info = 'info',
  Success = 'success',
  Warning = 'warning',
  Danger = 'danger',
}

export enum EButtonTypes {
  Primary = 'primary',
  Ghost = 'ghost',
  Dashed = 'dashed',
  Link = 'link',
  Text = 'text',
  Default = 'default',
}

export type TButtonColors = {
  color: Colors;
  bgColor: Colors;
  hoverColor: Colors;
};

export type TButtonColorProp = {
  color?: EButtonColors;
  outlined?: boolean;
};

export type TButtonStyles = { [key in EButtonColors]: TButtonColors };

export type TButtonProps = ButtonProps &
  TButtonColorProp & {
    width?: number;
    height?: number;
    $outlined?: boolean;
  };
