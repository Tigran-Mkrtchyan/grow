import React, { forwardRef } from 'react';
import { EyeInvisibleOutlined, EyeOutlined } from '@ant-design/icons';

import { InputRef } from 'antd/es/input';
import { TextAreaRef } from 'antd/es/input/TextArea';
import { SGroup, SInput, SPassword, STextArea } from './Input.styles';
import {
  TGroupProps,
  TInputProps,
  TPasswordProps,
  TTextAreaProps,
} from './Input.types';

export const TextField = forwardRef<InputRef, TInputProps>(
  (props: TInputProps, ref) => {
    return <SInput ref={ref} {...props} />;
  },
);

export const Password = (props: TPasswordProps) => {
  return (
    <SPassword
      iconRender={(visible: boolean) =>
        visible ? <EyeOutlined /> : <EyeInvisibleOutlined />
      }
      {...props}
    />
  );
};

export const TextArea = forwardRef<TextAreaRef, TTextAreaProps>(
  (props, ref) => {
    return <STextArea ref={ref} {...props} />;
  },
);

export const Group = (props: TGroupProps) => {
  return <SGroup {...props} />;
};
