import React, {Component} from 'react'
import {Jumbotron, Navbar, Nav, NavbarToggler, Collapse, NavItem, NavbarBrand} from 'reactstrap'
import { NavLink } from "react-router-dom";

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isNavOpen: false
        };
        this.toggleNav = this.toggleNav.bind(this);
    }

    toggleNav() {
        this.setState({
                isNavOpen: !this.state.isNavOpen
            }
        )
    }

    render() {
        return (
            <React.Fragment>
                <Navbar dark expand="md">
                    <div className="container">
                        <NavbarToggler onClick={this.toggleNav} />
                        <NavbarBrand className="mr-auto" href="/">
                            <img src="assets/images/logo.png" height="30" width="41"
                                 alt="" />
                        </NavbarBrand>
                        <Collapse isOpen={this.state.isNavOpen} navbar>
                            <Nav navbar>
                                <NavItem>
                                    <NavLink className="nav-link" to="/home">
                                        <span className="fa fa-home fa-lg"></span> 商城
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/aboutus">
                                        <span className="fa fa-info fa-lg"></span> 关于我们
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/menu">
                                        <span className="fa fa-list fa-lg"></span> 书籍
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/contactus">
                                        <span className="fa fa-address-card fa-lg"></span> 联系我们
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/car">
                                        <span className="fa fa-shopping-cart fa-lg"></span> 购物车
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/login">
                                        <span className="fa fa-user-plus fa-lg"></span> 用户注册
                                    </NavLink>
                                </NavItem>
                            </Nav>
                        </Collapse>
                    </div>
                </Navbar>
                <Jumbotron>
                    <div className="container">
                        <div className="row row-header">
                            <div className="col-12 col-sm-6">
                                <h1>e-Book系统</h1>
                                <p>我们致力于提供用户最佳的购书体验！相信我们，我们是专业的！</p>
                            </div>
                        </div>
                    </div>
                </Jumbotron>
            </React.Fragment>
        );
    }
}

export default Header
