import React, {Component, useState, useEffect} from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, FormControl, Link, TextField} from "@material-ui/core";
import DrugCreationForm from "../components/DrugCreationForm";

function DrugsReview() {

	const [open, setOpen] = React.useState(false);

	function openDragCreationModal() {
		setOpen(true);
	}

	function closeDragCreationModal() {
		setOpen(false);
	}

	return (
		<div className="App">
			<div className="app-body">
				<Button variant="contained" onClick={openDragCreationModal}>
					New Drug
				</Button>

			</div>
			<div>
				<DrugCreationForm open={open} onClose={closeDragCreationModal}></DrugCreationForm>
			</div>
		</div>
	)
}

export default DrugsReview;