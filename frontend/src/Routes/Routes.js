import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';

import React from 'react';
import paths from './paths';
import Home from '../pages/Home';

function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path={paths.home} component={Home} />
        <Route path="*">
          <Redirect to={paths.home} />
        </Route>
      </Switch>
    </BrowserRouter>
  );
}

export default Routes;
