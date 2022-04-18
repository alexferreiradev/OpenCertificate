import React from 'react';
import { Header, Input, Button } from 'semantic-ui-react';

import { Container, Card } from './styles';

function GerarCertificado() {
  return (
    <Container>
      <Card>
        <Header>Gerar certificados</Header>
        <div className="importar">
          <Input name="csv" disabled>
            Arquivo csv com dados do evento...
          </Input>
          <Button>Abrir arquivo</Button>
        </div>
        <div className="gerar">
          <Button>Gerar certificados e download de zip</Button>
        </div>
      </Card>
    </Container>
  );
}

export default GerarCertificado;
