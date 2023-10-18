import AddCard from './AddCard';
import React from 'react';
import { Row, Col, Card } from 'antd';
//const { Meta } = Card;

const AddCards = function ({ cdata }) {
    let cardInfo = [];

    if (cdata && cdata.response && cdata.response.body && cdata.response.body.items && cdata.response.body.items.item) {
        //console.log(cdata.response.body.items.item[0].addr1);
        cardInfo = cdata.response.body.items.item;
    } 
    else {
        console.log("데이터 로딩중");
    }

    return (
        <>
            <Row gutter={0} style={{width: "1100px"}}>
                    {
                        cardInfo.map((card, i) => 
                            <Col span={6} key={i}>
                                <AddCard name={card.title} address={card.addr1} image={card.firstimage}/>
                            </Col>)
                    }
            </Row>
        </>

        
    );
}

export default AddCards;