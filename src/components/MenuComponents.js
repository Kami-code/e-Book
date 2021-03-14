import React, {Component} from 'react';
import {Card, CardImg, CardImgOverlay, CardText, CardBody, CardTitle, Breadcrumb, BreadcrumbItem} from 'reactstrap';
import { Link } from 'react-router-dom';


function RenderMenuItem({book}) {
    return (

        <Card>
            <Link to={`/menu/${book.id}`}>
                <CardImg width="100%" object src={book.image} alt={book.name}/>
                <CardTitle>{book.name}</CardTitle>
            </Link>
            <CardTitle>¥{book.price}</CardTitle>
        </Card>
    );
}

const Menu = (props) => {
    const menu = props.books.map((book) =>{
        return (
            <div key = {book.id} className="col-12 col-md-2 m-1">
                <RenderMenuItem book={book} />
            </div>
        )
    });

    return (
        <div className="container">
            <div className="row">
                <Breadcrumb>
                    <BreadcrumbItem><Link to='home'>商城</Link></BreadcrumbItem>
                    <BreadcrumbItem active>热销书籍</BreadcrumbItem>
                </Breadcrumb>
                <div className="col-12">
                    <h3>热销书籍</h3>
                    <hr />
                </div>
            </div>
            <div className="row">
                    {menu}
            </div>
        </div>
    );
}


export default Menu;
