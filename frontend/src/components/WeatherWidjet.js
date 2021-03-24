import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import moment from "moment";
import {WiThermometer} from "react-icons/wi";
import {Card, CardContent, Typography} from "@material-ui/core";
import CardActions from '@material-ui/core/CardActions';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
	root: {
		flexGrow: 1,
	},
	menuButton: {
		marginRight: theme.spacing(2),
	},
	title: {
		flexGrow: 1,
	},

}));

function WeatherWidget() {
	useEffect(() => {
		callForData();
	}, []);

	const [currentWeatherState, setCurrentWeatherState] = useState(null);


	const callForData = (endpoint) => {
		fetch('/api/weather/v1/currentWeather')
			.then(response => response.json())
			.then(data => {
				setCurrentWeatherState(data);
				console.log(data)
			});
	}

	const useStyles = makeStyles({
		root: {
			minWidth: 275,
		},
		bullet: {
			display: 'inline-block',
			margin: '0 2px',
			transform: 'scale(0.8)',
		},
		title: {
			fontSize: 14,
		},
		pos: {
			// marginBottom: 12,
		},
		floatingCenterRow: {
			display: "flex",
			justifyContent: "center",
			alignItems: "center",
		},
		rightSection: {
			display: "flex",
			justifyContent: "start",
			alignItems: "start",
			flexDirection: "column",
		},
		leftSection: {
			display: "flex",
			justifyContent: "end",
			alignItems: "center",
			flexDirection: "column",
			paddingRight: "10px",
		},
		weatherForecastContent: {
			display: "flex",
			justifyContent: "center",
			alignItems: "center",
		},

		iconContainer: {
			height: "100px",
			width: "100px",
		},

	});

	const classes = useStyles();

	if (currentWeatherState !== null && currentWeatherState !== undefined) {
		return (

			<div className="widget-container">
				<Card className={classes.root}>
					<CardContent>
						<Typography className={classes.title} color="textSecondary" gutterBottom>
							Current weather:
						</Typography>
						<div className={classes.weatherForecastContent}>
							<div className={classes.leftSection}>
								<Typography variant="h5" component="h2">
									<div>
										<img id="wicon" src={"http://openweathermap.org/img/w/" + currentWeatherState.weather[0].icon + ".png"}
										     alt="Weather icon" className={classes.iconContainer}></img>
									</div>
								</Typography>

							</div>
							<div className={classes.rightSection}>
								<Typography variant="h5" component="h2">
									<div className={classes.floatingCenterRow}>
										<WiThermometer size={40}/> {currentWeatherState.main?.temp} °C
									</div>
								</Typography>
								<Typography className={classes.pos} color="textSecondary">
									Feels like: {currentWeatherState.main?.feels_like} °C
								</Typography>
								<Typography className={classes.pos} color="textSecondary">
									<WiThermometer size={20}/> min: {currentWeatherState.main?.temp_min} °C <br/>
								</Typography>
								<Typography className={classes.pos} color="textSecondary">
									<WiThermometer size={20}/> max: {currentWeatherState.main?.temp_max} °C
								</Typography>

								{/*<Typography variant="body2" component="p">
									max: {currentWeatherState.main?.temp_max} °C

								</Typography>*/}
							</div>
						</div>
					</CardContent>
					{/*<CardActions>
						<Button size="small">Learn More</Button>
					</CardActions>*/
					}
				</Card>


			</div>
		)
	}
	else {
		return <div></div>
	}
}

export default WeatherWidget;