import React, { Component } from "react";
import {
    Card,
    CardImg,
    CardText,
    CardBody,
    CardTitle,
    CardSubtitle,
    Breadcrumb,
    BreadcrumbItem,
    Form,
    FormGroup, Label, Col, Input, Button
} from "reactstrap";
import {Link, Redirect, withRouter} from "react-router-dom";

class Login extends Component {
    constructor(props) {
        super(props);
        this.state={
            account:'',
            password:''
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }


    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.check : target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        // let result = this.props.accounts(this.state);
        let localstate = this.state;
        let isSuccess = this.props.checkAccount(localstate);
        if (isSuccess) {
            this.props.history.push("/home");
        }
    }
    render() {
        return (
            <div className="container">
                <div className="row row-content">
                    <div className="col-12">
                        <h3>请输入登陆信息</h3>
                    </div>
                    <div className="col-12 col-md-9">
                        <Form onSubmit={this.handleSubmit}>
                            <FormGroup row>
                                <Label htmlFor="account" md={2}>账号</Label>
                                <Col md={10}>
                                    <Input type="text" id="account" name="account"
                                           placeholder="账号"
                                           value={this.state.account}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="password" md={2}>密码</Label>
                                <Col md={10}>
                                    <Input type="password" id="password" name="password"
                                           placeholder="密码"
                                           value={this.state.password}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md={{size:10, offset:2}}>
                                    <Button type="submit" color="primary">
                                        登陆
                                    </Button>
                                </Col>
                            </FormGroup>
                        </Form>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(Login);
