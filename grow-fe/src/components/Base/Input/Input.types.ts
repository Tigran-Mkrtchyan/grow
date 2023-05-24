import { Colors } from 'core/CssVariables';
import type { InputProps } from 'antd/es/input';
import type { TextAreaProps } from 'antd/es/input/TextArea';
import type { GroupProps } from 'antd/es/input/Group';
import type { PasswordProps } from 'antd/es/input/Password';

export enum EInputTypes {
  Default = 'default',
  SecondaryOutlined = 'secondaryOutlined',
  Warning = 'warning',
  Success = 'success',
  Danger = 'danger',
}

export type TInputProps = InputProps & TInputColorProp & { noMargin?: boolean };

export type TInputColors = {
  color: Colors;
  backgroundColor?: Colors;
  hoverColor: Colors;
  disabledColor?: Colors;
};

export type TInputColorProp = {
  color?: EInputTypes;
};

export type TInputStyles = { [key in EInputTypes]: TInputColors };
export type TPasswordProps = TInputProps & PasswordProps;
export type TGroupProps = TInputProps & GroupProps;
export type TTextAreaProps = TInputProps & TextAreaProps;
