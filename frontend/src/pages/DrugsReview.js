import React, {Component, useState, useEffect} from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, FormControl, Link, TextField} from "@material-ui/core";
import DrugCreationForm from "../components/DrugCreationForm";

function DrugsReview() {
	let selectedValue;
	const [open, setOpen] = React.useState(false);

	function openDragCreationModal() {
		setOpen(true);
	}

	const handleClose = () => {

		setOpen(false);
	};

	return (
		<div className="App">
			<div className="app-body">

				<Button variant="contained" onClick={openDragCreationModal}>
					New Drug
				</Button>

			</div>
			<div>
				<Dialog onClose={handleClose}
				        open={open}
				        scroll="paper"
				        aria-labelledby="scroll-dialog-title"
				        aria-describedby="scroll-dialog-description"
				>
					<DialogTitle id="simple-dialog-title">
						New Drug creation:
					</DialogTitle>
					<DialogContent dividers="true">
						<DialogContentText
							id="scroll-dialog-description"
							tabIndex={-1}
						>
							<DrugCreationForm/>
						</DialogContentText>
					</DialogContent>
					<DialogActions>
						<Button onClick={handleClose} color="primary">
							Cancel
						</Button>
						<Button onClick={handleClose} color="primary">
							Subscribe
						</Button>
					</DialogActions>
				</Dialog>
			</div>
		</div>
	)
}

export default DrugsReview;