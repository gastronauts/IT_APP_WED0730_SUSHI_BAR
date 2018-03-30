import { combineReducers } from "redux";
import NavigationReducer from './NavigationReducer'
import CartReducer from './CartReducer'
import { i18nReducer } from 'redux-react-native-i18n'

export default combineReducers({
    NavigationReducer,
    CartReducer,
    i18nReducer
});