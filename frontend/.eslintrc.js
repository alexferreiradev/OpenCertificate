module.exports = {
  env: {
    browser: true,
    es6: true,
  },
  extends: ['airbnb'],
  globals: {
    Atomics: 'readonly',
    SharedArrayBuffer: 'readonly',
  },
  parserOptions: {
    parser: 'babel-eslint',
    ecmaFeatures: {
      jsx: true,
    },
    ecmaVersion: 2018,
    sourceType: 'module',
    allowImportExportEverywhere: true,
  },
  plugins: ['react', 'prettier'],
  rules: {
    // <!-- somente em dev -->
    // 'no-unused-expressions': 'warn', // <!-- somente em dev -->
    // 'no-unused-vars': 'warn', // <!-- somente em dev -->
    // 'no-console': ['warn', { allow: ['error', 'warn', 'info'] }],

    'prettier/prettier': [
      'warn',
      {
        endOfLine: 'auto',
      },
    ],
    'no-console': ['error', { allow: ['error', 'warn', 'info'] }],
    'no-restricted-exports': 'off',
    'object-curly-newline': 'off',
    'comma-dangle': 'off',
    'import/no-named-as-default': 'off',
    'import/prefer-default-export': 'off',
    'import/no-named-as-default-member': 'off',
    'react/jsx-filename-extension': ['warn', { extensions: ['.jsx', '.js'] }],
    'react/jsx-indent': 'off',
    'react/jsx-one-expression-per-line': 'off',
  },
};
