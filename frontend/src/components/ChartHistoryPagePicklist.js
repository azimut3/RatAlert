import React, {Component, useState, useEffect} from 'react';
import {FormControl, InputLabel, MenuItem, Select, createMuiTheme} from "@material-ui/core";
import { makeStyles } from '@material-ui/core/styles';
import { ThemeProvider } from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
	formControl: {
		margin: theme.spacing(1),
		minWidth: 200,

	},
	selectEmpty: {
		marginTop: theme.spacing(2),
	},
}));

function ChartHistoryPagePicklist() {
	const [past24hours, setPeriod] = React.useState('past24hours');

	const classes = useStyles();

	const handleChange = (event) => {
		setPeriod(event.target.value);
	};

	const theme = createMuiTheme({
		palette: {
			type: "dark"
		}
	});

	return(
		<ThemeProvider theme={theme}>
			<FormControl variant="outlined" className={classes.formControl}>
				<InputLabel id="demo-simple-select-outlined-label">Period</InputLabel>
				<Select
					labelId="demo-simple-select-outlined-label"
					id="demo-simple-select-outlined"
					value={past24hours}
					onChange={handleChange}
					label="Period"

				>
					<MenuItem value="past24hours">Past 24 Hours</MenuItem>
					<MenuItem value="past3days" >Past 3 days</MenuItem>
					<MenuItem value="past7days" >Past Week</MenuItem>
				</Select>
			</FormControl>
		</ThemeProvider>
	)
}

export default ChartHistoryPagePicklist;
