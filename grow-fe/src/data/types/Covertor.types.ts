// FD = Front_end data
// BD = Back_end data

export type TAuthConverter<FD, BD, FD2, BD2> = {
    toDb: (data: FD) => BD;
    fromDb: (data: BD2) => FD2;
  };
  
  export type TConvertor<FD, BD> = {
    toDb: (data: FD) => BD;
    fromDb: (data: BD) => FD;
  };
  
  export type TFetchConverter<FD, BD> = {
    fromDb: (data: BD) => FD;
  };
  
  export type TCreateConverter<FD, BD> = {
    toDb: (data: FD) => BD;
  };
  
  export type TDeleteConverter<FD, BD> = {
    toDb: (data: FD) => BD;
  };
  