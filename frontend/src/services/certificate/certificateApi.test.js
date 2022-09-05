import certificateApi from './certificateApi';
import api from '../api';

jest.mock('../api');
console.error = jest.fn();

test('should service return data when api return data', async () => {
  const res = {data: "test"};
  api.post.mockResolvedValue(res);

  const input = { file: 'file' };

  const data = await certificateApi.importCertificate(input);

  expect(api.post).toBeCalled();
  expect(data).toBe(res.data);
});

test('should service return error when api return error', async () => {
  const error = new Error("Erro desconhecido");
  api.post.mockImplementation(() => {
    throw error;
  });

  const input = { file: 'file'};
  try {
    await certificateApi.importCertificate(input)
  } catch (error) {
    expect(error.message).toBe("Erro ao tentar importar evento");
  }
  expect(console.error).toBeCalledWith("Erro ao tentar importar certificados", error);
});
