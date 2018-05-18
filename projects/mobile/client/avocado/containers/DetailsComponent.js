import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {View, StyleSheet, Image, ToastAndroid} from 'react-native';
import {Text,Button} from 'react-native-elements';
import PropTypes from 'prop-types';
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import {addItemToCart, removeItemFromCart, updateAmountOfItemInCart} from "../actions";


class DetailsComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            itemsAmount: this.props.itemsAmount,
            price: this.props.price * this.props.itemsAmount,
        };
    }

    render(){
        let ingredients = this.props.ingredients.join(', ');

        let btn;
        switch(this.props.whereOpened){
            case 'MENU':
                btn = <View>
                    <Text style={style.priceStyle}>
                        {this.state.price} zl
                    </Text>
                    <View style={style.counterRowStyle}>
                        <Text
                            style={style.amountMinusStyle}
                            onPress = { () => {
                                let decAmount = this.state.itemsAmount > 0 ? this.state.itemsAmount -1 : 0;
                                let newPrice = decAmount > 1 ? decAmount * this.props.price : this.props.price;
                                this.setState({
                                    itemsAmount: decAmount,
                                    price: newPrice
                                })
                            }}
                        >
                            -
                        </Text>
                        <View style={style.amountCounterStyle}>

                            <Text style={style.amountCounterText}>
                                {this.state.itemsAmount}
                            </Text>

                        </View>
                        <Text
                            style={style.amountPlusStyle}
                            onPress = { () => {
                                let incAmount = this.state.itemsAmount + 1;
                                let newPrice = incAmount > 1 ? incAmount * this.props.price : this.props.price;
                                this.setState({
                                    itemsAmount: incAmount,
                                    price: newPrice
                                });
                            }}
                        >
                            +
                        </Text>
                    </View>

                    <Button
                        title='+'
                        color={constants.colors.darkGrey}
                        buttonStyle={{
                            borderRadius: 100,
                            width: 30,
                            height:30,
                            backgroundColor: constants.colors.yellow,
                        }}
                        onPress = { () => {
                            if(this.state.itemsAmount > 0){
                                this.props.addItemToCart(
                                    this.props.mealId,
                                    this.props.mealName,
                                    this.props.ingredients,
                                    this.props.price,
                                    this.state.itemsAmount,
                                    this.props.image,
                                    this.props.estimatedTime);
                                this.setState({
                                    itemsAmount: 0,
                                    price: this.props.price
                                });
                                ToastAndroid.showWithGravity(
                                    translate(dictionary, 'mealAdded', this.props.lang || 'en').mealAdded,
                                    ToastAndroid.SHORT,
                                    ToastAndroid.CENTER
                                );
                                this.props.closeModal();
                            }
                            else{
                                ToastAndroid.showWithGravity(
                                    translate(dictionary, 'chooseMealAmount', this.props.lang || 'en').chooseMealAmount,
                                    ToastAndroid.SHORT,
                                    ToastAndroid.CENTER
                                );
                            }

                        }}
                    />
                </View>;
                break;
            case 'CART':
                btn = <View>
                    <Text style={style.priceStyle}>
                        {this.state.price} zl
                    </Text>
                    <View style={style.counterRowStyle}>
                        <Text
                            style={style.amountMinusStyle}
                            onPress = { () => {
                                let decAmount = this.state.itemsAmount > 1 ? this.state.itemsAmount -1 : 0;
                                let newPrice = decAmount * this.props.price;
                                if(decAmount !== 0){
                                    this.setState({
                                        itemsAmount: decAmount,
                                        price: newPrice
                                    });
                                    this.props.updateAmountOfItemInCart(this.props.mealId, decAmount);
                                    this.props.setCurrentStatePriceAndAmount(newPrice,decAmount);
                                }
                                else {
                                    this.props.removeItemFromCart(this.props.mealId);
                                    this.props.closeModal();
                                }
                            }}
                        >
                            -
                        </Text>
                        <View style={style.amountCounterStyle}>

                            <Text style={style.amountCounterText}>
                                {this.state.itemsAmount}
                            </Text>

                        </View>
                        <Text
                            style={style.amountPlusStyle}
                            onPress = { () => {
                                let incAmount = this.state.itemsAmount + 1;
                                let newPrice = incAmount > 1 ? incAmount * this.props.price : this.props.price;
                                this.setState({
                                    itemsAmount: incAmount,
                                    price: newPrice
                                });
                                this.props.updateAmountOfItemInCart(this.props.mealId, incAmount);
                                this.props.setCurrentStatePriceAndAmount(newPrice,incAmount)
                            }}
                        >
                            +
                        </Text>
                    </View>
                </View>;
                break;
            case 'ORDER':
                btn = <View>
                    <Text style={style.priceStyle}>
                        {this.state.price} zl
                    </Text>
                </View>;
                break;

        }


        return (
            <View style={{flexDirection:'column'}}>
                <View style={style.buttonExtiRow}>
                    <View style={style.buttonExitStyle}>
                        <Text
                            style={style.XTextStyle}
                            onPress={ () => {
                                this.props.closeModal();
                            }}
                        >
                            X
                        </Text>

                    </View>
                </View>
            <View style={style.mainViewStyle}>
                <Image
                    style={style.imageStyle}
                    source={{uri: this.props.image}}
                />
                <Text style={style.mealNameStyle}>
                    {this.props.mealName}
                </Text>
                <Text style={style.ingredientsStyle}>
                    {ingredients}
                </Text>
                <Text style={style.detailsStyle}>
                    {translate(dictionary, 'details', this.props.lang || 'en').details}:
                </Text>
                <Text style={style.detailsContentStyle}>
                    {this.props.details}
                </Text>

                {btn}


            </View>
            </View>
        )
    }
}

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        addItemToCart: addItemToCart,
        removeItemFromCart: removeItemFromCart,
        updateAmountOfItemInCart: updateAmountOfItemInCart
    }, dispatch)
};

DetailsComponent.propTypes = {
    mealId: PropTypes.number,
    image: PropTypes.string,
    mealName: PropTypes.string,
    ingredients: PropTypes.array,
    price: PropTypes.number,
    estimatedTime: PropTypes.number,
    itemsAmount: PropTypes.number,
    whereOpened: PropTypes.string,
    details: PropTypes.string
};

const style = StyleSheet.create({
    mainViewStyle: {
        marginLeft: 15,
        marginTop: 15,
        flexDirection: 'column',
        alignItems: 'center'
    },
    imageStyle: {
        width: 170,
        height: 170
    },
    mealNameStyle: {
        fontSize: 18,
        color: constants.colors.darkGrey,
        marginBottom: 10
    },
    ingredientsStyle: {
        fontSize: 11,
        lineHeight: 22,
        textAlign: 'center',
        color: constants.colors.darkGrey,
        paddingTop: 10,
        marginRight: 20,
        marginLeft: 20
    },
    buttonExtiRow:{
        flexDirection: 'row',
        alignItems: 'flex-end',
        alignSelf: 'flex-end',
        marginTop: 6,
        justifyContent: 'flex-end'
    },
    buttonExitStyle: {
        flexDirection: 'row',
        alignItems: 'flex-end',
        backgroundColor: constants.colors.red,
        alignSelf: 'flex-end',
        borderRadius:100,
        width:20,
        height:20
    },
    XTextStyle: {
        color: constants.colors.white,
        paddingLeft: 7,
        fontSize:10,
        paddingBottom: 4
    },
    detailsStyle: {
        marginTop: 40,
        fontSize: 10
    },
    detailsContentStyle: {
        marginTop: 10,
        fontSize: 10
    },
    priceStyle: {
        fontSize: 18,
        color: constants.colors.darkGrey,
        marginTop: 70,
        marginLeft: 16
    },
    amountCounterStyle: {
        backgroundColor: constants.colors.lightGrey,
        width: 16,
        height: 16,
        justifyContent: 'center',
        alignItems: 'center'
    },
    amountCounterText:{
        fontSize: 12,
        color: constants.colors.darkGrey
    },
    counterRowStyle: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom:18,
        marginTop: 30
    },
    amountPlusStyle: {
        paddingLeft: 5,
        paddingRight: 5,
        marginLeft: 5,
        marginRight: 5,
        backgroundColor: constants.colors.green,
        borderRadius:100,
        fontSize:10,
        width:15,
        height:15
    },
    amountMinusStyle: {
        paddingLeft: 5,
        paddingRight: 5,
        marginLeft: 5,
        marginRight: 5,
        backgroundColor: constants.colors.green,
        borderRadius:100,
        fontSize:12,
        width:15,
        height:15
    },

});


export default connect(mapStateToProps, mapDispatchToProps)(DetailsComponent);