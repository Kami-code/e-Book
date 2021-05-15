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
    FormGroup, Label, Col, Input, Button, Form
} from 'reactstrap';
import { Link } from 'react-router-dom';
import {COMMENTS} from "../shared/comments";
import {PROMOTIONS} from "../shared/promotions";
import {LEADERS} from "../shared/leaders";


function RenderAdminMenuItem({book}) {
    return (
        <Card>
            <Link to={`/adminmenu/${book.id}`}>
                <CardImg width="100%" object src={book.image} alt={book.name}/>
                <CardTitle>{book.name}</CardTitle>
            </Link>
        </Card>
    );
}

function isPrefix(book, name) {
    const book_name = book.name;
    if (book_name.indexOf(name) !== -1) return true;
    else return false;
}

class AdminMenu extends Component {
    constructor(props) {
        super(props);
        this.state = {
            totalBooks: props.books,
            currentBooks: props.books,
        };
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const localBooks = this.state.totalBooks.filter((book)=> isPrefix(book, value));
        this.setState({
            currentBooks:localBooks,
        });
    }

    render() {
        const adminMenu = this.state.currentBooks.map((book) =>{
            return (
                <div key = {book.id} className="col-12 col-md-2 m-1">
                    <RenderAdminMenuItem book={book} />
                </div>
            )
        });
        return (
            <div className="container">
                <div className="row">
                    <Breadcrumb>
                        <BreadcrumbItem><Link to='home'>商城</Link></BreadcrumbItem>
                        <BreadcrumbItem active>管理书籍</BreadcrumbItem>
                    </Breadcrumb>
                    <div className="col-12">
                        <h3>管理书籍</h3>
                        <hr />
                    </div>
                </div>
                <div className="row">
                    <Form >
                        <FormGroup row>
                            <Label htmlFor="bookName" md={4}>查询</Label>
                            <Col md={8}>
                                <Input type="text" id="bookName" name="bookName"
                                       placeholder="书籍名字"
                                       value={this.state.account}
                                       onChange={this.handleInputChange} />
                            </Col>
                        </FormGroup>
                    </Form>

                </div>
                <div className="row">
                    {adminMenu}
                </div>
            </div>
        );
    }


}


export default AdminMenu;
