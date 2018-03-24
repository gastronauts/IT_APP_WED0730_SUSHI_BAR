import { StackNavigator } from 'react-navigation';
import { TabNavigator } from 'react-navigation';
import MenuScreen from '../screens/MenuScreen';
import OrderScreen from '../screens/OrderScreen';
import CartScreen from '../screens/CartScreen';
import TinderRollScreen from '../screens/TinderRollScreen';
import constants from '../constants/constants'


const Tabs = TabNavigator({
    Menu:   { screen: MenuScreen },
    Order:  {screen: OrderScreen}
},{
    tabBarPosition: 'bottom',
    swipeEnabled: true,
    tabBarOptions: {
        showIcon: true,
        style: {
            backgroundColor: constants.colors.darkGrey,
            height: 65
        },
        labelStyle: {
            fontSize: 10
        },
    }
});

const MainNavigator = StackNavigator({
    Menu:   { screen: Tabs },
    Order:  {screen: OrderScreen},
    Cart:   {screen: CartScreen},
    TinderRoll: {screen: TinderRollScreen}
});

export default MainNavigator;