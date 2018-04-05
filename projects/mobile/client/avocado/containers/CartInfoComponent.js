import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { View, StyleSheet, Image, Modal } from 'react-native';
import {Text, Button, Icon} from 'react-native-elements';
import constants from '../constants/constants'
import translate from "translatr";
import dictionary from '../translations/translations';
import {addOrder, emptyCart} from '../actions/index';
import uuidv3 from 'uuid';



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
                            this.props.addOrder(uuidv3(), this.props.itemsInCart,'waiting', this.props.sum, this.props.estimatedTime).then( () => {
                                this.props.emptyCart();
                            })
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
    estimatedTime: state.CartReducer.estimatedTime,
    itemsInCart: state.CartReducer.itemsInCart
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        addOrder: addOrder,
        emptyCart:emptyCart
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