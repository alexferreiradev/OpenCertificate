import React from 'react';
import { Header, Input, Button } from 'semantic-ui-react';

import { Container, Card } from './styles';
import fuctionsHook from './functionsHook';

function GerarCertificado() {
  const { importCsv } = fuctionsHook();

  async function onClickGenerate() {
    console.info('Seleção em botão de importar');
    const res = await importCsv(
      new File(
        [
          'Airon;Gonçalves;12312312390;4;GoJava;Java Jug Tour 2021;desenvolvimento ágil, carreira de TI e micro-profile;01/01/21;01/01/21',
          'Alex;Rabelo Ferreira;12312312390;4;GoJava;Java Jug Tour 2021;desenvolvimento ágil, carreira de TI e micro-profile;01/01/21;01/01/21',
        ],
        { type: 'text/plain' },
      ),
    );
    if (res == null) {
      console.error('Erro ao tentar importar CSV');
    } else {
      console.info(`ZIP retornado: ${res.arquivoZIP}`);
    }
  }

  return (
    <Container>
      <Card>
        <Header>Gerar certificados</Header>
        <div className="importar">
          <Input name="csv" disabled type="file">
            Arquivo csv com dados do evento...
          </Input>
          <Button>Abrir arquivo</Button>
        </div>
        <div className="gerar">
          <Button onClick={() => onClickGenerate()}>
            Gerar certificados e download de zip
          </Button>
        </div>
      </Card>
    </Container>
  );
}

export default GerarCertificado;
