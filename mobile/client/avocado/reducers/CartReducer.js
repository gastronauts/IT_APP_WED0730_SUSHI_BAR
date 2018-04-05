export default function CartReducer(
    state = {
        sum: 0,
        itemsInCart: [],
        estimatedTime: 0
    },
    action
) {
    switch (action.type) {

        case "ADD_ITEM_TO_CART":
            let newSum = 0;
            //let newEstimatedTime = 0;
            let updateItem = false;
            let newState = state.itemsInCart.map( (item) => {
                if(item.mealId === action.payload.mealId){
                    updateItem = true;
                    newSum += (item.amount + action.payload.amount) * item.price;
                    return { ...item, amount: item.amount + action.payload.amount }
                }
                else{
                    newSum += item.amount * item.price;
                    //newEstimatedTime = action.payload.estimatedTime > newEstimatedTime ? action.payload.estimatedTime : newEstimatedTime;
                    return item
                }
            });


            state = {...state,
                sum: updateItem ? newSum : newSum + action.payload.amount * action.payload.price,
                itemsInCart: updateItem ? newState : [...state.itemsInCart, action.payload],
                estimatedTime: action.payload.estimatedTime > state.estimatedTime ? action.payload.estimatedTime : state.estimatedTime
            };
            break;

        case "REMOVE_ITEM_FROM_CART":
            newSum = 0;
            newState = [];
            let newEstimatedTime = 0;

            state.itemsInCart.forEach( (item) => {
                if(item.mealId !== action.payload.mealId){
                    newState.push(item);
                    newSum += item.amount * item.price;
                    newEstimatedTime = item.estimatedTime > newEstimatedTime ? item.estimatedTime : newEstimatedTime;
                }
            });

            state = {...state,
                sum: newSum,
                itemsInCart: newState,
                estimatedTime: newEstimatedTime
            };
            break;

        case "UPDATE_AMOUNT_OF_ITEM_IN_CART":
            newSum = 0;
            newState = state.itemsInCart.map( (item) => {
                if(item.mealId === action.payload.mealId){
                    newSum += action.payload.amount * item.price;
                    return { ...item, amount: action.payload.amount }
                }
                else{
                    newSum += item.amount * item.price;
                    return item
                }
            });

            state = {...state,
                sum: newSum,
                itemsInCart: newState
            };
            break;

        case 'EMPTY_CART':
            state = {...state,
                sum: 0,
                itemsInCart: [],
                estimatedTime: 0
            };
            break;

        default:
            return state;
    }

    return state;
}