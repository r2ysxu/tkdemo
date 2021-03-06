import React from 'react';
import ReactDOM from 'react-dom';
import {
	  BrowserRouter as Router,
	  Switch,
	  Route
	} from "react-router-dom";
import Home from './page/Home';


function App() {
	return(
  		<Router>
			<Switch>
				<Route path="/" component={Home} />
			</Switch>
  		</Router>
	);
}

ReactDOM.render(
    <App />,
  document.getElementById("root")
);