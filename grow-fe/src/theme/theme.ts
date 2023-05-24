import { Colors } from 'core/CssVariables';

export type TThemeProps = {
  timesheet: {
    borderColor: string;
    backgroundColor: string;
  };
  avatar: {
    default: {
      color: string;
      backgroundColor: string;
    };
  };
  card: {
    default: {
      backgroundColor: string;
      border: string;
    };
    secondary: {
      backgroundColor: string;
    };
    primary: {
      backgroundColor: string;
    };
  };
  paragraph: {
    color: string;
  };
  editableText: {
    spinner: string;
    color: string;
  };
  scrollbar: {
    track: string;
    thumb: string;
  };
  color: string;
  text: {
    color: string;
  };
  secondaryColor: string;
  backButton: string;
  backgroundColor: string;
  secondaryBackgroundColor: string;
  radio: {
    label: string;
    backgroundColor: string;
  };
  input: {
    backgroundColor: string;
    placeholder: string;
    textColorPrimary: string;
    textColorDisabled: string;
    borderColor: string;
  };
  sidebar: {
    backgroundColor: string;
    menuBackgroundColor: string;
    color: string;
  };
  switch: {
    handle: string;
    themeSwitch: {
      backgroundColor: string;
      lightModeColor: string;
      darkModeColor: string;
    };
  };
  table: {
    backgroundColor: string;
    borderColor: string;
    hoverTextColor: string;
    sortersColor: string;
    pagesizeBackgroundColor: string;
    hoverRowBackgroundColor: string;
    checkboxBorderColor: string;
    checkboxLabelColor: string;
    filterBackgroundColor: string;
    textColor: string;
  };
  datePicker: {
    backgroundColor: string;
    borderColor: string;
    disabledBackgroundColor: string;
  };
  timeEntryTooltip: {
    backgroundColor: string;
  };
  timeEntryDetails: {
    item: {
      color: string;
    };
  };
  modal: {
    backgroundColor: string;
  };
  dropdown: {
    backgroundColor: string;
    selectedItemColor: string;
    selectedItemHoverColor: string;
    borderColor: string;
    boxShadowColor: string;
  };
  tooltip: {
    backgroundColor: string;
    color: string;
  };
  icon: {
    color: string;
  };
  normalWrapper: {
    backgroundColor: string;
  };
  noResult: {
    color: string;
  };
  select: {
    color: string;
  };
  selectableText: {
    backgroundColor: string;
  };
  profileMenu: {
    color: string;
  };
  weekDays: {
    backgroundColor: string;
  };
  uploadButton: {
    backgroundColor: string;
    borderColor: string;
    iconColor: string;
  };
};

export const DarkTheme: TThemeProps = {
  timesheet: {
    borderColor: Colors.DarkBorderColor,
    backgroundColor: Colors.Dark,
  },
  avatar: {
    default: {
      color: Colors.Grey,
      backgroundColor: Colors.DarkGrey,
    },
  },
  card: {
    secondary: {
      backgroundColor: Colors.Dark,
    },
    primary: {
      backgroundColor: Colors.DarkGrey,
    },
    default: {
      backgroundColor: Colors.Dark,
      border: Colors.Dark,
    },
  },
  paragraph: {
    color: Colors.TextColorPrimary,
  },
  text: {
    color: Colors.TextColorPrimary,
  },
  editableText: {
    color: Colors.Dark,
    spinner: Colors.White,
  },
  scrollbar: {
    track: Colors.TextColorPrimary,
    thumb: Colors.Dark,
  },
  color: Colors.White,
  secondaryColor: Colors.LightGrey,
  backButton: Colors.Grey,
  switch: {
    handle: Colors.Dark,
    themeSwitch: {
      backgroundColor: Colors.PrimaryColor,
      lightModeColor: Colors.White,
      darkModeColor: Colors.PrimaryColor,
    },
  },
  backgroundColor: Colors.DarkGrey,
  secondaryBackgroundColor: Colors.Dark,
  radio: {
    label: Colors.LightGrey,
    backgroundColor: Colors.DarkGrey,
  },
  input: {
    backgroundColor: Colors.DarkBackground,
    placeholder: Colors.Grey,
    textColorPrimary: Colors.LightGrey,
    textColorDisabled: Colors.LightGrey,
    borderColor: Colors.DarkBorderColor,
  },
  sidebar: {
    backgroundColor: Colors.DarkGrey,
    menuBackgroundColor: Colors.Dark,
    color: Colors.White,
  },
  table: {
    backgroundColor: Colors.DarkBackground,
    borderColor: Colors.DarkBorderColor,
    hoverTextColor: Colors.Dark,
    sortersColor: Colors.PrimaryColor,
    pagesizeBackgroundColor: Colors.Dark,
    hoverRowBackgroundColor: Colors.RowHover,
    checkboxBorderColor: Colors.LightGrey,
    checkboxLabelColor: Colors.LightGrey,
    filterBackgroundColor: Colors.DarkGrey,
    textColor: Colors.White,
  },
  datePicker: {
    backgroundColor: Colors.DarkBackground,
    borderColor: Colors.DarkBorderColor,
    disabledBackgroundColor: 'rgba(255, 255, 255, 0.06)',
  },
  timeEntryTooltip: {
    backgroundColor: Colors.Dark,
  },
  timeEntryDetails: {
    item: {
      color: Colors.White,
    },
  },
  modal: {
    backgroundColor: Colors.Dark,
  },
  dropdown: {
    backgroundColor: Colors.Dark,
    selectedItemColor: Colors.PrimaryColor,
    selectedItemHoverColor: Colors.PrimaryColorHover,
    borderColor: Colors.DarkBorderColor,
    boxShadowColor: 'rgba(0, 0, 0, 0.3)',
  },
  tooltip: {
    backgroundColor: Colors.LightBackground,
    color: Colors.Grey,
  },
  icon: {
    color: Colors.DarkGrey,
  },
  normalWrapper: {
    backgroundColor: Colors.DarkBackground,
  },
  noResult: {
    color: Colors.LightGrey,
  },
  select: {
    color: Colors.LightGrey,
  },
  selectableText: {
    backgroundColor: Colors.DarkGrey,
  },
  profileMenu: {
    color: Colors.LightGrey,
  },
  weekDays: {
    backgroundColor: Colors.DarkBackground,
  },
  uploadButton: {
    backgroundColor: Colors.Dark,
    borderColor: Colors.DarkBorderColor,
    iconColor: Colors.Grey,
  },
};

export const LightTheme: TThemeProps = {
  timesheet: {
    borderColor: Colors.LightBorderColor,
    backgroundColor: Colors.LightBackground,
  },
  avatar: {
    default: {
      color: Colors.LightGrey,
      backgroundColor: Colors.LightInput,
    },
  },
  card: {
    secondary: {
      backgroundColor: Colors.LightBackground,
    },
    primary: {
      backgroundColor: Colors.White,
    },
    default: {
      backgroundColor: 'transparent',
      border: Colors.LightBorderColor,
    },
  },
  paragraph: {
    color: Colors.TextColorPrimary,
  },
  text: {
    color: Colors.TextColorPrimary,
  },
  editableText: {
    spinner: Colors.PrimaryColor,
    color: Colors.Dark,
  },
  scrollbar: {
    thumb: Colors.LightGrey,
    track: Colors.LightBorderColor,
  },
  color: Colors.Dark,
  secondaryColor: Colors.Grey,
  backButton: Colors.Dark,
  switch: {
    handle: Colors.White,
    themeSwitch: {
      backgroundColor: Colors.PrimaryColor,
      lightModeColor: Colors.PrimaryColor,
      darkModeColor: Colors.Dark,
    },
  },
  backgroundColor: Colors.White,
  secondaryBackgroundColor: Colors.LightBackground,
  radio: {
    label: Colors.DarkGrey,
    backgroundColor: Colors.White,
  },
  input: {
    backgroundColor: Colors.White,
    placeholder: Colors.TextColorPrimary,
    textColorPrimary: Colors.TextColorPrimary,
    textColorDisabled: Colors.SecondaryColor,
    borderColor: Colors.PrimaryColor,
  },
  sidebar: {
    backgroundColor: Colors.White,
    menuBackgroundColor: Colors.LightBackground,
    color: Colors.Dark,
  },
  table: {
    backgroundColor: Colors.White,
    borderColor: Colors.LightBorderColor,
    hoverTextColor: Colors.Dark,
    sortersColor: Colors.PrimaryColor,
    pagesizeBackgroundColor: Colors.LightBackground,
    hoverRowBackgroundColor: Colors.LightTableBackground,
    checkboxBorderColor: Colors.Grey,
    checkboxLabelColor: Colors.DarkBorderColor,
    filterBackgroundColor: Colors.White,
    textColor: Colors.TextColorPrimary,
  },
  datePicker: {
    backgroundColor: Colors.LightInput,
    borderColor: Colors.LightBorderColor,
    disabledBackgroundColor: 'rgba(0, 0, 0, 0.06)',
  },
  timeEntryTooltip: {
    backgroundColor: Colors.White,
  },
  timeEntryDetails: {
    item: {
      color: Colors.Grey,
    },
  },
  modal: {
    backgroundColor: Colors.White,
  },
  dropdown: {
    backgroundColor: Colors.White,
    selectedItemColor: Colors.PrimaryColor,
    selectedItemHoverColor: Colors.PrimaryColorHover,
    borderColor: Colors.LightBorderColor,
    boxShadowColor: 'rgba(81, 81, 81, 0.12)',
  },
  tooltip: {
    backgroundColor: Colors.DarkBorderColor,
    color: Colors.White,
  },
  icon: {
    color: Colors.LightGrey,
  },
  normalWrapper: {
    backgroundColor: Colors.LightBackground,
  },
  noResult: {
    color: Colors.Grey,
  },
  select: {
    color: Colors.TextColorPrimary,
  },
  selectableText: {
    backgroundColor: Colors.LightInput,
  },
  profileMenu: {
    color: Colors.Grey,
  },
  weekDays: {
    backgroundColor: Colors.LightBorderColor,
  },
  uploadButton: {
    backgroundColor: Colors.LightBackground,
    borderColor: Colors.LightBorderColor,
    iconColor: Colors.LightGrey,
  },
};
