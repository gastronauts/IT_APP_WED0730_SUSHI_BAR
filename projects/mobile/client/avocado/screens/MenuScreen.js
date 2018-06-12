import React, {Component} from 'react';
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {View, Text, Image, StyleSheet, ScrollView, Modal} from 'react-native';
import {Icon} from "react-native-elements";
import icon from '../assets/roundLogoWithoutBackground.png';
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import MenuItemComponent from '../containers/MenuItemComponent';
import UnavailableMenuItemCoomponent from '../containers/UnavailableMenuItemComponent';
import {getCurrentMenu} from '../actions/index';
import ChooseTableComponent from '../containers/ChooseTableComponent';

const cacheImage = images =>
    images.map(image => {
        if (typeof image === "string") return Image.prefetch(image);

        return Expo.Asset.fromModule(image).downloadAsync();
    });


class MenuScreen extends Component {
    constructor(props){
        super(props);
        this.state = {
            items: [],
            modalVisible: !(this.props.table && typeof(this.props.table))
        }
    }

    closeModal = () => {
        this.setState({
            modalVisible: false
        })
    };

    componentWillMount() {
        this._loadAssetsAsync();

        let me = this;
        this.props.getCurrentMenu().then(() => {
            let items = this.props.menu.map( (item,index) => {
                return item.possibleToDo ? <MenuItemComponent
                    navi={this.props.navigation}
                    mealId={item.mealId}
                    mealName={item.mealName}
                    ingredients={item.ingredients}
                    price={item.price}
                    estimatedTime={item.estimatedTime}
                    image={item.image}
                    key={index}
                    details={item.details}
                />
                    : <UnavailableMenuItemCoomponent
                        navi={this.props.navigation}
                        mealId={item.mealId}
                        mealName={item.mealName}
                        ingredients={item.ingredients}
                        price={item.price}
                        estimatedTime={item.estimatedTime}
                        image={item.image}
                        key={index}
                        details={item.details}
                    />
            });
            me.setState({
                items: items
            });
        });
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
        //console.log('MENU',  nextProps.sum, this.props.navigation.state.params.sum,this.props.navigation);
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


        const {navigate} = this.props.navigation;
        return (
            <ScrollView>
                {this.state.items}
                <Modal
                    animationType="slide"
                    transparent={true}
                    visible={this.state.modalVisible}
                    onRequestClose={() => {

                    }}
                >
                    <View style={style.modalStyle}>
                        <View style={style.modalContentStyle}>
                           <ChooseTableComponent
                               closeModal={this.closeModal}
                           />
                        </View>
                    </View>
                </Modal>
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

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage,
    sum: state.CartReducer.sum,
    menu: state.MenuReducer.menu,
    itemsDownloaded: state.MenuReducer.itemsDownloaded,
    table: state.OrderReducer.table
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        getCurrentMenu: getCurrentMenu
    }, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(MenuScreen);