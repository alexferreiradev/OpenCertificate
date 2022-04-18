import React from 'react';
import { Button } from 'semantic-ui-react';
import { useHistory } from 'react-router';

import { Container, FullViewHeight, Body, Community } from './styles';

import strings from './strings';

function Home() {
  const history = useHistory();

  function createEventoHandler() {
    console.info('create click');
    history.push('/create');
  }
  function validateCertificadoHandler() {
    console.info('validate click');
    history.push('/validate');
  }

  return (
    <Container>
      <FullViewHeight>
        <header id="validate">
          <span className="logotype">{strings.title}</span>
          <span className="contributor">
            {strings.contributorCall}
            <a href="https://github.com.br/alexferreira/open-certificate">
              {strings.contributorGit}
            </a>
          </span>
        </header>
        <Body>
          <article>
            <Button.Group size="massive">
              <Button onClick={() => validateCertificadoHandler} primary>
                {strings.button.validate}
              </Button>
              <Button.Or text="Ou" />
              <Button onClick={() => createEventoHandler} secondary>
                {strings.button.create}
              </Button>
            </Button.Group>
          </article>
        </Body>
      </FullViewHeight>
      <FullViewHeight>
        <Community>
          <h2 id="comunidades">Comunidades utilizando:</h2>
          <ol>
            <li>
              <div>
                <img
                  alt="Logo do GOJava"
                  src="https://m-tv.imgix.net/7fee040fd3a8849181a175e7cfc497bde5f51cf53aa2696d753cdffaf326f852.png"
                />
                <span>Go-JAVA</span>
              </div>
            </li>
            <li>
              <div>
                <img alt="Logo do OC" src="#" />
                <span>Começe a utilizar</span>
              </div>
            </li>
          </ol>
        </Community>
      </FullViewHeight>
    </Container>
  );
}

export default Home;
