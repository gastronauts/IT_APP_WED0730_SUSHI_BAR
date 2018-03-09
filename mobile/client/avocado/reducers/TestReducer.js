export default function TestReducer(
    state = {
        testState: "click me to change text"
    },
    action
) {
    switch (action.type) {

        case "TEST_ACTION":
            state = Object.assign({}, state, {
                testState: action.payload.testState
            });
            break;

        default:
            return state;
    }

    return state;
}