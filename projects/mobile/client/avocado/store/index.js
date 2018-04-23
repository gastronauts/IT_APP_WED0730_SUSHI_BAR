import { createStore, applyMiddleware } from 'redux';
import reducers from '../reducers';
import thunk from 'redux-thunk';
import promise from 'redux-promise-middleware';
import { createLogger } from 'redux-logger';

const store = createStore(reducers, {}, applyMiddleware(createLogger(), thunk, promise()));

export default store;