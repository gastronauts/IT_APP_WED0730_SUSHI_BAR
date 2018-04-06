import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { View, StyleSheet, Image, Modal,ScrollView } from 'react-native';
import {Text, Button, Icon} from 'react-native-elements';
import PropTypes from 'prop-types'
import constants from '../constants/constants'
import translate from "translatr";
import dictionary from '../translations/translations';
import OrderMealItemComponent from './OrderMealItemComponent';

class OrderItemComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            modalVisible: false
        };
    };



    render(){

        let items = this.props.meals.map( (meal,index) => {
            return <OrderMealItemComponent
                mealId={meal.mealId}
                mealName={meal.mealName}
                ingredients={meal.ingredients}
                price={meal.price}
                image={meal.image}
                amount={meal.amount}
                estimatedTime={meal.estimatedTime}
                key={index}
            />
        });

        return (
            <View style = {style.cardStyle}>
                <View style = {style.infoStyle}>
                    <Text style={style.infoRowStyle}>
                        {translate(dictionary, 'status', this.props.lang || 'en').status}: {this.props.status}
                    </Text>
                    <Text style={style.infoRowStyle}>
                        {translate(dictionary, 'sum', this.props.lang || 'en').sum}: {this.props.sum}
                    </Text>
                    <Text>
                        {translate(dictionary, 'estimatedTime', this.props.lang || 'en').estimatedTime}: {this.props.estimatedTime}
                    </Text>
                </View>
                <View style={style.buttonStyle}>
                    <Button
                        title='Check details'
                        color={constants.colors.white}
                        fontSize={12}
                        buttonStyle={{
                            backgroundColor: constants.colors.green,
                            "justifyContent": "center",
                            "alignItems": "center",
                        }}
                        onPress = { () => {
                            this.setState({
                                modalVisible: true
                            })
                        }}
                    />
                </View>


                <Modal
                    animationType="slide"
                    transparent={true}
                    visible={this.state.modalVisible}
                    onRequestClose={() => {
                        this.setState({ modalVisible: false });
                    }}
                >
                    <View style={style.modalStyle}>
                        <View style={style.modalContentStyle}>
                            <View style={style.buttonExitStyle}>
                                <Text
                                    style={style.XTextStyle}
                                    onPress={ () => {
                                        this.setState({
                                            modalVisible: false
                                        })
                                    }}
                                >
                                    X
                                </Text>

                            </View>
                            <ScrollView>
                                {items}
                            </ScrollView>
                        </View>
                    </View>
                </Modal>
            </View>
        )
    }
}

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({

    }, dispatch)
};

const style = StyleSheet.create({
    cardStyle:{
        flexDirection: "row",
        alignItems: "stretch",
        marginTop: 10,
        marginRight: 10,
        marginLeft: 10,
        marginBottom: 0,
        borderBottomColor: "#ddd",
        borderBottomWidth: 3,
        borderRightColor: "#ddd",
        borderRightWidth: 3,
        borderTopColor: "#ddd",
        borderTopWidth: 1,
        borderLeftColor: "#ddd",
        borderLeftWidth: 1,
        backgroundColor: "#fff",
        padding: 20
    },
    modalStyle: {
        flex: 1,
        backgroundColor: "rgba(0,0,0,0.5)"
    },
    modalContentStyle: {
        flex: 1,
        marginTop: 25,
        marginBottom: 25,
        marginLeft: 25,
        marginRight: 25,
        backgroundColor: "#fff",
    },
    infoStyle: {
        flexDirection: "column",
        alignItems: "stretch",
        flex: 5
    },
    buttonStyle: {
        alignItems: 'stretch',
        justifyContent: 'center',
        flex: 4
    },
    infoRowStyle: {
        paddingBottom: 3
    },
    buttonExitStyle: {
        flexDirection: 'row',
        alignItems: 'flex-end',
        backgroundColor: constants.colors.red,
        alignSelf: 'flex-end',
        borderRadius:100,
        width:20,
        height:20,
        marginTop:5,
        marginRight: 5
    },
    XTextStyle: {
        color: constants.colors.white,
        paddingLeft: 7,
        fontSize:10,
    },

});

OrderItemComponent.propTypes = {
    orderId: PropTypes.string,
    status: PropTypes.string,
    sum: PropTypes.number,
    estimatedTime: PropTypes.number,
    meals: PropTypes.array
};


export default connect(mapStateToProps, mapDispatchToProps)(OrderItemComponent);