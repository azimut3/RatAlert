import React, {Component, useState, useEffect} from 'react';
import {
    Checkbox,
    makeStyles, TableCell, TableRow,
    withStyles
} from "@material-ui/core";

function CustomTableRow(props) {

    const [checkboxSelected, setCheckboxSelected] = useState(false);

    function onCheckboxClick(event) {
        setCheckboxSelected(!checkboxSelected);
        props.onCheckboxClick(props.row.id)
        console.log("Selected record id: " + props.row.id)
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
        </TableRow>

    )
}

export default CustomTableRow;