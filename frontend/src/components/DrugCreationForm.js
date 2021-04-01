import React, {Component, useState, useEffect} from 'react';
import {FormControl, TextField} from "@material-ui/core";

function DrugCreationForm() {
	const units = [
		{
			value: 'USD',
			label: '$',
		},
		{
			value: 'EUR',
			label: '€',
		},
		{
			value: 'BTC',
			label: '฿',
		},
		{
			value: 'JPY',
			label: '¥',
		},
	];

	const [newDrug, setNewDrug] = React.useState('EUR');

	const handleChange = (event) => {
		setNewDrug(event.target.value);
	};

	return (
		<div>
			<FormControl noValidate>
				<TextField
					id="filled-helperText"
					label="Name"
					defaultValue="Default Value"
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="filled-helperText"
					label="Description"
					defaultValue="Default Value"
					multiline
					rows={4}
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="filled-helperText"
					label="Quantity"
					defaultValue="Default Value"
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="filled-helperText"
					label="Unit strength"
					defaultValue="Default Value"
					//helperText="Some important text"
					variant="filled"
				/>
				<TextField
					id="outlined-select-currency-native"
					select
					label="Units"
					value={units}
					onChange={handleChange}
					SelectProps={{
						native: true,
					}}
					helperText="Please select your currency"
					variant="outlined"
				>
					{units.map((option) => (
						<option key={option.value} value={option.value}>
							{option.label}
						</option>
					))}
				</TextField>
			</FormControl>
		</div>
	)
}

export default DrugCreationForm;