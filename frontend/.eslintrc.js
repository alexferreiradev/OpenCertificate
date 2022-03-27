module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: ['airbnb', 'prettier'],
  globals: {
    Atomics: 'readonly',
    SharedArrayBuffer: 'readonly',
  },
  parserOptions: {
    ecmaFeatures: {
      jsx: true,
    },
    ecmaVersion: 12,
    sourceType: 'module',
  },
  plugins: ['prettier', 'react'],
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
    'import/prefer-default-export': 'off',
    'react/jsx-filename-extension': ['warn', { extensions: ['.jsx', '.js'] }],
  },
};
