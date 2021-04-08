import React, {Component, useState, useEffect} from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, FormControl, Link, TextField} from "@material-ui/core";
import DrugCreationForm from "../components/DrugCreationForm";

function DrugsReview() {

	function openDragCreationModal() {
		setOpen(true);
	}

	return (
		<div className="App">
			<div className="app-body">

				<Button variant="contained" onClick={openDragCreationModal}>
					New Drug
				</Button>


			</div>
			<div>

			</div>
		</div>
	)
}

export default DrugsReview;