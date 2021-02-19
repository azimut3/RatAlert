import React, {useState} from "react";
import {AppBar, Button, IconButton, Menu, MenuItem, Toolbar, Typography} from "@material-ui/core";
import MenuIcon from '@material-ui/icons/Menu';
import {makeStyles} from '@material-ui/core/styles';
import {
	BrowserRouter as Router,
	Switch,
	Route,
	Link
} from "react-router-dom";

const useStyles = makeStyles((theme) => ({
	root: {
		flexGrow: 1,
	},
	menuButton: {
		marginRight: theme.spacing(2),
	},
	title: {
		flexGrow: 1,
	},
	avisited:{

	}
}));

function AppMenu() {
	const [title, setTitle] = useState("Home");

	const classes = useStyles();

	const [anchorEl, setAnchorEl] = React.useState(null);

	const handleClick = (event) => {
		setAnchorEl(event.currentTarget);
	};

	const handleClose = (event) => {
		console.log(event)
		let titleValue = event.target.name;
		setTitle(titleValue);
		setAnchorEl(null);
	};

	return (
		<div className={classes.root}>
			<AppBar position="static">
				<Toolbar>
					<Typography variant="h6" className={classes.title}>
						{title}
					</Typography>
					<IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
						<MenuIcon aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
							Open Menu
						</MenuIcon>
						<Menu
							id="simple-menu"
							anchorEl={anchorEl}
							keepMounted
							open={Boolean(anchorEl)}
							onClose={handleClose}
						>
							<MenuItem component={Link} to={'/'} onClick={handleClose} name="Home">Home</MenuItem>
							<MenuItem component={Link} to={'/room-history'} onClick={handleClose} name="Data charts">Data charts</MenuItem>
							<MenuItem component={Link} to={'/about'} onClick={handleClose} name="About">About</MenuItem>
							{/*<MenuItem onClick={handleClose}>*/}
							{/*	<Link to="/">Home</Link>*/}
							{/*</MenuItem>*/}
							{/*<MenuItem onClick={handleClose}>*/}
							{/*	<Link to="/room-history">Room history charts</Link>*/}
							{/*</MenuItem>*/}
							{/*<MenuItem onClick={handleClose}>*/}
							{/*	<Link to="/about">About</Link>*/}
							{/*</MenuItem>*/}
						</Menu>
					</IconButton>
				</Toolbar>
			</AppBar>
		</div>
	)
}

export default AppMenu;