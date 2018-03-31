import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { View, StyleSheet, Image, Modal } from 'react-native';
import {Text, Button, Icon} from 'react-native-elements';
import { removeItemFromCart, updateAmountOfItemInCart } from '../actions/index';
import PropTypes from 'prop-types'
import constants from '../constants/constants'
import translate from "translatr";
import dictionary from '../translations/translations';



class CartInfoComponent extends Component {

    render(){
        return (
            <View style={style.mainViewStyle}>
                <View style={{flex: 3}}>
                    <Text style={style.infoTextStyle}>{translate(dictionary, 'sum', this.props.lang || 'en').sum}: {this.props.sum} zl</Text>
                    <Text style={style.infoTextStyle}>{translate(dictionary, 'estimatedTime', this.props.lang || 'en').estimatedTime}: {this.props.estimatedTime} min</Text>
                </View>
                <View style={{flex: 2}}>
                    <Button
                        title = 'ORDER'
                        color = {constants.colors.white}
                        onPress = { () => {
                            console.log('pressed');
                        }}
                        buttonStyle={{
                            backgroundColor: constants.colors.green,
                        }}
                        disabled = {this.props.sum === 0}
                    />
                </View>
            </View>
        )
    }
}

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage,
    sum: state.CartReducer.sum,
    estimatedTime: state.CartReducer.estimatedTime
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        removeItemFromCart: removeItemFromCart,
        updateAmountOfItemInCart: updateAmountOfItemInCart
    }, dispatch)
};

const style = StyleSheet.create({
    infoTextStyle: {
        fontSize: 16,
        marginBottom: 5
    },
    mainViewStyle: {
        marginLeft: 15,
        marginTop: 15,
        flexDirection: 'row',
        alignItems: 'stretch'
    }

});


export default connect(mapStateToProps, mapDispatchToProps)(CartInfoComponent);