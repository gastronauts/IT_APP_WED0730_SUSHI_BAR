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
                sum: parseFloat(action.payload.sum).toFixed(2),
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

        case 'GET_STATUSES_OF_ORDER_FULFILLED':
            let newOrders = state.orders.map( (item) => {
                let newStatus = action.payload.data.find((element) => {
                    return element.id === item.orderId;
                });
                return {...item, status: newStatus ? newStatus.status : null }
            });

            let finalNewOrders = [];

            newOrders.forEach((item) => {
                if(item.status !== null){
                    finalNewOrders.push(item);
                }
            });

            state = {...state,
                orders : finalNewOrders
            };

            break;

        default:
            return state;
    }

    return state;
}