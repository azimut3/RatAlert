import './App.css';
import React, {Component, useState, useEffect} from 'react';
import {
	BrowserRouter as Router,
	Switch,
	Route,
	Link
} from "react-router-dom";
import CurrentCondition from "./pages/CurrentConditions";
import HomeCharts from "./pages/graphs/HomeConditionCharts";
import About from "./pages/About";


function App() {

	return (
		<Router>
			<div>
				<ul>
					<li>
						<Link to="/">Home</Link>
					</li>
					<li>
						<Link to="/room-history">Room history charts</Link>
					</li>
					<li>
						<Link to="/about">About</Link>
					</li>
				</ul>

				<Switch>
					<Route exact path="/">
						<CurrentCondition />
					</Route>
					<Route path="/room-history">
						<HomeCharts />
					</Route>
					<Route path="/about">
						<About />
					</Route>
				</Switch>
			</div>
		</Router>
	);
}

export default App;
