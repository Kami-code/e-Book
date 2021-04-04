import React, { Component } from "react";
import {Card, CardBody, CardImg, CardText, CardTitle, Media} from "reactstrap";
import {Link} from "react-router-dom";

function RenderBookInCar({car, buyCar}) {
    const renderedBooks = car.map((book) => {
        return (
            <div key = "bookimage" className="col-12 col-md-12">
                <Card>
                    <Media>
                        <div className="col-12 col-md-2">
                            <CardImg width="100%" object src={book.image} alt={book.name}/>
                        </div>
                        <div className="col-12 col-md-7 m-2">
                            <Media body>
                                <Media heading>
                                    {book.name}
                                </Media>
                                <Media>
                                    <Media body>
                                        ¥{book.price}
                                    </Media>
                                </Media>
                            </Media>
                        </div>
                    </Media>
                </Card>
            </div>
        );
    });
    if (car.length === 0) return (
        <div key = "bookimage" className="col-12 col-md-12">
            <Card>
                <h1>当前购物车为空</h1>
            </Card>
        </div>
    );
    else return (
        <React.Fragment>
            {renderedBooks}
            <div key = "bookdescription" className="col-12 col-md-2 m-1">
                <Link to={`/car/`} onClick={buyCar}>
                    <CardImg  object src="/assets/images/buy.png" alt="立即购买" />
                </Link>
            </div>

        </React.Fragment>
    );

}



class Car extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        let totalPrice = 0;
        let car = this.props.car;
        for(let i = 0, len=car.length; i < len; i++) {
            totalPrice = totalPrice + parseFloat(car[i].price);
        }
        totalPrice = totalPrice.toFixed(2);
        return (
            <React.Fragment>
                <div className="container">
                    <RenderBookInCar car={car} buyCar={this.props.buyCar}/>
                    <div key = "bookimage" className="col-12 col-md-12">
                        <Card>
                            <CardTitle>共{car.length}本 总价为{totalPrice} </CardTitle>
                        </Card>
                    </div>
                </div>
            </React.Fragment>
        );
    }


}

export default Car;
