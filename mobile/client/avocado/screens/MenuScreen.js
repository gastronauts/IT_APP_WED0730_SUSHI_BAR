import React, {Component} from 'react';
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {View, Text, Image, StyleSheet} from 'react-native';
import {Icon} from "react-native-elements";
import icon from '../assets/roundLogoWithoutBackground.png';
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import MenuItemComponent from '../containers/MenuItemComponent'

const cacheImage = images =>
    images.map(image => {
        if (typeof image === "string") return Image.prefetch(image);

        return Expo.Asset.fromModule(image).downloadAsync();
    });

class MenuScreen extends Component {
    componentWillMount() {
        this._loadAssetsAsync();
    }

    async _loadAssetsAsync() {
        const imagesAssets = cacheImage([icon]);
        await Promise.all([...imagesAssets]);
        this.setState({
            appIsReady: true
        });
    };

    componentDidMount() {
        this.props.navigation.setParams({
            lang: this.props.language,
            sum: this.props.sum
        });
    }

    componentWillReceiveProps(nextProps){
        //prawie
        if(this.props.sum !== nextProps.sum){
            this.props.navigation.setParams({
                sum: this.props.sum
            });
        }
    };

    static navigationOptions = ({navigation}) => {
        const {state, setParams, navigate} = navigation;
        const params = state.params || {};
        return {
            title: translate(dictionary, 'menuTitle', params.lang || 'en').menuTitle,
            tabBarLabel: translate(dictionary, 'menuTitle', params.lang || 'en').menuTitle,
            tabBarIcon: ({ tintColor }) => (
                <Icon
                    name="ios-restaurant-outline"
                    type="ionicon"
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
        let ingredients = [
            "salmon",
            "kabayaki sauce",
            "lettuce",
            "lettuce",
            "lettuce"
        ];
        const {navigate} = this.props.navigation;
        return (
            <View>
                <MenuItemComponent mealId={1} mealName="Futo Grill Kabayaki" ingredients={ingredients} price={20} image='http://cdn.upmenu.com/static/product-images/8ca52eae-4d4a-11e4-ac27-00163edcb8a0/1d7dc623-be25-11e7-93f9-525400841de1/2/large/california_lion_roll.jpg'/>
                <MenuItemComponent mealId={2} mealName="Futo Grill Salmon" ingredients={ingredients} price={20} image='http://cdn.upmenu.com/static/product-images/8ca52eae-4d4a-11e4-ac27-00163edcb8a0/1d7dc623-be25-11e7-93f9-525400841de1/2/large/california_lion_roll.jpg'/>
                <MenuItemComponent mealId={3} mealName="Futo Grill Tuna" ingredients={ingredients} price={20} image='http://cdn.upmenu.com/static/product-images/8ca52eae-4d4a-11e4-ac27-00163edcb8a0/1d7dc623-be25-11e7-93f9-525400841de1/2/large/california_lion_roll.jpg'/>

            </View>
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
    sum: state.CartReducer.sum
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({}, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(MenuScreen);