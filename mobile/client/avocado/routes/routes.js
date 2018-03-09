import { StackNavigator } from 'react-navigation';
import TestScreen from "../screens/TestScreen";

const MainNavigator = StackNavigator({
    Test: { screen: TestScreen }
});

export default MainNavigator;