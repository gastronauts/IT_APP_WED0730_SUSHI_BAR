import React from 'react';
import { Provider } from 'react-redux';
import store from './store';
import { i18nActions, Loc } from 'redux-react-native-i18n';
import translation from './translations/translations';
import languages from './translations/languages';
import MainNavigator from './routes/routes';

export default class App extends React.Component {
    render() {
        //language initialization
        store.dispatch( i18nActions.setDictionaries(translation));
        store.dispatch( i18nActions.setLanguages(languages));
        store.dispatch( i18nActions.setCurrentLanguage("en"));

          return (
          <Provider store={store}>
            <MainNavigator />
          </Provider>
          );
        }
}

