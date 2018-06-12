import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {View, StyleSheet, Image} from 'react-native';
import {Text} from 'react-native-elements';
import PropTypes from 'prop-types';
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';

class TinderRollContentComponent extends Component {
    render(){

        let ingredients = this.props.ingredients.join(', ');

        return (
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
                <Text style={style.priceStyle}>
                    {this.props.price} zl
                </Text>
            </View>
        )
    }
}

TinderRollContentComponent.propTypes ={
    mealId: PropTypes.number,
    image: PropTypes.string,
    mealName: PropTypes.string,
    ingredients: PropTypes.array,
    price: PropTypes.number,
    estimatedTime: PropTypes.number,
    details: PropTypes.string
};

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage,
    menu: state.MenuReducer.menu
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({

    }, dispatch)
};


const style = StyleSheet.create({
    mainViewStyle: {
        marginLeft: 15,
        marginTop: 15,
        flexDirection: 'column',
        alignItems: 'center',
        backgroundColor: 'white',
        width: 380,
        height: 580
    },
    imageStyle: {
        width: 200,
        height: 200,
        marginBottom: 10,
        marginTop: 30
    },
    mealNameStyle: {
        fontSize: 18,
        color: constants.colors.darkGrey,
        marginBottom: 40
    },
    ingredientsStyle: {
        fontSize: 11,
        lineHeight: 22,
        textAlign: 'center',
        color: constants.colors.darkGrey,
        paddingTop: 10,
        marginRight: 30,
        marginLeft: 20
    },
    detailsStyle: {
        marginTop: 40,
        fontSize: 10
    },
    priceStyle: {
        fontSize: 18,
        color: constants.colors.darkGrey,
        marginTop: 70
    },
    detailsContentStyle: {
        marginTop: 10,
        fontSize: 10
    },
});


export default connect(mapStateToProps, mapDispatchToProps)(TinderRollContentComponent);