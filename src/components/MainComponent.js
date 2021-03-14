import React, { Component } from "react";
import Menu from "./MenuComponents";
import '../App.css';
import {BOOKS} from '../shared/books';
import { COMMENTS } from "../shared/comments";
import { LEADERS } from "../shared/leaders";
import { PROMOTIONS } from "../shared/promotions";
import BookDetail from "./BookdetailComponent";
import Contact from "./ContactComponent";
import Header from "./HeaderComponent";
import Footer from "./FooterComponent";
import Home from "./HomeComponent";
import {Switch, Route, Redirect} from "react-router-dom";
import About from "./AboutComponent";
import Login from "./LoginComponent";
import Car from "./CarComponent";

class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: BOOKS,
            comments: COMMENTS,
            promotion: PROMOTIONS,
            leaders: LEADERS,
            currentBook: null,
            car: [],
            accounts: []
        };
        this.addBookToCar=this.addBookToCar.bind(this);
    }

    addBookToCar(book) {
        let newList = [...this.state.car, book];
        this.setState({car: newList});
    }

    addAccount(account) {
        let newList = [...this.state.accounts, account];
        this.setState({accounts: newList});
        console.log(this.state.accounts);
        alert(this.state.accounts);
        return ("注册成功！");
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
        return (
            <div>
                <Header/>
                <Switch>
                    <Route path="/home" component={HomePage} />
                    <Route exact path="/menu" component={() => <Menu books={this.state.books}/>}/>
                    <Route path="/menu/:bookId" component={BookWithId} />
                    <Route exact path="/contactus" component={Contact} />
                    <Route exact path="/login" component={() => <Login accounts={this.state.accounts}/>}/>
                    <Route exact path="/car" component={() => <Car car={this.state.car}/>}/>
                    <Route exact path="/aboutus" component={() => <About leaders={this.state.leaders}/>}/>
                    <Redirect to="/home" />
                </Switch>
                <Footer/>
            </div>
        );
    }
}

export default Main;
