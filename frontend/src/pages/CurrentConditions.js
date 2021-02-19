import React, {Component, useState, useEffect} from 'react';

function CurrentCondition() {
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
			<div className="app-body">
				<h1>Hi there!</h1>
				<h3>Here is our current room condition</h3>
				<div class="conditionBlock">
					<div class="conditionRow">
						<div class="conditionLabel">Air quality:</div>
						<div class="conditionValue">{roomCondition.roomAirQualityLevel} ({roomCondition.roomAirQualityPpmValue} PPM) </div>
					</div>
					<div class="conditionRow">
						<div class="conditionLabel">Temperature:</div>
						<div class="conditionValue">{roomCondition.roomTemperature} °C
						</div>
					</div>
					<div className="conditionRow">
						<div class="conditionLabel humidityLabel" >Humidity:</div>
						<div class="conditionValue">{roomCondition.roomHumidity}%
						</div>
					</div>
				</div>

			</div>

		</div>
	)
}

export default CurrentCondition;
