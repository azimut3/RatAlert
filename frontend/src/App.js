import './App.css';
import React, {Component, useState, useEffect} from 'react';
import {
	BrowserRouter as Router,
	Switch,
	Route,
	Link
} from "react-router-dom";
import CurrentCondition from "./pages/CurrentConditions";


function App() {

	return (
		<Router>
			<div>
				<ul>
					<li>
						<Link to="/">Home</Link>
					</li>
					<li>
						<Link to="/about">About</Link>
					</li>
					<li>
						<Link to="/topics">Topics</Link>
					</li>
				</ul>

				<Switch>
					{/*<Route path="/about">*/}
					{/*	<About />*/}
					{/*</Route>*/}
					{/*<Route path="/topics">*/}
					{/*	<Topics />*/}
					{/*</Route>*/}
					<Route path="/">
						<CurrentCondition />
					</Route>
				</Switch>
			</div>
		</Router>
	);
}

export default App;
