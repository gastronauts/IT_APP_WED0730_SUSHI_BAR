import { StackNavigator } from 'react-navigation';
import MenuScreen from '../screens/MenuScreen';

const MainNavigator = StackNavigator({
    Menu: { screen: MenuScreen }
});

export default MainNavigator;