import { combineReducers } from "redux";
import TestReducer from './TestReducer';
import NavigationReducer from './NavigationReducer'
import { i18nReducer } from 'redux-react-native-i18n'

export default combineReducers({
    TestReducer,
    NavigationReducer,
    i18nReducer
});