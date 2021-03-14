import React from 'react';
import {Card, CardBody, CardImg, CardText, CardTitle, Media} from "reactstrap";
import {Link} from "react-router-dom";

function RenderBookInCar({car}) {
    const renderedBooks = car.map((book) => {
        return (
            <div key = "bookimage" className="col-12 col-md-12">
                <Card>
                    <Media>
                        <div className="col-12 col-md-2">
                            <Media left href="#">
                                <Media object src={book.image} alt={book.name} />
                            </Media>
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
    else return renderedBooks;

}



const Car = ({car}) => {
    let totalPrice = 0;
    for(let i = 0, len=car.length; i < len; i++) {
        totalPrice = totalPrice + parseFloat(car[i].price);
    }
    totalPrice = totalPrice.toFixed(2);
    return (
        <React.Fragment>
            <div className="container">
                <RenderBookInCar car={car}/>
                <div key = "bookimage" className="col-12 col-md-12">
                    <Card>
                        <CardTitle>共{car.length}本 总价为{totalPrice} </CardTitle>
                    </Card>
                </div>
            </div>
        </React.Fragment>
    );
}

export default Car;
