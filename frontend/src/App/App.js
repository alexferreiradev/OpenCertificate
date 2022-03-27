import React from 'react';
import { ToastContainer } from 'react-toastify';
import { Container } from 'semantic-ui-react';
import Routes from '../Routes/Routes';

function App() {
  return (
    <Container style={{ height: '100%' }}>
      <Routes />
      <ToastContainer />
    </Container>
  );
}

export default App;
