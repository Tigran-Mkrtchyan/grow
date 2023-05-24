import Button from 'antd/es/button';
import styled, { css } from 'styled-components';
import { Colors, FontSizes, FontWeights } from 'core/CssVariables';
import { TButtonProps, TButtonStyles, EButtonColors } from './Button.types';

export const btnStyles: TButtonStyles = {
  [EButtonColors.Primary]: {
    color: Colors.TextColorPrimary,
    bgColor: Colors.PrimaryColor,
    hoverColor: Colors.PrimaryColorHover,
  },
  [EButtonColors.Default]: {
    color: Colors.TextColorPrimary,
    bgColor: Colors.SecondaryColor,
    hoverColor: Colors.SecondaryColorHover,
  },
  [EButtonColors.Info]: {
    color: Colors.White,
    bgColor: Colors.InfoColor,
    hoverColor: Colors.InfoColorHover,
  },
  [EButtonColors.Success]: {
    color: Colors.White,
    bgColor: Colors.SuccessColor,
    hoverColor: Colors.SuccessColorHover,
  },
  [EButtonColors.Warning]: {
    color: Colors.White,
    bgColor: Colors.WarningColor,
    hoverColor: Colors.WarningColorHover,
  },
  [EButtonColors.Danger]: {
    color: Colors.White,
    bgColor: Colors.ErrorColor,
    hoverColor: Colors.ErrorColorHover,
  },
};

const createButtonStyles = (
  color: EButtonColors,
  { width, height, $outlined: outlined, shape, size, type }: TButtonProps,
) => css`
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-weight: 500;
  padding: ${size === 'small' ? '4px 12px' : '8px 24px'};
  background-color: ${outlined || type === 'link' || type === 'text'
    ? 'transparent'
    : btnStyles[color].bgColor};
  color: ${(outlined &&
    ![EButtonColors.Primary, EButtonColors.Default].includes(color)) ||
  type === 'link' ||
  type === 'text'
    ? btnStyles[color].bgColor
    : btnStyles[color].color};
  border-color: ${outlined ? btnStyles[color].bgColor : 'transparent'};

  &:hover,
  &:active,
  &:focus {
    .icon {
      path {
        fill: ${outlined || type === 'link'
          ? btnStyles[color].hoverColor
          : Colors.White};
      }
    }

    background-color: ${outlined || type === 'link' || type === 'text'
      ? 'transparent'
      : btnStyles[color].hoverColor};
    color: ${(outlined &&
      ![EButtonColors.Primary, EButtonColors.Default].includes(color)) ||
    type === 'link' ||
    type === 'text'
      ? btnStyles[color].hoverColor
      : btnStyles[color].color};
    border-color: ${outlined ? btnStyles[color].hoverColor : 'transparent'};
  }

  &:disabled,
  &:hover:disabled {
    .icon {
      path {
        fill: ${outlined || type === 'link' || type === 'text'
          ? `${btnStyles[color].bgColor}4D`
          : Colors.White};
      }
    }

    cursor: default;
    background-color: ${outlined || type === 'link' || type === 'text'
      ? Colors.Transparent
      : `${btnStyles[color].bgColor}4D`}; // 30% opacity
    color: ${outlined || type === 'link' || type === 'text'
      ? `${btnStyles[color].bgColor}4D`
      : `${btnStyles[color].color}4D`};
    border-color: ${outlined
      ? `${btnStyles[color].bgColor}4D}`
      : 'transparent'};
  }

  .icon {
    margin-right: 8px;
    margin-bottom: 1px;

    path {
      transition: fill 0.3s ease;
      fill: ${outlined ? btnStyles[color].bgColor : Colors.White};
    }
  }

  ${() =>
    shape === 'circle' &&
    css`
      display: flex;
      justify-content: space-around;
      width: ${width ? `${width}px` : 'auto'};
      min-width: ${width ? `${width}px` : 'auto'};
      height: ${height || width ? `${height || width}px` : 'auto'};

      .icon {
        margin-right: 0;
      }
    `}

  ${() =>
    (type === 'link' || type === 'text') &&
    css`
      padding: 0;
      height: auto;
      border: none;
      color: ${color === EButtonColors.Default ? Colors.TextColorPrimary : ''};

      &:hover {
        color: ${color === EButtonColors.Default
          ? Colors.TextColorPrimary
          : ''};
      }

      ${size === 'small' &&
      css`
        font-size: ${FontSizes.FontXS}px;
        font-weight: ${FontWeights.Regular};
      `}
      .icon {
        margin-right: 8px;
        height: 16px;
        width: 16px;

        path {
          fill: ${btnStyles[color].bgColor};
        }
      }

      ${type === 'link' &&
      css`
        &:not([disabled]) {
          &:hover {
            > span {
              text-decoration: underline;
            }
          }
        }
      `}
    `}
`;

const SButton = styled<typeof Button>(Button)`
  ${(props: TButtonProps) => {
    switch (props.type) {
      case 'default':
        return createButtonStyles(EButtonColors.Default, props);
      case 'primary':
      case 'link':
      case 'text':
        return createButtonStyles(props.color || EButtonColors.Primary, props);
      default:
        return createButtonStyles(EButtonColors.Primary, props);
    }
  }}
`;

export default SButton;
