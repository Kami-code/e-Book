import React, { Component } from "react";
import Menu from "./MenuComponents";
import '../App.css';
import {BOOKS} from '../shared/books';
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
            accounts: ACCOUNTS,
            onLoginAccount: null
        };
        this.addBookToCar = this.addBookToCar.bind(this);
        this.addAccount = this.addAccount.bind(this);
        this.checkAccount = this.checkAccount.bind(this);
        this.changeInventory = this.changeInventory.bind(this);
        this.changePrice = this.changePrice.bind(this);
        this.buyCar = this.buyCar.bind(this);
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
    }

    checkAccount(localAccount) {
        if (this.state.accounts === undefined) {
            alert("查找失败！");
            return false;
        }
        let localAccounts = this.state.accounts.filter((account) => account.account === localAccount.account && account.password === localAccount.password)
        if (localAccounts.length === 0) {
            alert("登陆失败！");
            return false;
        }
        else {
            alert("登陆成功！");
            this.setState({onLoginAccount: localAccounts[0]});
            sessionStorage.setItem("userName", localAccount.account);
            if (localAccounts[0].isAdmin === true) sessionStorage.setItem("isAdmin", "true");
            else sessionStorage.removeItem("isAdmin");
            return true;
        }
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

    buyCar() {
        const carLength = this.state.car.length;
        const bookLength = this.state.books.length;

        for (let i = 0; i < carLength; i++) {
            let index = -1;
            for (let j = 0; j < bookLength; j++) {
                if (this.state.books[j].id === this.state.car[i].id) {
                    index = j;
                }
            }
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
        this.setState({ //清空购物车
            car:[]
        });
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
                    <PrivateRoute exact path="/car" component={() => <Car car={this.state.car} buyCar={this.buyCar}/>}/>
                    <Route exact path="/aboutus" component={() => <About leaders={this.state.leaders}/>}/>
                    <PrivateRoute exact path="/adminmenu" component={() => <AdminMenu books={this.state.books}/>}/>
                    <Redirect to="/home" />
                </Switch>
                <Footer/>
            </div>
        );
    }
}

export default Main;
