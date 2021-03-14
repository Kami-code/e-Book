import React from "react";
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle} from "reactstrap";


function RenderCard({item}) {
    return (
        <Card>
            <CardImg src={item.image} alt={item.name} />
            <CardBody>
                <CardTitle>{item.name}</CardTitle>
                {item.designation ? <CardSubtitle>{item.designation}</CardSubtitle> : null}
                <CardText>{item.description}</CardText>
            </CardBody>
        </Card>
    );
}

function RenderFlash() {
    return (
        <Card>
            <CardImg src='/assets/images/home1.jpg' alt='' />
        </Card>
    );
}

function Home(props) {
    return(
        <div className="container">
            <div className="row align-items-start">
                <div className="col-12 col-md m-1">
                    <RenderFlash/>
                </div>
            </div>
            <h4>Home</h4>
        </div>
    );
}

export default Home;
