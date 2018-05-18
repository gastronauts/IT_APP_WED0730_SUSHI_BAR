import React, {Component} from 'react';
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {View, Text, StyleSheet, ScrollView, BackHandler} from 'react-native';
import {Icon} from "react-native-elements";
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import CartItemComponent from '../containers/CartItemComponent';
import CartInfoComponent from '../containers/CartInfoComponent';
import { NavigationActions } from "react-navigation";

class CartScreen extends Component {
    componentWillMount() {
      BackHandler.addEventListener('hardwareBackPressed', () => {
          const navigateAction = NavigationActions.navigate({
              routeName: 'Menu',
              params: {
                  sum : this.props.sum,
                  lang: this.props.lang
              }
          });
          this.props.navigation.dispatch(navigateAction);
          return true;
      })
    };

    componentWillUnmount() {
        BackHandler.removeEventListener('hardwareBackPress');
    }

    componentDidMount() {
        this.props.navigation.setParams({
            lang: this.props.language,
            sum: this.props.sum
        });
    };

    componentWillReceiveProps(nextProps){
        //console.log('CART', nextProps.sum, this.props.navigation.state.params.sum, this.props.navigation);
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
            title: translate(dictionary, 'cartTitle', params.lang || 'en').cartTitle,
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
                        const navigateAction = NavigationActions.navigate({
                            routeName: 'Menu',
                            params: {
                                sum : params.sum,
                                lang: params.lang
                            }
                        });
                        navigation.dispatch(navigateAction);
                    }}
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
                        />
                        <Text style={style.cartAmountStyle}>{params.sum} zl</Text>
                    </View>
                </View>
            )
        }
    };

    render() {
        let items = this.props.itemsInCart.map( (item, index) => {
            return <CartItemComponent
                navi={this.props.navigation}
                mealId={item.mealId}
                mealName={item.mealName}
                ingredients={item.ingredients}
                price={item.price}
                image={item.image}
                amount={item.amount}
                key={index}
                details={item.details}
            />
        });

        if(items.length === 0){
            items = <Text style={style.emptyCartTextStyle}>
                {translate(dictionary, 'emptyCartText', this.props.lang || 'en').emptyCartText}
            </Text>
        }

        const {navigate} = this.props.navigation;
        return (
            <ScrollView>
                <CartInfoComponent
                    navi={this.props.navigation}
                />
                {items}
            </ScrollView>
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
    },
    emptyCartTextStyle: {
        fontSize: 20,
        marginTop: 20,
        marginLeft: 10,
        marginRight:10,
        textAlign: 'center'
    }
});

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage,
    sum: state.CartReducer.sum,
    itemsInCart: state.CartReducer.itemsInCart
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({}, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(CartScreen);