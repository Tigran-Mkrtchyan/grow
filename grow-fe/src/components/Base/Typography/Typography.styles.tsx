import styled, { css } from 'styled-components';
import { Typography } from 'antd';
import {
  Colors,
  FontFamilies,
  FontSizes,
  fontSizes,
  FontWeights,
  marginBottom,
} from 'core/CssVariables';
import {
  TTextStyledProps,
  TLinkStyledProps,
  TTitleStyledProps,
  TParagraphStyledProps,
} from './Typography.types';

const { Paragraph, Text, Title, Link } = Typography;

const typographyGeneralProperties = css`
  margin-bottom: 0;
  font-size: ${({ $fontLevel }: TTextStyledProps) =>
    $fontLevel ? fontSizes[$fontLevel - 1] : FontSizes.FontMD}px;
  word-break: break-word;
`;

export const STitle = styled(Title)<TTitleStyledProps>`
  &.ant-typography {
    margin-bottom: 0;
    line-height: 24px;
    color: ${({ type, color, theme }) => color || type || theme.color};
    font-size: ${({ $fontLevel }) => fontSizes[$fontLevel - 1]}px;
    font-weight: ${({ fontWeight }) => fontWeight || FontWeights.SemiBold};
  }
`;

export const SText = styled(Text)<TTextStyledProps>`
  &.ant-typography {
    ${typographyGeneralProperties};
    color: ${({ type, color, theme }) => color || type || theme.text.color};
    font-weight: ${({ fontWeight }) => fontWeight};
  }
`;

export const SSecondaryText = styled(Text)<TTextStyledProps>`
  &.ant-typography {
    ${typographyGeneralProperties};
    font-weight: ${({ fontWeight }) => fontWeight || FontWeights.Light};
    color: ${({ theme }) => theme.secondaryColor};

    .icon {
      path {
        fill: ${({ theme }) => theme.secondaryColor};
      }
    }
  }
`;

export const SLink = styled(Link)<TLinkStyledProps>`
  &.ant-typography {
    margin-bottom: 0;
    font-size: ${({ $fontLevel }) =>
      $fontLevel ? fontSizes[$fontLevel - 1] : FontSizes.FontMD}px;
    color: ${({ color }) => color || Colors.PrimaryColor};
    font-weight: ${({ fontWeight }) => fontWeight || FontWeights.Light};
    &:hover {
      color: ${({ color }) => color || Colors.PrimaryColorHover};
    }
  }
`;

export const SParagraph = styled(Paragraph)<TParagraphStyledProps>`
  &.ant-typography {
    color: ${({ type, color, subtitle, theme }) =>
      subtitle ? Colors.Dark : color || type || theme.paragraph.color};
    font-size: ${({ $fontLevel }) => fontSizes[$fontLevel - 1]}px;
    margin-bottom: ${({ $fontLevel }) => marginBottom[$fontLevel - 1]}px;
    font-family: ${FontFamilies.FontSecondary};
    font-weight: ${({ fontWeight }) => fontWeight || FontWeights.Light};
  }
`;
