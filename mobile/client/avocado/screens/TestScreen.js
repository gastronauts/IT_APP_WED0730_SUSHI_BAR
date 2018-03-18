import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { View } from 'react-native';
import TestComponent from '../containers/TestComponent';


class TestScreen extends Component {
    render(){
        return (
            <View>
                <TestComponent />
            </View>
        )
    }
}

const mapStateToProps = (state) => ({
    testState: state.TestReducer.testState
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
    }, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(TestScreen);