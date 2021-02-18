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
import AppMenu from "./components/AppMenu";

function App() {

	return (
		<Router>
			<div>
				<AppMenu />

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
				{/*<p className="App-intro">
					To get started, edit <code>src/App.js</code> and save to reload.
				</p>*/}
			</div>
		</Router>
	);
}

export default App;
