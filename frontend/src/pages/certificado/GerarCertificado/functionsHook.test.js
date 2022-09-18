import fuctionsHook from "./functionsHook";
import api from '../../../services/certificate';

jest.mock("../../../services/certificate");

console.error = jest.fn();
console.info = jest.fn();
const {importCsv} = fuctionsHook();

test('should hook return a import csv function', async () => {
  const hooks = fuctionsHook();

  expect(hooks.importCsv).toBeDefined();
});

test('should importCsv return valid zip file when called', async () => {
  const hooks = fuctionsHook();
  const fakeResponse = {arquivoZIP: 'fakeZip'};
  const fakeFile = "fakeFile";

  api.importCertificate.mockImplementation(() => fakeResponse);

  const res = await importCsv(fakeFile);

  expect(res.arquivoZIP).toBeDefined();
  expect(console.info).toBeCalledTimes(2);
  expect(console.info.mock.calls[0][0]).toEqual("Enviando CSV para importar e gerar ZIP com certificados");
  expect(console.info.mock.calls[1][0]).toEqual("CSV importado e ZIP foi retornado");
  expect(api.importCertificate).toBeCalledWith({file: fakeFile});
});

test('should importCsv show error when api throws error', async () => {
  const error = new Error("Erro desconhecido da API");
  api.importCertificate.mockImplementation(() => {throw error;});

  const res = await importCsv("fakeFile");

  expect(res).toBeNull();
  expect(console.error).toBeCalledWith("Erro ao tentar importar CSV: " + error.message, error)
});
