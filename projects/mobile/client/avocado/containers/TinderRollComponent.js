import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {StyleSheet, ToastAndroid} from 'react-native';
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import {addItemToCart} from "../actions";
import SwipeCards from 'react-native-swipe-cards';
import TinderRollContentComponent from './TinderRollContentComponent';

class TinderRollComponent extends Component {

    handleYup = (card) => {
        this.props.addItemToCart(
            card.mealId,
            card.mealName,
            card.ingredients,
            card.price,
            1,
            card.image,
            card.estimatedTime);

        ToastAndroid.showWithGravity(
            translate(dictionary, 'mealAdded', this.props.lang || 'en').mealAdded,
            ToastAndroid.SHORT,
            ToastAndroid.CENTER
        );
    };


    render(){
        return (
                <SwipeCards style={{flex:1}}
                    cards={this.props.menu}
                    renderCard={(item) =>
                        <TinderRollContentComponent
                            mealId={item.mealId}
                            image={item.image}
                            mealName={item.mealName}
                            ingredients={item.ingredients}
                            price={item.price}
                            estimatedTime={item.estimatedTime}
                            details={item.details}
                        />}
                    handleYup={this.handleYup}
                    hasMaybeAction={false}
                />

        )
    }
}

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage,
    menu: state.MenuReducer.menu
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        addItemToCart:addItemToCart
    }, dispatch)
};


const style = StyleSheet.create({
    mainViewStyle: {
        marginLeft: 15,
        marginTop: 15,
        flexDirection: 'column',
        alignItems: 'center',
        backgroundColor: 'white'
    },


});


export default connect(mapStateToProps, mapDispatchToProps)(TinderRollComponent);