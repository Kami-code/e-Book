import React, { Component } from "react";
import {Route,Redirect,withRouter} from 'react-router-dom';
import PropTypes from 'prop-types';

//私有路由，只有登录的用户才能访问
class PrivateRoute extends Component{
    componentWillMount(){
        let isAuthenticated =  !!sessionStorage.getItem("username");
        let isAdmin = !!sessionStorage.getItem("isAdmin");
        this.setState({isAuthenticated:isAuthenticated});
        this.setState({isAdmin:isAdmin});
        if(!isAuthenticated){
            const {history} = this.props;
            setTimeout(() => {
                history.replace("/");
            }, 1000)
        }
    }
    render(){
        let { component: Component,path="/",exact=false,strict=false} = this.props;
        if (this.state.isAuthenticated) {
            if (path.indexOf("admin") !== -1 && this.state.isAdmin === false) { //没有admin权限的情况
                return (<Redirect to={{pathname: "/"}}/>);
            }
            else return (<Route  path={path} exact={exact} strict={strict} render={(props)=>( <Component {...props} /> )} />);
        }
        else { //没有登录的情况
            return (<Redirect to={{pathname: "/"}}/>);
        }
    }
}

PrivateRoute.propTypes  ={
    path:PropTypes.string.isRequired,
    exact:PropTypes.bool,
    strict:PropTypes.bool,
    component:PropTypes.func.isRequired
}

export default withRouter(PrivateRoute);
