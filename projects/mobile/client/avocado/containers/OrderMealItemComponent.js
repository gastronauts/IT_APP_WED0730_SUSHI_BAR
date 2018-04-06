import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { View, StyleSheet, Image, Modal } from 'react-native';
import {Text, Button, Icon} from 'react-native-elements';
import { removeItemFromCart, updateAmountOfItemInCart } from '../actions/index';
import PropTypes from 'prop-types'
import constants from '../constants/constants'
import translate from "translatr";



class OrderMealItemComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            itemsAmount: this.props.amount,
            price: this.props.price * this.props.amount,
            modalVisible: false
        };
    }


    render(){
        let ingredients = this.props.ingredients.join(', ');

        return (
            <View style = {style.cardStyle}>
                <View style = {style.imageViewStyle}>
                    <Image
                        style={style.imageStyle}
                        source={{uri: this.props.image}}
                    />
                </View>
                <View style = {style.descriptionViewStyle}>
                    <Text style={style.title}>{this.props.mealName}</Text>
                    <View style = {style.ingredientsStyle}>
                        <Text style={{fontSize:12}}>{ingredients}</Text>
                    </View>
                    <Text
                        style={{fontSize:12}}
                        onPress={() => {
                            this.setState({
                                modalVisible: true
                            })
                        }}
                    >
                        Details...
                    </Text>
                </View>
                <View style={style.amountColumnStyle}>
                    <View style={style.counterRowStyle}>
                        <Text style={style.amountMinusStyle} >
                            -
                        </Text>
                        <View style={style.amountCounterStyle}>

                            <Text style={style.amountCounterText}>
                                {this.state.itemsAmount}
                            </Text>

                        </View>
                        <Text style={style.amountPlusStyle}>
                            +
                        </Text>
                    </View>
                    <View style={style.priceColumnStyle}>
                        <Text>{this.state.price}zł</Text>
                    </View>
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
                            <Text style={style.modalTitle}> TITLE </Text>
                            <View>
                                <Text>Content</Text>
                            </View>
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
        removeItemFromCart: removeItemFromCart,
        updateAmountOfItemInCart: updateAmountOfItemInCart
    }, dispatch)
};

const style = StyleSheet.create({
    imageViewStyle: {
        flex: 1.5,
        marginLeft: -14,
        marginTop: -5
    },
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
    descriptionViewStyle:{
        flex: 3,
        flexDirection: "column"
    },
    imageStyle: {
        width:70,
        height: 70
    },
    ingredientsStyle: {
        flexDirection: "row",
        paddingTop: 5,
        paddingBottom: 5
    },
    priceColumnStyle:{
        justifyContent: 'center',
        alignItems: 'center',
        paddingTop: 25
    },
    amountColumnStyle:{
        flex: 0.7,
        alignItems: 'center',
        justifyContent: 'center',
        marginRight: -10,
        paddingLeft: 10
    },
    title: {
        fontSize: 16
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
        marginBottom:18
    },
    amountPlusStyle: {
        paddingLeft: 5,
        paddingRight: 5,
        marginLeft: 5,
        marginRight: 5,
        backgroundColor: constants.colors.lightGrey,
        borderRadius:100,
        fontSize:10,
        width:15,
        height:15,
        color: constants.colors.white
    },
    amountMinusStyle: {
        paddingLeft: 5,
        paddingRight: 5,
        marginLeft: 5,
        marginRight: 5,
        backgroundColor: constants.colors.lightGrey,
        borderRadius:100,
        fontSize:12,
        width:15,
        height:15,
        color: constants.colors.white
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

        alignItems: "center"
    },
    modalTitle: {
        fontSize: 20,
        marginTop: 25,
        marginBottom: 20,
        marginLeft: 20,
        marginRight: 20,
        textAlign: "center"
    }

});

OrderMealItemComponent.propTypes = {
    mealId: PropTypes.number,
    mealName: PropTypes.string,
    ingredients: PropTypes.array,
    price: PropTypes.number,
    image: PropTypes.string,
    amount: PropTypes.number,
    estimatedTime: PropTypes.number
};


export default connect(mapStateToProps, mapDispatchToProps)(OrderMealItemComponent);