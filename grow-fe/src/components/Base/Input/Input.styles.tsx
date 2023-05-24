import Input from 'antd/es/input';
import styled, { css, ThemeProps } from 'styled-components';
import { Colors } from 'core/CssVariables';

import { TThemeProps } from 'theme/theme';
import {
  TGroupProps,
  TInputProps,
  TInputStyles,
  EInputTypes,
  TPasswordProps,
  TTextAreaProps,
} from './Input.types';

const { Password, TextArea, Group } = Input;

const inputStyles: TInputStyles = {
  [EInputTypes.Default]: {
    color: Colors.PrimaryColor,
    hoverColor: Colors.PrimaryColorHover,
    disabledColor: Colors.TextColorSecondary,
  },
  [EInputTypes.SecondaryOutlined]: {
    color: Colors.PrimaryColor,
    backgroundColor: Colors.BackgroundColor,
    hoverColor: Colors.PrimaryColorHover,
    disabledColor: Colors.TextColorSecondary,
  },
  [EInputTypes.Warning]: {
    color: Colors.WarningColor,
    hoverColor: Colors.WarningColorHover,
  },
  [EInputTypes.Success]: {
    color: Colors.SuccessColor,
    hoverColor: Colors.SuccessColorHover,
  },
  [EInputTypes.Danger]: {
    color: Colors.ErrorColor,
    hoverColor: Colors.ErrorColorHover,
  },
};

export const createInputStyles = (
  theme: TThemeProps,
  color?: EInputTypes,
) => css`
  &.ant-input-affix-wrapper,
  &.ant-input {
    padding: 8px 12px;
    width: 100%;
  }

  &.ant-input-affix-wrapper,
  &.ant-input,
  &.ant-input-search,
  &.ant-input-password {
    &,
    .ant-input {
      background-color: ${color
        ? inputStyles[color].backgroundColor || theme.input.backgroundColor
        : theme.input.backgroundColor};
      color: ${theme.input.textColorPrimary};
      border-color: ${color
        ? inputStyles[color].color
        : theme.input.borderColor};

      &::placeholder {
        color: ${theme.input.placeholder};
      }

      input {
        color: ${theme.input.textColorPrimary};
        background-color: ${theme.input.backgroundColor};

        &.ant-input {
          padding: 0 1px 0 3px;
        }
      }

      &:hover {
        box-shadow: ${color
          ? `0px 0px 7px 0px ${inputStyles[color].color}21`
          : `0px 0px 7px 0px ${Colors.PrimaryColor}21`};

        border-color: ${color ? inputStyles[color].color : Colors.PrimaryColor};
      }

      &:active,
      &:focus,
      &.ant-input-affix-wrapper-focused {
        border-color: ${color ? inputStyles[color].color : Colors.PrimaryColor};
        box-shadow: none;

        &::placeholder {
          color: ${Colors.TextColorSecondary};
        }
      }

      &:disabled,
      &:disabled:hover,
      &:disabled:focus,
      &:disabled:active {
        cursor: default;
        border-color: ${color
          ? inputStyles[color].disabledColor || `${inputStyles[color].color}4D`
          : theme.input.borderColor};

        &::placeholder {
          color: ${theme.input.textColorDisabled};
        }

        &:-webkit-autofill {
          background-color: ${theme.input.backgroundColor};
        }
      }

      &.ant-input-password {
        padding: 8px 12px 8px 4px;

        input {
          padding-left: 8px;
        }
      }
    }
  }
`;

export const SInput = styled(Input)`
  ${(props: TInputProps & ThemeProps<TThemeProps>) => {
    return createInputStyles(props.theme, props.color || EInputTypes.Default);
  }}
`;

export const SPassword = styled(Password)`
  ${(props: TPasswordProps & ThemeProps<TThemeProps>) => {
    return createInputStyles(props.theme, props.color);
  }}
`;

export const STextArea = styled(TextArea)`
  ${(props: TTextAreaProps & ThemeProps<TThemeProps>) => {
    return createInputStyles(props.theme, props.color);
  }}
`;

export const SGroup = styled(Group)`
  ${(props: TGroupProps & ThemeProps<TThemeProps>) => {
    return createInputStyles(props.theme, props.color);
  }}
`;
