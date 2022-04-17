import React from 'react';
import { ToastContainer } from 'react-toastify';
import { Container } from 'semantic-ui-react';
import Routes from '../Routes/Routes';
import Footer from '../components/Footer';

function App() {
  return (
    <Container style={{ height: '100%' }}>
      <Routes />
      <Footer />
      <ToastContainer />
    </Container>
  );
}

export default App;
