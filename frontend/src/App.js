import logo from './logo.svg';
import './App.css';
import React, {Component, useState, useEffect} from 'react';


function App() {
	const [roomCondition, setRoomCondition] = useState("");

	useEffect(() => {
		fetch('/api/v1/currentCondition')
			.then(response => response.json())
			.then(data => {
				setRoomCondition(data);
			});
	}, [])
	return (
		<div className="App">
			<header className="App-header">
				<img src={logo} className="App-logo" alt="logo"/>
				<h1>Hi there!</h1>
				<h3>Here is our current room condition</h3>
				<div class="conditionBlock">
					<div class="conditionRow">
						<div class="conditionLabel">Air quality:</div>
						<div class="conditionValue">{roomCondition.airQualityPpm} PPM</div>
					</div>
					<div class="conditionRow">
						<div class="conditionLabel">Temperature:</div>
						<div class="conditionValue">{roomCondition.temperature} °C
						</div>
					</div>
					<div className="conditionRow">
						<div class="conditionLabel">Humidity:</div>
						<div class="conditionValue">{roomCondition.humidity} %
						</div>
					</div>

				</div>

			</header>
			<p className="App-intro">
				To get started, edit <code>src/App.js</code> and save to reload.
			</p>
		</div>
	)
}

export default App;
