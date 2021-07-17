import React, {Component, useState, useEffect} from 'react';
import {Dialog, DialogActions, DialogContent, DialogTitle, FormControl, MenuItem, TextField} from "@material-ui/core";
import {makeStyles} from '@material-ui/core/styles';
import Button from "@material-ui/core/Button";

const useStyles = makeStyles((theme) => ({
	root: {
		'& .MuiTextField-root': {
			margin: theme.spacing(1),
			width: '25ch',
		},
	},
}));

function DrugCreationForm(props) {
	const [unitsPicklistValues, setUnitsPicklistValues] = useState([]);
	const classes = useStyles();
	const [newDrug, setNewDrug] = React.useState({});
	const [selectedUnit, setSelectedUnit] = React.useState("No value selected");

	useEffect(() => {
		getDrugUnits();
	}, []);

	function getDrugUnits() {
		fetch('/api/drugs/v1/drugUnitType')
			.then(response => response.json())
			.then(data => {
				setUnitsPicklistValues(data);
				setSelectedUnit(data[0].value)
				setNewDrug({
					...newDrug,
					units: data[0].value
				})
				//console.log(data)
			});
	}

	const handleChange = (e) => {
		setNewDrug({
			...newDrug,
			// Trimming any whitespace
			[e.target.name]: e.target.value.trim()
		})
		//console.log(newDrug)
	};

	const handleClose = () => {
		props.onClose();
	};

	function handleSave(event) {
		event.preventDefault();
		//console.log("props.drug ", props.drug)
		setNewDrug({
			...newDrug,
			// Trimming any whitespace
			id: props.drug.id ? props.drug.id : null
		})

		//console.log(newDrug)
		let requestOptions = {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(newDrug)
		};

		fetch('/api/drugs/v1/create', requestOptions)
			.then(response => {
				if(response.status === 200) {
					//console.log('response: ', response);
					props.onSave();
				}
			})


		handleClose();
	}

	return (
		<div>
			<Dialog onClose={handleClose}
			        open={props.open}
			        scroll="paper"
			        aria-labelledby="scroll-dialog-title"
			        aria-describedby="scroll-dialog-description"
			>
				<DialogTitle id="simple-dialog-title">
					New Drug creation:
				</DialogTitle>

				<DialogContent dividers={true}>

					<form>
						<FormControl noValidate className={classes.root}>
							<TextField
								id="filled-helperText"
								label="Name"
								name="name"
								defaultValue={props.drug.name ? props.drug.name : ''}
								//helperText="Some important text"
								variant="filled"
								onChange={handleChange}
							/>
							<TextField
								id="filled-helperText"
								label="Description"
								name="description"
								defaultValue={props.drug.description ? props.drug.description : ''}
								multiline
								rows={4}
								//helperText="Some important text"
								variant="filled"
								onChange={handleChange}
							/>
							<TextField
								id="filled-helperText"
								label="Quantity"
								name="quantity"
								defaultValue={props.drug.quantity ? props.drug.quantity : ''}
								//helperText="Some important text"
								variant="filled"
								onChange={handleChange}
							/>
							<TextField
								id="filled-helperText"
								label="Unit strength"
								name="unitStrength"
								defaultValue={props.drug.unitStrength ? props.drug.unitStrength : ''}
								//helperText="Some important text"
								variant="filled"
								onChange={handleChange}
							/>
							<TextField
								id="outlined-select-currency-native"
								select
								label="Units"
								name="units"
								//value={props.drug.selectedUnit ? props.drug.selectedUnit : ''}
								onChange={handleChange}

								helperText="Please select drug type"
								variant="filled"
							>
								{unitsPicklistValues.map((option) => (
									<MenuItem key={option.value} value={props.drug.selectedUnit ? props.drug.selectedUnit : option.value}>
										{option.label}
									</MenuItem>
								))}
							</TextField>
						</FormControl>

					</form>
				</DialogContent>

				<DialogActions>
					<Button onClick={handleClose} color="primary">
						Cancel
					</Button>
					<Button onClick={handleSave} color="primary">
						Save
					</Button>
				</DialogActions>
			</Dialog>
		</div>
	)
}

export default DrugCreationForm;