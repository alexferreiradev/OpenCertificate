import api from '../api';

async function importCertificate({ file }) {
  try {
    const form = new FormData();
    form.append('csv', file);
    form.append('entityName', 'GOJAVA');
    const res = await api.post('/certificados', form, {
      headers: { 'Content-type': 'multipart/form-data' },
    });

    return res.data;
  } catch (error) {
    const msg = 'Erro no serviço ao tentar importar certificados';
    console.error(msg, error);
    throw new Error(msg, { cause: error });
  }
}

export default {
  importCertificate,
};
