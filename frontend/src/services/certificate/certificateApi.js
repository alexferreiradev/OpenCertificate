import api from '../api';

async function importCertificate({ file, listener }) {
  const multipart = {
    csv: file,
    entityName: 'GOJAVA',
  };

  try {
    const res = await api.post('/certificados', multipart);
    listener(res.data);
  } catch (error) {
    console.error('Erro ao tentar importar certificados', error);
  }
}

export default {
  importCertificate,
};
