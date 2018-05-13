export default function OrderReducer(
    state = {
        orders: [],
        table: null
    },
    action
) {
    switch (action.type) {

        case 'ADD_ORDER_FULFILLED':
            let newMeals = action.payload.meals.map( (meal) => {
                return meal;
            });

            let newOrder =  {
                orderId: action.payload.orderId,
                sum: action.payload.sum,
                estimatedTime: action.payload.estimatedTime,
                status: action.payload.status,
                meals: newMeals
            };

            state = {...state,
                orders: [...state.orders, newOrder]
            };

            break;

        case 'POST_ORDER_FULFILLED':
            break;

        case 'SET_TABLE_NUMBER':
            state = {...state,
                table: action.payload.tableNumber
            };

            break;

        default:
            return state;
    }

    return state;
}