import React, {Component, useState, useEffect} from 'react';
import {Line} from 'react-chartjs-2';

function HomeCharts() {
	const [hourlyData, setHourlyData] = useState("");

	useEffect(() => {
		fetch('/api/v1/hourlyStatsData')
			.then(response => response.json())
			.then(data => {
				setHourlyData(data);
			});
	}, [])

	const data = {
		labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
		datasets: [
			{
				label: 'Dataset of Months',
				fill: false,
				lineTension: 0.1,
				backgroundColor: 'rgba(75,192,192,0.4)',
				borderColor: 'rgba(75,192,192,1)',
				borderCapStyle: 'butt',
				borderDash: [],
				borderDashOffset: 0.0,
				borderJoinStyle: 'miter',
				pointBorderColor: 'rgba(75,192,192,1)',
				pointBackgroundColor: '#fff',
				pointBorderWidth: 1,
				pointHoverRadius: 5,
				pointHoverBackgroundColor: 'rgba(75,192,192,1)',
				pointHoverBorderColor: 'rgba(220,220,220,1)',
				pointHoverBorderWidth: 2,
				pointRadius: 1,
				pointHitRadius: 10,
				data: [65, 59, 80, 81, 56, 55, 40]
			}
		]
	};

	return (
		<div className="App-header">
			<h1>Hourly data charts:</h1>
			<Line data={data}/>
		</div>
	)
}

export default HomeCharts;