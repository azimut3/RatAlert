import './App.css';
import './styles/HomeConditionChart.scss';
import './styles/CurrentCondition.scss';
import React, {Component, useState, useEffect} from 'react';
import {
	BrowserRouter as Router,
	Switch,
	Route,
	Link
} from "react-router-dom";
import HomePage from "./pages/HomePage";
import HomeCharts from "./pages/graphs/HomeConditionCharts";
import About from "./pages/About";
import AppMenu from "./components/AppMenu";
import {createMuiTheme} from "@material-ui/core";
import {ThemeProvider} from "@material-ui/styles";
import WeatherWidget from "./components/WeatherWidjet";
import DrugsReview from "./pages/DrugsReview";

function App() {
	const theme = createMuiTheme({
		palette: {
			type: "dark"
		}
	});

	return (
		<Router>
			<div className="body-container">
				<ThemeProvider theme={theme}>
					<AppMenu/>
					<div className="app-container container">
						<div className="content">
							<Switch>
								<Route exact path="/">
									<HomePage/>
								</Route>
								<Route path="/room-history">
									<HomeCharts/>
								</Route>
								<Route path="/about">
									<About/>
								</Route>
								<Route path="/drugs">
									<DrugsReview/>
								</Route>
							</Switch>
						</div>
						<div className="right-sidebar">
							<WeatherWidget></WeatherWidget>
						</div>
					</div>
				</ThemeProvider>

			</div>
		</Router>
	);
}

export default App;
