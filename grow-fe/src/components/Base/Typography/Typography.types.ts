import type { TitleProps } from 'antd/es/typography/Title';
import type { TextProps } from 'antd/es/typography/Text';
import type { LinkProps } from 'antd/es/typography/Link';
import type { ParagraphProps } from 'antd/es/typography/Paragraph';
import { Colors } from 'core/CssVariables';

export type TLevel = 1 | 2 | 3 | 4 | 5 | 6 | 7;

export type TFontLevel = {
  fontLevel?: TLevel;
};

export type TFontLevelStyled = {
  $fontLevel: TLevel;
};

export type TColor = {
  color?: Colors;
};

export type TWeight = {
  fontWeight?: 300 | 400 | 500 | 600 | 700;
};

export type TParagraph = {
  subtitle?: boolean;
};

export type TTextProps = TextProps & TFontLevel & TWeight & TColor;
export type TTitleProps = TitleProps & TFontLevel & TWeight & TColor;
export type TLinkProps = LinkProps & TFontLevel & TWeight & TColor;
export type TParagraphProps = ParagraphProps &
  TFontLevel &
  TWeight &
  TColor &
  TParagraph;

export type TTextStyledProps = TextProps & TFontLevelStyled & TWeight & TColor;
export type TTitleStyledProps = TitleProps &
  TFontLevelStyled &
  TWeight &
  TColor;
export type TLinkStyledProps = LinkProps & TFontLevelStyled & TWeight & TColor;
export type TParagraphStyledProps = ParagraphProps &
  TFontLevelStyled &
  TWeight &
  TColor &
  TParagraph;
