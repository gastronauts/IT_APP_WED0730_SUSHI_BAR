import React, {Component} from 'react';
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {View, Text, Image, StyleSheet, ScrollView} from 'react-native';
import {Icon} from "react-native-elements";
import icon from '../assets/roundLogoWithoutBackground.png';
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import OrderItemComponent from '../containers/OrderItemComponent';

class OrderScreen extends Component {

    componentDidMount() {
        this.props.navigation.setParams({
            lang: this.props.language,
            sum: this.props.sum
        });
    };

    componentWillReceiveProps(nextProps){
        if(typeof this.props.navigation !== 'undefined' && typeof this.props.navigation.state.params !== 'undefined' && this.props.navigation.state.params.sum !== nextProps.sum){
            this.props.navigation.setParams({
                sum: nextProps.sum
            });
        }
    };


    static navigationOptions = ({navigation}) => {
        const {state, setParams, navigate} = navigation;
        const params = state.params || {};
        return {
            title: translate(dictionary, 'orderTitle', params.lang || 'en').orderTitle,
            tabBarLabel: translate(dictionary, 'orderTitle', params.lang || 'en').orderTitle,
            tabBarIcon: ({ tintColor }) => (
                <Icon
                    name="tasklist"
                    type="octicon"
                    size={28}
                    color={constants.colors.white} />
            ),
            headerStyle: {
                backgroundColor: constants.colors.darkGrey
            },
            headerTitleStyle: {
                color: constants.colors.white
            },
            headerLeft: (
                <Image
                    source={icon}
                    style={style.navHeaderLeft}
                />
            ),
            headerRight: (
                <View style={style.navHeaderRight}>
                    <Icon
                        name="fire"
                        type="material-community"
                        size={28}
                        color="#fff"
                        iconStyle={style.headerRightIconFire}
                        onPress={() => {
                            navigation.navigate("TinderRoll");
                        }}
                    />
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
        let items = this.props.orders.map( (order, index) => {
            return <OrderItemComponent
                orderId={order.orderId}
                estimatedTime={order.estimatedTime}
                sum={order.sum}
                status={order.status}
                meals={order.meals}
                key={index}
            />
        });

        const {navigate} = this.props.navigation;
        return (
            <ScrollView>
                {items}
            </ScrollView>
        )
    }
}

const style = StyleSheet.create({
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
    orders: state.OrderReducer.orders,
    sum: state.CartReducer.sum
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({}, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(OrderScreen);