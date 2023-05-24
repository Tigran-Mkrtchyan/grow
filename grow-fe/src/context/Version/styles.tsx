import styled from 'styled-components';

export const UpdateSectionWrapper = styled.div`
  margin-left: 20px;
  position: absolute;
  bottom: 90px;
  width: 100%;
`;
export const ButtonsSectionWrapper = styled.div`
  //width: 100%;
  margin-top: 24px;
  display: flex;
  justify-content: space-between;
  .close-btn {
    width: 45%;
    color: black !important;
    background: transparent !important;
    :hover {
      background: unset !important;
    }
  }
  .update-btn {
    width: 45%;
  }
`;
