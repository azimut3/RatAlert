import React, {Component, useState, useEffect} from 'react';
import {
    Checkbox,
    makeStyles, TableCell, TableRow,
    withStyles
} from "@material-ui/core";
import {IoMdCreate} from "react-icons/all";
import DrugCreationForm from "./DrugCreationForm";

function CustomTableRow(props) {

    const [checkboxSelected, setCheckboxSelected] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);

    function onCheckboxClick(event) {
        setCheckboxSelected(!checkboxSelected);
        props.onCheckboxClick(props.row.id)
        //console.log("Selected record id: " + props.row.id)
    }

    function onEdit(event) {
        props.onEdit(props.row);
    }

    function onModalClose(event) {
        setModalOpen(false)
    }

    const StyledTableCell = withStyles((theme) => ({
        head: {
            backgroundColor: theme.palette.common.black,
            color: theme.palette.common.white,
        },
        body: {
            fontSize: 14,
        },
    }))(TableCell);



    const useStyles = makeStyles({
        table: {
            minWidth: 700,
        },

        tableHeader: {
            display: "flex",
            flexDirection: "row-reverse",
            width: "100%",
        },
    });

    const classes = useStyles();

    return (
        <TableRow key={props.row.name}>
            <StyledTableCell component="th" scope="row">
                <Checkbox
                    data-id={props.row.id}
                    checked={checkboxSelected}
                    onChange={onCheckboxClick}
                    inputProps={{'aria-label': 'select all desserts'}}
                />
            </StyledTableCell>
            <StyledTableCell component="th" scope="row">
                {props.row.name}
            </StyledTableCell>
            <StyledTableCell align="right">{props.row.description}</StyledTableCell>
            <StyledTableCell align="right">{props.row.unitStrength}</StyledTableCell>
            <StyledTableCell align="right">{props.row.quantity}</StyledTableCell>
            <StyledTableCell align="right">{props.row.units}</StyledTableCell>
            <StyledTableCell align="right">
                <IoMdCreate size={25} onClick={onEdit}/>
            </StyledTableCell>
        </TableRow>
    )
}

export default CustomTableRow;