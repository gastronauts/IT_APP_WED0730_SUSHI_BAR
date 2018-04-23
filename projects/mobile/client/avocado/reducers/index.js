import { combineReducers } from "redux";
import NavigationReducer from './NavigationReducer';
import CartReducer from './CartReducer';
import OrderReducer from './OrderReducer';
import MenuReducer from './MenuReducer';
import { i18nReducer } from 'redux-react-native-i18n';

export default combineReducers({
    NavigationReducer,
    CartReducer,
    OrderReducer,
    MenuReducer,
    i18nReducer
});