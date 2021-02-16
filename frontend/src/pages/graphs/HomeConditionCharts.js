import React, {Component, useState, useEffect} from 'react';
import {Line} from 'react-chartjs-2';
import moment from "moment";

function HomeCharts() {
	const [hourlyData, setHourlyData] = useState("");
	const [statTimeStamps, setStatTimeStamps] = useState("");
	const [temperature, setTemperature] = useState("");
	const [humidity, setHumidity] = useState("");
	const [airQuality, setAirQuality] = useState("");

	useEffect(() => {
		fetch('/api/v1/hourlyStatsData')
			.then(response => response.json())
			.then(data => {
				setHourlyData(data);
				let statTimeStampsList = [];
				let temperatureList = [];
				let humidityList = [];
				let airQualityList = [];
				data.map(stat => {
					let datetime = moment(stat.creationDate)
					statTimeStampsList.push(datetime.format('DD/MM HH:mm'));
					temperatureList.push(stat.roomTemperature);
					humidityList.push(stat.roomHumidity);
					airQualityList.push(stat.roomAirQualityPpmValue);
				})
				console.log(statTimeStamps);

				setStatTimeStamps(statTimeStampsList);
				setTemperature(temperatureList);
				setHumidity(humidityList);
				setAirQuality(airQualityList);
			});
	}, [])

	const data = {
		labels: statTimeStamps,
		datasets: [
			{
				label: 'Temperature',
				fill: false,
				lineTension: 0.1,
				backgroundColor: 'rgb(192,180,75)',
				borderColor: 'rgb(192,145,75)',
				borderCapStyle: 'butt',
				borderDash: [],
				borderDashOffset: 0.0,
				borderJoinStyle: 'miter',
				pointBorderColor: 'rgb(192,180,75)',
				pointBackgroundColor: '#fff',
				pointBorderWidth: 1,
				pointHoverRadius: 5,
				pointHoverBackgroundColor: 'rgb(221,177,67)',
				pointHoverBorderColor: 'rgba(220,220,220,1)',
				pointHoverBorderWidth: 2,
				pointRadius: 1,
				pointHitRadius: 10,
				data: temperature
			},
			{
				label: 'Humidity',
				fill: false,
				lineTension: 0.1,
				backgroundColor: 'rgb(75,108,192)',
				borderColor: 'rgb(75,132,192)',
				borderCapStyle: 'butt',
				borderDash: [],
				borderDashOffset: 0.0,
				borderJoinStyle: 'miter',
				pointBorderColor: 'rgb(65,115,200)',
				pointBackgroundColor: '#fff',
				pointBorderWidth: 1,
				pointHoverRadius: 5,
				pointHoverBackgroundColor: 'rgb(8,117,234)',
				pointHoverBorderColor: 'rgba(220,220,220,1)',
				pointHoverBorderWidth: 2,
				pointRadius: 1,
				pointHitRadius: 10,
				data: humidity
			},
			{
				label: 'Air Quality',
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
				data: airQuality
			}
		]
	};

	const options = {
		type: 'line',
		options: {
			responsive: true,
			legend: {
				position: 'right'
			},
			tooltips: {
				mode: 'index',
				position: "nearest"
			},
			animation: {
				animateScale: true,
				animateRotate: true
			}
		}
	}

	return (
		<div className="App-header">
			<h1>Hourly data charts:</h1>
			<Line data={data} options={options}/>
		</div>
	)
}

export default HomeCharts;