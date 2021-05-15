import React, { Component } from "react";
import {Card, CardBody, CardImg, CardText, CardTitle, Media} from "reactstrap";
import {Link} from "react-router-dom";
import {COMMENTS} from "../shared/comments";
import {PROMOTIONS} from "../shared/promotions";
import {LEADERS} from "../shared/leaders";
import {ACCOUNTS} from "../shared/accounts";





class Order extends Component{
    constructor(props) {
        super(props);
        this.state = {
            orders: this.props.orders,
            books: this.props.books
        };
        this.setState({orders:this.props.orders});
        this.RenderOrder = this.RenderOrder.bind(this);
        fetch('http://localhost:9090/order/' + String(sessionStorage.getItem("user_id")),{
            method:'GET'
        })
            .then(res =>res.json())
            .then((data) => {
                console.log(data);
                this.setState({orders:data});
            });
    }

    RenderOrder() {
        if (this.state.orders === null || this.state.orders === undefined || this.state.orders.length === 0) {
            return (
                <div key = "order_div" className="col-12 col-md-12">
                    <Card>
                        <h1>暂无订单</h1>
                    </Card>
                </div>
            );
        }

        const renderedOrders = this.state.orders.map((order, index) => {
            const renderedItems = order.order_item[0].map((item, index) => {
                let book = this.state.books.filter((book) => (book.id === item.itemid))[0];
                return (

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

                );
            });
            console.log("order_items = ", renderedItems);
            let date = new Date(order.order_master.createtime);
            // 格式化日期
            let dateTime = date.toLocaleString();
            // let ans = await fetch('http://localhost:9090/order_item/' + String(order.orderid), {method:'GET'}).data;
            // console.log(ans);
            return (
                <div key = {index} className="col-12 col-md-12">
                    <Card>
                        <Media>
                            <div className="col-12 col-md-7 m-2">
                                <Media body>
                                    <Media heading>{order.id}</Media>
                                    <Media>
                                        <Media>订单金额：{order.order_master.payment}</Media>
                                        <Media>下单时间：{dateTime}</Media>

                                    </Media>
                                </Media>
                                {renderedItems}
                            </div>
                        </Media>
                    </Card>
                </div>
            );
        });

        return (
            <React.Fragment>
                {renderedOrders}
            </React.Fragment>
        );
    }

    render() {
        let orders = this.RenderOrder();
        return (
            <React.Fragment>
                <div className="container">
                    {orders}
                </div>
            </React.Fragment>
        );
    }


}

export default Order;
