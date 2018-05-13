import React, { Component } from 'react';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {View, StyleSheet, Image, ToastAndroid} from 'react-native';
import {Text,Button,FormLabel, FormInput, FormValidationMessage} from 'react-native-elements';
import PropTypes from 'prop-types';
import constants from '../constants/constants';
import translate from "translatr";
import dictionary from '../translations/translations';
import {setTableNumber} from "../actions";


class ChooseTableComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            errorMsg: null,
            tabelNumber: null
        }
    }

    checkInput = () => {
        console.log('test')
    };

    render(){

        return (
            <View style={style.mainViewStyle}>
                <FormInput
                    onChangeText={(tableNumber) => this.setState({tableNumber})}
                    value={this.state.tableNumber}
                    placeholder={translate(dictionary, 'tableNumber', this.props.lang || 'en').tableNumber}
                   inputStyle={{
                       width: 200,
                       textAlign: 'center',
                       fontSize: 20,
                       paddingTop: 10
                   }}
                />
                <FormValidationMessage>{this.state.errorMsg}</FormValidationMessage>
                <Button
                title="ACCEPT"
                buttonStyle={{
                    backgroundColor: constants.colors.green,
                    width: 200,
                }}
                onPress={ () => {
                    if(!(this.state.tableNumber && typeof(this.state.tableNumber))){
                        this.setState({
                            errorMsg: translate(dictionary, 'emptyField', this.props.lang || 'en').emptyField
                        })
                    }
                    else{
                        if(isNaN(this.state.tableNumber)){
                            this.setState({
                                errorMsg: translate(dictionary, 'stringValue', this.props.lang || 'en').stringValue
                            })
                        }
                        else {
                            this.props.setTableNumber(this.state.tableNumber);
                            this.props.closeModal();
                        }
                    }
                }}
                />
            </View>
        )
    }
}

const mapStateToProps = (state) => ({
    language: state.i18nReducer.currentLanguage
});

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        setTableNumber:setTableNumber
    }, dispatch)
};


const style = StyleSheet.create({
    mainViewStyle: {
        marginLeft: 15,
        marginTop: 15,
        flexDirection: 'column',
        alignItems: 'center'
    },


});


export default connect(mapStateToProps, mapDispatchToProps)(ChooseTableComponent);