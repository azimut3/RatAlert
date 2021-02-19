import React, {Component, useState, useEffect} from 'react';
import {Line} from 'react-chartjs-2';
import moment from "moment";
import ChartHistoryPagePicklist from "../../components/ChartHistoryPagePicklist";

function HomeCharts() {
	const [hourlyData, setHourlyData] = useState("");
	const [statTimeStamps, setStatTimeStamps] = useState("");
	const [temperature, setTemperature] = useState("");
	const [humidity, setHumidity] = useState("");
	const [airQuality, setAirQuality] = useState("");

	useEffect(() => {
		callForData("past24HourData");
	}, [])

	const data = {
		labels: statTimeStamps,
		options: {
			tooltips: {
				mode: 'x'
			},
			responsive: true,
			maintainAspectRatio: true,
		},
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

	const setHistoryChange = (data) => {
		console.log(`data changed ${data}`)
		switch (data) {
			case "past3days" : {
				callForData("past3DaysData");
				break;
			}
			case "past7days" : {
				callForData("pastWeekData");
				break;
			}
			case "past24hours" : {
				callForData("past24HourData");
				break;
			}
			default: {
				callForData("past24HourData");
			}
		}

	};

	const callForData = (endpoint) => {
		fetch('/api/chart-data/v1/' + endpoint)
			.then(response => response.json())
			.then(data => {
				console.log(`fetching data`)
				console.log(data)
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
	}


	return (
		<div className="app-body">
			<h1>Hourly data charts:</h1>
			<div className="chart-data-picklist">
				<ChartHistoryPagePicklist setHistoryChange={ setHistoryChange }/>
			</div>
			<div className="chart-container">
				<Line data={data}/>
			</div>
		</div>
	)
}

export default HomeCharts;