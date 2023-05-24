import { Colors, FontSizes, FontWeights, Screens } from 'core/CssVariables';
import { createGlobalStyle, ThemeProps } from 'styled-components';
import { generateSpaceClassNames } from '../helpers/utils';
import { TThemeProps } from '../theme/theme';

const spaces = [0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40];

const GlobalStyles = createGlobalStyle`
  /*spacing*/
  input {
    &[type="number"]::-webkit-outer-spin-button,
    &[type="number"]::-webkit-inner-spin-button {
      display: none;
      background: yellow;
      margin: 0;
    }
    
    &[type=number] {
      -moz-appearance: textfield;
    }
  }
  
  ${() => generateSpaceClassNames(spaces)}
  .text-left {
    text-align: left;
  }

  .text-right {
    text-align: right;
  }

  .text-center {
    text-align: center;
  }

  .h-100 {
    height: 100%;
  }

  /*select*/
  .ant-select-item-option-selected {
    &:not(.ant-select-item-option-disabled) {
      font-weight: 400;
    }
  }

  /*timepicker*/
  .ant-picker-ranges {
    .ant-picker-ok {
      .ant-btn {
        height: 24px;
        padding: 0 7px;
      }
    }
  }

  .ant-picker-time-panel-column {
    &:after {
      height: auto;
    }
  }

  .pointer {
    cursor: pointer;
  }

  .customScroll {
  }

  ::-webkit-scrollbar {
    width: 4px;
    height: 6px;
  }

  ::-webkit-scrollbar-track {
    border-radius: 8px;
    background: ${(props: ThemeProps<TThemeProps>) =>
      props.theme.scrollbar.track};
  }

  ::-webkit-scrollbar-thumb {
    background: ${(props: ThemeProps<TThemeProps>) =>
      props.theme.scrollbar.thumb};
    border-radius: 8px;
  }

  label {
    color: ${({ theme }) => theme.secondaryColor};
    font-size: ${FontSizes.FontXS}px;
    font-weight: ${FontWeights.Light};
  }

  .secondary-text {
    color: ${({ theme }) => theme.secondaryColor} !important;

    .ant-typography {
      color: ${({ theme }) => theme.secondaryColor} !important;
    }
  }

  .secondary-icon {
    path {
      fill: ${({ theme }) => theme.secondaryColor} !important;
    }

  }
`;

export default GlobalStyles;

const DropDownGlobalStyle = createGlobalStyle`
  .ant-select-dropdown, .ant-table-filter-dropdown, .ant-dropdown-menu {
    ::-webkit-scrollbar {
      width: 4px;
      height: 6px;
    }

    ::-webkit-scrollbar-track {
      border-radius: 8px;
      background: ${(props: ThemeProps<TThemeProps>) =>
        props.theme.scrollbar.track};
    }

    ::-webkit-scrollbar-thumb {
      background: ${(props: ThemeProps<TThemeProps>) =>
        props.theme.scrollbar.thumb};
      border-radius: 8px;
    }

    max-height: 250px;
    overflow: auto;
    background-color: ${({ theme }: ThemeProps<TThemeProps>) =>
      theme.dropdown.backgroundColor};
    border: 1px solid ${({ theme }: ThemeProps<TThemeProps>) =>
      theme.dropdown.borderColor};
    box-shadow: 0 4px 12px ${({ theme }: ThemeProps<TThemeProps>) =>
      theme.dropdown.boxShadowColor};

    border-radius: 8px;

    .ant-select-item-option, .ant-dropdown-menu-item,
    .ant-select-item-option-active:not(.ant-select-item-option-disabled) {
      background-color: transparent;

      &:hover {
        background-color: ${() => Colors.BackgroundColor};

        .ant-select-item-option-content {
          color: ${Colors.TextColorPrimary};
        }
      }
    }

    & .ant-select-item.ant-select-item-option-selected, .ant-select-item.ant-dropdown-menu-item-selected {
      background-color: ${({ theme }: ThemeProps<TThemeProps>) =>
        theme.dropdown.selectedItemColor};
      
      &:hover {
      background-color: ${({ theme }: ThemeProps<TThemeProps>) =>
        theme.dropdown.selectedItemHoverColor};
      }
      
      & .ant-select-item-option-state {
        display: none;
      }
    }

    .ant-select-item-option-content, .ant-dropdown-menu-item {
      color: ${Colors.TextColorPrimary};

      .ant-dropdown-menu-title-content {
        > span {
          margin-left: 8px;
        }
      }
    }

    .ant-table-filter-dropdown-btns {
      border: none;
    }

    .ant-empty {
      .ant-empty-description {
        color: ${({ theme }) => theme.color};
      }
    }

    .menu-item-ticket-filter {
      span {
        width: 200px;
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
    }
  }

  .ant-tooltip {
    .ant-tooltip-content {
      .ant-tooltip-inner {
        background-color: ${({ theme }) => theme.tooltip.backgroundColor};
        color: ${({ theme }) => theme.tooltip.color};
        border-radius: 8px;
        font-style: normal;
        font-weight: 300;
        font-size: 12px;
      }
    }
  }

  .custom-tooltip.ant-tooltip {
    .ant-tooltip-content {
      .ant-tooltip-inner {
        background-color: ${({ theme }) =>
          theme.timeEntryTooltip.backgroundColor};

      }
    }

    .ant-tooltip-arrow-content {
      width: 8px;
      height: 8px;
      background-color: ${({ theme }) =>
        theme.timeEntryTooltip.backgroundColor};
    }
  }

  .ant-dropdown {
    .ant-table-filter-dropdown {
      background: ${({ theme }) => theme.table.filterBackgroundColor};
      box-shadow: 0 0 7px rgba(0, 0, 0, 0.3);

      .ant-table-filter-dropdown-search {
        border: none;

        .ant-table-filter-dropdown-search-input, .ant-input, .ant-input-prefix, .anticon {
          background-color: ${({ theme }) => theme.input.backgroundColor};
          color: ${({ theme }) => theme.input.textColorPrimary};
          border-color: ${({ theme }) => theme.input.borderColor};
        }
      }

      .ant-table-filter-dropdown-btns {
        display: flex;
        justify-content: flex-end;
        align-items: center;

        .ant-btn-primary {
          width: 59px;
          height: 32px;
          background-color: ${Colors.InfoColor};
          border-color: ${Colors.InfoColor};
        }

        .ant-btn-link {
          color: ${Colors.PrimaryColor};
        }

      }

      .ant-dropdown-menu {
        background: ${({ theme }) => theme.table.filterBackgroundColor};

        .ant-dropdown-menu-item {
          .ant-dropdown-menu-title-content {
            color: ${({ theme }) => theme.table.checkboxLabelColor};

            .ant-checkbox-wrapper {
              .ant-checkbox {
                .ant-checkbox-inner {
                  border-radius: 4px;
                  border: 1px solid ${({ theme }) =>
                    theme.table.checkboxBorderColor};
                  background-color: transparent;

                  &::after {
                    border-width: 3px;
                  }
                }

                &.ant-checkbox-indeterminate {
                  .ant-checkbox-inner {
                    &::after {
                      width: 8px;
                      height: 3px;
                      background-color: ${({ theme }) =>
                        theme.table.backgroundColor};
                    }
                  }
                }

                &.ant-checkbox-checked {
                  &::after {
                    border: none;
                    border-color: ${({ theme }) => theme.table.backgroundColor};
                  }

                  .ant-checkbox-inner {
                    &::after {
                      border-color: ${({ theme }) =>
                        theme.table.backgroundColor};
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  .ant-picker-dropdown {
    .ant-picker-range-arrow {
      &::after {
        border-top-color: ${({ theme }) => theme.dropdown.backgroundColor};
        border-right-color: ${({ theme }) => theme.dropdown.backgroundColor};
      }
    }

    .ant-picker-panel-container {
      background-color: ${({ theme }: ThemeProps<TThemeProps>) =>
        theme.dropdown.backgroundColor};
      border: 1px solid ${({ theme }: ThemeProps<TThemeProps>) =>
        theme.dropdown.borderColor};
      box-shadow: 0 4px 12px ${({ theme }: ThemeProps<TThemeProps>) =>
        theme.dropdown.boxShadowColor};

      .ant-picker-panel {
        border-width: 0;

        .ant-picker-footer {
          border: none;
        }

        .ant-picker-time-panel {
          border: none;

          .ant-picker-content {
            .ant-picker-time-panel-column {
              border: none;

              ::-webkit-scrollbar {
                width: 4px;
                height: 6px;
              }

              ::-webkit-scrollbar-track {
                border-radius: 8px;
                background: ${(props: ThemeProps<TThemeProps>) =>
                  props.theme.scrollbar.track};
              }

              ::-webkit-scrollbar-thumb {
                background: ${(props: ThemeProps<TThemeProps>) =>
                  props.theme.scrollbar.thumb};
                border-radius: 8px;
              }

              .ant-picker-time-panel-cell {
                .ant-picker-time-panel-cell-inner {
                  color: ${({ theme }) => theme.color};

                  &:hover {
                    background-color: ${Colors.White};
                  }
                }
              }

              .ant-picker-time-panel-cell-selected {
                .ant-picker-time-panel-cell-inner {
                  color: ${Colors.PrimaryColor};
                  font-weight: ${FontWeights.SemiBold};
                  background-color: ${Colors.White};
                }
              }
            }
          }
        }

      }

      .ant-picker-cell {
        color: ${Colors.LightGrey};
      }

      .ant-picker-cell-today {
        .ant-picker-cell-inner {
          &::before {
            border-color: ${Colors.PrimaryColor};
          }
        }
      }

      .ant-picker-cell-disabled::before {
        background-color: ${({ theme }) =>
          theme.datePicker.disabledBackgroundColor};
      }

      .ant-picker-cell-in-view {
        color: ${({ theme }) => theme.color};

        .ant-picker-cell-inner {
          &:hover {
            background-color: ${Colors.PrimaryColor};
            color: ${Colors.White};
          }
        }
      }

      .ant-picker-header {
        color: ${({ theme }) => theme.color};
        border: none;

        button {
          color: ${Colors.LightGrey};
        }
      }

      .ant-picker-body {
        table {
          thead {
            tr > th {
              color: ${({ theme }) => theme.color};
            }
          }
        }
      }

      @media (max-width: ${Screens.ScreensSM}) {
        .ant-picker-panels {
          flex-direction: column;
        }
      }

    }

  }

  .profile-menu {
    .ant-dropdown-menu {
      background-color: ${({ theme }) => theme.secondaryBackgroundColor};

      .ant-typography {
        color: ${({ theme }) => theme.profileMenu.color};

        svg {
          path {
            fill: ${({ theme }) => theme.profileMenu.color};
          }
        }
      }

      .ant-dropdown-menu-item-selected {
        background-color: ${Colors.White};

        .ant-typography {
          color: ${Colors.SecondaryColor};

          svg {
            path {
              fill: ${Colors.SecondaryColor} !important;
            }
          }
        }
      }
    }
  }

  .ant-notification-notice {
    border-radius: 16px;

    .ant-notification-notice-content {
      .ant-notification-notice-message {
        color: ${Colors.White};
      }

      .ant-notification-notice-description {
        color: ${Colors.White};
        font-weight: ${FontWeights.Light};
      }
    }

    .ant-notification-notice-close {
      .ant-notification-notice-close-x {
        fill: ${Colors.White};
      }
    }
  }
`;
export { DropDownGlobalStyle };
