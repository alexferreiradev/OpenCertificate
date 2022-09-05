import api from '../api';

async function importCertificate({ file }) {
  try {
    const form = new FormData();
    form.append('csv', file);
    form.append('entityName', 'GOJAVA');
    const res = await api.post('/certificados', {
      data: form,
      config: {
        headers: { 'content-type': 'application/x-www-form-urlencoded' },
      },
    });

    return res.data;
  } catch (error) {
    console.error('Erro ao tentar importar certificados', error);
    throw new Error('Erro ao tentar importar evento');
  }
}

export default {
  importCertificate,
};
