import React from 'react';
import { ButtonsSectionWrapper } from '../styles';
import { TButtonsGroupProps } from '../types';

const ButtonsGroup = ({ handleUpdate, handleClose }: TButtonsGroupProps) => {
  return (
    <ButtonsSectionWrapper>
      <button type="button" className="close-btn" onClick={handleClose}>
        Close
      </button>
      <button type="button" className="update-btn" onClick={handleUpdate}>
        Update
      </button>
    </ButtonsSectionWrapper>
  );
};

export default ButtonsGroup;
