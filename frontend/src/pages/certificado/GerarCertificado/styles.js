import styled from 'styled-components';
import { Segment, Container as SContainer } from 'semantic-ui-react';

export const Container = styled(SContainer)`
  min-height: 100vh;
`;

export const Card = styled(Segment)`
  div.importar {
    display: flex;
    flex-direction: row;
    justify-content: space-between;

    .input {
      flex: 1;
      min-width: 30%;
      border: 1px solid #333;
      padding: 4px;
      border-radius: 4px;
      margin-right: 16px;
    }
  }

  div.gerar {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;
