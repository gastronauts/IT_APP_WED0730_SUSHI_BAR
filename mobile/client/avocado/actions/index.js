export const testAction = (testState) => {
    return {
        type: 'TEST_ACTION',
        payload: {
            testState: testState
        }
    }
};