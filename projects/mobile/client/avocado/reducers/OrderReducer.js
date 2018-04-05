export default function OrderReducer(
    state = {
        sum: 0,
        status: '',
        estimatedTime: 0,
        meals: [],
        orderId: ''
    },
    action
) {
    switch (action.type) {

        case 'ADD_ORDER_FULFILLED':
            let newMeals = action.payload.meals.map( (meal) => {
                return meal;
            });
            state = {...state,
                orderId: action.payload.orderId,
                sum: action.payload.sum,
                estimatedTime: action.payload.estimatedTime,
                status: action.payload.status,
                meals: newMeals
            };
            break;

        default:
            return state;
    }

    return state;
}