import React from 'react';

import { Container } from './styles';

function Footer() {
  return (
    <Container>
      <h5>
        Hospedado com ❤️ pelo <a href="https://www.heroku.com/about">Heroku</a>
      </h5>
      <h5>
        Mantido com ❤️ pela comunidade{' '}
        <a href="https://www.gojava.dev">GO-JAVA</a>
      </h5>
    </Container>
  );
}

export default Footer;
