import { combineReducers } from "redux";
import NavigationReducer from './NavigationReducer';
import CartReducer from './CartReducer';
import OrderReducer from './OrderReducer';
import { i18nReducer } from 'redux-react-native-i18n';

export default combineReducers({
    NavigationReducer,
    CartReducer,
    OrderReducer,
    i18nReducer
});