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

class AdminAddBook extends Component {
    constructor(props) {
        super(props);
        this.state={
            id:0,
            name:'',
            image:'',
            price:'',
            inventory: 0,
            ISBN:'',
            description:''
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }


    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        let localState = this.state;
        localState.id = parseInt(localState.id, 10);
        this.props.addBook(localState);
    }
    render() {
        return (
            <div className="container">
                <div className="row row-content">
                    <div className="col-12">
                        <h3>请输入添加的书籍</h3>
                    </div>
                    <div className="col-12 col-md-9">
                        <Form onSubmit={this.handleSubmit}>
                            <FormGroup row>
                                <Label htmlFor="id" md={2}>书籍ID</Label>
                                <Col md={10}>
                                    <Input type="number" id="id" name="id"
                                           placeholder="id"
                                           value={this.state.id}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="name" md={2}>书名</Label>
                                <Col md={10}>
                                    <Input type="text" id="name" name="name"
                                           placeholder="name"
                                           value={this.state.name}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="image" md={2}>封面</Label>
                                <Col md={10}>
                                    <Input type="text" id="image" name="image"
                                           placeholder="image"
                                           value={this.state.image}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="price" md={2}>价格</Label>
                                <Col md={10}>
                                    <Input type="text" id="price" name="price"
                                           placeholder="price"
                                           value={this.state.price}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="inventory" md={2}>库存</Label>
                                <Col md={10}>
                                    <Input type="text" id="inventory" name="inventory"
                                           placeholder="inventory"
                                           value={this.state.inventory}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="ISBN" md={2}>ISBN</Label>
                                <Col md={10}>
                                    <Input type="text" id="ISBN" name="ISBN"
                                           placeholder="ISBN"
                                           value={this.state.ISBN}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label htmlFor="description" md={2}>描述</Label>
                                <Col md={10}>
                                    <Input type="text" id="description" name="description"
                                           placeholder="description"
                                           value={this.state.description}
                                           onChange={this.handleInputChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Col md={{size:10, offset:2}}>
                                    <Button type="submit" color="primary">
                                        添加
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

export default AdminAddBook;
