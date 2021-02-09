import logo from './logo.svg';
import './App.css';
import React, {Component, useState, useEffect} from 'react';


function App() {
	const [message, setMessage] = useState("");

	useEffect(() => {
		fetch('/api/v1/esp')
			.then(response => response.text())
			.then(message => {
				setMessage(message);
			});
	}, [])
	return (
		<div className="App">
			<header className="App-header">
				<img src={logo} className="App-logo" alt="logo"/>
				<p>{message}</p>
			</header>
			<p className="App-intro">
				To get started, edit <code>src/App.js</code> and save to reload.
			</p>
		</div>
	)
}

export default App;
