module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: [
    'plugin:import/typescript',
    'plugin:react/recommended',
    'airbnb-typescript-prettier',
  ],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaFeatures: {
      jsx: true,
    },
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: ['react', '@typescript-eslint'],
  rules: {
    'react/react-in-jsx-scope': 'off',
    'react/function-component-definition': 'off',
    'react/no-unstable-nested-components': 'warn',
    'jsx-a11y/label-has-associated-control': 'off',
    'react/jsx-no-useless-fragment': [1, { allowExpressions: true }],
    'no-param-reassign': 0,
    'import/no-named-as-default': 'off',
    'import/no-extraneous-dependencies': 0,
    'react/jsx-filename-extension': [1, { extensions: ['.tsx', '.ts'] }],
    '@typescript-eslint/no-explicit-any': 2,
    'react-hooks/exhaustive-deps': 'off',
    'react/jsx-props-no-spreading': 0,
    '@typescript-eslint/camelcase': 0,
    'max-classes-per-file': ['error', 10],
    'react/require-default-props': 'off',
    'import/prefer-default-export': 'warn',
    '@typescript-eslint/ban-ts-comment': 'warn',
    'prettier/prettier': [
      'warn',
      {
        endOfLine: 'auto',
      },
    ],
  },
  overrides: [
    {
      files: ['**/*.test.ts', '**/*.test.tsx'],
      env: {
        jest: true,
      },
    },
  ],
  settings: {
    'import/resolver': {
      node: {
        paths: ['./src'],
        extensions: ['.js', '.jsx', '.ts', '.tsx'],
        moduleDirectory: ['node_modules', 'src/'],
      },
    },
  },
};
