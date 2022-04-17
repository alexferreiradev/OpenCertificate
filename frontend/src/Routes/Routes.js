import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';

import React from 'react';
import paths from './paths';
import Home from '../pages/Home';
import GerarCertificado from '../pages/certificado/GerarCertificado';

function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path={paths.home} component={Home} />
        <Route path={paths.gerar} component={GerarCertificado} />
        <Route path="*">
          <Redirect to={paths.home} />
        </Route>
      </Switch>
    </BrowserRouter>
  );
}

export default Routes;
