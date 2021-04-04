import React, {Component, useState, useEffect} from 'react';
import {FormControl, MenuItem, TextField} from "@material-ui/core";
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
	root: {
		'& .MuiTextField-root': {
			margin: theme.spacing(1),
			width: '25ch',
		},
	},
}));

function DrugCreationForm() {
	const [units, setUnits] = useState([]);

	function getDrugUnits() {
		fetch('/api/drugs/v1/drugUnitType')
			.then(response => response.json())
			.then(data => {
				setUnits(data);
				setSelectedUnit(data[0].value)
				console.log(data)
			});
	}

	useEffect(() => {
		getDrugUnits();
	}, []);

	const classes = useStyles();
	const [newDrug, setNewDrug] = React.useState('EUR');
	const [selectedUnit, setSelectedUnit] = React.useState("No value selected");

	const handleChange = (event) => {
		setSelectedUnit(event.target.value);
	};

	return (
		<div>
			<FormControl noValidate className={classes.root}>
				<TextField
					id="filled-helperText"
					label="Name"
					//defaultValue="Default Value"
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="filled-helperText"
					label="Description"
					//defaultValue="Default Value"
					multiline
					rows={4}
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="filled-helperText"
					label="Quantity"
					//defaultValue="Default Value"
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="filled-helperText"
					label="Unit strength"
					//defaultValue="Default Value"
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="outlined-select-currency-native"
					select
					label="Units"
					value={selectedUnit}
					onChange={handleChange}

					helperText="Please select drug type"
					variant="filled"
				>
					{units.map((option) => (
						<MenuItem  key={option.value} value={option.value}>
							{option.label}
						</MenuItem >
					))}
				</TextField>
			</FormControl>
		</div>
	)
}

export default DrugCreationForm;