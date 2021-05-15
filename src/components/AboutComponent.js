import React from 'react';
import {
    Breadcrumb,
    BreadcrumbItem,
    Card,
    CardBody,
    CardHeader,
    CardImg,
    CardImgOverlay,
    CardTitle,
    Media
} from 'reactstrap';
import { Link } from 'react-router-dom';

function RenderLeader(props) {
    return (
        <React.Fragment>
            <Media>
                <div className="col-12 col-md-2">
                <Media left href="#">
                    <Media width="100%"  object src={props.leader.image} alt="Generic placeholder image" />
                </Media>
                </div>
                <Media body>
                    <Media heading>
                        {props.leader.name}
                    </Media>
                        {props.leader.designation}
                    <Media>
                        <Media body>
                            {props.leader.description}
                        </Media>
                    </Media>
                </Media>
            </Media>
            <br/><br/>
        </React.Fragment>
    );
}

function About(props) {

    const leaders = props.leaders.map((leader) => {
        return (
            // <p>Leader {leader.name}</p>
            <RenderLeader leader={leader} />
        );
    });

    return(
        <div className="container">
            <div className="row">
                <Breadcrumb>
                    <BreadcrumbItem><Link to="/home">主页</Link></BreadcrumbItem>
                    <BreadcrumbItem active>关于我们</BreadcrumbItem>
                </Breadcrumb>
                <div className="col-12">
                    <h3>关于我们</h3>
                    <hr />
                </div>
            </div>
            <div className="row row-content">
                <div className="col-12 col-md-6">
                    <h2>我们的历史</h2>
                    <p>从1999年11月正式开通至今，e-book已从早期的网上卖书拓展到网上卖各品类百货，包括图书音像、美妆、家居、母婴、服装和3C数码等几十个大类，数百万种商品。物流方面，e-book在全国600个城市实现“111全天达”，在1200多个区县实现了次日达，货到付款(COD)方面覆盖全国2700个区县。e-book于美国时间2010年12月8日在纽约证券交易所正式挂牌上市，成为中国第一家完全基于线上业务、在美国上市的B2C网上商城。2016年5月28日，e-book宣布与e-book控股有限公司和e-book合并有限公司签署最终的合并协议与计划。2016年9月12日，e-book股东投票批准了该私有化协议。e-book从纽交所退市，变成一家私人控股企业。</p>
                </div>
                <div className="col-12 col-md-5">
                    <Card>
                        <CardHeader className="bg-primary text-white">公司概况</CardHeader>
                        <CardBody>
                            <dl className="row p-1">
                                <dt className="col-6">开通时间</dt>
                                <dd className="col-6">3 Nov. 1999</dd>
                                <dt className="col-6">主要股东</dt>
                                <dd className="col-6">SK Inc.</dd>
                                <dt className="col-6">上一年度营业额</dt>
                                <dd className="col-6">$1,250,375, 000, 000</dd>
                                <dt className="col-6">雇员数量</dt>
                                <dd className="col-6">40, 000, 000</dd>
                            </dl>
                        </CardBody>
                    </Card>
                </div>
                <div className="col-12">
                    <Card>
                        <CardBody className="bg-faded">
                            <blockquote className="blockquote">
                                <p className="mb-0">书中自有黄金屋。</p>
                                <footer className="blockquote-footer">Yogi Berra,
                                    <cite title="Source Title">The Wit and Wisdom of Yogi Berra,
                                        P. Pepe, Diversion Books, 2014</cite>
                                </footer>
                            </blockquote>
                        </CardBody>
                    </Card>
                </div>
            </div>
            <div className="row row-content">
                <div className="col-12">
                    <h2>主要合伙人</h2>
                </div>
                <div className="col-12">
                    <Media list>
                        {leaders}
                    </Media>
                </div>
            </div>
        </div>
    );
}

export default About;
