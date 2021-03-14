import React from 'react';
import {Card, CardImg, CardImgOverlay, CardText, CardBody, CardTitle, Breadcrumb, BreadcrumbItem} from 'reactstrap'
import { Link } from 'react-router-dom'

function RenderBook({book, addBookToCar}) {
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
                        <CardTitle>ebook价</CardTitle>
                        <CardText>¥{book.price}</CardText>
                        <div  className="row">
                            <Link to={`/car`} onClick={() => addBookToCar(book)}>
                                <CardImg  object src="/assets/images/add.png" alt="加入购物车" />
                            </Link>
                            <Link to={`/home/${book.id}`}>
                                <CardImg  object src="/assets/images/buy.png" alt="立即购买" />
                            </Link>
                         </div>
                    </CardBody>
                </Card>

            </div>
        </React.Fragment>
    );
}

function RenderComments({comments}) {

    let comment_fun = comments.map((comment) =>{
        return (
            <div>
                <Card>
                <li>{comment.comment}</li><br />
                <li>--{comment.author},{new Intl.DateTimeFormat('en-US',{year:'numeric', month:'short', day: '2-digit'}).format(new Date(Date.parse(comment.date)))}</li> <br />
                </Card>
            </div>
        );
    });
    if (comment_fun.length === 0) {
        return (
            <div key = "comment" className="col-12 col-md-10 m-2">
                <h4>商品评论</h4>
                <ul className="list-unstyled">
                    暂无用户评论
                </ul>
            </div>
        );
    }
    else {
        return (
            <div key = "comment" className="col-12 col-md-10 m-2">
                <h4>商品评论</h4>
                <ul className="list-unstyled">
                    {comment_fun}
                </ul>
            </div>
        );
    }
}

const BookDetail = (props) => {
    if (props.book === null || props.book === undefined) return (<div>404</div>);
    else {
        return (
            <div className="container">
                    <div className="row">
                        <Breadcrumb>
                            <BreadcrumbItem><Link to='/menu'>热销书籍</Link></BreadcrumbItem>
                            <BreadcrumbItem active>{props.book.name}</BreadcrumbItem>
                        </Breadcrumb>
                        <div className="col-12">
                            <h3>{props.book.name}</h3>
                            <hr />
                        </div>
                    </div>
                    <div className="row">
                        <RenderBook book={props.book} addBookToCar={props.addBookToCar}/>
                        <RenderComments comments={props.comments}/>
                    </div>
            </div>
        );
    }

}


export default BookDetail;
