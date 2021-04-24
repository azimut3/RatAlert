import React, {Component, useState, useEffect} from 'react';
import {
    Button,
    makeStyles, Paper, Table, TableBody, TableCell,
    TableContainer, TableHead, TableRow,
    withStyles
} from "@material-ui/core";
import Icon from '@material-ui/core/Icon';
import DrugCreationForm from "../components/DrugCreationForm";
import * as PropTypes from "prop-types";
import CustomTableRow from "../components/CustomTableRow";
import {BsFillPlusCircleFill, IoIosAddCircle, IoIosTrash, IoMdRefresh, SiAddthis} from "react-icons/all";

class StyledTableCell extends Component {
    render() {
        return null;
    }
}

StyledTableCell.propTypes = {children: PropTypes.node};

function DrugsReview() {

    useEffect(() => {
        getDrugsDataCallout();
    }, []);

    const [drugsData, setDrugsData] = useState([]);
    const [open, setOpen] = React.useState(false);
    const [selectedRows, setSelectedRows] = React.useState([]);

    const getDrugsDataCallout = () => {
        fetch('/api/drugs/v1/drugList')
            .then(response => response.json())
            .then(data => {
                setDrugsData(data);
                console.log(data)
            });
    }

    const deleteDrugsCallout = () => {
        let requestOptions = {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(selectedRows)
        };

        fetch('/api/drugs/v1/delete', requestOptions)
            .then(response => {
                if (response.status === 200) {
                    //console.log('response: ', response);
                    getDrugsDataCallout();
                }
            })
    }


    function openDragCreationModal() {
        setOpen(true);
    }

    function closeDragCreationModal() {
        setOpen(false);
    }

    function onSave() {
        getDrugsDataCallout();
    }

    function onDelete() {
        deleteDrugsCallout();
    }

    function onCheckboxClick(id) {
        if (selectedRows.indexOf(id) > -1) {
            selectedRows.splice(selectedRows.indexOf(id), 1);
        } else {
            selectedRows.push(id)
        }
        setSelectedRows(selectedRows);
        console.log(selectedRows)
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

    const StyledTableRow = withStyles((theme) => ({
        root: {
            '&:nth-of-type(odd)': {
                backgroundColor: theme.palette.action.hover,
            },
        },
    }))(TableRow);

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
    const numSelected = 0, rowCount = 0;

    return (
        <div>
            <div className="app-body">
                <div className={classes.table}>
                    <div className={classes.tableHeader}>
                        <IoIosAddCircle size={30} onClick={openDragCreationModal}/>
                        <IoMdRefresh size={30} onClick={onSave}/>
                        <IoIosTrash size={30} onClick={onDelete}/>
                    </div>

                    <TableContainer component={Paper}>
                        <Table aria-label="customized table">
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell></StyledTableCell>
                                    <StyledTableCell>Name</StyledTableCell>
                                    <StyledTableCell align="right">Description</StyledTableCell>
                                    <StyledTableCell align="right">Quantity</StyledTableCell>
                                    <StyledTableCell align="right">Unit strength</StyledTableCell>
                                    <StyledTableCell align="right">Units</StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {drugsData.map((row) => (
                                    <CustomTableRow row={row} onCheckboxClick={onCheckboxClick}></CustomTableRow>
                                    // <StyledTableRow key={row.name}>
                                    //     <StyledTableCell component="th" scope="row">
                                    //         <Checkbox
                                    //             data-id={row.id}
                                    //             indeterminate={numSelected > 0 && numSelected < rowCount}
                                    //             checked={rowCount > 0 && numSelected === rowCount}
                                    //             onChange={onCheckboxClick}
                                    //             inputProps={{ 'aria-label': 'select all desserts' }}
                                    //         />
                                    //     </StyledTableCell>
                                    //     <StyledTableCell component="th" scope="row">
                                    //         {row.name}
                                    //     </StyledTableCell>
                                    //     <StyledTableCell align="right">{row.description}</StyledTableCell>
                                    //     <StyledTableCell align="right">{row.unitStrength}</StyledTableCell>
                                    //     <StyledTableCell align="right">{row.quantity}</StyledTableCell>
                                    //     <StyledTableCell align="right">{row.units}</StyledTableCell>
                                    // </StyledTableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>

                </div>

                <div>
                    <DrugCreationForm open={open} onClose={closeDragCreationModal} onSave={onSave}></DrugCreationForm>
                </div>
            </div>
        </div>
    )
}

export default DrugsReview;