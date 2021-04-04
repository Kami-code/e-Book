import React, {Component} from 'react';
import {
    Card,
    CardImg,
    CardImgOverlay,
    CardText,
    CardBody,
    CardTitle,
    Breadcrumb,
    BreadcrumbItem,
    Form, FormGroup, Label, Col, Input, Button
} from 'reactstrap'
import { Link } from 'react-router-dom'



class RenderAdminBook extends  Component {
    constructor(props) {
        super(props);
        this.state={
            inventory:'',
            price:''
        };
        this.handleClick=this.handleClick.bind(this);
        this.handleInputChange=this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        event.preventDefault();
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({
            [name]: Number(value)
        });
    }

    handleClick(event){
        const target = event.target;
        const id = target.id;
        const value = target.value;
        if (id === "inventory") {
            this.props.changeInventory(this.props.book.id, this.state.inventory);
        }
        else if (id === "price") {
            this.props.changePrice(this.props.book.id, this.state.price);
        }
    }

    render() {
        let book = this.props.book;
        return (
            <React.Fragment>
                <div key = "bookimage" className="col-12 col-md-5 m-1">
                    <Card>

                        <CardImg width="100%" object src={book.image} alt={book.name} />
                    </Card>
                </div>
                <div key = "bookdescription" className="col-12 col-md-5 m-1">
                    <h1>{book.name}</h1>
                    <text>{book.description}</text>
                    <br/><br/><br/>
                    <Card>
                        <CardBody>
                            <div className="row">
                                <Form>
                                    <CardTitle >库存：{book.inventory}</CardTitle>
                                    <FormGroup row>
                                        <Col md={{size:8, offset:2}}>
                                            <Input type="value" name="inventory"
                                                   placeholder="库存"
                                                   onChange={this.handleInputChange}
                                            />
                                        </Col>
                                        <Col md={{size:2}}>
                                            <Button type="submit" color="primary" id="inventory" onClick={this.handleClick}>
                                                修改
                                            </Button>
                                        </Col>
                                    </FormGroup>
                                    <CardTitle>价格：{book.price}</CardTitle>
                                    <FormGroup row>
                                        <Col md={{size:8, offset:2}}>
                                            <Input type="value" name="price"
                                                   placeholder="价格"
                                                   onChange={this.handleInputChange}
                                            />
                                        </Col>
                                        <Col md={{size:2}}>
                                            <Button type="submit" color="primary" id="price" onClick={this.handleClick}>
                                                修改
                                            </Button>
                                        </Col>
                                    </FormGroup>
                                </Form>

                            </div>
                        </CardBody>
                    </Card>

                </div>
            </React.Fragment>
        );
    }


}

class AdminBookDetail extends Component{

    constructor(props) {
        super(props);
    }



    render() {
        if (this.props.book === null || this.props.book === undefined) return (<div>404</div>);
        else {
            return (
                <div className="container">
                    <div className="row">
                        <Breadcrumb>
                            <BreadcrumbItem><Link to='/adminmenu'>管理书籍</Link></BreadcrumbItem>
                            <BreadcrumbItem active>{this.props.book.name}</BreadcrumbItem>
                        </Breadcrumb>
                        <div className="col-12">
                            <h3>{this.props.book.name}</h3>
                            <hr />
                        </div>
                    </div>
                    <div className="row">
                        <RenderAdminBook
                            book={this.props.book}
                            changeInventory={this.props.changeInventory}
                            changePrice={this.props.changePrice}
                        />
                    </div>
                </div>
            );
        }
    }
}


export default AdminBookDetail;
