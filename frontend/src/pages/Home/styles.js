import styled from 'styled-components';
import { Container as ContainerS } from 'semantic-ui-react';

export const Container = styled(ContainerS)`
  display: flex !important;
  flex-direction: column;
  align-items: stretch;

  height: 100%;

  header {
    display: flex;
    justify-content: space-between;
    min-height: 15rem;
    background-color: #444;
    align-items: center;
    padding: 1.5rem;

    span,
    a {
      color: #fff;
    }

    span.logotype {
      font-size: 2.6rem;
    }

    span.contributor {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-around;
      margin-right: 3rem;
      min-height: 5rem;
      font-weight: bold;
    }
  }
`;

export const FullViewHeight = styled.div`
  display: flex;
  flex-direction: column;

  min-height: 100vh;
`;

export const Body = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;

  & article {
    display: flex;
    flex: 1;
    justify-content: center;
    align-items: center;

    padding: 16px;
  }
`;

export const Community = styled.article`
  flex: 1;
  display: flex;
  flex-direction: column;

  background: #eee;
  padding: 8px;
  align-items: flex-start;
  justify-content: flex-start;

  h2 {
    align-self: flex-start;
    font-size: 1.5rem;
    color: #666;
  }

  ol {
    display: flex;
    flex-direction: row;
    list-style: none;
    justify-content: space-around;
    align-items: stretch;
    width: 100%;
    padding: 0;

    li {
      background: #fff;
      padding: 16px;
    }

    li img {
      min-height: 112px;
      max-height: 148px;

      border: 0.01px solid #eee transparent;
    }

    li span {
      margin-top: 16px;
      text-transform: uppercase;
      font-weight: bold;
      font-size: 1.5rem;
    }

    li div {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
  }
`;
