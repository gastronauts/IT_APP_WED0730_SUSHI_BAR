import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { View, Button, Text} from 'react-native';
import {testAction} from '../actions/index';
import translate from "translatr";

class TestComponent extends Component {

    render(){
        return (
            <View>
                <Text>
                    {translate(this.props.dictionary, "testText", this.props.language).testText}
                </Text>
                <Button
                    title={this.props.testState}
                    onPress={() => {
                        this.props.testAction('New text on button')
                    }}
                />
            </View>
        )
    }
}

const mapStateToProps = (state) => ({
    testState: state.TestReducer.testState,
    language: state.i18nReducer.currentLanguage,
    dictionary: state.i18nReducer.dictionaries
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        testAction: testAction
    }, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(TestComponent);