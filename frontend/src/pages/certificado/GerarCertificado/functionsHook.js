import api from '../../../services/certificate';

function fuctionsHook() {
  async function importCsv(file) {
    console.info('Enviando CSV para importar e gerar ZIP com certificados');
    try {
      const res = await api.importCertificate({ file });
      console.info('CSV importado e ZIP foi retornado');
      return res;
    } catch (error) {
      console.error(`Erro ao tentar importar CSV: ${error.message}`, error);
      return null;
    }
  }

  return { importCsv };
}

export default fuctionsHook;
