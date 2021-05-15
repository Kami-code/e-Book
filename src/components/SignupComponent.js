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
import {Link} from "react-router-dom";
import request from "./helper";

class Signup extends Component {
    constructor(props) {
        super(props);
        this.state={
            account:'',
            password:'',
            telnum:'',
            email:'',
            agree: false,
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
        this.props.addAccount(localstate);
        console.log(localstate)
        // request({
        //     url:'/login',
        //     method:'post',
        //     data:localstate
        // }).then(function(res){
        //     console.log(res)
        // })
        let formData = new FormData();

        formData.append('account', this.state.account);
        formData.append('password', this.state.password);
        formData.append('telnum', this.state.telnum);
        formData.append('email', this.state.email);
        console.log(formData);
        fetch('http://localhost:9090/signup',{
            method:'POST',
            body:formData
        })
            .then(res =>res.json())
            .then((data) => {
                console.log(data)
                })

    }
    render() {
        return (
            <div className="container">
                <div className="row row-content">
                    <div className="col-12">
                        <h3>请输入注册信息</h3>
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
                                <Label htmlFor="telnum" md={2}>手机号码</Label>
                                <Col md={10}>
                                    <Input type="tel" id="telnum" name="telnum"
                                           placeholder="手机号码"
                                           value={this.state.telnum}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="email" md={2}>电子邮箱</Label>
                                <Col md={10}>
                                    <Input type="email" id="email" name="email"
                                           placeholder="电子邮箱"
                                           value={this.state.email}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md={{size:6, offset:2}}>
                                    <FormGroup check>
                                        <Label check>
                                            <Input type="checkbox" name="agree"
                                                   checked={this.state.agree}
                                                   onChange={this.handleInputChange} /> {''}
                                            <strong>我已知悉用户协议内容</strong>
                                        </Label>
                                    </FormGroup>
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md={{size:10, offset:2}}>
                                    <Button type="submit" color="primary">
                                        注册
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

export default Signup;
