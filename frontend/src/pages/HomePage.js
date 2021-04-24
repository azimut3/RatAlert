import React, {Component, useState, useEffect} from 'react';
import {Grid, IconButton, Paper, Tooltip, Typography} from "@material-ui/core";
import InfoIcon from '@material-ui/icons/Info';
import {withStyles} from "@material-ui/styles";
import moment from "moment";
import WeatherWidget from "../components/WeatherWidjet";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
	paper: {
		padding: theme.spacing(1),
		textAlign: 'center',
		color: theme.palette.text.secondary,
		whiteSpace: 'nowrap',
		marginBottom: theme.spacing(1),
	},
	container: {
		display: 'grid',
		gridTemplateColumns: 'repeat(12, 1fr)',
		gridGap: theme.spacing(3),
		width: '100%',
		margin: '0 20px',
	},
	mainColumn: {
		gridColumnEnd: 'span 9',
	},
	rightColumn: {
		gridColumnEnd: 'span 3',
		marginTop: '20px',
	},
}))

function HomePage() {
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

	const classes = useStyles();

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
				<div>
					<div>
							<div className="conditionRowFooter">
								Last update: {moment(roomCondition.creationDate).format("HH:mm:ss DD/MM/YYYY")}
							</div>
							<h1>Hi there!</h1>
							<h3>Here is our current room condition</h3>
							<div className="conditionBlock">
								<div className="conditionRow">
									<div className="conditionLabel airQualityLabel">Air quality:</div>
									<div className="conditionValue">
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
												<InfoIcon/>
											</IconButton>
										</HtmlTooltip>
									</div>
								</div>
								<div className="conditionRow">
									<div className="conditionLabel temperatureLabel">Temperature:</div>
									<div className="conditionValue">{roomCondition.roomTemperature} °C
									</div>
								</div>
								<div className="conditionRow">
									<div className="conditionLabel humidityLabel">Humidity:</div>
									<div className="conditionValue">{roomCondition.roomHumidity}%
									</div>
								</div>
							</div>
					</div>
				</div>
				<div>
					<iframe src="http://94.158.155.196:83/" width="800px" height="800px"></iframe>
				</div>


			</div>

		</div>
	)
}

export default HomePage;
