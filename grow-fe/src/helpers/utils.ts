export const generateSpaceClassNames = (array: number[]) => {
  let classNames = ``;
  array.forEach((n, i) => {
    classNames += `
            .mx-${i} {
                margin: 0 ${n}px !important;
            }
            .my-${i} {
                margin: ${n}px 0 !important;
            }
            .m-${i} {
                margin: ${n}px !important;
            }
            .mt-${i} {
                margin-top: ${n}px !important;
            }
            .mb-${i} {
                margin-bottom: ${n}px !important;
            }
            .ml-${i} {
                margin-left: ${n}px !important;
            }
            .mr-${i} {
                margin-right: ${n}px !important;
            }
            .pt-${i} {
                padding-top: ${n}px !important;
            }
            .pb-${i} {
                padding-bottom: ${n}px !important;
            }
            .pl-${i} {
                padding-left: ${n}px !important;
            }
            .pr-${i} {
                padding-right: ${n}px !important;
            }
            .px-${i} {
                padding: 0 ${n}px !important;
            }
            .py-${i} {
                padding: ${n}px 0 !important;
            }
            .p-${i} {
                padding: ${n}px !important;
            }
        `;
  });

  return classNames;
};
