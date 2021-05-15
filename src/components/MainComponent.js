import React, { Component } from "react";
import Menu from "./MenuComponents";
import '../App.css';
import { COMMENTS } from "../shared/comments";
import { LEADERS } from "../shared/leaders";
import { PROMOTIONS } from "../shared/promotions";
import {ACCOUNTS} from "../shared/accounts";
import BookDetail from "./BookDetailComponent";
import Contact from "./ContactComponent";
import Header from "./HeaderComponent";
import Footer from "./FooterComponent";
import Home from "./HomeComponent";
import {Switch, Route, Redirect} from "react-router-dom";
import About from "./AboutComponent";
import Signup from "./SignupComponent";
import Login from "./LoginComponent";
import Car from "./CarComponent";
import AdminMenuComponents from "./AdminMenuComponent";
import AdminMenu from "./AdminMenuComponent";
import AdminBookDetail from "./AdminBookDetailComponent";
import PrivateRoute from "./PrivateRouteComponent";
import AdminAddBook from "./AdminAddBookComponent";
import Order from "./OrderComponent";


class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: Array(),
            comments: COMMENTS,
            promotion: PROMOTIONS,
            leaders: LEADERS,
            currentBook: null,
            car: [],
            accounts: ACCOUNTS,
            onLoginAccount: null,
            orders: Array()
        };
        fetch('http://localhost:9090/books',{
            method:'GET'
        })
            .then(res =>res.json())
            .then((data) => {
                console.log(data)
                this.setState({books:data});
            })
        fetch('http://localhost:9090/order/' + String(sessionStorage.getItem("user_id")),{
            method:'GET'
        })
            .then(res =>res.json())
            .then((data) => {
                console.log(data)
                this.setState({orders:data});
            })
        this.addBookToCar = this.addBookToCar.bind(this);
        this.addBook = this.addBook.bind(this);
        this.buyBook = this.buyBook.bind(this);
        this.changeInventory = this.changeInventory.bind(this);
        this.changePrice = this.changePrice.bind(this);
    }

    addBookToCar(book) {
        let book_summary = {
            id: book.id,
            number: 1,
            price: book.price
        }
        let newList = [...this.state.car, book_summary];
        this.setState({car: newList});
    }

    addBook(book) {
        let newList = [...this.state.books, book];
        this.setState({books: newList});
        console.log(this.state.books);
    }

    changeInventory(bookId, inventory) {
        let len = this.state.books.length;
        let index = -1;
        for (let i = 0; i < len; i++) {
            if (this.state.books[i].id === bookId) {
                index = i;
            }
        }
        if (index === -1) return false;
        let localBooks = this.state.books;
        localBooks[index].inventory = inventory;
        this.setState({books:localBooks});
        return true;
    }

    buyBook(index) {
        if (index === -1) {
            alert("系统数据出错！不存在\"" + String(this.state.car[i].name) + "\"");
        }
        else if (this.state.books[index].inventory <= 0){
            alert ("\"" + String(this.state.car[i].name) + "\"库存不足！");
        }
        else {
            this.changeInventory(this.state.books[index].id, this.state.books[index].inventory - 1);
            alert("购买\"" + String(this.state.car[i].name) + "\"成功！");
        }
    }

    changePrice(bookId, price) {
        let len = this.state.books.length;
        let index = -1;
        for (let i = 0; i < len; i++) {
            if (this.state.books[i].id === bookId) {
                index = i;
            }
        }
        if (index === -1) return false;
        let localBooks = this.state.books;
        localBooks[index].price = price;
        this.setState({books:localBooks});
        return true;
    }

    render() {
        const HomePage = () => {
            return (
                <Home book={this.state.books.filter((book)=> book.featured)[0]}
                    promotion={this.state.promotion.filter((promo) => promo.featured)[0]}
                      leader={this.state.leaders.filter((leader) => leader.featured)[0]}
                />
            )
        }

        const BookWithId = ({match}) =>{
            return (
                <BookDetail book={this.state.books.filter((book) => book.id === parseInt(match.params.bookId,10))[0]}
                    comments={this.state.comments.filter((comment) => comment.bookId === parseInt(match.params.bookId,10))}
                    addBookToCar={this.addBookToCar}
                />
            );
        };
        const AdminBookWithId = ({match}) =>{
            return (
                <AdminBookDetail book={this.state.books.filter((book) => book.id === parseInt(match.params.bookId,10))[0]}
                                 changeInventory={this.changeInventory}
                                 changePrice={this.changePrice}
                />
            );
        };

        return (
            <div>
                <Header onLoginAccount = {this.state.onLoginAccount}/>
                <Switch>
                    <Route path="/home" component={HomePage} />
                    <PrivateRoute exact path="/menu" component={() => <Menu books={this.state.books}/>}/>
                    <PrivateRoute path="/menu/:bookId" component={BookWithId} />
                    <PrivateRoute path="/adminmenu/:bookId" component={AdminBookWithId} />
                    <Route exact path="/contactus" component={Contact} />
                    <Route exact path="/signup" component={() => <Signup accounts={this.state.accounts} addAccount = {this.addAccount}/>}/>
                    <Route exact path="/login" component={() => <Login accounts={this.state.accounts} checkAccount = {this.checkAccount}/>}/>
                    <PrivateRoute exact path="/car" component={() => <Car books = {this.state.books} car={this.state.car} />}/>
                    <PrivateRoute exact path="/order" component={() => <Order books = {this.state.books} orders={this.state.orders} />}/>
                    <Route exact path="/aboutus" component={() => <About leaders={this.state.leaders}/>}/>
                    <PrivateRoute exact path="/adminmenu" component={() => <AdminMenu books={this.state.books}/>}/>
                    <PrivateRoute exact path="/adminAddBook" component={() => <AdminAddBook addBook={this.addBook}/>}/>
                    <Redirect to="/home" />
                </Switch>
                <Footer/>
            </div>
        );
    }
}

export default Main;
