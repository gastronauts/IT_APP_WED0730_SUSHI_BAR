import React, {Component} from 'react';
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {View, Text, Image, StyleSheet} from 'react-native';
import {Icon} from "react-native-elements";
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import { NavigationActions } from "react-navigation";

class TinderRollScreen extends Component {

    componentDidMount() {
        this.props.navigation.setParams({
            lang: this.props.language,
            sum: this.props.sum
        });
    }

    static navigationOptions = ({navigation}) => {
        const {state, setParams, navigate} = navigation;
        const params = state.params || {};
        return {
            title: translate(dictionary, 'tinderRollTitle', params.lang || 'en').tinderRollTitle,
            headerStyle: {
                backgroundColor: constants.colors.darkGrey
            },
            headerTitleStyle: {
                color: constants.colors.white
            },
            headerLeft: (
                <Icon
                    name="ios-arrow-back"
                    type="ionicon"
                    size={28}
                    color="#fff"
                    iconStyle={style.backIconStyle}
                    onPress={() => {
                        const backAction = NavigationActions.back();
                        navigation.dispatch(backAction)
                    }}
                />
            ),
            headerRight: (
                <View style={style.navHeaderRight}>
                    <View style={style.cartView}>
                        <Icon
                            name="ios-cart-outline"
                            type="ionicon"
                            size={24}
                            color="#fff"
                            iconStyle={style.headerRightIconCart}
                            onPress={() => {
                                navigation.navigate("Cart");
                            }}
                        />
                        <Text style={style.cartAmountStyle}>{params.sum} zl</Text>
                    </View>
                </View>
            )
        }
    };

    render() {
        const {navigate} = this.props.navigation;
        return (
            <Text>Tinder Roll screen content</Text>
        )
    }
}

const style = StyleSheet.create({
    backIconStyle: {
        paddingLeft: 15
    },
    navHeaderLeft: {
        width: 30,
        height: 28,
        marginLeft: 15,
        marginTop: 10,
        marginBottom: 8
    },
    navHeaderRight: {
        flexDirection: "row",
        alignItems: "stretch",
    },
    headerRightIconFire: {
        paddingRight: 10,
    },
    headerRightIconCart: {
        paddingRight: 10,
        marginRight: 15,
        paddingLeft: 20
    },
    cartView: {
        flexDirection: "column",
        justifyContent: 'center',
        alignItems: 'center'
    },
    cartAmountStyle: {
        color: '#fff',
        fontSize: 10
    }
});

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage,
    sum: state.CartReducer.sum
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({}, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(TinderRollScreen);