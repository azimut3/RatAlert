import React, {Component, useState, useEffect} from 'react';
import {IconButton, Tooltip, Typography} from "@material-ui/core";
import InfoIcon from '@material-ui/icons/Info';
import {withStyles} from "@material-ui/styles";
import moment from "moment";

function CurrentCondition() {
	const [roomCondition, setRoomCondition] = useState("");

	const HtmlTooltip = withStyles((theme) => ({
		tooltip: {
			backgroundColor: '#f5f5f9',
			color: 'rgba(0, 0, 0, 0.87)',
			maxWidth: 220,
			fontSize: theme.typography.pxToRem(12),
			border: '1px solid #dadde9',
		},
	}))(Tooltip);

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
				<div className="conditionRowFooter">
					Last update: {moment(roomCondition.creationDate).format("HH:mm:ss YYYY/MM/DD")}
				</div>
				<h1>Hi there!</h1>
				<h3>Here is our current room condition</h3>
				<div class="conditionBlock">
					<div class="conditionRow">
						<div class="conditionLabel airQualityLabel">Air quality:</div>
						<div class="conditionValue">
							{roomCondition.roomAirQualityLevel} ({roomCondition.roomAirQualityPpmValue} PPM)
							<HtmlTooltip
								title={
									<React.Fragment>
										<Typography color="inherit">Air quality values:</Typography>
										<b><em>{"Good:   "}</em></b> {' 0 - 50 PPM'}.<br/>
										<b><em>{"Moderate:   "}</em></b> {' 51 - 100 PPM'}.<br/>
										<b><em>{"Unhealthy for Sensitive Groups:     "}</em></b> {' 101 - 150 PPM'}.<br/>
										<b><em>{"Unhealthy:  "}</em></b> {' 151 - 200 PPM'}.<br/>
										<b><em>{"Very Unhealthy: "}</em></b> {' 201 - 300 PPM'}.<br/>
										<b><em>{"Hazardous:  "}</em></b> {' > 300 PPM'}.<br/>
									</React.Fragment>
								}
							>
								<IconButton aria-label="info">
									<InfoIcon />
								</IconButton>
							</HtmlTooltip>
						</div>
					</div>
					<div class="conditionRow">
						<div class="conditionLabel temperatureLabel">Temperature:</div>
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
