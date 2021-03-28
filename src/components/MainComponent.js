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
import Signup from "./SignupComponent";
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
            accounts: [{
                account:'admin',
                password:'111111',
                telnum:'111111',
                email:'111111',
                agree: true,
            }],
            onloginaccount: null
        };
        this.addBookToCar = this.addBookToCar.bind(this);
        this.addAccount = this.addAccount.bind(this);
        this.checkAccount = this.checkAccount.bind(this);
    }

    addBookToCar(book) {
        let newList = [...this.state.car, book];
        this.setState({car: newList});
    }

    addAccount(account) {
        let newList = [...this.state.accounts, account];

        this.setState({accounts: newList}, () => {
            console.log('加载完成')
            console.log(this.state.accounts);
        });

        alert("注册成功！");
        //return ("注册成功！");
    }

    checkAccount(loacalaccount) {
        if (this.state.accounts === undefined) {
            alert("查找失败！");
            return;
        }
        let local_accounts = this.state.accounts.filter((account) => account.account === loacalaccount.account && account.password === loacalaccount.password)
        if (local_accounts.length === 0) {
            alert("登陆失败！");

            return;
        }
        else {
            alert("登陆成功！");
            this.setState({onloginaccount: local_accounts[0]});

        }
    }

    buyBooks() {

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
                <Header onlogin = {this.state.onloginaccount}/>
                <Switch>
                    <Route path="/home" component={HomePage} />
                    <Route exact path="/menu" component={() => <Menu books={this.state.books}/>}/>
                    <Route path="/menu/:bookId" component={BookWithId} />
                    <Route exact path="/contactus" component={Contact} />
                    <Route exact path="/signup" component={() => <Signup accounts={this.state.accounts} addAccount = {this.addAccount}/>}/>
                    <Route exact path="/login" component={() => <Login accounts={this.state.accounts} checkAccount = {this.checkAccount}/>}/>
                    <Route exact path="/car" component={() => <Car car={this.state.car} buyBooks = {this.buyBooks}/>}/>
                    <Route exact path="/aboutus" component={() => <About leaders={this.state.leaders}/>}/>
                    <Redirect to="/home" />
                </Switch>
                <Footer/>
            </div>
        );
    }
}

export default Main;
